package com.example.josh.popularmoviesstageone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josh.popularmoviesstageone.R;
import com.example.josh.popularmoviesstageone.data.Movie;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Josh on 13/12/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>  {

    private Context mContext;
    private List<Movie> mMovies;

    public MovieAdapter(Context context, List<Movie> movies){

        mContext = context;
        mMovies = movies;
    }

    @Override
    public int getItemCount() {
        if(mMovies != null)
            return mMovies.size();

        return 0;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }


    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {

        final int position = i;

        Picasso.with(mContext).load(mMovies.get(i).getPosterPath()).into(movieViewHolder.moviePoster);
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        public ImageView moviePoster;

        public MovieViewHolder(View itemView){
            super(itemView);

            moviePoster = itemView.findViewById(R.id.image_view);
        }
    }

}
