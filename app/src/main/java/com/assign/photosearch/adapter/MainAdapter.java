package com.assign.photosearch.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assign.photosearch.R;
import com.assign.photosearch.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Result> mData = new ArrayList<>();
    private SubItemClickListner mListner;

    public MainAdapter(SubItemClickListner listner) {
        mListner = listner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);

        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder mViewHolder, int position) {
        final Result data = mData.get(position);

        if (data.getUrls().getRegular() != null) mViewHolder.setAvatar(data.getUrls().getRegular());
        mViewHolder.itemView.setOnClickListener(v -> {
            if (mListner != null) {
                mListner.onClickItemListener(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mData == null ? 0 : mData.size());
    }

    public List<Result> getData() {
        return mData;
    }

    public void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }

    public boolean addData(List<Result> recordList) {
        mData.addAll(recordList);
        List<Result> newList = new ArrayList<>();
        newList.addAll(mData);
        mData = newList;
        notifyDataSetChanged();
        return true;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mAvatatImg;

        ViewHolder(@NonNull final View mView) {
            super(mView);

            mAvatatImg = mView.findViewById(R.id.avatarImg);
        }

        public void setAvatar(String avatar) {
            Picasso.get().load(avatar).into(mAvatatImg);
        }

    }

    public interface SubItemClickListner {

        void onClickItemListener(Result data);
    }
}
