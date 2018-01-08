package com.example.josh.popularmoviesstageone.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.josh.popularmoviesstageone.R;
import com.example.josh.popularmoviesstageone.activity.MovieDetailActivity;
import com.example.josh.popularmoviesstageone.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Josh on 13/12/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private List<Movie> mMovies;

    public MovieAdapter(Context context, List<Movie> movies){

        mContext = context;
        mMovies = movies;
    }

    public void addItems(List<Movie> newMovies){
        if(mMovies != null){
            mMovies.clear();
            mMovies.addAll(newMovies);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mMovies != null)
            return mMovies.size();

        return 0;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(TAG, "Creating a view for the movie");
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder movieViewHolder, int position) {

        movieViewHolder.moviePoster.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher));
        Log.i(TAG, mMovies.get(position).getPosterPath());
        Picasso.with(mContext).load(mMovies.get(position).getPosterPath())
                .resize(185, 277)
                .placeholder(Drawable.createFromPath(TAG))
                .into(movieViewHolder.moviePoster);

        movieViewHolder.moviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context viewContext = view.getContext();
                Intent intent = new Intent(viewContext, MovieDetailActivity.class);
                intent.putExtra("movie", mMovies.get(movieViewHolder.getAdapterPosition()));
                viewContext.startActivity(intent);
            }
        });
    }

     static class MovieViewHolder extends RecyclerView.ViewHolder{

        private ImageView moviePoster;

        private MovieViewHolder(View itemView){
            super(itemView);
            moviePoster = itemView.findViewById(R.id.image_view);
        }
    }
}
