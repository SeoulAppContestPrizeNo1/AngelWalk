package com.walk.angel.angelwalk.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.walk.angel.angelwalk.Adapter.WheelchairChargingAdapter;
import com.walk.angel.angelwalk.Api.ServerAPI;
import com.walk.angel.angelwalk.Data.LoginData;
import com.walk.angel.angelwalk.Data.WheelchairChargingData;
import com.walk.angel.angelwalk.DaumMapSchemeURL;
import com.walk.angel.angelwalk.ItemClickSupport;
import com.walk.angel.angelwalk.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.walk.angel.angelwalk.Api.ServerURL.BASE_URL;

public class WheelchairChargingActivity extends AppCompatActivity {

    WheelchairChargingAdapter adapter;
    RecyclerView recyclerView;
    List<WheelchairChargingData> listOfWheelchairChargingData = new ArrayList<>();
    List<WheelchairChargingData> listOfCurrentWheelchairChargingData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheelchair_charging);

        listOfWheelchairChargingData = WheelchairChargingData.createWheelchairChargingList(20);
        listOfCurrentWheelchairChargingData = listOfWheelchairChargingData;
        setupViews();
    }

    void setupViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_wheelchair);

        // Setup RecyclerView, associated adapter, and layout manager.
        adapter = new WheelchairChargingAdapter();

        //RecyclerView에 Adapter세팅
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.addMoreWheelchairCharging(WheelchairChargingData.createWheelchairChargingList(20));

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){

            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                //SightsData data = new SightsData(position, listOfSightData.get(position).getName(), listOfSightData.get(position).getAddress(), listOfSightData.get(position).getCategory());
                WheelchairChargingData data = listOfCurrentWheelchairChargingData.get(position);
                Toast.makeText(WheelchairChargingActivity.this, "클릭한 아이템의 이름은 " + listOfCurrentWheelchairChargingData.get(position).getAddress(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(WheelchairChargingActivity.this, WheelchairChargingInfoActivity.class);
//                intent.putExtra("WheelchairChargingAddress", data);
//                startActivity(intent);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(WheelchairChargingActivity.this);

                // 제목셋팅
                alertDialogBuilder.setTitle("길찾기 안내");

                // AlertDialog 셋팅
                alertDialogBuilder
                        .setMessage("충전소까지 길찾기 안내를 해드릴까요?")
                        .setCancelable(false)
                        .setPositiveButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setNegativeButton("안내",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        //dialog.cancel();
                                        Intent intent = getDaumIntent(37.54,127.07);
                                        if(intent != null){
                                            startActivity(intent);
                                        }
                                    }
                                });

                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();

                // 다이얼로그 보여주기
                alertDialog.show();
            }
        });

    }

    private Intent getDaumIntent(double latitude, double longitude){

        SharedPreferences pref = getSharedPreferences("currentPosition", MODE_PRIVATE);
        Long currentLatitude = pref.getLong("currentLatitude", 0);
        Long currentLongitude = pref.getLong("currentLongitude", 0);

        String url = "daummaps://route?sp="+currentLatitude.doubleValue()+","+currentLongitude.doubleValue()+"&ep="+latitude+","+longitude+"&by=PUBLICTRANSIT";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        DaumMapSchemeURL daumMapSchemeURL = new DaumMapSchemeURL(this, intent) {
            @Override
            public boolean canOpenDaummapURL() {
                return super.canOpenDaummapURL();
            }
        };

        if(daumMapSchemeURL.existDaummapApp()){
            return intent;
        } else {
            DaumMapSchemeURL.openDaummapDownloadPage(this);
        }
        return null;
    }


    public void connectServer(final String userId, final String userPassword){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ServerAPI serverAPI = retrofit.create(ServerAPI.class);
        Call<LoginData> call = serverAPI.sendLoginData(userId, userPassword);

        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                try{
                    if(response.isSuccessful()) { // 200번대
                        LoginData loginData = response.body();
                        String result = loginData.getResult();

                        Toast.makeText(WheelchairChargingActivity.this, result, Toast.LENGTH_SHORT).show();
                        if ("Success".equals(result)){
                            Intent intentMain = new Intent(WheelchairChargingActivity.this, MainActivity.class);
                            startActivity(intentMain);
                            finish();
                        }
                        else {
                            //to do nothing
                        }
                    }else{
                        WheelchairChargingActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(WheelchairChargingActivity.this, "아이디 혹은 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
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
