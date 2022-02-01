package com.imtiaz.movieslist.Listeners;

import com.imtiaz.movieslist.Model.MoviesInfoModel;

public interface OnSearchApiListener {
    void onResponse(MoviesInfoModel response);
    void onError(String message);
}
