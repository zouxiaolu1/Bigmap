package net.xiaolu.italker.bigmap;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView tv_intro;
    private ArrayList<Ad> list= new ArrayList<>();
    private LinearLayout dot_layout;
    public String[] shape = new String[]{"oval","rect","ring"};
    private String type = "ring";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();

    }

    private void initListener() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                updateIntroAndDot();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {

        list.add(new Ad(R.mipmap.a,"巩俐不低俗，我就不低俗"));
        list.add(new Ad(R.mipmap.b,"朴树又回来了，再唱经典老歌,再唱经典老歌再唱经典老歌"));
        list.add(new Ad(R.mipmap.c,"揭秘北京电影如何升级"));
        list.add(new Ad(R.mipmap.d,"乐视版TV大放送"));
        list.add(new Ad(R.mipmap.e,"热血屌丝的反杀"));
        initDots();
        viewPager.setAdapter(new MyPagerAdapter());
        int centerValue = Integer.MAX_VALUE/2;
        int value = centerValue%list.size();
        viewPager.setCurrentItem(centerValue-value);
        updateIntroAndDot();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tv_intro = (TextView) findViewById(R.id.tv_intro);
        dot_layout= (LinearLayout) findViewById(R.id.dot_layout);
    }
    private void initDots(){
        for(int i = 0;i<list.size();i++){
            if(shape[0]==type){
                View view = new View(this);
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(16,16);
                if(i!=0){
                    param.leftMargin = 18;
                }
                view.setLayoutParams(param);
                view.setBackgroundResource(R.drawable.selector_dot_oval);
                dot_layout.addView(view);
            }else if(shape[1]==type){
                View view = new View(this);
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(32,12);
                if(i!=0){
                    param.leftMargin = 18;
                }
                view.setLayoutParams(param);
                view.setBackgroundResource(R.drawable.selector_dot_rect);
                dot_layout.addView(view);
            }else if(shape[2]==type){
                View view = new View(this);
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(16,16);
                if(i!=0){
                    param.leftMargin = 18;
                }
                view.setLayoutParams(param);
                view.setBackgroundResource(R.drawable.selector_dot_ring);
                dot_layout.addView(view);
            }

        }
    }

    private void updateIntroAndDot(){
        int currentPage = viewPager.getCurrentItem()%list.size();
        tv_intro.setText(list.get(currentPage).getIntro());
        for(int i = 0;i<dot_layout.getChildCount();i++){
            dot_layout.getChildAt(i).setEnabled(i==currentPage);
        }
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        /*
        返回true表示不去创建使用缓存，返回false表示重新创建
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        /*
        类似于BaseAdapter的getView方法
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(MainActivity.this,R.layout.adapter_ad,null);
            ImageView imageview = (ImageView) view.findViewById(R.id.image);
            Ad ad = list.get(position%list.size());

            imageview.setImageResource(ad.getIconResId());
            container.addView(view);
            return view;
        }

        /*
        销毁page
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
