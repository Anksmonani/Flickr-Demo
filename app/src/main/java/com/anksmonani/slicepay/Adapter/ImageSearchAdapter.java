package com.anksmonani.slicepay.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anksmonani.slicepay.Bean.Photo;
import com.anksmonani.slicepay.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ImageSearchAdapter extends RecyclerView.Adapter<ImageSearchAdapter.ViewHolder> {

    private Context mContext;
    private List<Photo> mListPhotos;

    public ImageSearchAdapter(Context context, List<Photo> mPhotos) {
        mListPhotos = mPhotos;
        mContext = context;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivImage)
        AppCompatImageView mIvImage;

        @BindView(R.id.tvImageTitle)
        AppCompatTextView mTvImageTitle;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    @Override
    public ImageSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(mContext).inflate(R.layout.row_grid_image, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {



        if(mListPhotos.get(position).getTitle() == null)
        {
                        //Do nothing
        }
        else
        {
            holder.mTvImageTitle.setText(mListPhotos.get(position).getTitle());

            String url = "http://farm" + mListPhotos.get(position).getFarm() + ".staticflickr.com/" + mListPhotos.get(position).getServer() +
                    "/" + mListPhotos.get(position).getId() + "_" + mListPhotos.get(position).getSecret() + ".jpg";
            Glide.with(holder.itemView.getContext())
                    .load(url)
                    .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background).centerCrop())
                    .thumbnail(0.1f)
                    .into(holder.mIvImage);

        }

    }


    @Override
    public int getItemCount() {
        return mListPhotos.size();
    }


    public void setFilter(List<Photo> countryModels) {
        mListPhotos = new ArrayList<>();
        mListPhotos.addAll(countryModels);
        notifyDataSetChanged();
    }


}