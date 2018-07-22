package com.walk.angel.angelwalk.retrofit.api;

import com.walk.angel.angelwalk.retrofit.data.MessageData;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AngelWalkServerAPI {

    @GET("url/{parameter}")
    Call<ResponseBody> getInformationFromServerByGetMethod(@Path("parameter") String parameter);

    @GET("url/wantToRequestUsingURLQuery")
    Call<ResponseBody> getInformationFromServerByGetMethodAndURLQuery(@Query("parameter1") String parameter1, @Query("parameter2") String parameter2, @Query("parameter3") String parameter3);

    @FormUrlEncoded
    @POST("url/wantPostURL")
    Call<MessageData> postParameterToWantPostURL(@Field("id") String id, @Field("password") String password, @Field("nickName") String nickName);

    @Multipart
    @POST("url/wantPostMultipartURL")
    Call<ResponseBody> postMultipartParameterToWantPostURL(@Part("parameter")RequestBody parameterBody);

}
