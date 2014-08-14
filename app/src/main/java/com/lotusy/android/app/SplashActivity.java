package com.lotusy.android.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lotusy.android.app.activity.LoginActivity;
import com.lotusy.android.app.activity.MainActivity;
import com.lotusy.android.sdk.AccountSDK;
import com.lotusy.android.sdk.LotusySDK;
import com.lotusy.android.sdk.domain.account.LotusyTokenAuthCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LotusySDK.setup("9D0E7CE8711F6F1CF87704557828A16E", LotusySDK.ENVIRONMENT.INT);

        setContentView(R.layout.activity_application);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String accessToken = sharedPref.getString("_access_token", null);
        if (accessToken==null) {
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        } else {
            final Activity activity = this;
            AccountSDK.tokenLogin(accessToken, new LotusyTokenAuthCallback() {
                @Override
                public void callback(LotusyTaskResult lotusyTaskResult) {
                if (lotusyTaskResult.isSuccess()) {
                    Intent main = new Intent(activity, MainActivity.class);
                    startActivity(main);
                }
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.application, menu);
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
}
