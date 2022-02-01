package com.imtiaz.movieslist.Retrofit;

import com.imtiaz.movieslist.Model.MoviesInfoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface moviesApi {
    @Headers({
            "Accept: application/json",
            "x-rapidapi-host: imdb-internet-movie-database-unofficial.p.rapidapi.com",
            "x-rapidapi-key: ea93cd3c68msh02daf2d1d59356ep184e19jsn9572b39298ae"
    })

    @GET("search/{movie_name}")
    Call<MoviesInfoModel> getMovies(
            @Path("movie_name") String movie_name
    );

}
