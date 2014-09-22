package com.lotusy.android.app.weibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lotusy.android.app.R;
import com.lotusy.android.sdk.AccountSDK;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.account.LotusyTokenCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

public class WBAuthActivity extends Activity {

    private static WeiboAuth mWeiboAuth = null;
    private static SsoHandler mSsoHandler = null;
    private static Oauth2AccessToken mAccessToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wbauth);

        mWeiboAuth = new WeiboAuth(this, WeiboConstants.APP_KEY, WeiboConstants.REDIRECT_URL, WeiboConstants.SCOPE);
        mSsoHandler = new SsoHandler(WBAuthActivity.this, mWeiboAuth);

        mSsoHandler.authorize(new WeiboAuthListener() {
            @Override
            public void onComplete(Bundle bundle) {
                mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
                if (mAccessToken.isSessionValid()) {
                    String accessToken = mAccessToken.getToken();

                    AccountSDK.authenticate("weibo", accessToken, new LotusyTokenCallback() {
                        @Override
                        public void callback(LotusyTaskResult result, LotusyToken token) {
                            if (result.isSuccess()) {

                            }
                        }
                    });
                } else {
                    String code = bundle.getString("code", "");
                }
            }
            @Override
            public void onWeiboException(WeiboException e) {
                e.getCause();
            }
            @Override
            public void onCancel() {}
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wbauth, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
}
