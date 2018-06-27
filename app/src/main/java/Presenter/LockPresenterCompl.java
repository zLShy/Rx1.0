package Presenter;

import Model.ILockDao;
import Model.LockDao;
import View.ILockView;

/**
 * Created by zhangli on 2018/6/25.
 */

public class LockPresenterCompl implements ILockPresenter {
    private ILockView mLockView;
    private ILockDao mILockDao;

    public LockPresenterCompl(ILockView mLockView) {
        this.mLockView = mLockView;
        this.mILockDao = new LockDao();
    }

    @Override
    public void doLuck() {
        mILockDao.LockScreen(new ILockDao.CallBack() {
            @Override
            public void sendLock() {
                mLockView.LockSreen();
            }
        });
    }
}
