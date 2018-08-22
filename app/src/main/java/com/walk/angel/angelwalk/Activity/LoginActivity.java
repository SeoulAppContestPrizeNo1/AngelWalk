package com.walk.angel.angelwalk.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.walk.angel.angelwalk.Api.ServerAPI;
import com.walk.angel.angelwalk.Api.ServerURL;
import com.walk.angel.angelwalk.Data.LoginData;
import com.walk.angel.angelwalk.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements ServerURL {

    private Button btnLogin;
    private Button btnRegister;
    private EditText editId;
    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editId = (EditText) findViewById(R.id.editId);
        editPassword = (EditText) findViewById(R.id.editPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(btnClickListener);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(btnClickListener);
    }

    private Button.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btnLogin:
                    Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intentMain);

                    connectServer(editId.getText().toString(), editPassword.getText().toString());
                    break;

                case R.id.btnRegister:
                    Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intentRegister);
                    break;

            }
        }
    };

    public void connectServer(String userId, String userPassword){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ServerAPI serverAPI = retrofit.create(ServerAPI.class);
        Call<LoginData> call = serverAPI.sendLoginData(userId, userPassword);

        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                try{
                    if(response.isSuccessful()) { // 200번대


                    }else{
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }catch (Exception e){
                    Log.d("error", "Login Error is " + e.toString());
                }
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {

            }
        });
    }
}
