package com.imtiaz.movieslist.RecyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imtiaz.movieslist.Model.Cast;
import com.imtiaz.movieslist.R;

import java.util.List;

public class DetailsCastRecyclerAdapter extends RecyclerView.Adapter<CastViewHolder> {
    Context context;
    List<Cast> list;

    public DetailsCastRecyclerAdapter(Context context, List<Cast> cast) {
        this.context = context;
        this.list = cast;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CastViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_cast_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        holder.textView_actor.setText(list.get(position).getActor());
        holder.textView_character.setText(list.get(position).getCharacter());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class CastViewHolder extends RecyclerView.ViewHolder{
    TextView textView_actor,textView_character;
    public CastViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_actor = itemView.findViewById(R.id.textView_actor);
        textView_character = itemView.findViewById(R.id.textView_character);
    }
}
