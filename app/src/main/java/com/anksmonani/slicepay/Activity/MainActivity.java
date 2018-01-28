package com.anksmonani.slicepay.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.anksmonani.slicepay.Adapter.ImagesAdapter;
import com.anksmonani.slicepay.Bean.GetImages;
import com.anksmonani.slicepay.Bean.Photo;
import com.anksmonani.slicepay.Listener.OnLoadMoreListener;
import com.anksmonani.slicepay.Networking.APIInterface;
import com.anksmonani.slicepay.Networking.FlickrClient;
import com.anksmonani.slicepay.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity{

    private ImagesAdapter mAdapter;
    private int PageNumber = 1;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    public List<Photo> mListPhotos;
    @SuppressLint("StaticFieldLeak")
    private static MainActivity instance;

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        instance = this;
        SetToolbar();
        initialize();

    }

    private void initialize() {
        mListPhotos = new ArrayList<>();
        ButterKnife.bind(this);
        GridLayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ImagesAdapter(mListPhotos,mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        GetImageData(PageNumber);

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mListPhotos.add(null);
                mAdapter.notifyItemInserted(mListPhotos.size() - 1);
                PageNumber = PageNumber + 1;
                GetImageData(PageNumber);
            }
        });
    }

    @SuppressLint("NewApi")
    private void SetToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Flikr");
        toolbar.setTitleTextColor(getColor(R.color.white));
        setSupportActionBar(toolbar);
    }

    private void GetImageData(int mIntPageNumber) {
        FlickrClient.getClient().create(APIInterface.class).getImageDetails("9f89151d82e427401680cd48dd2d5cf5", mIntPageNumber,
                "json", "1", "5").enqueue(new Callback<GetImages>() {
            @Override
            public void onResponse(retrofit2.Call<GetImages> call, retrofit2.Response<GetImages> response) {

                if (response.isSuccessful()) {
                    if (mListPhotos.size() > 0) {
                        mListPhotos.remove(mListPhotos.size() - 1);
                        mAdapter.notifyItemRemoved(mListPhotos.size());
                    }
                    mListPhotos.addAll(response.body().getPhotos().getPhoto());
                    mAdapter.notifyDataSetChanged();
                    mAdapter.setLoaded();

                }
            }

            @Override
            public void onFailure(retrofit2.Call<GetImages> call, Throwable t) {
                //show error
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {

            if(mListPhotos.size()>0)
            {
                Intent in = new Intent(MainActivity.this, ImageSearch.class);
                startActivity(in);
            }

        }
        return super.onOptionsItemSelected(item);
    }

}


