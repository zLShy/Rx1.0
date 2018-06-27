package Model;

/**
 * Created by zhangli on 2018/6/22.
 */

public interface ITopMoive {
    public void getMovies(int start,int count,CallsBack callsBack);


    interface CallsBack{
        public void onSuccess(String msg);
        public void onFail();
    }
}
