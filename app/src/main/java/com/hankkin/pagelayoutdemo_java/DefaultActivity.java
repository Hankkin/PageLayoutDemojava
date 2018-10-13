package com.hankkin.pagelayoutdemo_java;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.hankkin.pagelayout_java.PageLayout;

public class DefaultActivity extends AppCompatActivity {

    private PageLayout mPageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        mPageLayout = new PageLayout.Builder(this)
                .initPage(findViewById(R.id.ll_default))
                .setCustomView(LayoutInflater.from(this).inflate(R.layout.layout_custom,null))
                .setOnRetryListener(new PageLayout.OnRetryClickListener() {
                    @Override
                    public void onRetry() {
                        loadData();
                    }
                })
                .create();

        loadData();
    }

    private void loadData(){
        mPageLayout.showLoading();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mPageLayout.hide();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menus,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_content:
                mPageLayout.hide();
                break;
            case R.id.menu_customer:
                mPageLayout.showCustom();
                break;
            case R.id.menu_empty:
                mPageLayout.showEmpty();
                break;
            case R.id.menu_error:
                mPageLayout.showError();
                break;
            case R.id.menu_loading:
                mPageLayout.showLoading();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
