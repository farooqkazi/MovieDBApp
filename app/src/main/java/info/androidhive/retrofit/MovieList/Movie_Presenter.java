package info.androidhive.retrofit.MovieList;

import java.util.List;

import javax.inject.Inject;

import info.androidhive.retrofit.UseCase.Interacter;
import info.androidhive.retrofit.model.Movie;
import info.androidhive.retrofit.model.MoviesResponse;
import info.androidhive.retrofit.service.Constants;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Kazi on 19/Apr/17.
 */

public class Movie_Presenter implements IMoviesContract.IPresenter {

    Interacter interacter;
    IMoviesContract.IView iView;

    @Inject //Dagger Step 6
    public Movie_Presenter(Interacter interacter) {
        this.interacter = interacter;

    }

    @Override
    public void bindView(IMoviesContract.IView view) {
        this.iView = view;
    }

    @Override
    public void unbindView() {

       iView = null;

    }

    @Override
    public void callMoviesInteracter() {

        if (Constants.API_KEY.isEmpty()){

        }
        interacter.getMoviesUseCase().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MoviesResponse moviesResponse) {
                        List<Movie> movies = moviesResponse.getResults();


                        iView.showDataInRecyclerView(movies);

                    }
                });






    }
}
