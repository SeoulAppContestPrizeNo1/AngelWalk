package com.walk.angel.angelwalk.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.walk.angel.angelwalk.Adapter.SightsAdapter;
import com.walk.angel.angelwalk.Adapter.WheelchairChargingAdapter;
import com.walk.angel.angelwalk.Data.SightsData;
import com.walk.angel.angelwalk.Data.WheelchairChargingData;
import com.walk.angel.angelwalk.Fragment.MapFragment;
import com.walk.angel.angelwalk.ItemClickSupport;
import com.walk.angel.angelwalk.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;

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
                Intent intent = new Intent(WheelchairChargingActivity.this, SightInfoActivity.class);
                intent.putExtra("WheelchairChargingAddress", data);
                startActivity(intent);
            }
        });

    }
}
