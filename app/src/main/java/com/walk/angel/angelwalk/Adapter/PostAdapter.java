package com.walk.angel.angelwalk.Adapter;

import android.content.Context;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.walk.angel.angelwalk.Data.PostData;
import com.walk.angel.angelwalk.R;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends ListAdapter<PostData, PostAdapter.ViewHolder> {
    private List<PostData> mPost = new ArrayList<>();

    public static final DiffUtil.ItemCallback<PostData> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<PostData>() {
                @Override
                public boolean areItemsTheSame(PostData oldItem, PostData newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(PostData oldItem, PostData newItem) {
                    return (oldItem.getAuthor().equals(newItem.getAuthor()) && oldItem.getTitle().equals(newItem.getTitle()));
                }
            };

    public PostAdapter() {
        super(DIFF_CALLBACK);
    }

    public void addMorePost(List<PostData> newSights) {
        mPost.addAll(newSights);
        submitList(mPost); // DiffUtil takes care of the chekc
    }

    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.data_post, parent, false);

        PostAdapter.ViewHolder viewHolder = new PostAdapter.ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostAdapter.ViewHolder viewHolder, int position) {
        PostData post = getItem(position);

        TextView textViewAuthor = viewHolder.authorTextView;
        textViewAuthor.setText(post.getAuthor());

        TextView textViewTitle = viewHolder.titleTextView;
        textViewTitle.setText(post.getTitle());


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView authorTextView;
        public TextView titleTextView;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            authorTextView = (TextView) itemView.findViewById(R.id.txtPostAuthor);
            titleTextView = (TextView) itemView.findViewById(R.id.txtPostTitle);
        }
    }

    public void updateList(List<PostData> list){
        mPost.clear();
        mPost.addAll(list);
        this.notifyDataSetChanged();
    }
}
