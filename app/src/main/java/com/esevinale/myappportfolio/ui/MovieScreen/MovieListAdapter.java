package com.esevinale.myappportfolio.ui.MovieScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esevinale.myappportfolio.R;
import com.esevinale.myappportfolio.models.MovieItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<MovieItem> movies = new ArrayList<>();
    private MovieListView view;
    private Context context;

    public MovieListAdapter(MovieListView moviesView) {
        view = moviesView;
    }

    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.movie = movies.get(position);
        holder.name.setText(movies.get(position).getOriginalTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public MovieItem getItem(int position) {
        return movies.get(position);
    }

    public void addMovies(List<MovieItem> movieItemList) {
        movies.addAll(movieItemList);
        notifyDataSetChanged();
    }

    public void setMovies(List<MovieItem> movieItemList) {
        clearList();
        addMovies(movieItemList);
    }

    public void clearList() {
        movies.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.movie_poster)
        ImageView poster;
        @BindView(R.id.title_background)
        View titleBackground;
        @BindView(R.id.movie_name)
        TextView name;

        public MovieItem movie;

        public ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {
            MovieListAdapter.this.view.onMovieClicked(movie);
        }
    }
}