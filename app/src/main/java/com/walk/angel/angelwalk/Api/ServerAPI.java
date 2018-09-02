package com.walk.angel.angelwalk.Api;

import com.walk.angel.angelwalk.Data.LoginData;
import com.walk.angel.angelwalk.Data.board.BoardList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerAPI {
    @FormUrlEncoded
    @POST("user/signIn")
    Call<LoginData> sendLoginData(@Field("userId") String userId, @Field("userPassword") String userPassword);

    @GET("board/boardList")
    Call<BoardList> getBoardList(@Query("pageNumber") int pageNumber);

}
