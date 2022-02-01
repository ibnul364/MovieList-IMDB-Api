package com.imtiaz.movieslist.Retrofit;

import android.content.Context;
import android.widget.Toast;

import com.imtiaz.movieslist.Listeners.OnDetailsApiListener;
import com.imtiaz.movieslist.Listeners.OnSearchApiListener;
import com.imtiaz.movieslist.Model.MovieDetailsApiResponse;
import com.imtiaz.movieslist.Model.SearchMoviesModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {

    Context mContext;
    private static final String BASE_URL = "https://imdb-internet-movie-database-unofficial.p.rapidapi.com/";
    private static ApiController controller;
    private static Retrofit retrofit;

    public ApiController(Context mContext) {
        this.mContext = mContext;
    }

    ApiController(){
        //Retrofit Object Creation
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiController getInstance(){
        if(controller == null){
            controller = new ApiController();
        }
        return controller;
    }

    public getMoviesApi getMoviesApi(){
        return retrofit.create(getMoviesApi.class);
    }

    public getMovieDetailsApi getMovieDetailsApi(){
        return retrofit.create(getMovieDetailsApi.class);
    }

    //method for Searching process the data
    public void searchMovies(OnSearchApiListener listener, String movie_name){
        Call<SearchMoviesModel> call = ApiController
                                        .getInstance()
                                        .getMoviesApi()
                                        .getMovies(movie_name);


        call.enqueue(new Callback<SearchMoviesModel>() {
            @Override
            public void onResponse(Call<SearchMoviesModel> call, Response<SearchMoviesModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(mContext, "couldn't fetch data!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<SearchMoviesModel> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });

    }

    //method for Searching movie details process the data
    public void searchMovieDetails(OnDetailsApiListener listener, String movie_id){
        Call<MovieDetailsApiResponse> call = ApiController
                .getInstance()
                .getMovieDetailsApi()
                .getMoviesDetails(movie_id);


        call.enqueue(new Callback<MovieDetailsApiResponse>() {
            @Override
            public void onResponse(Call<MovieDetailsApiResponse> call, Response<MovieDetailsApiResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(mContext, "couldn't fetch data!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetailsApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });

    }

}
