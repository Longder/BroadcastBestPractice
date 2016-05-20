package com.longder.broadcastbestpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Longder on 2016/5/18.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }
}
