package com.science.app.sciencelab;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

public class App extends Application {
    @Override
    public void onCreate() {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale("ar".toLowerCase()));
        res.updateConfiguration(conf, dm);
        super.onCreate();
    }
}
