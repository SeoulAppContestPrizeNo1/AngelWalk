package com.walk.angel.angelwalk.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.walk.angel.angelwalk.Fragment.MapFragment;
import com.walk.angel.angelwalk.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class WheelchairChargingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheelchair_charging);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
