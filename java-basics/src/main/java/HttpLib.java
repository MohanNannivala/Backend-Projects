import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpLib {

    OkHttpClient client = new OkHttpClient();

    public String getExamplDotCom() {
        Request request = new Request.Builder()
                .url("http://example.com/")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }catch (Exception e){
            return e.getMessage();
        }
    }



}
