package info.androidhive.retrofit.UseCase;

import java.util.Observable;

import info.androidhive.retrofit.model.MoviesResponse;

/**
 * Created by Kazi on 19/Apr/17.
 */

public interface Interacter {

    rx.Observable<MoviesResponse> getMoviesUseCase();
}
