package com.example.qd.cloud.ui;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.qd.cloud.R;
import com.example.qd.cloud.interfaces.ActionSelectListener;
import com.example.qd.cloud.ui.base.BaseActivity;
import com.example.qd.cloud.utils.CustomActionWebView;
import com.example.qd.cloud.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.webView)
    CustomActionWebView webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        List<String> list = new ArrayList<>();
        list.add("复制");
        //设置item
        webView.setActionList(list);
        //链接js注入接口，使能选中返回数据
        webView.linkJSInterface();
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        //使用javascript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int i) {
                if (i < 100) {
                    progressBar.setProgress(i);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
//                super.onProgressChanged(webView, i);
            }

            @Override
            public void onReceivedTitle(WebView webView, String title) {
                //加载错误显示的页面
                super.onReceivedTitle(webView, title);
            }
        });
        webView.loadUrl("http://h5.gamemm.com/user/penalties");

        //复制webView内容
        webView.setActionSelectListener(new ActionSelectListener() {
            @Override
            public void onClick(String title, String selectText) {
                ToastUtil.show(mContext, "复制成功");
                // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(selectText);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.dismissAction();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
