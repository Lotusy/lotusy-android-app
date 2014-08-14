package com.lotusy.android.app.wxapi;

import android.content.Context;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by indochino on 2014-08-13.
 */
public class LotusyWechat {
    public static String WECHAT_KEY = "wx43c51d2eb8f9a4ed";
    private static IWXAPI api;

    public static void init(Context c) {
        if (api==null) {
            api = WXAPIFactory.createWXAPI(c, WECHAT_KEY, true);
            api.registerApp(WECHAT_KEY);
        }
    }

    public static IWXAPI api() {
        return api;
    }
}
