import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        if(args.length == 0){
            System.out.println(Lib.getGreeting());
        }else{

            if(args[0].equals("example")){
                HttpLib httpLib=new HttpLib();
                System.out.println(httpLib.getExamplDotCom());
            }

            if(args[0].equals("posts")) {

                JSONPlaceholderAPI api = JSONPlaceholderAPI.getInstance();

                List<Post> posts;
                try {
                    posts = api.getPosts().execute().body();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                for (Post p : posts) {
                    System.out.println(p.getTitle());
                }
            }

            if(args[0].equals("photos")) {

                JSONPlaceholderAPI api = JSONPlaceholderAPI.getInstance();


                List<Photo> photos;
                try {
                    photos = api.getPhoto().execute().body();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                for (Photo p : photos) {
                    System.out.println(p.getUrl());
                }
            }

        }
    }
}
