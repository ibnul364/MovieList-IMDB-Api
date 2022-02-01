package com.imtiaz.movieslist.Listeners;

import com.imtiaz.movieslist.Model.MovieDetailsApiResponse;

public interface OnDetailsApiListener {
    void onResponse(MovieDetailsApiResponse response);
    void onError(String message);
}
