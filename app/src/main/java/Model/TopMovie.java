package Model;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhangli on 2018/6/22.
 */

public class TopMovie implements ITopMoive {
    private CallsBack mCallsBack;
    private Context mContext;

    public TopMovie(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getMovies(int start, int count, CallsBack callsBack) {

        this.mCallsBack = callsBack;

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(mContext, "http://api.douban.com/v2/movie/top250?start="+start+"&count="+count, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                mCallsBack.onSuccess(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                mCallsBack.onFail();
            }
        });
    }
}
