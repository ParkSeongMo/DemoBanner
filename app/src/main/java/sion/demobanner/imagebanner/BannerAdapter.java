package sion.demobanner.imagebanner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import sion.demobanner.R;

public class BannerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<String> list;
    private List<String> displayList = new ArrayList<>();

    public BannerAdapter(Context context, ArrayList<String> list) {

        this.context = context;
        this.list = list;
        this.displayList.addAll(list);
        setLoopingAdapter();
    }

    private void setLoopingAdapter() {

        if (list.size() < 2) {
            return;
        }

        displayList.add(list.get(0));
        displayList.add(0, list.get(list.size() - 1));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        int realPosition = (position) % getRealCount();
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_banner_item, container, false);
        ImageView ivBanner = (ImageView) itemView.findViewById(R.id.ivBanner);
        String imgUrl = displayList.get(realPosition);

        Glide.with(context).load(imgUrl).asBitmap().into(ivBanner);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public int getCount() {
        return displayList.size();
    }

    public int getRealCount() {
        return list.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}
