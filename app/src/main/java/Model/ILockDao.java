package Model;

/**
 * Created by zhangli on 2018/6/25.
 */

public interface ILockDao {
    public void LockScreen(CallBack callBack);

    interface CallBack {
        void sendLock();
    }
}
