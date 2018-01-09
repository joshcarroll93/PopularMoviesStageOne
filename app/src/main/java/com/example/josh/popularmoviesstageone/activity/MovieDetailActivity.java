package com.example.josh.popularmoviesstageone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josh.popularmoviesstageone.R;
import com.example.josh.popularmoviesstageone.data.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if(getIntent().getExtras() != null){
            mMovie = getIntent().getParcelableExtra("movie");
        }

        ImageView mMoviePoster = findViewById(R.id.id_detail_poster);
        TextView mTitle = findViewById(R.id.id_title);
        TextView mOverview = findViewById(R.id.id_overview);
        TextView mReleaseDate = findViewById(R.id.id_release_date);
        TextView mMovieRating = findViewById(R.id.id_rating);

        Picasso.with(getApplicationContext()).load(mMovie.getPosterPath())
                .into(mMoviePoster);
        mTitle.setText(mMovie.getTitle());
        mOverview.setText(mMovie.getOverView());
        mReleaseDate.setText(mMovie.getReleaseDate());
        mMovieRating.setText(mMovie.getVoteAverage());
    }
}
