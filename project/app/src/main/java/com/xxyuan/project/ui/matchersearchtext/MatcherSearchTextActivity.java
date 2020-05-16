package com.xxyuan.project.ui.matchersearchtext;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ResourceUtils;
import com.xxyuan.project.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatcherSearchTextActivity extends AppCompatActivity {

    @BindView(R.id.tv_matcher)
    TextView tvMatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matcher_search_text);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        SpannableString string = matcherSearchText(getResources().getColor(R.color.colorAccent),"1233456123", "123");
        tvMatcher.setText(string);
    }

    /**
     * 正则匹配 返回值是一个SpannableString 即经过变色处理的数据
     */
    private SpannableString matcherSearchText(int color, String text, String keyword) {
        SpannableString spannableString = new SpannableString(text);
        //条件 keyword
        Pattern pattern = Pattern.compile(keyword);
        //匹配
        Matcher matcher = pattern.matcher(new SpannableString(text.toLowerCase()));
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            //ForegroundColorSpan 需要new 不然也只能是部分变色
            spannableString.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        //返回变色处理的结果
        return spannableString;
    }
}
