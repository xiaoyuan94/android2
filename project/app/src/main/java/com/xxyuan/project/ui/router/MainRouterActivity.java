package com.xxyuan.project.ui.router;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xxyuan.project.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainRouterActivity extends AppCompatActivity {

    @BindView(R.id.bt_router)
    Button btRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_main_router);
        ButterKnife.bind(this);
        initEvent();
    }

    private void initEvent() {
        btRouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRouter();
            }
        });
    }

    private void startRouter() {
        ARouter.getInstance().build("/ModuleARouter/RouterActivity")
                .withString("key3", "MainRouterActivity跳转过去")
                .navigation();



        /**
         * .withBoolean( String key, boolean value)
         * .withChar( String key, char value )
         * .withShort( String key, short value)
         * .withInt( String key, int value)
         * .withLong( String key, long value)
         * .withDouble( String key, double value)
         * .withByte( String key, byte value)
         * .withFloat( String key, float value)
         * .withCharSequence( String key,  CharSequence value)
         *
         * //数组类型
         * .withParcelableArrayList( String key, ArrayList<? extends Parcelable > value)
         * .withStringArrayList( String key,  ArrayList<String> value)
         * .withIntegerArrayList( String key, ArrayList<Integer> value)
         * .withSparseParcelableArray( String key, SparseArray<? extends Parcelable> value)
         * .withCharSequenceArrayList( String key, ArrayList<CharSequence> value)
         * .withShortArray( String key,  short[] value)
         * .withCharArray( String key, char[] value)
         * .withFloatArray( String key, float[] value)
         * .withCharSequenceArray( String key,  CharSequence[] value)
         *
         * //Bundle 类型
         * .with( Bundle bundle )
         *
         * //Activity 跳转动画
         * .withTransition(int enterAnim, int exitAnim)
         *
         * //其他类型
         * .withParcelable( String key, Parcelable value)
         * .withParcelableArray( String key,  Parcelable[] value)
         * .withSerializable( String key, Serializable value)
         * .withByteArray( String key, byte[] value)
         * .withTransition(int enterAnim, int exitAnim)
         */
    }
}