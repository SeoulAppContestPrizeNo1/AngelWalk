package com.walk.angel.angelwalk.Api;

import com.walk.angel.angelwalk.Data.CommonData;
import com.walk.angel.angelwalk.Data.LoginData;
import com.walk.angel.angelwalk.Data.board.BoardList;
import com.walk.angel.angelwalk.Data.SignupData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerAPI {
    @FormUrlEncoded
    @POST("user/sign-in")
    Call<LoginData> sendLoginData(@Field("id") String userId, @Field("password") String userPassword);

    @FormUrlEncoded
    @POST("user/sign-up")
    Call<SignupData> sendSignupData(@Field("id") String userId,
                                    @Field("nickName") String userNickname,
                                    @Field("password") String userPassword,
                                    @Field("passwordCheck") String userPasswordCheck);

    @GET("board/boardList")
    Call<BoardList> getBoardList(@Query("pageNumber") int pageNumber);

    @FormUrlEncoded
    @POST("user/update/nick-name")
    Call<CommonData> sendNicknameUpdate(@Field("token") String userToken,
                                    @Field("nickName") String userNickname);

    @FormUrlEncoded
    @POST("user/update/password")
    Call<CommonData> sendPasswordUpdate(@Field("token") String userToken,
                                        @Field("password") String userPassword);
}
