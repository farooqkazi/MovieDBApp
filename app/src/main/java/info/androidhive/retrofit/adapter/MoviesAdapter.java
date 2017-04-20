package info.androidhive.retrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.androidhive.retrofit.R;
import info.androidhive.retrofit.model.Movie;
import info.androidhive.retrofit.model.MoviesResponse;
import info.androidhive.retrofit.rest.ApiClient;
import info.androidhive.retrofit.rest.ApiInterface;
import info.androidhive.retrofit.utilities.ItemClickListener;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {





    private List<Movie> movies;
    String title, description;
    private int rowLayout;
    int size=0;
    private Context context;
    CompositeSubscription compositeSubscription= new CompositeSubscription();

    private final static String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";
    private ApiInterface apiService =
            ApiClient.getClient().create(ApiInterface.class);
    /**
     * 3 Implement interface: this will create two methods click and long click
     */
    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        //LinearLayout moviesLayout;
        //TextView movieTitle;
        //TextView data;
        //TextView movieDescription;
        //TextView rating;

        @BindView(R.id.movies_layout) LinearLayout moviesLayout;
        @BindView(R.id.rating) TextView rating;
        @BindView(R.id.subtitle) TextView data;
        @BindView(R.id.description) TextView movieDescription;
        @BindView(R.id.title) TextView movieTitle;

        /**
         * 1 Add itemClickListerner Interface
         * 2 Create object
         */
        private ItemClickListener clickListener;



        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        /**
         * 4 click methods
         * @param view
         */


        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
        public MovieViewHolder(View v) {
            super(v);

            ButterKnife.bind(this,itemView);// Enable ButterKnife


            //moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            //movieTitle = (TextView) v.findViewById(R.id.title);
            //data = (TextView) v.findViewById(R.id.subtitle);
            //movieDescription = (TextView) v.findViewById(R.id.description);
            //rating = (TextView) v.findViewById(R.id.rating);

            /**
             * 5 Most important: add Listener
             */
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }



    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder,  int position) {

              holder.movieTitle.setText(movies.get(position).getTitle());
              holder.data.setText(movies.get(position).getReleaseDate());
              holder.movieDescription.setText(movies.get(position).getOverview());
              holder.rating.setText(movies.get(position).getVoteAverage().toString());


        Toast.makeText(context.getApplicationContext(),"onCompleted"+ movies.get(position).getId(),Toast.LENGTH_LONG).show();


        /**
         * 6 Call click here
         */
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, final int position, boolean isLongClick) {
                if(isLongClick){

                }else{
                    //clicked
                    compositeSubscription.add((apiService.getMovieDetails(movies.get(position).getId(),API_KEY)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<MoviesResponse>() {
                                @Override
                                public void onCompleted() {
                                    Toast.makeText(context.getApplicationContext(),"onCompleted",Toast.LENGTH_LONG).show();

                                }

                                @Override
                                public void onError(Throwable e) {
                                  //  Toast.makeText(context.getApplicationContext(),"error"+ e.getMessage(),Toast.LENGTH_LONG).show();

                                }

                                @Override
                                public void onNext(MoviesResponse moviesResponse) {
                                    Log.i("MoviesResponse","  "+ moviesResponse.getResults().get(position).getTitle());
                                    Toast.makeText(context.getApplicationContext(),""+ moviesResponse.getResults().get(position).getTitle(),Toast.LENGTH_LONG).show();
                                }
                            })));
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}