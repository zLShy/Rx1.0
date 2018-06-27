package Presenter;

import Model.ILoginDao;
import Model.LoginDao;
import View.ILoginView;

/**
 * Created by zhangli on 2018/6/22.
 */

public class LoginPresenterCompl implements ILoginPresenter {
    private ILoginDao mILoginDao;
    private ILoginView mILoginView;

    public LoginPresenterCompl(ILoginView mIoginView) {
        this.mILoginView = mIoginView;
        this.mILoginDao = new LoginDao();
    }

    @Override
    public void login() {
        mILoginView.showLoading();
        mILoginDao.CheckUserRigth(mILoginView.getName(), mILoginView.getPAss(), new ILoginDao.CallBack() {
            @Override
            public void onSuccess() {
                mILoginView.hideLoading();
                mILoginView.showSuccessMsg();
            }

            @Override
            public void onFail() {

                mILoginView.hideLoading();
                mILoginView.showErrorMsg();
            }
        });
    }
}
