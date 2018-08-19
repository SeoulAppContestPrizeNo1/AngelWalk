package com.walk.angel.angelwalk.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.walk.angel.angelwalk.R;

public class PostingActivity extends AppCompatActivity {

    Button btnPosting2;
    EditText editPostingTitle, editPostingContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        editPostingTitle = (EditText) findViewById(R.id.editPostingTitle);
        editPostingContent = (EditText) findViewById(R.id.editPostingContent);

        btnPosting2 = (Button) findViewById(R.id.btnPosting2);
        btnPosting2.setOnClickListener(btnClickListener);

    }

    private Button.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btnPosting2:
                    Intent intentPosting = new Intent(PostingActivity.this, PostDetailActivity.class);
                    intentPosting.putExtra("title", String.valueOf(editPostingTitle.getText()));
                    intentPosting.putExtra("content", String.valueOf(editPostingContent.getText()));
                    startActivity(intentPosting);
                    break;
            }
        }
    };


}
