package com.example.josh.popularmoviesstageone;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.josh.popularmoviesstageone.adapter.MovieAdapter;
import com.example.josh.popularmoviesstageone.data.Movie;
import com.example.josh.popularmoviesstageone.utils.JsonUtils;
import com.example.josh.popularmoviesstageone.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();

    List<Movie> movieList;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mLayoutManager = new GridLayoutManager(this, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mMovieAdapter = new MovieAdapter(getApplicationContext(), movieList);
//        mRecyclerView.setAdapter(mMovieAdapter);

        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        makeMovieSearchQuery();
    }

    private void makeMovieSearchQuery() {

        URL githubSearchUrl = NetworkUtils.buildUrl();
        new MovieQuery().execute(githubSearchUrl);
    }

    private void showJsonDataView() {
        // First, make sure the error is invisible
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        // Then, make sure the JSON data is visible
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        // First, hide the currently visible data
        mRecyclerView.setVisibility(View.INVISIBLE);
        // Then, show the error
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    public class MovieQuery extends android.os.AsyncTask<URL, Void, String>{

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "in onPreExecute");
            mLoadingIndicator.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            Log.d(TAG, "in doInBackground");
            URL searchURL = urls[0];
            String movieQuery = null;

            try{
                movieQuery = NetworkUtils.getResponseFromHttpUrl(searchURL);
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
            return movieQuery;
        }

        @Override
        protected void onPostExecute(String movieResults) {
            Log.d(TAG, "in onPostExecute");
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
            if (movieResults != null && !movieResults.equals("")) {
                showJsonDataView();
                Log.d(TAG, movieResults);
                
                try {
                    movieList = JsonUtils.getMovieStringsFromJson(getApplicationContext(), movieResults);
                    mMovieAdapter = new MovieAdapter(getApplicationContext(), movieList);
                    mRecyclerView.setAdapter(mMovieAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                showErrorMessage();
            }
        }
    }
}
