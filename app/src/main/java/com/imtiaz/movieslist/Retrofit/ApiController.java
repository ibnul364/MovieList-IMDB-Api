package com.imtiaz.movieslist.Retrofit;

import android.content.Context;
import android.widget.Toast;

import com.imtiaz.movieslist.Listeners.OnSearchApiListener;
import com.imtiaz.movieslist.Model.MoviesInfoModel;

import java.util.List;

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

    public moviesApi getMoviesApi(){
        return retrofit.create(moviesApi.class);
    }

    //method for process the data
    public void searchMovies(OnSearchApiListener listener, String movie_name){
        Call<MoviesInfoModel> call = ApiController
                                        .getInstance()
                                        .getMoviesApi()
                                        .getMovies(movie_name);


        call.enqueue(new Callback<MoviesInfoModel>() {
            @Override
            public void onResponse(Call<MoviesInfoModel> call, Response<MoviesInfoModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(mContext, "couldn't fetch data!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<MoviesInfoModel> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });

    }



}
