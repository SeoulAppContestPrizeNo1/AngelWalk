package com.walk.angel.angelwalk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.walk.angel.angelwalk.Api.ServerAPI;
import com.walk.angel.angelwalk.Api.ServerURL;
import com.walk.angel.angelwalk.Api.interceptor.CookieInterceptor;
import com.walk.angel.angelwalk.Data.board.BoardData;
import com.walk.angel.angelwalk.Data.board.CommentData;
import com.walk.angel.angelwalk.Data.board.CommentList;
import com.walk.angel.angelwalk.R;
import com.walk.angel.angelwalk.scroll.locker.UILocker;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BoardDetailActivity extends AppCompatActivity implements ServerURL {

    private UILocker uiLocker;
    private BoardData boardData;
    private ArrayList<CommentData> arrayListOfCommentData = new ArrayList<>();

    @BindView(R.id.txtTitle)
    TextView txtTitle;

    @BindView(R.id.txtNickName)
    TextView txtNickName;

    @BindView(R.id.txtCreateDate)
    TextView txtCreateDate;

    @BindView(R.id.txtContent)
    TextView txtContent;

    @BindView(R.id.txtCommentCount)
    TextView txtCommentCount;

    @BindView(R.id.txtLikeCount)
    TextView txtLikeCount;

    @BindView(R.id.imgComment)
    ImageView imgComment;

    @BindView(R.id.imgLike)
    ImageView imgLike;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);
        ButterKnife.bind(this);
        uiLocker = new UILocker(getApplicationContext());
        Intent intent = getIntent();
        int boardIndex = intent.getIntExtra("boardIndex", -1);
        new ConnectServer().getBoard(boardIndex);

    }

    class ConnectServer {
        CookieInterceptor cookieInterceptor = new CookieInterceptor(getApplicationContext());
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(cookieInterceptor).build();

        public void getBoard(final int boardIndex) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
            ServerAPI serverAPI = retrofit.create(ServerAPI.class);
            Call<BoardData> call = serverAPI.getBoard(boardIndex);

            call.enqueue(new Callback<BoardData>() {
                @Override
                public void onResponse(Call<BoardData> call, Response<BoardData> response) {
                    try {
                        if (response.isSuccessful()) {
                            boardData = response.body();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    txtTitle.setText(boardData.getTitle());
                                    txtNickName.setText(boardData.getNickName());
                                    txtCreateDate.setText(boardData.getCreateDate());
                                    txtContent.setText(boardData.getContent());
                                    txtLikeCount.setText(boardData.getLikeCount());
                                    new ConnectServer().getComment(boardIndex);
                                }
                            });
                        } else {
                            Toast.makeText(BoardDetailActivity.this, "게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("error", "Gson Converter Error is " + e.toString());
                    }
                }

                @Override
                public void onFailure(Call<BoardData> call, Throwable t) {

                }
            });
        }

        public void getComment(int boardIndex){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
            ServerAPI serverAPI = retrofit.create(ServerAPI.class);
            Call<CommentList> call = serverAPI.getComment(boardIndex);

            call.enqueue(new Callback<CommentList>() {
                @Override
                public void onResponse(Call<CommentList> call, Response<CommentList> response) {
                    try {
                        if (response.isSuccessful()) {
                            arrayListOfCommentData = response.body().getArrayListOfCommentData();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    txtCommentCount.setText(arrayListOfCommentData.size());
                                    imgComment.setOnTouchListener(onTouchListener);
                                    imgLike.setOnTouchListener(onTouchListener);
                                    uiLocker.unlock();
                                }
                            });
                        } else {
                            Toast.makeText(BoardDetailActivity.this, "게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("error", "Gson Converter Error is " + e.toString());
                    }
                }

                @Override
                public void onFailure(Call<CommentList> call, Throwable t) {

                }
            });
        }
    }

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (view.getId()){
                case R.id.imgComment:
                    Intent intent = new Intent(BoardDetailActivity.this, CommentActivity.class);
                    intent.putExtra("arrayListOfCommentDate", arrayListOfCommentData);
                    startActivity(intent);
                    break;

                case R.id.imgLike:
                    break;
            }
            return false;
        }
    };
}