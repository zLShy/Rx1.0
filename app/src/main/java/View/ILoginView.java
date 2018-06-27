package View;

/**
 * Created by zhangli on 2018/6/22.
 */

public interface ILoginView {
    /**
     *
     */
    public String getName();

    public String getPAss();


    public void showSuccessMsg();

    public void showErrorMsg();

    public void showLoading();

    public void hideLoading();
}
