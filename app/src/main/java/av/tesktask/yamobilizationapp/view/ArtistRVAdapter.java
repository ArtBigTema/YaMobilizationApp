package av.tesktask.yamobilizationapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import av.tesktask.yamobilizationapp.DetailActivity;
import av.tesktask.yamobilizationapp.R;
import av.tesktask.yamobilizationapp.models.Artist;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Artem on 31.03.2016.
 */
public class ArtistRVAdapter extends RecyclerView.Adapter<ArtistRVAdapter.ArtistsViewHolder> {
    private List<Artist> list = Collections.emptyList();//FIXME rename
    private final int LAYOUT = R.layout.cv_item;

    public ArtistRVAdapter(List<Artist> artistList) {
        this.list = artistList;
        //   Collections.sort(list);
    }

    @Override
    public ArtistsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext())
                .inflate(LAYOUT, null);
        ArtistsViewHolder viewHolder = new ArtistsViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArtistsViewHolder artistViewHolder, int position) {
        artistViewHolder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateData(List<Artist> newArtists) {
        list = newArtists;
    }

    public static class ArtistsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private Context context;
        private Artist artist;//TODO remove if need

        @Bind(R.id.iv_artist_small_photo)
        protected ImageView imageView;
        @Bind(R.id.tv_artist_name)
        protected TextView name;
        @Bind(R.id.tv_artist_genres)
        protected TextView genres;
        @Bind(R.id.tv_artist_summary)
        protected TextView summary;

        public ArtistsViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setData(Artist artist) {
            this.artist = artist;
            setName(artist.getName());

            setGenres(artist.getGenresSingleLine());
            setSummary(artist.getSummary(context,
                    context.getString(R.string.divider_item_list_summary) + " "));

            Picasso.with(context)
                    .load(artist.getSmallCover())
                    .fit().centerCrop()
                    .error(R.drawable.error_drawable)
                    .placeholder(R.drawable.error_drawable)
                    .into(imageView);
        }

        public void setName(String itemName) {
            name.setText(itemName);
        }

        public void setGenres(String itemGenres) {
            genres.setText(itemGenres);
        }

        public void setSummary(String itemSummary) {
            summary.setText(itemSummary);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra(Artist.class.getName(), (new Gson()).toJson(artist));//TODO
            v.getContext().startActivity(intent);
        }
    }
}