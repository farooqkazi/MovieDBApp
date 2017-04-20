package info.androidhive.retrofit.MovieList.Injection;

import dagger.Component;
import info.androidhive.retrofit.activity.MainActivity;

/**
 * Created by Kazi on 19/Apr/17.
 */

//Dagger Step 3
@Component(dependencies = MovieList_Module.class)
public interface MovieList_Component {

    //Where
    void inject(MainActivity mainActivity);

}


