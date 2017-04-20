package info.androidhive.retrofit.rest;

import info.androidhive.retrofit.model.MoviesResponse;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiInterface {

    @GET("")
    public void getCakes(Callback<MoviesResponse> responseCallback);

//http://api.themoviedb.org/3/movie/top_rated?api_key=7e8f60e325cd06e164799af1e317d7a7


    @GET("movie/top_rated")
    Observable<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);


    //http://api.themoviedb.org/3/movie/550?api_key=7e8f60e325cd06e164799af1e317d7a7
    @GET("movie/{id}")
    Observable<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}
