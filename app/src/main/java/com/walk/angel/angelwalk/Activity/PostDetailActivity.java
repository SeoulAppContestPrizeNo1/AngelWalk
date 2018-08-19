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
    TextView txtPostDetailContent;

    PostData data;
    String editPostingTitle;
    String editPostingContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        txtPostDetailAuthor = (TextView) findViewById(R.id.txtPostDetailAuthor);
        txtPostDetailTitle = (TextView) findViewById(R.id.txtPostDetailTitle);
        txtPostDetailContent = (TextView) findViewById(R.id.txtPostDetailContent);

        Intent intent = getIntent();
        if(intent.hasExtra("postDetail")){
            data = (PostData) intent.getSerializableExtra("postDetail");

            // Data TextView에 적용
            txtPostDetailAuthor.setText(data.getAuthor());
            txtPostDetailTitle.setText(data.getTitle());
            txtPostDetailContent.setText(data.getContent());
        }

        if(intent.hasExtra("title") && intent.hasExtra("content")){
            editPostingTitle = (String) intent.getSerializableExtra("title");
            editPostingContent = (String) intent.getSerializableExtra("content");

            // txtPostDetailAuthor.setText(data.getAuthor());
            txtPostDetailTitle.setText(editPostingTitle);
            txtPostDetailContent.setText(editPostingContent);
        }



    }
}
