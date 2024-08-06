package fpoly.ph45160.thithu_and103_1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    //10.24.13.234
    //192.168.1.2
    //192.168.1.3
    private static final String BASE_URL = "http://10.24.58.195:3000/api/"; // Thay đổi URL phù hợp với API của bạn
    private static Retrofit retrofit;


    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
