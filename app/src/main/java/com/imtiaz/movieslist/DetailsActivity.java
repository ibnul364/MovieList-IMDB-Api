package com.imtiaz.movieslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.imtiaz.movieslist.Listeners.OnDetailsApiListener;
import com.imtiaz.movieslist.Model.MovieDetailsApiResponse;
import com.imtiaz.movieslist.RecyclerViewAdapter.DetailsCastRecyclerAdapter;
import com.imtiaz.movieslist.Retrofit.ApiController;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    TextView textView_movie_name,textView_movie_released_time,textView_movie_runtime,textView_movie_rating,textView_movie_votes,textView_movie_plot,textView_movie_trailer_link;
    ImageView imageView_movie_poster;
    RecyclerView recycler_movie_cast;
    DetailsCastRecyclerAdapter adapter;
    ApiController apiController;
    ProgressDialog dialog;
    WebView webView;
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
        textView_movie_trailer_link = findViewById(R.id.textView_movie_trailer_link);
        recycler_movie_cast = findViewById(R.id.recycler_movie_cast);
        webView = findViewById(R.id.webView);

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
        textView_movie_released_time.setText("In Theatre: "+ response.getYear());
        textView_movie_runtime.setText("Length: "+response.getLength());
        textView_movie_rating.setText("Rating: "+response.getRating());
        textView_movie_votes.setText(response.getRating_votes()+ " Votes");
        textView_movie_plot.setText(response.getPlot());
        textView_movie_trailer_link.setText(response.getTrailer().getLink());

        webView.setWebViewClient(new MyBrowser());
        textView_movie_trailer_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               webView.getSettings().setLoadsImagesAutomatically(true);
               webView.getSettings().setJavaScriptEnabled(true);
               webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
               webView.loadUrl(response.getTrailer().getLink());
            }
        });



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

    private class MyBrowser extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}