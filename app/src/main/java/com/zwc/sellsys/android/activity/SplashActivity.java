package com.zwc.sellsys.android.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;

import com.zwc.sellsys.android.R;
import com.zwc.sellsys.android.ad.yad.YAdUtil;
import com.zwc.sellsys.android.utils.LogUtil;
import com.zwc.sellsys.android.utils.ToastUtil;

import cdc.sed.yff.nm.cm.ErrorCode;
import cdc.sed.yff.nm.sp.SplashViewSettings;
import cdc.sed.yff.nm.sp.SpotListener;
import cdc.sed.yff.nm.sp.SpotManager;
import cdc.sed.yff.nm.sp.SpotRequestListener;

public class SplashActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        YAdUtil.init(this);
        preloadAd();
        setupSplashAd();

    }
    /**
     * 预加载广告
     */
    private void preloadAd() {
        // 注意：不必每次展示插播广告前都请求，只需在应用启动时请求一次
        SpotManager.getInstance(this).requestSpot(new SpotRequestListener() {
            @Override
            public void onRequestSuccess() {
                ToastUtil.show("请求插屏广告成功");
                //				// 应用安装后首次展示开屏会因为本地没有数据而跳过
                //              // 如果开发者需要在首次也能展示开屏，可以在请求广告成功之前展示应用的logo，请求成功后再加载开屏
                //				setupSplashAd();
            }

            @Override
            public void onRequestFailed(int errorCode) {
                LogUtil.i("请求插屏广告失败，errorCode: %s", errorCode+"");
                switch (errorCode) {
                    case ErrorCode.NON_NETWORK:
                        ToastUtil.show("网络异常");
                        break;
                    case ErrorCode.NON_AD:
                        ToastUtil.show("暂无插屏广告");
                        break;
                    default:
                        ToastUtil.show("请稍后再试");
                        break;
                }
            }
        });
    }
    /**
     * 设置开屏广告
     */
    private void setupSplashAd() {
        // 创建开屏容器
        ConstraintLayout container = findViewById(R.id.adContainer);

        // 对开屏进行设置
        SplashViewSettings splashViewSettings = new SplashViewSettings();
        // 设置跳转的窗口类
        splashViewSettings.setTargetClass(MainFragmentActivity.class);
        // 设置开屏的容器
        splashViewSettings.setSplashViewContainer(container);
        // 展示开屏广告
        SpotManager.getInstance(this)
                .showSplash(this, splashViewSettings, new SpotListener() {

                    @Override
                    public void onShowSuccess() {
                        ToastUtil.show("开屏展示成功");
                        LogUtil.i(TAG,"开屏展示成功");
                    }

                    @Override
                    public void onShowFailed(int errorCode) {
                        LogUtil.i(TAG,"开屏展示失败");
                        switch (errorCode) {
                            case ErrorCode.NON_NETWORK:
                                LogUtil.i(TAG,"网络异常");
                                break;
                            case ErrorCode.NON_AD:
                                LogUtil.i(TAG,"暂无开屏广告");
                                break;
                            case ErrorCode.RESOURCE_NOT_READY:
                                LogUtil.i(TAG,"开屏资源还没准备好");
                                break;
                            case ErrorCode.SHOW_INTERVAL_LIMITED:
                                LogUtil.i(TAG,"开屏展示间隔限制");
                                break;
                            case ErrorCode.WIDGET_NOT_IN_VISIBILITY_STATE:
                                LogUtil.i(TAG,"开屏控件处在不可见状态");
                                break;
                            default:

                                break;
                        }
                    }

                    @Override
                    public void onSpotClosed() {
                        LogUtil.i(TAG,"开屏被关闭");
                    }

                    @Override
                    public void onSpotClicked(boolean isWebPage) {
                        LogUtil.i(TAG,"开屏被点击");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 开屏展示界面的 onDestroy() 回调方法中调用
        SpotManager.getInstance(this).onDestroy();
    }
}
