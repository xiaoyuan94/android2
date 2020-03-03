package com.xxyuan.project.ui.immersionBar.view;


import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentTransaction;

import com.xxyuan.project.R;
import com.xxyuan.project.base.BaseActivity;
import com.xxyuan.project.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImmersionBarActivity extends BaseActivity {


    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.ll_category)
    LinearLayout llCategory;
    @BindView(R.id.ll_service)
    LinearLayout llService;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;
    @BindView(R.id.ll)
    LinearLayout ll;
    private HomeTwoFragment homeTwoFragment;
    private CategoryTwoFragment categoryTwoFragment;
    private ServiceTwoFragment serviceTwoFragment;
    private MineTwoFragment mineTwoFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_immersion_bar;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        selectedFragment(0);
        tabSelected(llHome);
    }

    /**
     * 颜色改变
     * @param linearLayout
     */
    private void tabSelected(LinearLayout linearLayout) {
        llHome.setSelected(false);
        llCategory.setSelected(false);
        llService.setSelected(false);
        llMine.setSelected(false);
        linearLayout.setSelected(true);
    }
    private void selectedFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (homeTwoFragment == null) {
                    homeTwoFragment = new HomeTwoFragment();
                    transaction.add(R.id.content, homeTwoFragment);
                } else {
                    transaction.show(homeTwoFragment);
                }
                break;
            case 1:
                if (categoryTwoFragment == null) {
                    categoryTwoFragment = new CategoryTwoFragment();
                    transaction.add(R.id.content, categoryTwoFragment);
                } else {
                    transaction.show(categoryTwoFragment);
                }
                break;
            case 2:
                if (serviceTwoFragment == null) {
                    serviceTwoFragment = new ServiceTwoFragment();
                    transaction.add(R.id.content, serviceTwoFragment);
                } else {
                    transaction.show(serviceTwoFragment);
                }
                break;
            case 3:
                if (mineTwoFragment == null) {
                    mineTwoFragment = new MineTwoFragment();
                    transaction.add(R.id.content, mineTwoFragment);
                } else {
                    transaction.show(mineTwoFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (homeTwoFragment != null) {
            transaction.hide(homeTwoFragment);
        }
        if (categoryTwoFragment != null) {
            transaction.hide(categoryTwoFragment);
        }
        if (serviceTwoFragment != null) {
            transaction.hide(serviceTwoFragment);
        }
        if (mineTwoFragment != null) {
            transaction.hide(mineTwoFragment);
        }
    }
    @OnClick({R.id.ll_home,R.id.ll_category,R.id.ll_service,R.id.ll_mine,})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                selectedFragment(0);
                tabSelected(llHome);
                break;
            case R.id.ll_category:
                selectedFragment(1);
                tabSelected(llCategory);
                break;
            case R.id.ll_service:
                selectedFragment(2);
                tabSelected(llService);
                break;
            case R.id.ll_mine:
                selectedFragment(3);
                tabSelected(llMine);
                break;
            default:
                break;
        }
    }

}
