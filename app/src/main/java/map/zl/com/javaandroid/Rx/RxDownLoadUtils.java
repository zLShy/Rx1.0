package map.zl.com.javaandroid.Rx;


import android.util.Log;

import java.io.IOException;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by zhangli on 2018/6/26.
 */

public class RxDownLoadUtils {

    private OkHttpClient mClient;
    public RxDownLoadUtils() {
        mClient = new OkHttpClient();
    }

    public  Observable<byte[]> DownLoadImage(String path) {

        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    Request request = new Request.Builder().url(path).build();
                    mClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                byte[] bytes = response.body().bytes();
                                if (bytes != null) {
                                    subscriber.onNext(bytes);
                                    Log.e("TGA",bytes.toString());
                                }
                            }
                            subscriber.onCompleted();
                        }
                    });
                }
            }
        });

    }
}
