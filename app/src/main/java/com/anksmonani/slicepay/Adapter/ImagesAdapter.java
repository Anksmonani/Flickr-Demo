package com.anksmonani.slicepay.Adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.anksmonani.slicepay.Bean.GetImages;
import com.anksmonani.slicepay.Bean.Photo;
import com.anksmonani.slicepay.Listener.OnLoadMoreListener;
import com.anksmonani.slicepay.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private List<Photo> listPhoes;
    public ImagesAdapter( List<Photo> list,final RecyclerView mRecyclerView) {

        this.listPhoes =list;
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mRecyclerView.getLayoutManager().getItemCount() > 0) {
                    totalItemCount = mRecyclerView.getLayoutManager().getItemCount();
                    lastVisibleItem = ((GridLayoutManager) mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();

                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (mOnLoadMoreListener != null) {
                            mOnLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }
            }
        });
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_grid_image, parent, false);
            return new ViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_loading_item, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        LoadingViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {

            ViewHolder ImageHolder = (ViewHolder) holder;

            ImageHolder.mTvImageTitle.setText(listPhoes.get(position).getTitle());

            String url = "http://farm" + listPhoes.get(position).getFarm() + ".staticflickr.com/" + listPhoes.get(position).getServer() +
                    "/" + listPhoes.get(position).getId() + "_" + listPhoes.get(position).getSecret() + ".jpg";
            Glide.with(holder.itemView.getContext())
                    .load(url)
                    .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background).centerCrop())
                    .thumbnail(0.1f)
                    .into(ImageHolder.mIvImage);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return listPhoes != null ? listPhoes.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return listPhoes.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

}