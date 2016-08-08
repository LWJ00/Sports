package com.hzuapp.team.sports.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.hzuapp.team.sports.R;
import com.hzuapp.team.sports.base.BaseActivity;

public class WelcomeActivity extends Activity {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        autoLogin();

    }

    private void autoLogin() {
        handler.sendEmptyMessageDelayed(1,1000);
    }


}
