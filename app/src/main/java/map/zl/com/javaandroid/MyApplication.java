package map.zl.com.javaandroid;

import android.app.Application;

import com.aliyun.vod.common.httpfinal.QupaiHttpFinal;

/**
 * Created by zhangli on 2018/6/27.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        System.loadLibrary("live-openh264");
        System.loadLibrary("QuCore-ThirdParty");
        System.loadLibrary("QuCore");

        QupaiHttpFinal.getInstance().initOkHttpFinal();
        com.aliyun.vod.common.httpfinal.QupaiHttpFinal.getInstance().initOkHttpFinal();
    }
}
