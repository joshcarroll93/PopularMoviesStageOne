package com.example.josh.popularmoviesstageone.utils;

import android.content.Context;
import android.util.Log;

import com.example.josh.popularmoviesstageone.data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 13/12/2017.
 */

public class JsonUtils {

    private final static String MDB_TITLE = "title";
    private final static String MDB_RELEASE_DATE = "release_date";
    private final static String MDB_POSTER_PATH = "poster_path";
    private final static String MDB_VOTE_AVERAGE = "vote_average";
    private final static String MDB_OVERVIEW = "overview";

    public static List<Movie> getMovieStringsFromJson(Context context, String movieJsonStr)
            throws JSONException {

        JSONObject movieJson = new JSONObject(movieJsonStr);
        JSONArray movieArray = movieJson.getJSONArray(NetworkUtils.MDB_RESULT);

        Log.d("OUTPUT", movieArray.toString());

        List<Movie> movieList = new ArrayList<>();

        for (int i = 0; i < movieArray.length(); i++) {

            JSONObject jsonMovie = movieArray.getJSONObject(i);

            String title = (String) jsonMovie.get(MDB_TITLE);
            String releaseDate = (String)jsonMovie.get(MDB_RELEASE_DATE);
            String posterPath = (String)jsonMovie.get(MDB_POSTER_PATH);
            String voteAverage = String.valueOf(jsonMovie.get(MDB_VOTE_AVERAGE));
            String overView = (String)jsonMovie.get(MDB_OVERVIEW);

//            movieList.add(new Movie(
//                    title,
//                    releaseDate,
//                    posterPath,
//                    voteAverage,
//                    overView
//            ));

            Movie movie = new Movie("",",","","","");
            movie.setTitle(title);
            movie.setReleaseDate(releaseDate);
            movie.setPosterPath(posterPath);
            movie.setVoteAverage(voteAverage);
            movie.setOverView(overView);

            movieList.add(movie);
        }
        return movieList;
    }
}
