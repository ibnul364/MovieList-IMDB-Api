package com.imtiaz.movieslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.imtiaz.movieslist.Listeners.OnDetailsApiListener;
import com.imtiaz.movieslist.Model.MovieDetailsApiResponse;
import com.imtiaz.movieslist.RecyclerViewAdapter.DetailsCastRecyclerAdapter;
import com.imtiaz.movieslist.Retrofit.ApiController;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    TextView textView_movie_name,textView_movie_released_time,textView_movie_runtime,textView_movie_rating,textView_movie_votes,textView_movie_plot;
    ImageView imageView_movie_poster;
    RecyclerView recycler_movie_cast;
    DetailsCastRecyclerAdapter adapter;
    ApiController apiController;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textView_movie_name = findViewById(R.id.textView_movie_name);
        textView_movie_released_time = findViewById(R.id.textView_movie_released_time);
        textView_movie_runtime = findViewById(R.id.textView_movie_runtime);
        textView_movie_rating = findViewById(R.id.textView_movie_rating);
        textView_movie_votes = findViewById(R.id.textView_movie_votes);
        textView_movie_plot = findViewById(R.id.textView_movie_plot);
        imageView_movie_poster = findViewById(R.id.imageView_movie_poster);
        recycler_movie_cast = findViewById(R.id.recycler_movie_cast);

        apiController = new ApiController(this);

        //retrieve the data here
        String movie_id = getIntent().getStringExtra("dataId");

        dialog = new ProgressDialog(this);
        dialog.setTitle("Wait a moment");
        dialog.show();

        apiController.searchMovieDetails(listener,movie_id);
    }

    private final OnDetailsApiListener listener = new OnDetailsApiListener(){

        @Override
        public void onResponse(MovieDetailsApiResponse response) {
            dialog.dismiss();
            if(response.equals(null)){
                Toast.makeText(DetailsActivity.this, "Error response!!!", Toast.LENGTH_SHORT).show();
                return;
            }
            showResults(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(DetailsActivity.this, "Error!!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showResults(MovieDetailsApiResponse response) {
        textView_movie_name.setText(response.getTitle());
        textView_movie_released_time.setText("Released In: "+ response.getYear());
        textView_movie_runtime.setText("Length: "+response.getLength());
        textView_movie_rating.setText("Rating: "+response.getRating());
        textView_movie_votes.setText(response.getRating_votes()+ " Votes");
        textView_movie_plot.setText(response.getPlot());

        try{
            Picasso.get().load(response.getPoster()).into(imageView_movie_poster);
        }catch(Exception e){
            e.printStackTrace();
        }

        recycler_movie_cast.setHasFixedSize(true);
        recycler_movie_cast.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new DetailsCastRecyclerAdapter(this,response.getCast());
        recycler_movie_cast.setAdapter(adapter);

    }
}