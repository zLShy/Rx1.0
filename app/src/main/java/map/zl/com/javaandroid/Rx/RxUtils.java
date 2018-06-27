package map.zl.com.javaandroid.Rx;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhangli on 2018/6/26.
 */

public class RxUtils {
    public static String TGA = RxUtils.class.getSimpleName();

    public static void CreateObservable() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("hh");
                    subscriber.onNext("aa");
                    subscriber.onNext(getInfo());

                    subscriber.onCompleted();
                }
            }
        });

        Subscriber<String> stringSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e("TGA","complete");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("TGA",s);
            }
        };

        observable.subscribe(stringSubscriber);
    }

    public static String getInfo() {
        return "JSON DATA";
    }

    public static void CreateRxFun() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i =  0;i<5;i++) {
                        subscriber.onNext(String.valueOf(i));
                    }
                }

                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e("TGA","OnCom");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("TGA",s);
            }
        });
    }


    /**
     * 返回数值类型
     */

    public static void fromDemo() {
        Integer[] integers = {1,2,3,4,5,6,7,8,9};
        Observable observable = Observable.from(integers);

        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.e(TGA,o.toString());
            }
        });
    }
    /**
     * 指定间隔跳用数据
     */
    public static void interval() {
        Integer[] items = {1,2,3,4};
        Observable observable = Observable.interval(1,1, TimeUnit.SECONDS);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {

            }
        });
    }

    /**
     * just 方法
     */

    public static void justDemo() {
        Integer[] items = {1,2,3,4};
        Integer[] items2 = {1,2,3,4,5,6,7};
        Observable observable = Observable.just(items,items2);
       observable.subscribe(new Subscriber<Integer[]>() {

           @Override
           public void onCompleted() {
               Log.e(TGA,"onCom");
           }

           @Override
           public void onError(Throwable e) {
            Log.e("TGA","ERROR");
           }

           @Override
           public void onNext(Integer[] integers) {
               for(int i=0;i<integers.length;i++){
                   Log.e(TGA,integers[i]+"");
               }
           }
       });
    }

    public static void range() {
        Observable observable = Observable.range(1,20);
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e(TGA,"onCom");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer o) {
                Log.e(TGA,o+"");
            }
        });
    }

    /**
     *
     * RX过滤
     */
    public  static void filterDemo() {
        Integer[] items = {1,2,3,4,5,6,7,8};

        Observable observable = Observable.just(items);
        observable.filter(new Func1<Integer,Boolean>() {

            @Override
            public Boolean call(Integer integer) {
                return integer<5;
            }
        }).observeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

                Log.e(TGA,"onCom");
            }

            @Override
            public void onError(Throwable e) {

                Log.e(TGA,"ERROR");
            }

            @Override
            public void onNext(Integer o) {
                Log.e(TGA,o.toString());
            }
        });
    }
}
