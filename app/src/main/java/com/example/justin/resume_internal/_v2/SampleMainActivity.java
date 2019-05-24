package com.example.justin.resume_internal._v2;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.justin.resume_internal.MainFragment;
import com.example.justin.resume_internal.R;

public class SampleMainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "SampleMainActivity";

    int curItem = 0;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_activity_layout1);
        textView = findViewById(R.id.textView);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/EB_Garamond/EBGaramond-Regular.ttf"));
        //        textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/Cardo/Cardo-Regular.ttf"));
//        textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/Crimson_Text/CrimsonText-Regular.ttf"));
        textView.setText(getResources().getString(R.string.background_information));
        textView.setTextColor(getResources().getColor(android.R.color.black));
        Log.d(LOG_TAG,"App is running");
        BottomNavigationView navigation = findViewById(R.id.bottomNav);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            sideDetector(curItem, item.getOrder());
            curItem = item.getOrder();
            MainFragment fragment = MainFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .commit();
//            Log.d(LOG_TAG, "Item selected");
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    return true;
//                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
//                    return true;
//                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
//                    return true;
//            }
            return true;
        }
    };

    void sideDetector(int cur, int sel){
        if (sel > cur){
            textView.setText("Shift Right");

        }else if (sel < cur) {
            textView.setText("Shift Left");
        }
    }
}
