package com.bei.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.bei.R;
import com.bei.adapter.BaseAdapter;

import org.json.JSONArray;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by tiptimes on 15/8/7.
 */
public class SwipeRecyclerView extends RelativeLayout implements SwipeRefreshLayout.OnRefreshListener, BaseAdapter.OnLoadMoreListener, FindCallback {
    private Context mContext;
    private RelativeLayout container;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private BaseAdapter adapter;
    private Class dataClass;

    public SwipeRecyclerView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public SwipeRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.v_swipe_recycler, this);
        container = (RelativeLayout) findViewById(R.id.container);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public void addWhere(String key, Object object) {
        query.addWhereEqualTo(key, object);
    }

    public void addInclude(String include) {
        this.include = include;
    }

    public void setAdapter(BaseAdapter adapter, Class dataClass) {
        this.adapter = adapter;
        adapter.setOnLoadMoreListener(this);
        recyclerView.setAdapter(adapter);
        this.dataClass = dataClass;
        query = new BmobQuery(dataClass.getSimpleName());
    }


    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRefreshabled(boolean abled) {
        swipeRefreshLayout.setEnabled(abled);
    }

    private int STATE_TYPE = STATE_REFRESH;
    private static final int STATE_REFRESH = 0;// 下拉刷新
    private static final int STATE_MORE = 1;// 加载更多
    private int limit = 10;// 每页的数据是10条
    private int curPage = 0;
    private String include = "";

    private BmobQuery query;

    private void queryData(int page) {
        query.setLimit(limit);
        if (!include.equals("")) {
            query.include(include);
        }
        query.setSkip(page * limit);
        query.findObjects(mContext, this);
    }

    public void start() {
        onRefresh();
    }

    @Override
    public void onRefresh() {
        STATE_TYPE = STATE_REFRESH;
        swipeRefreshLayout.setRefreshing(true);
        queryData(0);
    }

    @Override
    public void onLoadMore() {
        STATE_TYPE = STATE_MORE;
        queryData(curPage);
    }

    @Override
    public void onSuccess(JSONArray jsonArray) {
        if (JSON.parseArray(jsonArray.toString(), dataClass).size() > 0) {
            if (STATE_TYPE == STATE_REFRESH) {
                curPage = 0;
                adapter.setDataList(JSON.parseArray(jsonArray.toString(), dataClass));
                adapter.notifyDataSetChanged();
            } else {
                adapter.getDataList().addAll(JSON.parseArray(jsonArray.toString(), dataClass));
                adapter.notifyDataSetChanged();
            }
            curPage++;
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailure(int i, String s) {

    }
}
