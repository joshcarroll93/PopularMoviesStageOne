package com.example.josh.popularmoviesstageone.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josh.popularmoviesstageone.R;
import com.example.josh.popularmoviesstageone.activity.MainActivity;
import com.example.josh.popularmoviesstageone.activity.MovieDetailActivity;
import com.example.josh.popularmoviesstageone.data.Movie;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Josh on 13/12/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context mContext;
    private List<Movie> mMovies;

    public MovieAdapter(Context context, List<Movie> movies){

        mContext = context;
        mMovies = movies;
    }

    public void addItems(List<Movie> newMovies){
        mMovies.addAll(newMovies);
        this.notifyDataSetChanged();
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

        Log.d("OUTPPUT", "Creating a view for the movie");
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }


    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, final int i) {

        final int position = i;
        movieViewHolder.moviePoster.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher));
//        movieViewHolder.title.setText(mMovies.get(i).getTitle());
        Log.i("MOVIE_ADAPTER", mMovies.get(i).getPosterPath());
        Picasso.with(mContext).load(mMovies.get(i).getPosterPath())
                .resize(185, 277)
                .into(movieViewHolder.moviePoster);

        movieViewHolder.moviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context viewContext = view.getContext();
                Intent intent = new Intent(viewContext, MovieDetailActivity.class);
                intent.putExtra("movie", mMovies.get(i));
                viewContext.startActivity(intent);

            }
        });

    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public ImageView moviePoster;

        public MovieViewHolder(View itemView){
            super(itemView);

//            title = itemView.findViewById(R.id.id_title);
            moviePoster = itemView.findViewById(R.id.image_view);
        }
    }
//
//    @Override
//    public Bitmap transform(Bitmap source) {
//        int size = Math.min(source.getWidth(), source.getHeight());
//        int x = (source.getWidth() - size) / 2;
//        int y = (source.getHeight() - size) / 2;
//        Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
//        if (result != source) {
//            source.recycle();
//        }
//        return result;
//    }
//
//    @Override public String key() { return "square()"; }
//
//    public final class SquaredImageView extends android.support.v7.widget.AppCompatImageView {
//        public SquaredImageView(Context context) {
//            super(context);
//        }
//
//        public SquaredImageView(Context context, AttributeSet attrs) {
//            super(context, attrs);
//        }
//
//        @Override
//        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//            setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
//        }
//    }
}
