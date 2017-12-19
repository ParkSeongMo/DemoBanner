package sion.demobanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import sion.demobanner.imagebanner.ActivityImageBanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControl();
    }

    private void initControl() {

        Button btnImageBanner = (Button) findViewById(R.id.btnImageBanner);
        btnImageBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ActivityImageBanner.class);
            }
        });
    }

    private void startActivity(Class cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }

}
