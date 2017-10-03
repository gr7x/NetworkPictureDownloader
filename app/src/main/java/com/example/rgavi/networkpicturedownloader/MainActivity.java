package com.example.rgavi.networkpicturedownloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    final static String DYERS_URL = "http://www.ibiblio.org/wm/paint/auth/hiroshige/dyers.jpg";
    final static String MOON_PINE_URL = "http://www.ibiblio.org/wm/paint/auth/hiroshige/moonpine.jpg";
    final static String PLUM_ESTATE_URL = "http://www.ibiblio.org/wm/paint/auth/hiroshige/plum.jpg";
    final static String USHIMACHI_URL = "http://www.ibiblio.org/wm/paint/auth/hiroshige/takanawa.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
