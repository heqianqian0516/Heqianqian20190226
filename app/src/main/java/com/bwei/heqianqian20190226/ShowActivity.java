package com.bwei.heqianqian20190226;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import adapter.HotAdapter;
import adapter.ShowAdapter;
import api.Apis;
import bean.HotBean;
import bean.ShowBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presenter.PresenterImp;
import view.IView;

public class ShowActivity extends AppCompatActivity implements IView {
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.image_pop)
    ImageView mImagePop;
    @BindView(R.id.recyView)
    RecyclerView mRecyView;
    private PresenterImp presenter;
    private List<ShowBean.ResultBean> lists = new ArrayList<>();
    private int page=1;
    private int count=10;
    private ShowAdapter showAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        presenter = new PresenterImp(this);
        initLoad();
        initView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("电影商城");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.back);
    }

    private void initView() {
        //创建布局管理器
        GridLayoutManager gridLayoutManager=new GridLayoutManager(ShowActivity.this,2);
        mRecyView.setLayoutManager(gridLayoutManager);
    }

    private void initLoad() {
        presenter.startRequestGet(String.format(Apis.URL_RELECEMOVIE,page,count), ShowBean.class);
    }

    @Override
    public void requestDataSuccess(Object o) {
        if (o instanceof ShowBean){

            ShowBean showBean= (ShowBean) o;
            lists.addAll(showBean.getResult());
            showAdapter = new ShowAdapter(lists,ShowActivity.this);
            mRecyView.setAdapter(showAdapter);
        }
    }

    @Override
    public void requestDataFail(String error) {

    }

    @OnClick({R.id.back, R.id.image_pop, R.id.recyView})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.image_pop:
                break;
            case R.id.recyView:
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDeath();
    }
}
