package com.example.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private List<Trailer> trailerList = new ArrayList<>();
    private onTrailerClickListener onTrailerClickListener;

    public void setOnTrailerClickListener(TrailerAdapter.onTrailerClickListener onTrailerClickListener) {
        this.onTrailerClickListener = onTrailerClickListener;
    }

    public void setTrailerList(List<Trailer> trailerList) {
        this.trailerList = trailerList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.trailer_item,
                        parent,
                        false
                );
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        Trailer trailer = trailerList.get(position);
        holder.textViewTrailerName.setText(trailer.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTrailerClickListener != null) {
                    onTrailerClickListener.onTrailerClick(trailer);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    interface onTrailerClickListener{

        void onTrailerClick(Trailer trailer);

    }

    static class TrailerViewHolder extends RecyclerView.ViewHolder{

        private final TextView textViewTrailerName;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTrailerName = itemView.findViewById(R.id.TextViewTrailerName);
        }
    }
}
