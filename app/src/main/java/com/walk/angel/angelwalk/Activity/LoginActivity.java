package com.walk.angel.angelwalk.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.walk.angel.angelwalk.R;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(btnClickListener);
    }

    private Button.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btn_login:
                    Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intentMain);
                    break;

            }
        }
    };
}
