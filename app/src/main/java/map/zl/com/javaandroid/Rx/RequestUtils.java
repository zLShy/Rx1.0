package map.zl.com.javaandroid.Rx;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by zhangli on 2018/6/26.
 */

public class RequestUtils {
    private OkHttpClient mClient;

    public RequestUtils() {
        mClient = new OkHttpClient();
    }

    public Observable<String> Login(String path, Map<String,String> map) {

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                if (!subscriber.isUnsubscribed()) {
                    FormBody.Builder builder = new FormBody.Builder();
                    if (map != null&&!map.isEmpty()) {
                        for (Map.Entry<String,String> entry:map.entrySet()) {
                            builder.add(entry.getKey(),entry.getValue());
                        }
                    }
                    RequestBody requestBody = builder.build();
                    Request request = new Request.Builder().url(path).post(requestBody).build();
                    mClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            if (response.isSuccessful()) {
                                subscriber.onNext(response.body().string());
                            }
                            subscriber.onCompleted();
                        }
                    });
                }
            }
        });

    }
}
