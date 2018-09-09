package com.walk.angel.angelwalk.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.walk.angel.angelwalk.Activity.LoginActivity;
import com.walk.angel.angelwalk.Activity.RegisterActivity;
import com.walk.angel.angelwalk.Api.ServerAPI;
import com.walk.angel.angelwalk.Data.CommonData;
import com.walk.angel.angelwalk.Data.SignupData;
import com.walk.angel.angelwalk.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.walk.angel.angelwalk.Api.ServerURL.BASE_URL;

public class SettingFragment extends Fragment {

    private EditText editNickname;
    private EditText editPassword;
    private Button btnNicknameChange;
    private Button btnPasswordChange;
    private Button btnLogout;

    private String token;
    private String userId;
    private String userPassword;

    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup SettingFragment = (ViewGroup) inflater.inflate(R.layout.fragment_setting, container, false);

        pref = getActivity().getSharedPreferences("login", MODE_PRIVATE);
        token = pref.getString("token", "");
        userId = pref.getString("id", "");
        userPassword = pref.getString("id", "");

        editNickname = (EditText) SettingFragment.findViewById(R.id.editNickName);
        editPassword = (EditText) SettingFragment.findViewById(R.id.editPassword);

        btnNicknameChange = (Button) SettingFragment.findViewById(R.id.btnChangeNickname);
        btnPasswordChange = (Button) SettingFragment.findViewById(R.id.btnChangePassword);
        btnLogout = (Button) SettingFragment.findViewById(R.id.btnLogout);

        btnNicknameChange.setOnClickListener(btnClickListener);
        btnPasswordChange.setOnClickListener(btnClickListener);
        btnLogout.setOnClickListener(btnClickListener);

        return SettingFragment;
    }

    private Button.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btnChangeNickname:
                    if (editNickname.getText().toString() != null || !"".equals(editNickname.getText().toString())) {
                        connectServerForNickname(editNickname.getText().toString());
                    }else{
                        Toast.makeText(getView().getContext(), "닉네임을 입력하세요.", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.btnChangePassword:
                    if (editNickname.getText().toString() == null || "".equals(editNickname.getText().toString())) {
                        Toast.makeText(getView().getContext(), "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    }else if(editNickname.getText().toString() == userPassword || editNickname.getText().toString().equals(userPassword)){
                        Toast.makeText(getView().getContext(), "이전 비밀번호와 똑같습니다.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        connectServerForPassword(editPassword.getText().toString());
                    }
                    break;

                case R.id.btnLogout:
                    removePreferences();
                    Intent intentLogout = new Intent(getView().getContext(), LoginActivity.class);
                    startActivity(intentLogout);
                    getActivity().finish();
            }
        }
    };

    public void connectServerForNickname(String userNickname){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ServerAPI serverAPI = retrofit.create(ServerAPI.class);
        Call<CommonData> call = serverAPI.sendNicknameUpdate(token, userNickname);

        call.enqueue(new Callback<CommonData>() {
            @Override
            public void onResponse(Call<CommonData> call, Response<CommonData> response) {
                try{

                    if(response.isSuccessful()) { // 200번대

                        CommonData commonData = response.body();
                        String result = commonData.getResult();

                        if("Success".equals(result)){

                        }else{
                            Toast.makeText(getView().getContext(), commonData.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getView().getContext(), response.code(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("error", "Login Error is " + e.toString());
                }
            }

            @Override
            public void onFailure(Call<CommonData> call, Throwable t) {

            }
        });
    }

    public void connectServerForPassword(String userPassword){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ServerAPI serverAPI = retrofit.create(ServerAPI.class);
        Call<CommonData> call = serverAPI.sendPasswordUpdate(token, userPassword);

        call.enqueue(new Callback<CommonData>() {
            @Override
            public void onResponse(Call<CommonData> call, Response<CommonData> response) {
                try{

                    if(response.isSuccessful()) { // 200번대

                        CommonData commonData = response.body();
                        String result = commonData.getResult();

                        if("Success".equals(result)){

                        }else{
                            Toast.makeText(getView().getContext(), commonData.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getView().getContext(), response.code(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("error", "Login Error is " + e.toString());
                }
            }

            @Override
            public void onFailure(Call<CommonData> call, Throwable t) {

            }
        });
    }
    //값 삭제하기
    private  void  removePreferences(){
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

}
