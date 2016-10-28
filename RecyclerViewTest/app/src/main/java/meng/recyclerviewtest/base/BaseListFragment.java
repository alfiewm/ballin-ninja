package meng.recyclerviewtest.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import meng.recyclerviewtest.R;
import meng.recyclerviewtest.widget.BaseViewHolder;

/**
 * 列表fragment基类 Created by meng on 2016/10/20.
 */

public abstract class BaseListFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private List<T> dataList = new ArrayList<>();

    /**
     * 提供一个默认的实现，返回包含RecyclerView的SwipeRefreshLayout实现，子类若要自定义layout需要在layout中
     * 至少声明一个id为R.id.list的RecyclerView和一个id为R.id.swipe_refresh的SwipeRefreshView
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base_list, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ensureViews();
    }

    private void ensureViews() {
        if (recyclerView != null) {
            return;
        }
        View root = getView();
        if (root == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        recyclerView = (RecyclerView) root.findViewById(R.id.list);
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * 默认线性布局，子类可以返回自定义布局管理器
     */
    @NonNull
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    private RecyclerView.Adapter<BaseViewHolder> adapter = new RecyclerView.Adapter<BaseViewHolder>() {
        static final int TYPE_LOAD_MORE = -1;

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_LOAD_MORE) {
                ProgressBar progressBar = new ProgressBar(getActivity());
                return new LoadMoreViewHolder(progressBar);
            }
            return getViewHolder(parent, viewType);
        }

        @Override
        public int getItemViewType(int position) {
            if (canLoadMore && position == getItemCount() - 1) {
                return TYPE_LOAD_MORE;
            }
            return BaseListFragment.this.getItemViewType(position);
        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            if (canLoadMore && position == getItemCount() -1) {
                onLoadMore();
                return;
            }
            BaseListFragment.this.onBindViewHolder(holder, position, dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size() + (canLoadMore ? 1 : 0);
        }
    };

    private class LoadMoreViewHolder extends BaseViewHolder {

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onItemViewClick(View itemView) {
            Toast.makeText(getActivity(), "wait for a minute!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public abstract void onRefresh();

    protected abstract void onLoadMore();

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);

    protected abstract void onBindViewHolder(BaseViewHolder holder, int position, @NonNull T item);

    protected int getItemViewType(int position) {
        return 0; // default 1 type.
    }

    boolean canLoadMore = false;

    protected void setRefreshCompleted(List<T> data, boolean canLoadMore) {
        swipeRefreshLayout.setRefreshing(false);
        this.canLoadMore = canLoadMore;
        dataList.clear();
        dataList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    protected void setLoadMoreCompleted(List<T> data, boolean canLoadMore) {
        swipeRefreshLayout.setRefreshing(false);
        this.canLoadMore = canLoadMore;
        dataList.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
