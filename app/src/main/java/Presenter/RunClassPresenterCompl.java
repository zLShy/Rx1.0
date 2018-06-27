package Presenter;

import android.content.Context;

import Model.IRunClassNo;
import Model.RunClassNo;
import View.IRunClassView;

/**
 * Created by zhangli on 2018/6/22.
 */

public class RunClassPresenterCompl implements IRunClassPresenter {

    private IRunClassView mIRunClassView;
    private IRunClassNo mIRunClassNo;
    private Context mContext;

    public RunClassPresenterCompl(IRunClassView mIRunClassView,Context context) {
        this.mIRunClassView = mIRunClassView;
        this.mContext = context;
        mIRunClassNo = new RunClassNo(mContext);
    }

    @Override
    public void getResult(String guid) {
        mIRunClassNo.getNO(guid, new IRunClassNo.CallBack() {
            @Override
            public void onSuccess(String no) {
                mIRunClassView.showNo(no);
            }

            @Override
            public void onFail() {
                mIRunClassView.showNo("0");
            }
        });
    }
}
