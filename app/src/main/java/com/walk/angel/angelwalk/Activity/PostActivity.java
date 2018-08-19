package com.walk.angel.angelwalk.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.walk.angel.angelwalk.Adapter.PostAdapter;
import com.walk.angel.angelwalk.Data.PostData;
import com.walk.angel.angelwalk.ItemClickSupport;
import com.walk.angel.angelwalk.R;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    PostAdapter adapter;
    RecyclerView recyclerView;
    List<PostData> listOfPostData = new ArrayList<>();
    List<PostData> listOfCurrentPostData = new ArrayList<>();
    Button btnPosting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        btnPosting = (Button) findViewById(R.id.btnPosting);
        btnPosting.setOnClickListener(btnClickListener);

        listOfPostData = PostData.createPostList(20);
        listOfCurrentPostData = listOfPostData;
        setupViews();

    }

    private Button.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btnPosting:
                    Intent intentPosting = new Intent(PostActivity.this, PostingActivity.class);
                    startActivity(intentPosting);
                    break;
            }
        }
    };

    void setupViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_post);

        // Setup RecyclerView, associated adapter, and layout manager.
        adapter = new PostAdapter();
        //dd
        //RecyclerView에 Adapter세팅
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.addMorePost(PostData.createPostList(20));

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){

            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                //SightsData data = new SightsData(position, listOfSightData.get(position).getName(), listOfSightData.get(position).getAddress(), listOfSightData.get(position).getCategory());
                PostData data = listOfCurrentPostData.get(position);
                Toast.makeText(PostActivity.this, "클릭한 아이템의 이름은 " + listOfCurrentPostData.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PostActivity.this, PostDetailActivity.class);
                intent.putExtra("postDetail", data);
                startActivity(intent);
            }
        });

        ItemClickSupport.addTo(recyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(PostActivity.this, "길게 눌렀구나 " + listOfPostData.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });



    }

}
