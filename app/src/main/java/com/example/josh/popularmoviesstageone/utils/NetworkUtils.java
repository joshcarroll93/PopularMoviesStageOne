package com.example.josh.popularmoviesstageone.utils;

import android.net.Uri;
import android.util.Log;

import com.example.josh.popularmoviesstageone.BuildConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Josh on 13/12/2017.
 */

public class NetworkUtils {

    private final static String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private final static String API_KEY = BuildConfig.THE_MOVIE_DB_API_KEY;
    private final static String QUERY_APPKEY = "api_key";

    final static String MDB_RESULT = "results";

    public static URL buildUrl(String sortBy) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(sortBy)
                .appendQueryParameter(QUERY_APPKEY, API_KEY)
                .build();

        String TAG = "NETWORK_UTILS";
        Log.i(TAG, builtUri.toString());

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        BufferedReader reader = null;
        String moviesJsonStr = null;

        try {

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder builder = new StringBuilder();

            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Adds '\n' at last line if not already there.
                // This supposedly makes it easier to debug.
                builder.append(line).append("\n");
            }

            if (builder.length() == 0) {
                // No data found. Nothing more to do here.
                return null;
            }

            moviesJsonStr = builder.toString();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            // Tidy up: release url connection and buffered reader
            urlConnection.disconnect();

            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return moviesJsonStr;
    }
}
