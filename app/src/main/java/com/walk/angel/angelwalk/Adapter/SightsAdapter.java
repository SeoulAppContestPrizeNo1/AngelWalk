package com.walk.angel.angelwalk.Adapter;

import android.content.Context;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.walk.angel.angelwalk.Data.SightsData;
import com.walk.angel.angelwalk.R;

import java.util.ArrayList;
import java.util.List;

public class SightsAdapter extends ListAdapter<SightsData, SightsAdapter.ViewHolder> {
    private List<SightsData> mSights = new ArrayList<>();

    public static final DiffUtil.ItemCallback<SightsData> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<SightsData>() {
                @Override
                public boolean areItemsTheSame(SightsData oldItem, SightsData newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(SightsData oldItem, SightsData newItem) {
                    return (oldItem.getName().equals(newItem.getName()) && oldItem.getAddress().equals(newItem.getAddress()));
                }
            };

    public SightsAdapter() {
        super(DIFF_CALLBACK);
    }

    public void addMoreSights(List<SightsData> newSights) {
        mSights.addAll(newSights);
        submitList(mSights); // DiffUtil takes care of the chekc
    }

    @Override
    public SightsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View sightsView = inflater.inflate(R.layout.data_sights, parent, false);

        SightsAdapter.ViewHolder viewHolder = new SightsAdapter.ViewHolder(sightsView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SightsAdapter.ViewHolder viewHolder, int position) {
        SightsData sight = getItem(position);

        TextView textViewName = viewHolder.nameTextView;
        textViewName.setText(sight.getName());

        TextView textViewAddress = viewHolder.addressTextView;
        textViewAddress.setText(sight.getAddress());


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView addressTextView;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.txtSightName);
            addressTextView = (TextView) itemView.findViewById(R.id.txtSightAddress);
        }
    }

    public void updateList(List<SightsData> list){
        mSights.clear();
        mSights.addAll(list);
        this.notifyDataSetChanged();
    }
}
