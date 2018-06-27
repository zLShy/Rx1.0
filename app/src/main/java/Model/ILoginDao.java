package Model;

/**
 * Created by zhangli on 2018/6/22.
 */

public interface ILoginDao {

    public void CheckUserRigth(String name,String pass,CallBack callBack);

    interface CallBack {
        public void onSuccess();
        public void onFail();
    }
}
