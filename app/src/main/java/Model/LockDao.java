package Model;

/**
 * Created by zhangli on 2018/6/25.
 */

public class LockDao implements ILockDao {
    @Override
    public void LockScreen(CallBack callBack) {
        callBack.sendLock();
    }
}
