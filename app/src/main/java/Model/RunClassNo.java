package Model;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import map.zl.com.javaandroid.Utils.Tool;

/**
 * Created by zhangli on 2018/6/22.
 */

public class RunClassNo implements IRunClassNo {
    private CallBack mCallBack;
    private Context mContext;
    @Override
    public void getNO(String guid, final CallBack callBack) {
        this.mCallBack = callBack;
        String time = Tool.stampToDate(String.valueOf(System.currentTimeMillis()))+" 00:00";

        String url = "http://runmate.runtogether.cn/runmate-paoban/v1/paoban/count?startTime="+Tool.dataOne(time)+"&endTime="+String.valueOf(Long.valueOf(Tool.dataOne(time)+604800));
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(mContext,url,new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String body = new String(responseBody);
                Log.e("TGA",body);
                mCallBack.onSuccess("success");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                mCallBack.onFail();
            }
        });
    }

    public RunClassNo(Context mContext) {
        this.mContext = mContext;
    }
}
