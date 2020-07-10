package com.xxyuan.project.ui.banner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.snackbar.Snackbar;
import com.xxyuan.project.R;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.ZoomOutPageTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerActivity extends AppCompatActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.banner2)
    Banner banner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        ImageAdapter adapter = new ImageAdapter(DataBean.getTestData2());
        banner.setAdapter(adapter)//设置适配器
//              .setCurrentItem(3,false)
                .addBannerLifecycleObserver(this)//添加生命周期观察者
//              .setBannerRound(BannerUtils.dp2px(5))//圆角
//              .addPageTransformer(new RotateYTransformer())//添加切换效果
                .setIndicator(new CircleIndicator(this))//设置指示器
                .setOnBannerListener((data, position) -> {
                    Snackbar.make(banner, ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                    LogUtils.d("position：" + position);
                });//设置点击事件,传this也行
        //添加间距(如果使用了画廊效果就不要添加间距了，因为内部已经添加过了)
//        banner.addPageTransformer(new MarginPageTransformer((int) BannerUtils.dp2px(10)));
        //魅族效果
//        banner.setBannerGalleryMZ(20);

        //实现1号店和淘宝头条类似的效果
        banner2.setAdapter(new TopLineAdapter(DataBean.getTestData2()))
                .setOrientation(Banner.VERTICAL)
                .setPageTransformer(new ZoomOutPageTransformer())
                .setOnBannerListener((data, position) -> {
                    Snackbar.make(banner, ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                    LogUtils.d("position：" + position);
                });

    }
}