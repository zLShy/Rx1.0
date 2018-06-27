package map.zl.com.javaandroid;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Presenter.IMoviePresenterCompl;
import Presenter.LockPresenterCompl;
import Presenter.RunClassPresenterCompl;
import View.IRunClassView;
import View.IMovieView;
import View.ILockView;
import map.zl.com.javaandroid.Rx.RxDownLoadUtils;
import map.zl.com.javaandroid.Rx.RxUtils;
import map.zl.com.javaandroid.Utils.MyAdmin;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity implements IRunClassView,IMovieView,ILockView {

    private TextView mNo;
    private Button mSure;
    private RunClassPresenterCompl mRunClassPresenterCompl;
    private IMoviePresenterCompl mMoviePresenterCompl;
    private LockPresenterCompl mLockPresenterCompl;
    private DevicePolicyManager dpm;
    private ImageView mIv;

    private RxDownLoadUtils utils;
    private String PATH = "http://images.runmate2015.com/0000D759-549B-43DF-8678-3E73DDC39D3B";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mNo = (TextView)findViewById(R.id.shownotv);
        mSure = (Button)findViewById(R.id.getrunclassbtn);
        mIv = (ImageView) findViewById(R.id.iv);

        mRunClassPresenterCompl = new RunClassPresenterCompl(this,this);
        mMoviePresenterCompl = new IMoviePresenterCompl(this,this);
        mLockPresenterCompl = new LockPresenterCompl(this);
        dpm = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);

        utils = new RxDownLoadUtils();
//        RxUtils.CreateObservable();
//        RxUtils.CreateRxFun();
//        RxUtils.fromDemo();
//        RxUtils.justDemo();

//        RxUtils.range();

        utils.DownLoadImage(PATH).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<byte[]>() {
            @Override
            public void onCompleted() {
                Log.e("TGA","onCom");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TGA","ERROR");
            }

            @Override
            public void onNext(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                mIv.setImageBitmap(bitmap);
            }
        });
        RxUtils.filterDemo();
        mSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRunClassPresenterCompl.getResult("");

                mMoviePresenterCompl.getMovies(0,25);
            }
        });

        findViewById(R.id.getLock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mLockPresenterCompl.doLuck();
                ComponentName who = new ComponentName(Main2Activity.this, MyAdmin.class);
                if (dpm.isAdminActive(who)) {
                    dpm.lockNow();
                    dpm.resetPassword("1234",0);
                }else {
//                    Toast.makeText(Main2Activity.this, "请先开启管理员权限!", Toast.LENGTH_SHORT)
//                            .show();
                    openAdmin();
                }
            }
        });


    }

    @Override
    public void showNo(String no) {
        mNo.setText(no);
    }

    @Override
    public void LogMsg(String msg) {
        Log.e("TGA",msg);
    }

    @Override
    public void LockSreen() {
        Log.e("TGA","Lock!!!");
    }

    private void openAdmin() {
        // 创建一个Intent 添加设备管理员
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        // 激活MyAdmin广播接收着
        ComponentName who = new ComponentName(this, MyAdmin.class);

        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, who);
        // 说明用户开启管理员权限的好处
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                "开启可以一键锁屏，防止勿碰");
        startActivity(intent);

        Toast.makeText(Main2Activity.this, "管理员权限已开启!", Toast.LENGTH_SHORT).show();
    }
}
