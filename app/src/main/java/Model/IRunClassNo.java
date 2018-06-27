package Model;

/**
 * Created by zhangli on 2018/6/22.
 */

public interface IRunClassNo {
    public void getNO(String guid,CallBack callBack);

    interface CallBack{
        public void onSuccess(String no);
        public void onFail();
    }
}
