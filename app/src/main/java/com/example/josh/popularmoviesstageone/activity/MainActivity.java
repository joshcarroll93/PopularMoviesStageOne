package com.example.josh.popularmoviesstageone.activity;


import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josh.popularmoviesstageone.R;
import com.example.josh.popularmoviesstageone.adapter.MovieAdapter;
import com.example.josh.popularmoviesstageone.data.Movie;
import com.example.josh.popularmoviesstageone.utils.JsonUtils;
import com.example.josh.popularmoviesstageone.utils.NetworkUtils;


import org.json.JSONException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));

        mRecyclerView = findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mMovieAdapter = new MovieAdapter(getApplicationContext(), new ArrayList<Movie>());
        mRecyclerView.setAdapter(mMovieAdapter);

        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        makeMovieSearchQuery(getString(R.string.popular_api));
    }

    private void makeMovieSearchQuery(String sortBy) {
        URL movieSearchUrl = NetworkUtils.buildUrl(sortBy);
        new MovieQuery().execute(movieSearchUrl);
    }

    private void showJsonDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.search_type, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.popular){

            Toast.makeText(getApplicationContext(),"Popular", Toast.LENGTH_LONG).show();
            makeMovieSearchQuery(getString(R.string.popular_api));

            return true;
        }
        if(id == R.id.top_rated){

            Toast.makeText(getApplicationContext(), "Top Rated", Toast.LENGTH_LONG).show();
            makeMovieSearchQuery(getString(R.string.top_rated_api));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("StaticFieldLeak")
    public class MovieQuery extends android.os.AsyncTask<URL, Void, String>{

        String TAG = "MOVIE_QUERY";


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
            Log.i(TAG, searchURL.toString());
            String movieResults = null;

            try{
                movieResults = NetworkUtils.getResponseFromHttpUrl(searchURL);
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
            return movieResults;
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
                    List<Movie> movieList = JsonUtils.getMovieStringsFromJson(movieResults);
                    mMovieAdapter.addItems(movieList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                showErrorMessage();
            }
        }
    }
}
