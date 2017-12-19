package sion.demobanner.imagebanner;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import sion.demobanner.R;


public class BannerView extends RelativeLayout {

    private final String TAG = BannerView.class.getSimpleName();

    private ViewPager viewPager;
    private BannerAdapter adapter;
    private ArrayList<String> urlList;
    private ClickListener listener;
    private int realPosition;

    public BannerView(Context context) {
        super(context);
        initView();
    }

    public BannerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BannerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        View view = inflate(getContext(), R.layout.layout_banner, null);
        addView(view);


        final GestureDetector tapGestureDetector = new GestureDetector(getContext(), new TapGestureListener());
        viewPager = (ViewPager) findViewById(R.id.vpBanner);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tapGestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }

    public void setBannerView(ArrayList<String> urlList, ClickListener listener) {
        this.urlList = urlList;
        this.listener = listener;

        adapter = new BannerAdapter(getContext(), urlList);
        viewPager.setAdapter(adapter);
        if(urlList.size() > 1) {
            viewPager.setCurrentItem(1, false);
        }
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {

            realPosition = position % adapter.getRealCount();
            Log.d(TAG, "onPageSelected position : " + position + ", currentPosition : " + realPosition);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

            if(state == ViewPager.SCROLL_STATE_IDLE) {

                int selectedPage = viewPager.getCurrentItem();
                int realCount = adapter.getRealCount();

                Log.d(TAG, "onItemSelected selectedPage : " + selectedPage + ", realCount : " + realCount);
                if(selectedPage == 0) {
                    viewPager.setCurrentItem(realCount, false);
                } else if(selectedPage > realCount) {
                    viewPager.setCurrentItem(1, false);
                }
            }
        }
    };

    private class TapGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {

            if(listener != null) {
                listener.onClink(urlList.get(realPosition));
            }

            return false;
        }
    }
    public interface ClickListener {
        void onClink(String url);
    }
}
