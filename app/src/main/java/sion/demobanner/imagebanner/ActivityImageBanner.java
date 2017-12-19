package sion.demobanner.imagebanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import sion.demobanner.R;

public class ActivityImageBanner extends AppCompatActivity {

    private BannerView bannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_banner);
        initControl();
    }

    private void initControl() {

        bannerView = (BannerView) findViewById(R.id.bannerView);
        bannerView.setBannerView(getImageUrlList(), new BannerView.ClickListener() {
            @Override
            public void onClink(String url) {

            }
        });

    }

    private ArrayList<String> getImageUrlList() {

        ArrayList<String> list = new ArrayList<>();
        list.add("https://storage.googleapis.com/material-design/publish/material_v_12/assets/0Bx4BSt6jniD7QTA5cHFBUlV3RTA/materialdesign-goals-language.png");
        list.add("https://storage.googleapis.com/material-design/publish/material_v_12/assets/0Bx4BSt6jniD7c0R4RUtiTkhMZTQ/materialdesign-goals-system.png");

        return list;
    }


}
