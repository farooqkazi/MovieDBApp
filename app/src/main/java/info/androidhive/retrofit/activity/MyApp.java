package info.androidhive.retrofit.activity;

import android.app.Application;
import android.content.Context;

import info.androidhive.retrofit.MovieList.Injection.DaggerMovieList_Component;
import info.androidhive.retrofit.MovieList.Injection.MovieList_Component;

/**
 * Created by kazi on 14/06/2016.
 */
public class MyApp extends Application {


    public MovieList_Component getMovieList_component() {
        return movieList_component;
    }

    private static Context context;

    MovieList_Component movieList_component;

    @Override
    public void onCreate() {
        super.onCreate();


        MyApp.context = getApplicationContext();
        movieList_component= DaggerMovieList_Component.create();


    }

    public static Context getAppContext() {
        return MyApp.context;
    }


}


