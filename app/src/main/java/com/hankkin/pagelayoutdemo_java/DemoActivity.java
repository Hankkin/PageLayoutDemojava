package com.hankkin.pagelayoutdemo_java;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hankkin.pagelayout_java.PageLayout;

public class DemoActivity extends AppCompatActivity {

    private PageLayout mPageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        View custom = LayoutInflater.from(this)
                .inflate(R.layout.layout_custom, null);
        ((ImageView) (custom.findViewById(R.id.iv_custom))).setImageResource(R.mipmap.icon_smile);
        ((TextView) custom.findViewById(R.id.tv_custom_content)).setText("This is PageLayout");
        mPageLayout = new PageLayout.Builder(this)
                .initPage(findViewById(R.id.ll_demo))
                .setLoading(R.layout.layout_loading_demo, R.id.tv_page_loading_demo)
                .setEmpty(R.layout.layout_empty_demo, R.id.tv_page_empty_demo)
                .setCustomView(custom)
                .setError(R.layout.layout_error_demo, R.id.tv_page_error_demo, new PageLayout.OnRetryClickListener() {
                    @Override
                    public void onRetry() {
                        loadData();
                    }
                })
                .setEmptyDrawable(R.drawable.pic_empty)
                .setErrorDrawable(R.drawable.pic_error)
                .setLoadingText("Loading")
                .create();

        loadData();
    }


    private void loadData() {
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
        new MenuInflater(this).inflate(R.menu.menus, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
