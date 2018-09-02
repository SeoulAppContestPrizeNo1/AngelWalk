package com.walk.angel.angelwalk.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.walk.angel.angelwalk.Activity.BoardActivity;
import com.walk.angel.angelwalk.Adapter.BoardListAdapter;
import com.walk.angel.angelwalk.Api.ServerAPI;
import com.walk.angel.angelwalk.Api.ServerURL;
import com.walk.angel.angelwalk.Api.interceptor.CookieInterceptor;
import com.walk.angel.angelwalk.Data.board.BoardData;
import com.walk.angel.angelwalk.Data.board.BoardList;
import com.walk.angel.angelwalk.R;
import com.walk.angel.angelwalk.scroll.EndlessRecyclerViewScrollListener;
import com.walk.angel.angelwalk.scroll.locker.UILocker;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BoardFragment extends Fragment implements ServerURL{
    @Bind(R.id.boardRecyclerView)
    RecyclerView boardRecyclerView;

    private ArrayList<BoardData> arrayListOfBoardData = new ArrayList<>();

    private LinearLayoutManager linearLayoutManager;

    private BoardListAdapter boardListAdapter;

    private UILocker uiLocker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        uiLocker = new UILocker(getView().getContext());
        uiLocker.lock();

        new ConnectServer().run(1);
    }

    class ConnectServer{
        CookieInterceptor cookieInterceptor = new CookieInterceptor(getActivity().getApplicationContext());
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(cookieInterceptor).build();

        public void run(int page) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
            ServerAPI serverAPI = retrofit.create(ServerAPI.class);
            Call<BoardList> call = serverAPI.getBoardList(page);

            call.enqueue(new Callback<BoardList>() {
                @Override
                public void onResponse(Call<BoardList> call, Response<BoardList> response) {
                    try {
                        if (response.isSuccessful() && "SUCCESS".equals(response.body().getResult())) {
                            arrayListOfBoardData = response.body().getArrayListOfBoardData();
                            linearLayoutManager = new LinearLayoutManager(getView().getContext());

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    boardListAdapter = new BoardListAdapter(arrayListOfBoardData);
                                    boardRecyclerView.setAdapter(boardListAdapter);
                                    boardRecyclerView.setLayoutManager(linearLayoutManager);
                                    boardRecyclerView.addOnItemTouchListener(onItemTouchListener);
                                    boardRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                                        @Override
                                        public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                                            uiLocker.lock();
                                            new ConnectServer().reload(page);
                                        }
                                    });
                                    uiLocker.unlock();
                                }
                            });
                        } else {
                            Toast.makeText(getView().getContext(), "게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("error", "Gson Converter Error is " + e.toString());
                    }
                }

                @Override
                public void onFailure(Call<BoardList> call, Throwable t) {

                }
            });
        }

        private void reload(int page){
                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
                ServerAPI serverAPI = retrofit.create(ServerAPI.class);
                Call<BoardList> call = serverAPI.getBoardList(page);
                call.enqueue(new Callback<BoardList>() {
                    @Override
                    public void onResponse(Call<BoardList> call, Response<BoardList> response) {
                        try {
                            if (response.isSuccessful() && "SUCCESS".equals(response.body().getResult())) {
                                final ArrayList<BoardData> arrayListOfReloadedBoardData = response.body().getArrayListOfBoardData();

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        int currentBoardSize = arrayListOfBoardData.size();
                                        arrayListOfBoardData.addAll(arrayListOfReloadedBoardData);
                                        boardListAdapter.notifyItemRangeInserted(currentBoardSize, arrayListOfReloadedBoardData.size());
                                        uiLocker.unlock();
                                    }
                                });
                            } else {
                                Toast.makeText(getView().getContext(), "게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.d("error", "Gson Converter Error is " + e.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<BoardList> call, Throwable t) {

                    }
                });

        }
    }

    RecyclerView.OnItemTouchListener onItemTouchListener = new RecyclerView.OnItemTouchListener() {

        final GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View chileView = rv.findChildViewUnder(e.getX(), e.getY());

            if (chileView != null && gestureDetector.onTouchEvent(e)) {
                int currentPosition = rv.getChildAdapterPosition(chileView);
                Intent intent = new Intent(getActivity().getApplicationContext(), BoardActivity.class);
                intent.putExtra("boardIndex", arrayListOfBoardData.get(currentPosition).getBoardIndex());
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
