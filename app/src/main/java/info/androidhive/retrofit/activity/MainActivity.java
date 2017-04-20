package info.androidhive.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import info.androidhive.retrofit.MovieList.IMoviesContract;
import info.androidhive.retrofit.MovieList.Movie_Presenter;
import info.androidhive.retrofit.R;
import info.androidhive.retrofit.UseCase.Interacter;
import info.androidhive.retrofit.adapter.MoviesAdapter;
import info.androidhive.retrofit.model.Movie;
import info.androidhive.retrofit.model.MoviesResponse;
import info.androidhive.retrofit.rest.ApiClient;
import info.androidhive.retrofit.rest.ApiInterface;
import info.androidhive.retrofit.utilities.RxUtils;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class MainActivity extends AppCompatActivity implements IMoviesContract.IView {

    private Unbinder unbinder;


    //Dagger Step 8
    @Inject Movie_Presenter movie_presenter;


    //Dagger Step 7
    @Inject
    public void setMovie_presenter(Movie_Presenter movie_presenter){
        this.movie_presenter = movie_presenter;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        movie_presenter.unbindView();
        unbinder.unbind();
    }


    //  implements IMovieListContract.IView{

    RecyclerView recyclerView;
    //Interacter interacter;
    //Movie_Presenter movie_presenter;


    CompositeSubscription compositeSubscription= new CompositeSubscription();
    /**
     * Can be replaced by mock API interface
     */
    private ApiInterface apiService =
            ApiClient.getClient().create(ApiInterface.class);
    @Override
    public void onResume(){

    super.onResume();

        RxUtils.getNewCompositeSubIfUnsubscribed(compositeSubscription);
        // cakeListImplPresenter.start();
}
    @Override
    public void onStop(){
        super.onStop();

        RxUtils.unsubscribeIfNotNull(compositeSubscription);
        // cakeListImplPresenter.start();
    }



    private static final String TAG = MainActivity.class.getSimpleName();


    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        //interacter = new ApiClient();
        //movie_presenter = new Movie_Presenter(interacter);


        ((MyApp)getApplication()).getMovieList_component().inject(this);// Dagger Step 5



        movie_presenter.bindView(this);
        //cakeListImplPresenter= new CakeListPresenter_Impl(this);

         recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));

            movie_presenter.callMoviesInteracter();


        //TRADITIONAL RETROFIT
//        apiService.getCakes(new Callback<MoviesResponse>() {
//            @Override
//            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<MoviesResponse> call, Throwable t) {
//
//            }
//        })


       /* compositeSubscription.add(apiService.getTopRatedMovies(API_KEY)
                .subscribeOn(Schedulers.io())
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

                        //recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                    }
                })); */

//
//        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
//        call.enqueue(new Callback<MoviesResponse>() {
//            @Override
//            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
//                int statusCode = response.code();
//                List<Movie> movies = response.body().getResults();
//                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
//            }
//
//            @Override
//            public void onFailure(Call<MoviesResponse> call, Throwable t) {
//                // Log error here since request failed
//                Log.e(TAG, t.toString());
//            }
//        });
//    }
    }

    @Override
    public void showDataInRecyclerView(List<Movie> movies) {

        recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));

    }

    @Override
    public void showToastMessage() {

        Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();

    }
}
