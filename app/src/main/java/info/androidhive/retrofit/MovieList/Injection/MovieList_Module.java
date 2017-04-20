package info.androidhive.retrofit.MovieList.Injection;

import dagger.Module;
import dagger.Provides;
import info.androidhive.retrofit.UseCase.Interacter;
import info.androidhive.retrofit.rest.ApiClient;

/**
 * Created by Kazi on 19/Apr/17.
 */
//Which

@Module // Dagger Step 1
public class MovieList_Module {

    // Dagger Step 2
    @Provides Interacter getMovieListModule(){

        return new ApiClient();
    }


}
