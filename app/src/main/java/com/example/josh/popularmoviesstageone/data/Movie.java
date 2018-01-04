package com.example.josh.popularmoviesstageone.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Josh on 15/12/2017.
 */

public class Movie implements Parcelable{

    private String mTitle;
    private String mReleaseDate;
    private String mPosterPath;
    private String mVoteAverage;
    private String mOverView;

    public Movie(String title, String releaseDate, String posterPath, String voteAverage, String overView){

        mTitle = title;
        mReleaseDate = releaseDate;
        mPosterPath = posterPath;
        mVoteAverage = voteAverage;
        mOverView = overView;
    }
    private Movie(Parcel parcel){
        mTitle = parcel.readString();
        mReleaseDate = parcel.readString();
        mPosterPath = parcel.readString();
        mVoteAverage = parcel.readString();
        mOverView = parcel.readString();
    }
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getPosterPath() {
        String MDB_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185/";

        return MDB_IMAGE_BASE_URL + mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getOverView() {
        return mOverView;
    }

    public void setOverView(String overView) {
        mOverView = overView;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mReleaseDate);
        parcel.writeString(mPosterPath);
        parcel.writeString(mVoteAverage);
        parcel.writeString(mOverView);
    }
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };
}
