package com.saint.bang.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.saint.bang.BaseActivity;
import com.saint.bang.R;
import com.saint.bang.main.MainActivity;


/**
 * Created by zzh on 16-1-20.
 */
public class SplashActivity extends BaseActivity {
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.class);
                overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
            }
        }, 5000);

    }


    @Override
    protected void setUpActionBar() {

    }

}

