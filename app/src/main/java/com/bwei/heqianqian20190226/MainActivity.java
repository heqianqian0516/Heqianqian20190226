package com.bwei.heqianqian20190226;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.HotAdapter;
import api.Apis;
import bean.HotBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presenter.PresenterImp;
import view.IView;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.image_pop)
    ImageView mImagePop;
    @BindView(R.id.recy)
    XRecyclerView mRecy;
    private PresenterImp presenter;
   private List<HotBean.ResultBean> lists=new ArrayList<>();
    private HotAdapter hotAdapter;
    private int page=1;
    private int count=10;
    private PopupWindow popupWindow;
    private int mpage=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        mpage=1;
        //创建布局管理器
        GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,2);
        mRecy.setLayoutManager(gridLayoutManager);
        mRecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mpage++;
                initLoad();
            }

            @Override
            public void onLoadMore() {
                initLoad();
            }
        });
    }

    //请求网络
    private void initLoad() {
        presenter.startRequestGet(String.format(Apis.URL_HOTMOVIE,page,count),HotBean.class);
    }

    @OnClick({R.id.back, R.id.image_pop, R.id.recy})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.image_pop:
                Intent intent=new Intent(MainActivity.this,ShowActivity.class);
                startActivity(intent);
                break;
            case R.id.recy:
                break;
        }
    }

    @Override
    public void requestDataSuccess(Object o) {
         if (o instanceof HotBean){

             HotBean hotBean= (HotBean) o;
             lists.addAll(hotBean.getResult());
             hotAdapter = new HotAdapter(lists,MainActivity.this);
             mRecy.setAdapter(hotAdapter);
             }
             mpage++;
             mRecy.loadMoreComplete();
             mRecy.refreshComplete();
             Toast.makeText(MainActivity.this,"没有更多数据了",Toast.LENGTH_SHORT).show();
         }


    @Override
    public void requestDataFail(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDeath();
    }
}
