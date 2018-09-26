package com.walk.angel.angelwalk.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.walk.angel.angelwalk.Adapter.SightsAdapter;
import com.walk.angel.angelwalk.Api.ServerAPI;
import com.walk.angel.angelwalk.Api.interceptor.CookieInterceptor;
import com.walk.angel.angelwalk.Data.SightList;
import com.walk.angel.angelwalk.Data.SightsData;
import com.walk.angel.angelwalk.R;
import com.walk.angel.angelwalk.scroll.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.walk.angel.angelwalk.Api.ServerURL.BASE_URL;

public class SightsActivity extends AppCompatActivity {

    SightsAdapter sightListAdapter;
    RecyclerView recyclerView;
    List<SightsData> listOfSightData = new ArrayList<>();
    List<SightsData> listOfCurrentSightData = new ArrayList<>();
    EditText editTxtSearch;
    Button btnAll, btnCattle, btnPark;

    private ArrayList<SightsData> arrayListOfSightData = new ArrayList<>();
    private GestureDetector gestureDetector;
    private LinearLayoutManager linearLayoutManager;
    private SightsAdapter sightsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights);

        editTxtSearch = (EditText) findViewById(R.id.editTxtSearch);
        btnAll = (Button) findViewById(R.id.btnAll);
        btnCattle = (Button) findViewById(R.id.btnCattle);
        btnPark = (Button) findViewById(R.id.btnPark);


        btnAll.setOnClickListener(btnClickListener);
        btnCattle.setOnClickListener(btnClickListener);
        btnPark.setOnClickListener(btnClickListener);

        listOfSightData = SightsData.createSightsList(20);
        listOfCurrentSightData = listOfSightData;
        setupViews();

        gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

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

        new ConnectServer().run();
    }

    void setupViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_sight);

        // Setup RecyclerView, associated adapter, and layout manager.

        //RecyclerView에 Adapter세팅
        recyclerView.setAdapter(sightListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        sightListAdapter.addMoreSights(SightsData.createSightsList(20));

//        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
//
//            @Override
//            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//
//                //SightsData data = new SightsData(position, listOfSightData.get(position).getName(), listOfSightData.get(position).getAddress(), listOfSightData.get(position).getCategory());
//                SightsData data = listOfCurrentSightData.get(position);
//                Toast.makeText(SightsActivity.this, "클릭한 아이템의 이름은 " + listOfCurrentSightData.get(position).getName(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(SightsActivity.this, SightInfoActivity.class);
//                intent.putExtra("sightsInfo", data);
//                startActivity(intent);
//            }
//        });
//
//        ItemClickSupport.addTo(recyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
//                Toast.makeText(SightsActivity.this, "길게 눌렀구나 " + listOfSightData.get(position).getName(), Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });

    }

    private Button.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btnAll:
                    sightListAdapter.updateList(listOfSightData);
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
        sightListAdapter.updateList(sightlist);
    }
    class ConnectServer {
        CookieInterceptor cookieInterceptor = new CookieInterceptor(getApplicationContext());
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(cookieInterceptor).build();

        public void run() {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
            ServerAPI serverAPI = retrofit.create(ServerAPI.class);
            Call<SightList> call = serverAPI.getSightList();

            call.enqueue(new Callback<SightList>() {
                @Override
                public void onResponse(Call<SightList> call, Response<SightList> response) {
                    try {
                        if (response.isSuccessful() && "Success".equals(response.body().getResult())) {
                            arrayListOfSightData = response.body().getArrayListOfSightData();
                            linearLayoutManager = new LinearLayoutManager(getApplicationContext());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    sightListAdapter = new SightsAdapter(arrayListOfSightData);
                                    recyclerView.setAdapter(sightListAdapter);
                                    recyclerView.setLayoutManager(linearLayoutManager);
                                    recyclerView.addOnItemTouchListener(onItemTouchListener);
                                    recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                                        @Override
                                        public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                                            //uiLocker.lock();
                                            //new SightsActivity.ConnectServer().reload(page);
                                        }
                                    });
                                }
                            });
                        } else {
                            Toast.makeText(SightsActivity.this, "관광지를 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            //uiLocker.unlock();
                        }
                    } catch (Exception e) {
                        Log.d("error", "Gson Converter Error is " + e.toString());
                    }
                }

                @Override
                public void onFailure(Call<SightList> call, Throwable t) {

                }
            });
        }
    }

    RecyclerView.OnItemTouchListener onItemTouchListener = new RecyclerView.OnItemTouchListener() {
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View chileView = rv.findChildViewUnder(e.getX(), e.getY());

            if (chileView != null && gestureDetector.onTouchEvent(e)) {
                int currentPosition = rv.getChildAdapterPosition(chileView);

                SightsData data = listOfCurrentSightData.get(currentPosition);
                Toast.makeText(SightsActivity.this, "클릭한 아이템의 이름은 " + listOfCurrentSightData.get(currentPosition).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SightsActivity.this, SightInfoActivity.class);
                intent.putExtra("sightsInfo", data);
                startActivity(intent);

                return true;
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    };

}
