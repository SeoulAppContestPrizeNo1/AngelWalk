package com.walk.angel.angelwalk.Api;

import com.walk.angel.angelwalk.Data.LoginData;
import com.walk.angel.angelwalk.Data.SignupData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServerAPI {
    @FormUrlEncoded
    @POST("user/sign-in")
    Call<LoginData> sendLoginData(@Field("id") String userId, @Field("password") String userPassword);

    @FormUrlEncoded
    @POST("user/sign-up")
    Call<SignupData> sendSignupData(@Field("id") String userId,
                                    @Field("nickName") String userNickname,
                                    @Field("password") String userPassword);

}
