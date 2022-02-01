package com.imtiaz.movieslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.imtiaz.movieslist.Listeners.OnMovieClickListener;
import com.imtiaz.movieslist.Listeners.OnSearchApiListener;
import com.imtiaz.movieslist.Model.SearchMoviesModel;
import com.imtiaz.movieslist.RecyclerViewAdapter.IndexRecyclerAdapter;
import com.imtiaz.movieslist.Retrofit.ApiController;

public class MainActivity extends AppCompatActivity implements OnMovieClickListener {

    SearchView search_Movies;
    RecyclerView recycler_view_index;
    IndexRecyclerAdapter indexRecyclerAdapter;
    ApiController apiController;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_Movies = findViewById(R.id.search_Movies);
        recycler_view_index = findViewById(R.id.recycler_view_index);

        dialog = new ProgressDialog(this);
        apiController = new ApiController(this);

        search_Movies.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Wait a minute to find");
                dialog.show();
                apiController.searchMovies(onSearchApiListener,query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private  final OnSearchApiListener  onSearchApiListener = new OnSearchApiListener(){

        @Override
        public void onResponse(SearchMoviesModel response) {
            dialog.dismiss();
            if(response == null){
                Toast.makeText(MainActivity.this, "No Data is Available", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "An Error has been occurred", Toast.LENGTH_SHORT).show();
        }
    } ;

    private void showResult(SearchMoviesModel response) {
        recycler_view_index.setHasFixedSize(true);
        recycler_view_index.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        indexRecyclerAdapter = new IndexRecyclerAdapter(this,response.getTitles(),this);
        recycler_view_index.setAdapter(indexRecyclerAdapter);
    }

    @Override
    public void OnMovieClicked(String id) {
        startActivity(new Intent(MainActivity.this,DetailsActivity.class).putExtra("dataId",id));
    }
}