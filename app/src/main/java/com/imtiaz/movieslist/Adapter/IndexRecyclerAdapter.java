package com.imtiaz.movieslist.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.imtiaz.movieslist.Listeners.OnMovieClickListener;
import com.imtiaz.movieslist.Model.SearchMoviesModelObject;
import com.imtiaz.movieslist.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IndexRecyclerAdapter extends RecyclerView.Adapter<CustomViewHolder>{

    Context context;
    List<SearchMoviesModelObject> listOfObject;
    OnMovieClickListener listener;

    public IndexRecyclerAdapter(Context context, List<SearchMoviesModelObject> listOfObject, OnMovieClickListener listener) {
        this.context = context;
        this.listOfObject = listOfObject;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.index_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView_MovieName.setText(listOfObject.get(position).getTitle());
        holder.textView_MovieName.setSelected(true);
        Picasso.get().load(listOfObject.get(position).getImage()).into(holder.imageView_MoviePoster);

        holder.index_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnMovieClicked(listOfObject.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfObject.size();
    }
}

class CustomViewHolder extends RecyclerView.ViewHolder{

        CardView index_container;
        ImageView imageView_MoviePoster;
        TextView textView_MovieName;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            index_container = itemView.findViewById(R.id.index_container);
            imageView_MoviePoster = itemView.findViewById(R.id.imageView_MoviePoster);
            textView_MovieName = itemView.findViewById(R.id.textView_MovieName);

        }
    }




