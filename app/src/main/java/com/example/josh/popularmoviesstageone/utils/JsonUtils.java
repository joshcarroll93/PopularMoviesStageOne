package com.example.josh.popularmoviesstageone.utils;

import android.content.Context;

import com.example.josh.popularmoviesstageone.data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
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
    private final static String MDB_BACKDROPS = "backdrop_path";
    private final static String IMAGE_BACKDROPS_URL = "https://image.tmdb.org/t/p/w300/";

    public static List<Movie> getMovieStringsFromJson(Context context, String movieJsonStr)
            throws JSONException {

        /* Weather information. Each day's forecast info is an element of the "list" array */
        final String MDB_LIST = "list";


        final String MDB_MESSAGE_CODE = "cod";

        JSONObject movieJson = new JSONObject(movieJsonStr);
        JSONArray movieArray = movieJson.getJSONArray(NetworkUtils.MDB_RESULT);

        /* Is there an error? */
        if (movieJson.has(MDB_MESSAGE_CODE)) {
            int errorCode = movieJson.getInt(MDB_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    /* Location invalid */
                    return null;
                default:
                    /* Server probably down */
                    return null;
            }
        }


        List<Movie> movieList = new ArrayList<>();

        for (int i = 0; i < movieList.size(); i++) {

            JSONObject movie = movieArray.getJSONObject(i);

            String title = (String) movie.get(MDB_TITLE);
            String releaseDate = (String)movie.get(MDB_RELEASE_DATE);
            String posterPath = (String)movie.get(MDB_POSTER_PATH);
            String voteAverage = (String)movie.get(MDB_VOTE_AVERAGE);
            String overView = (String)movie.get(MDB_OVERVIEW);
            String backDrops =(String)movie.get(MDB_BACKDROPS);

            movieList.add(new Movie(
                    title,
                    releaseDate,
                    posterPath,
                    voteAverage,
                    overView,
                    backDrops
            ));
        }
        return movieList;
    }

}
