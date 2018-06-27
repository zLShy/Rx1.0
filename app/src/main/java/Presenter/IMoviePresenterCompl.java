package Presenter;

import android.content.Context;

import Model.ITopMoive;
import Model.TopMovie;
import View.IMovieView;

/**
 * Created by zhangli on 2018/6/22.
 */

public class IMoviePresenterCompl implements IMoviePresenter {

    private IMovieView mIMovieView;
    private TopMovie mTopMovie;

    public IMoviePresenterCompl(IMovieView mIMovieView, Context context) {
        this.mIMovieView = mIMovieView;
        mTopMovie = new TopMovie(context);
    }

    @Override
    public void getMovies(int start, int count) {
        mTopMovie.getMovies(start, count, new ITopMoive.CallsBack() {
            @Override
            public void onSuccess(String msg) {
                mIMovieView.LogMsg(msg);
            }

            @Override
            public void onFail() {

            }
        });
    }
}
