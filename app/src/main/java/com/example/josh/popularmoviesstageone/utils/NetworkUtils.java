package com.example.josh.popularmoviesstageone.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Josh on 13/12/2017.
 */

public class NetworkUtils {

    private final static String BASE_URL = "https://api.themoviedb.org/3/movie/top_rated?";
    private final static String QUERY_SORT_BY = "sort_by";
    private final static String QUERY_APPKEY = "api_key";
    private final static String QUERY_VOTE_COUNT = "vote_count.gte";
    private final static String PARAM_MIN_VOTES = "100";

    final static String MDB_RESULT = "results";

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_APPKEY, "07edc0b067ac61fe00f8259504795708")
                .appendQueryParameter(QUERY_VOTE_COUNT, PARAM_MIN_VOTES)
                .build();

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
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
//            scanner.useDelimiter("\\a");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
