package com.walk.angel.angelwalk.Adapter;

import android.content.Context;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.walk.angel.angelwalk.Data.WheelchairChargingData;
import com.walk.angel.angelwalk.R;

import java.util.ArrayList;
import java.util.List;

public class WheelchairChargingAdapter extends ListAdapter<WheelchairChargingData, WheelchairChargingAdapter.ViewHolder> {
    private List<WheelchairChargingData> mWheelchairChargings = new ArrayList<>();

    public static final DiffUtil.ItemCallback<WheelchairChargingData> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<WheelchairChargingData>() {
                @Override
                public boolean areItemsTheSame(WheelchairChargingData oldItem, WheelchairChargingData newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(WheelchairChargingData oldItem, WheelchairChargingData newItem) {
                    return (oldItem.getAddress().equals(newItem.getAddress()) && oldItem.getAddress().equals(newItem.getAddress()));
                }
            };

    public WheelchairChargingAdapter() {
        super(DIFF_CALLBACK);
    }

    public void addMoreWheelchairCharging(List<WheelchairChargingData> newWheelchairCharging) {
        mWheelchairChargings.addAll(newWheelchairCharging);
        submitList(mWheelchairChargings); // DiffUtil takes care of the chekc
    }

    @Override
    public WheelchairChargingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View wheelchairChargingView = inflater.inflate(R.layout.data_wheelchair_charging, parent, false);

        WheelchairChargingAdapter.ViewHolder viewHolder = new WheelchairChargingAdapter.ViewHolder(wheelchairChargingView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WheelchairChargingAdapter.ViewHolder viewHolder, int position) {
        WheelchairChargingData sight = getItem(position);

        TextView textViewAddress = viewHolder.addressTextView;
        textViewAddress.setText(sight.getAddress());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView addressTextView;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            addressTextView = (TextView) itemView.findViewById(R.id.txtChargingAddress);
        }
    }

    public void updateList(List<WheelchairChargingData> list){
        mWheelchairChargings.clear();
        mWheelchairChargings.addAll(list);
        this.notifyDataSetChanged();
    }
}
