package com.ooothla.blogapi.users;


import com.ooothla.blogapi.users.exceptions.IncorrectPasswordException;
import com.ooothla.blogapi.users.exceptions.UserNotFoundException;
import com.ooothla.blogapi.users.dto.CreateUserDTO;
import com.ooothla.blogapi.users.dto.LoginUserDTO;
import com.ooothla.blogapi.users.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
    }

    public UserResponseDTO createUser(CreateUserDTO createUserDTO) {

        //TODO: Encrypt password
        //TODO: validate email
        //TODO: Check if username already exists

        UserEntity newUserEntity = modelMapper.map(createUserDTO, UserEntity.class);
        UserEntity savedUser = userRepository.save(newUserEntity);
        UserResponseDTO userResponseDTO = modelMapper.map(savedUser, UserResponseDTO.class);
        return userResponseDTO;
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO){

        UserEntity userEntity = userRepository.findByUserName(loginUserDTO.getUserName());

        if(userEntity == null){
            throw new UserNotFoundException(loginUserDTO.getUserName());
        }

        //TODO: Encrypt password

        if(!loginUserDTO.getPassword().equals(userEntity.getPassword())){
            throw new IncorrectPasswordException();
        }

        UserResponseDTO userResponseDTO = modelMapper.map(userEntity, UserResponseDTO.class);

        return userResponseDTO;
    }
}
