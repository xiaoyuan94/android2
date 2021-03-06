package com.xxyuan.project.ui.marqueeview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuggestionSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.xxyuan.project.R;

public class ViewsFlipperActivity extends AppCompatActivity {

    private List<Integer> colors = new ArrayList<>();
    private List<Pair<Integer, String>> items = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_views_flipper);
        initData();

        final ViewsFlipper flipper = findViewById(R.id.flipper);
        final FlipperAdapter adapter = new FlipperAdapter(items);
        flipper.setAdapter(adapter);
        flipper.setOrientation(RecyclerView.HORIZONTAL);
        flipper.startFlipping();

        Button btn = findViewById(R.id.btn_change_data);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.clear();
                items.add(new Pair<>(R.drawable.ic_ac_unit_black_24dp, "我已经从你的全世界路过"));
                items.add(new Pair<>(R.drawable.ic_android_black_24dp, "像一颗流星"));
                items.add(new Pair<>(R.drawable.ic_brightness_3_black_24dp, "划过命运的天空"));
                adapter.notifyDataSetChanged();
            }
        });

        Button btn2 = findViewById(R.id.btn_change_adapter);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.clear();
                items.add(new Pair<>(R.drawable.ic_ac_unit_black_24dp, "我已经从你的全世界路过"));
                items.add(new Pair<>(R.drawable.ic_android_black_24dp, "像一颗流星"));
                items.add(new Pair<>(R.drawable.ic_brightness_3_black_24dp, "划过命运的天空"));
                flipper.setAdapter(new FlipperAdapter(items));
                flipper.startFlipping();
            }
        });

        Button btn3 = findViewById(R.id.btn_change_orientation);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setOrientation(flipper.getOrientation() == RecyclerView.VERTICAL ? RecyclerView.HORIZONTAL : RecyclerView.VERTICAL);
            }
        });

        TextView tvSpan = findViewById(R.id.tv_span_test);
        SpannableString string = new SpannableString("我已经从你的全世界路过");
//        string.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        string.setSpan(new BackgroundColorSpan(Color.CYAN), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        string.setSpan(new RelativeSizeSpan(2.0f), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        string.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelOffset(R.dimen.text_32sp)), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        string.setSpan(new UnderlineSpan(), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

//        string.setSpan(new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                Toast.makeText(MainActivity.this, "click ME！", Toast.LENGTH_LONG).show();
//            }
//        }, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        string.setSpan(new URLSpan("https://me.csdn.net/blog/newcoderzZ"), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        tvSpan.setMovementMethod(LinkMovementMethod.getInstance());
//        string.setSpan(new ImageSpan(this, R.drawable.ic_favorite_black_24dp), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        string.setSpan(new StyleSpan(Typeface.BOLD), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        string.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        string.setSpan(new SubscriptSpan(), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        string.setSpan(new SuperscriptSpan(), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        tvSpan.setText(string);

    }

    private void initData() {
        colors.add(Color.BLUE);
        colors.add(Color.CYAN);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        items.add(new Pair<>(R.drawable.ic_ac_unit_black_24dp, "感受停在我发端的指尖"));
        items.add(new Pair<>(R.drawable.ic_android_black_24dp, "如何瞬间 冻结时间"));
        items.add(new Pair<>(R.drawable.ic_brightness_3_black_24dp, "记住望着我坚定的双眼"));
        items.add(new Pair<>(R.drawable.ic_cloud_black_24dp, "也许已经 没有明天"));
        items.add(new Pair<>(R.drawable.ic_favorite_black_24dp, "面对浩瀚的星海"));
        items.add(new Pair<>(R.drawable.ic_favorite_border_black_24dp, "我们微小得像尘埃"));
        items.add(new Pair<>(R.drawable.ic_send_black_24dp, "漂浮在 一片无奈"));
        items.add(new Pair<>(R.drawable.ic_sentiment_very_satisfied_black_24dp, "缘份让我们相遇乱世以外"));
        items.add(new Pair<>(R.drawable.ic_spa_black_24dp, "命运却要我们危难中相爱"));
        items.add(new Pair<>(R.drawable.ic_toys_black_24dp, "也许未来遥远在光年之外"));
        items.add(new Pair<>(R.drawable.ic_whatshot_black_24dp, "我愿守候未知里为你等待"));
        items.add(new Pair<>(R.drawable.ic_android_black_24dp, "我没想到 为了你 我能疯狂到"));
        items.add(new Pair<>(R.drawable.ic_favorite_black_24dp, "山崩海啸 没有你 根本不想逃"));
        items.add(new Pair<>(R.drawable.ic_ac_unit_black_24dp, "我的大脑 为了你 已经疯狂到"));
        items.add(new Pair<>(R.drawable.ic_sentiment_very_satisfied_black_24dp, "脉搏心跳 没有你 根本不重要"));
        items.add(new Pair<>(R.drawable.ic_spa_black_24dp, "一双围在我胸口的臂弯"));
        items.add(new Pair<>(R.drawable.ic_favorite_black_24dp, "足够抵挡 天旋地转"));
        items.add(new Pair<>(R.drawable.ic_brightness_3_black_24dp, "一种执迷不放手的倔强"));
        items.add(new Pair<>(R.drawable.ic_android_black_24dp, "足以点燃 所有希望"));
        items.add(new Pair<>(R.drawable.ic_toys_black_24dp, "宇宙磅礡而冷漠"));
        items.add(new Pair<>(R.drawable.ic_favorite_black_24dp, "我们的爱微小却闪烁"));
        items.add(new Pair<>(R.drawable.ic_cloud_black_24dp, "颠簸 却如此忘我"));
        items.add(new Pair<>(R.drawable.ic_sentiment_very_satisfied_black_24dp, "也许航道以外 是醒不来的梦"));
        items.add(new Pair<>(R.drawable.ic_favorite_black_24dp, "乱世以外 是纯粹的相拥"));
    }

    class FlipperAdapter extends RecyclerView.Adapter<VH> {
        private List<Pair<Integer, String>> list;

        FlipperAdapter(List<Pair<Integer, String>> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_flipper_child, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            holder.bind(list.get(position).first, list.get(position).second);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class VH extends RecyclerView.ViewHolder {
        private TextView tvLyric;
        private ImageView iv;

        VH(@NonNull View itemView) {
            super(itemView);
            tvLyric = itemView.findViewById(R.id.tv_child);
            iv = itemView.findViewById(R.id.iv_child);
        }

        void bind(int drawableId, String lyric) {
            Log.d("==========", "lyric : " + lyric);
            iv.setImageResource(drawableId);
            tvLyric.setText(lyric);
            tvLyric.setTextColor(colors.get(new Random(System.currentTimeMillis()).nextInt(5)));
        }
    }
}
