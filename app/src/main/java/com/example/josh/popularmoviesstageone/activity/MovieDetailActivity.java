package com.example.josh.popularmoviesstageone.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josh.popularmoviesstageone.R;
import com.example.josh.popularmoviesstageone.data.Movie;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MovieDetailActivity extends AppCompatActivity {

    private Movie mMovie;
    private Context mContext;
    private ImageView mMoviePoster;
    private TextView mTitle;
    private TextView mOverview;
    private TextView mReleaseDate;
    private TextView mMovieRating;

//    public MovieDetailActivity(Context context, Movie movie){
//
//        mContext = context;
//        mMovie = movie;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if(getIntent().getExtras() != null){
            mMovie = getIntent().getParcelableExtra("movie");
        }

        mMoviePoster = findViewById(R.id.id_detail_poster);
        mTitle = findViewById(R.id.id_title);
        mOverview = findViewById(R.id.id_overview);
        mReleaseDate = findViewById(R.id.id_release_date);
        mMovieRating = findViewById(R.id.id_rating);

        Picasso.with(getApplicationContext()).load(mMovie.getPosterPath())
                .into(mMoviePoster);
        mTitle.setText(mMovie.getTitle());
        mOverview.setText(mMovie.getOverView());
        mReleaseDate.setText(mMovie.getReleaseDate());
        mMovieRating.setText(mMovie.getVoteAverage());
    }
}
