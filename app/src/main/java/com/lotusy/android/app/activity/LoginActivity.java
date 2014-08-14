package com.lotusy.android.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lotusy.android.app.R;
import com.lotusy.android.app.wxapi.LotusyWechat;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendAuth;

public class LoginActivity extends Activity {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onFacebookClick(View v) {
        if(v.getId() == R.id.fbbutton){

        }
    }

    public void onWechatClick(View v) {
        if(v.getId() == R.id.webutton){
            LotusyWechat.init(this);

            final SendAuth.Req authReq = new SendAuth.Req();
            authReq.scope = "snsapi_userinfo";
            authReq.state = "weixin";
            LotusyWechat.api().sendReq(authReq);
        }
    }
}
