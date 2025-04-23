package com.example.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private static final String BAD_REVIEW = "Негативный";
    private static final String MID_REVIEW = "Нейтральный";
    private static final String GOOD_REVIEW = "Позитивный";


    private List<Review> reviewList = new ArrayList<>();

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false
        );
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        Review review = reviewList.get(position);

        holder.authorName.setText(review.getAuthor());
        holder.review.setText(review.getReview());

        String typeOfReview = review.getFilmType();

        int colorId = 0;

        switch (typeOfReview) {
            case GOOD_REVIEW:
                colorId = android.R.color.holo_green_light;
                break;
            case MID_REVIEW:
                colorId = android.R.color.holo_red_light;
                break;
            default:
                colorId = android.R.color.holo_orange_light;
        }

        int color = ContextCompat.getColor(holder.itemView.getContext(), colorId);
        holder.linearLayoutReview.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }


    static class ReviewViewHolder extends RecyclerView.ViewHolder{

        private final TextView authorName;
        private final TextView review;
        private final LinearLayout linearLayoutReview;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            this.authorName = itemView.findViewById(R.id.TextViewAuthorName);
            this.review = itemView.findViewById(R.id.TextViewReview);
            this.linearLayoutReview = itemView.findViewById(R.id.LinearLayoutReview);
        }
    }
}
