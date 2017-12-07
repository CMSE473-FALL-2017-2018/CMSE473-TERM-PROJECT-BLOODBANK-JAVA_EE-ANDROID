package cmse473.bloodbank.RetrofitAPI;

import java.util.List;
import java.util.Map;

import cmse473.bloodbank.Model.Seek;
import cmse473.bloodbank.Model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @POST("login")
    @FormUrlEncoded
    Call<User> login(@FieldMap Map<String,String> params);

    @POST("register")
    Call<Void> register(@Body User user);

    @POST("searchDonor")
    @FormUrlEncoded
    Call<List<User>> searchDonor(@FieldMap Map<String, String> params);

    @GET("allDonors")
    Call<List<User>> fetchAllDonors();

    @POST("searchSeeker")
    @FormUrlEncoded
    Call<List<Seek>> searchSeeker(@FieldMap Map<String, String> params);

    @GET("allSeeks")
    Call<List<Seek>> fetchAllSeeks();

    @POST("changeStatus")
    @FormUrlEncoded
    Call<Void> changeStatus(@FieldMap Map<String,String> params);

    @POST("insertSeeker")
    Call<Void> seek(@Body Seek seek);

    @GET("history/{email}/")
    Call<List<Seek>> history(@Path("email") String email);

    @DELETE("deleteHistory/{id}")
    Call<Void> deleteHistory(@Path("id") String id);

    @POST("update")
    Call<Void> update(@Body User user);

    @DELETE("deleteMe/{userEmail}/")
    Call<Void> deleteMe(@Path("userEmail") String mail);
}
