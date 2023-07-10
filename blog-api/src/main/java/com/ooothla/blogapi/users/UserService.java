package com.ooothla.blogapi.users;


import com.ooothla.blogapi.security.jwt.JWTService;
import com.ooothla.blogapi.users.exceptions.IncorrectPasswordException;
import com.ooothla.blogapi.users.exceptions.UserNotFoundException;
import com.ooothla.blogapi.users.dto.CreateUserDTO;
import com.ooothla.blogapi.users.dto.LoginUserDTO;
import com.ooothla.blogapi.users.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;


    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JWTService jwtService){
        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
        this.passwordEncoder=passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponseDTO createUser(CreateUserDTO createUserDTO) {

        //TODO: Encrypt password
        //TODO: validate email
        //TODO: Check if username already exists

        UserEntity newUserEntity = modelMapper.map(createUserDTO, UserEntity.class);
        newUserEntity.setPassword(passwordEncoder.encode(newUserEntity.getPassword()));
        UserEntity savedUser = userRepository.save(newUserEntity);
        UserResponseDTO userResponseDTO = modelMapper.map(savedUser, UserResponseDTO.class);
        userResponseDTO.setToken(jwtService.createJWT(savedUser.getId()));
        return userResponseDTO;
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO){

        UserEntity userEntity = userRepository.findByUserName(loginUserDTO.getUserName());

        if(userEntity == null){
            throw new UserNotFoundException(loginUserDTO.getUserName());
        }

        Boolean isPasswordCorrect = passwordEncoder.matches(loginUserDTO.getPassword(), userEntity.getPassword());

        if(!isPasswordCorrect){
            throw new IncorrectPasswordException("Incorrect Password");
        }

        UserResponseDTO userResponseDTO = modelMapper.map(userEntity, UserResponseDTO.class);
        userResponseDTO.setToken(jwtService.createJWT(userResponseDTO.getId()));

        return userResponseDTO;
    }
}
