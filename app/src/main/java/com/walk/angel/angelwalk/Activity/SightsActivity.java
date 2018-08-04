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
import android.widget.Toast;

import com.walk.angel.angelwalk.Adapter.SightsAdapter;
import com.walk.angel.angelwalk.Data.SightsData;
import com.walk.angel.angelwalk.ItemClickSupport;
import com.walk.angel.angelwalk.R;

import java.util.ArrayList;
import java.util.List;

public class SightsActivity extends AppCompatActivity {

    SightsAdapter adapter;
    RecyclerView recyclerView;
    List<SightsData> listOfSightData = new ArrayList<>();
    EditText editTxtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights);

        editTxtSearch = (EditText) findViewById(R.id.editTxtSearch);
        setupViews();

        editTxtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
                filter(s.toString());

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // filter your list from your input
                filter(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });
    }

    void setupViews() {
<<<<<<< Updated upstream
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

=======
        //layout xml에 작성해둔 RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
>>>>>>> Stashed changes
        // Setup RecyclerView, associated adapter, and layout manager.
        adapter = new SightsAdapter();

        //RecyclerView에 Adapter세팅
        recyclerView.setAdapter(adapter);
        //
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listOfSightData = SightsData.createSightsList(20);

        adapter.addMoreSights(SightsData.createSightsList(20));

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){

            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                SightsData data = new SightsData(position, listOfSightData.get(position).getName(), listOfSightData.get(position).getAddress());
                Toast.makeText(SightsActivity.this, "클릭한 아이템의 이름은 " + listOfSightData.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SightsActivity.this, SightInfoActivity.class);
                intent.putExtra("sightsInfo", data);
                startActivity(intent);
            }
        });

        ItemClickSupport.addTo(recyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(SightsActivity.this, "길게 눌렀구나 " + listOfSightData.get(position).getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    private Button.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {

            }
        }
    };


    void filter(String text){
        List<SightsData> sightlist = new ArrayList();
        for(SightsData data: listOfSightData){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(data.getName().equals(text)){
                sightlist.add(data);
                Toast.makeText(SightsActivity.this, data.getName(), Toast.LENGTH_SHORT).show();

            }
        }
        adapter.updateList(sightlist);
    }
}
