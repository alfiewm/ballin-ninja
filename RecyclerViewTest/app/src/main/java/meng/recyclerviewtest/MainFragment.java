package meng.recyclerviewtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import meng.recyclerviewtest.base.BaseListFragment;
import meng.recyclerviewtest.data.BaseModel;
import meng.recyclerviewtest.data.ImageApi;
import meng.recyclerviewtest.data.ImageData;
import meng.recyclerviewtest.widget.BaseViewHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meng on 2016/10/20.
 */

public class MainFragment extends BaseListFragment<ImageData> {

    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gank.io").addConverterFactory(GsonConverterFactory.create()).build();
    private ImageApi imageApi = retrofit.create(ImageApi.class);
    int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        onRefresh();
        return rootView;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(BaseViewHolder holder, int position, @NonNull ImageData item) {
        Glide.with(getActivity())
                .load(item.url)
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(((ViewHolder) holder).iv);
    }

    @Override
    public void onRefresh() {
        page = 1;
        Call<BaseModel<ArrayList<ImageData>>> call = imageApi.defaultBenefits(20, page++);
        call.enqueue(new Callback<BaseModel<ArrayList<ImageData>>>() {
            @Override
            public void onResponse(Call<BaseModel<ArrayList<ImageData>>> call, Response<BaseModel<ArrayList<ImageData>>> response) {
                setRefreshCompleted(response.body().results, response.body().results.size() == 20);
            }

            @Override
            public void onFailure(Call<BaseModel<ArrayList<ImageData>>> call, Throwable t) {
                setRefreshCompleted(null, false);
            }
        });
    }

    @Override
    protected void onLoadMore() {
        Call<BaseModel<ArrayList<ImageData>>> call = imageApi.defaultBenefits(20, page++);
        call.enqueue(new Callback<BaseModel<ArrayList<ImageData>>>() {
            @Override
            public void onResponse(Call<BaseModel<ArrayList<ImageData>>> call, Response<BaseModel<ArrayList<ImageData>>> response) {
                setLoadMoreCompleted(response.body().results, response.body().results.size() == 20);
            }

            @Override
            public void onFailure(Call<BaseModel<ArrayList<ImageData>>> call, Throwable t) {
                setLoadMoreCompleted(null, false);
            }
        });
    }

    @NonNull
    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
//        return new GridLayoutManager(getActivity(), 2);
        return new LinearLayoutManager(getActivity());
//        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    private class ViewHolder extends BaseViewHolder {
        ImageView iv;

        ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.image_view);
        }

        @Override
        protected void onItemViewClick(View itemView) {
            Toast.makeText(getActivity(), "UNDEFINED", Toast.LENGTH_SHORT).show();
        }
    }
}
