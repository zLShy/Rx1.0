package Model;

import android.os.Handler;
import android.os.Message;

/**
 * Created by zhangli on 2018/6/22.
 */

public class LoginDao implements ILoginDao{
    private CallBack mCallBack;
    public static int sCount = 0;
    @Override
    public void CheckUserRigth(String name, String pass, CallBack callBack) {

        this.mCallBack = callBack;

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (sCount++ %2 == 0) {
                    mHandler.sendEmptyMessageDelayed(1,3000);
                }else {
                    mHandler.sendEmptyMessageDelayed(2,3000);
                }
            }
        }).start();
    }



    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mCallBack.onSuccess();
                    break;
                case 2:
                    mCallBack.onFail();
                    break;
            }
        }
    };
}
