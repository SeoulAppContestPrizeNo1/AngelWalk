package com.walk.angel.angelwalk.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.walk.angel.angelwalk.Data.PostData;
import com.walk.angel.angelwalk.R;

public class PostDetailActivity extends AppCompatActivity {

    TextView txtPostDetailAuthor;
    TextView txtPostDetailTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        Intent intent = getIntent();
        PostData data = (PostData) intent.getSerializableExtra("postDetail");

        txtPostDetailAuthor = (TextView) findViewById(R.id.txtPostDetailAuthor);
        txtPostDetailTitle = (TextView) findViewById(R.id.txtPostDetailTitle);

        // Data TextView에 적용
        txtPostDetailAuthor.setText(data.getAuthor());
        txtPostDetailTitle.setText(data.getTitle());
    }
}
