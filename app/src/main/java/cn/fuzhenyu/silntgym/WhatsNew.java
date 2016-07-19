package cn.fuzhenyu.silntgym;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by 77dfeba on 2016/6/24.
 */

public class WhatsNew extends Activity {

    /** Viewpager对象 */
    private ViewPager viewPager;
    private ImageView imageView;
    /** 创建一个数组，用来存放每个页面要显示的View */
    private ArrayList<View> pageViews;
    /** 创建一个imageview类型的数组，用来表示导航小圆点 */
    private ImageView[] imageViews;
    /** 装显示图片的viewgroup */
    private ViewGroup viewPictures;
    /** 导航小圆点的viewgroup */
    private ViewGroup viewPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<View>();
        pageViews.add(inflater.inflate(R.layout.wahtsnew_page1,null));
        pageViews.add(inflater.inflate(R.layout.whatsnew_page2, null));
        pageViews.add(inflater.inflate(R.layout.whatsnew_page3, null));
        pageViews.add(inflater.inflate(R.layout.whatsnew_page4, null));
        pageViews.add(inflater.inflate(R.layout.whatsnew_page5, null));

        // 小圆点数组，大小是图片的个数
        imageViews = new ImageView[pageViews.size()];
        // 从指定的XML文件中加载视图
        viewPictures = (ViewGroup) inflater.inflate(R.layout.viewpagers, null);

        viewPager = (ViewPager) viewPictures.findViewById(R.id.guidePagers);
        viewPoints = (ViewGroup) viewPictures.findViewById(R.id.viewPoints);

        // 添加小圆点导航的图片
        for (int i = 0; i < pageViews.size(); i++) {
            imageView = new ImageView(WhatsNew.this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            imageView.setPadding(5, 0, 5, 0);
            // 吧小圆点放进数组中
            imageViews[i] = imageView;
            // 默认选中的是第一张图片，此时第一个小圆点是选中状态，其他不是
            if (i == 0)
                imageViews[i].setImageDrawable(getResources().getDrawable(
                        R.drawable.page_indicator_focused));
            else
                imageViews[i].setImageDrawable(getResources().getDrawable(
                        R.drawable.page_indicator_unfocused));
            // 将imageviews添加到小圆点视图组
            viewPoints.addView(imageViews[i]);
        }

        setContentView(viewPictures);

        viewPager.setAdapter(new NavigationPageAdapter());
        // 为viewpager添加监听，当view发生变化时的响应
        viewPager.addOnPageChangeListener(new NavigationPageChangeListener());


        // 开始按钮方法，开始按钮在XML文件中onClick属性设置；
        //Oncreate预先运行需要指定位置读取按钮位置，否则访问空
        Button btn = (Button)pageViews.get(4).findViewById(R.id.toMain);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent intent = new Intent(WhatsNew.this, MainActivity.class);
                startActivity(intent);
                WhatsNew.this.finish();
            }
        });
    }

    // 导航图片view的适配器，必须要实现的是下面四个方法
    private class NavigationPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        // 初始化每个Item
        @Override
        public Object instantiateItem(ViewGroup viewGroup, int position) {
            viewGroup.addView(pageViews.get(position));
            return pageViews.get(position);
        }

        // 销毁每个Item
        @Override
        public void destroyItem(ViewGroup viewGroup, int position, Object object) {
            viewGroup.removeView(pageViews.get(position));
        }

    }

    // viewpager的监听器，主要是onPageSelected要实现
    private class NavigationPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            // 循环主要是控制导航中每个小圆点的状态
            for (int i = 0; i < imageViews.length; i++) {
                // 当前view下设置小圆点为选中状态
                imageViews[i].setImageDrawable(getResources().getDrawable(
                        R.drawable.page_indicator_focused));
                // 其余设置为非选中状态
                if (position != i)
                    imageViews[i].setImageDrawable(getResources().getDrawable(
                            R.drawable.page_indicator_unfocused));
            }
        }

    }

}
