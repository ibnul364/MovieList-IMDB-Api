package com.imtiaz.movieslist.Listeners;

import com.imtiaz.movieslist.Model.SearchMoviesModel;

public interface OnSearchApiListener {
    void onResponse(SearchMoviesModel response);
    void onError(String message);
}
