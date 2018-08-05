package com.walk.angel.angelwalk.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.walk.angel.angelwalk.Data.SightsData;
import com.walk.angel.angelwalk.R;

public class SightInfoActivity extends AppCompatActivity {

    TextView txtSightInfoName;
    TextView txtSightInfoAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight_info);

        Intent intent = getIntent();
        SightsData data = (SightsData) intent.getSerializableExtra("sightsInfo");

        txtSightInfoName = (TextView) findViewById(R.id.txtSightInfoName);
        txtSightInfoAddress = (TextView) findViewById(R.id.txtSightInfoAddress);

        // Data TextView에 적용
        txtSightInfoName.setText(data.getName());
        txtSightInfoAddress.setText(data.getAddress());

    }
}
