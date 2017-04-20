package info.androidhive.retrofit.MovieList;

import java.util.List;

import info.androidhive.retrofit.model.Movie;

/**
 * Created by Kazi on 19/Apr/17.
 */

public interface IMoviesContract {

    interface IView{
        void showDataInRecyclerView(List<Movie> movies);
        void showToastMessage();

    }

    interface IPresenter{
        void bindView(IView view);
        void unbindView();
        void callMoviesInteracter();
    }
}
