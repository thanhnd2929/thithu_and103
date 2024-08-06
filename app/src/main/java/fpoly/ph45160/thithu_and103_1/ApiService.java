package fpoly.ph45160.thithu_and103_1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("xemays")
    Call<List<XeMayModel>> getXeMay();

    @POST("xemays")
    Call<XeMayModel> createXeMay(@Body XeMayModel xeMayModel);

    @PUT("xemays/{id}")
    Call<XeMayModel> updateXeMay(@Path("id") String id, @Body XeMayModel xeMayModel);

    @DELETE("xemays/{id}")
    Call<Void> deleteXeMay(@Path("id") String id);

    @GET("xemays/search")
    Call<List<XeMayModel>> searchXeMay(@Query("ten_xe_ph45160") String tenXe);
}
