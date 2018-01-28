package com.anksmonani.slicepay.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.anksmonani.slicepay.Adapter.ImageSearchAdapter;
import com.anksmonani.slicepay.Bean.Photo;
import com.anksmonani.slicepay.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageSearch extends AppCompatActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.mEdtSearchBox)
    AppCompatEditText mEdtSearchBox;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private List<Photo> mListPhotos;
    private ImageSearchAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);
        ButterKnife.bind(this);

        setUpToolBar();
        init();
    }

    private void setUpToolBar() {


        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Image Search");
        mToolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }

    private void init()
    {
        mListPhotos = MainActivity.getInstance().mListPhotos;
        GridLayoutManager mLayoutManager = new GridLayoutManager(ImageSearch.this, 2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ImageSearchAdapter(ImageSearch.this,mListPhotos);
        mRecyclerView.setAdapter(mAdapter);



        mEdtSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {


                if(mListPhotos.size()>0)
                {
                    final List<Photo> filteredModelList = filter(mListPhotos, String.valueOf(cs));
                    mAdapter.setFilter(filteredModelList);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        });
    }



    private List<Photo> filter(List<Photo> models, String query) {
        query = query.toLowerCase();

        final List<Photo> filteredModelList = new ArrayList<>();
        for (Photo model : models) {
            final String text = model.getTitle().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

}
