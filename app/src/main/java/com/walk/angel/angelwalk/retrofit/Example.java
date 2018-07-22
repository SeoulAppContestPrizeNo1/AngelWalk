package com.walk.angel.angelwalk.retrofit;

import com.walk.angel.angelwalk.retrofit.api.AngelWalkServerAPI;
import com.walk.angel.angelwalk.retrofit.data.MessageData;
import com.walk.angel.angelwalk.retrofit.url.AngelWalkServerURL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Example implements AngelWalkServerURL {

    //Activity 내에서 서버와 통신하기 위해 다음과 같이 Retrofit API를 Implements 해야한다
    private void connectServer(String userId, String userPassword, String userNickName) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        AngelWalkServerAPI angelWalkServerAPI = retrofit.create(AngelWalkServerAPI.class);
        Call<MessageData> call = angelWalkServerAPI.postParameterToWantPostURL(userId, userPassword, userNickName);

        call.enqueue(new Callback<MessageData>() {
            @Override
            public void onResponse(Call<MessageData> call, Response<MessageData> response) {
                if (response.isSuccessful()) {
                    MessageData messageData = response.body();

                /* Activity 내에서 통신을 할 때 UI와 별개로 통신을 위한 Thread 가 생성되어 돌아가므로
                * 통신이 끝나서 onResponse() 메서드로 진입했을 때 Activity 내부 메서드인 runOnUiThread()를
                * 사용하여 UI관련 코드를 작성해주어야 한다.
                * runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(PreviousActivity.this, NextActivity.class);
                                    intent.putExtra("parameter", messageData.getId());
                                    intent.putExtra("parameter", messageData.getPassword());
                                    intent.putExtra("parameter", messageData.getNickName());
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            });
                * */
                }
            }

            @Override
            public void onFailure(Call<MessageData> call, Throwable t) {

            }
        });
    }

}





