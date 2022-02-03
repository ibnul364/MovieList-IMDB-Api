package com.imtiaz.movieslist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imtiaz.movieslist.R;

import java.util.List;

public class DetailsSpecRecyclerAdapter extends RecyclerView.Adapter<TechnicalSpecViewHolder> {
    Context context;
    List<List<String>> specList;

    public DetailsSpecRecyclerAdapter(Context context, List<List<String>> specList) {
        this.context = context;
        this.specList = specList;
    }

    @NonNull
    @Override
    public TechnicalSpecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TechnicalSpecViewHolder(LayoutInflater.from(context).inflate(R.layout.technical_specs_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TechnicalSpecViewHolder holder, int position) {
        holder.textView_ts_actor.setText(specList.get(position).get(0));
        holder.textView_ts_character.setText(specList.get(position).get(1));
    }

    @Override
    public int getItemCount() {
        return specList.size();
    }
}

class TechnicalSpecViewHolder extends RecyclerView.ViewHolder{
    TextView textView_ts_actor,textView_ts_character;
    public TechnicalSpecViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_ts_actor = itemView.findViewById(R.id.textView_ts_actor);
        textView_ts_character = itemView.findViewById(R.id.textView_ts_character);
    }
}
