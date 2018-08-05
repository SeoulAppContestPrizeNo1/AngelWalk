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
    List<SightsData> listOfCurrentSightData = new ArrayList<>();
    EditText editTxtSearch;
    Button btnCattle, btnPark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights);

        editTxtSearch = (EditText) findViewById(R.id.editTxtSearch);
        btnCattle = (Button) findViewById(R.id.btnCattle);
        btnPark = (Button) findViewById(R.id.btnPark);

        btnCattle.setOnClickListener(btnClickListener);
        btnPark.setOnClickListener(btnClickListener);

        listOfSightData = SightsData.createSightsList(20);
        listOfCurrentSightData = listOfSightData;
        setupViews();

        editTxtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
                filter(s.toString(),"search");

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // filter your list from your input
                filter(s.toString(), "search");
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });
    }

    void setupViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Setup RecyclerView, associated adapter, and layout manager.
        adapter = new SightsAdapter();

        //RecyclerView에 Adapter세팅
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.addMoreSights(SightsData.createSightsList(20));

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){

            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                //SightsData data = new SightsData(position, listOfSightData.get(position).getName(), listOfSightData.get(position).getAddress(), listOfSightData.get(position).getCategory());
                SightsData data = listOfCurrentSightData.get(position);
                Toast.makeText(SightsActivity.this, "클릭한 아이템의 이름은 " + listOfCurrentSightData.get(position).getName(), Toast.LENGTH_SHORT).show();
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
                case R.id.btnAll:
                    adapter.updateList(listOfSightData);
                    break;
                case R.id.btnCattle:
                    filter("고궁","category");
                    break;
                case R.id.btnPark:
                    filter("공원","category");
                    break;
                case R.id.btnSite:
                    filter("유적지","category");
                    break;
                case R.id.btnMuseum:
                    filter("박물관","category");
                    break;
                default:


            }
        }
    };


    void filter(String text, String type){
        List<SightsData> sightlist = new ArrayList();
        for(SightsData data: listOfSightData){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(type.equals("search") && data.getName().equals(text)){
                sightlist.add(data);
            }

            if(type.equals("category") && data.getCategory().equals(text)){
                sightlist.add(data);
            }
        }
        listOfCurrentSightData = sightlist;
        adapter.updateList(sightlist);
    }
}
