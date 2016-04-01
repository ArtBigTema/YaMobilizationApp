package av.tesktask.yamobilizationapp.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import av.tesktask.yamobilizationapp.R;
import av.tesktask.yamobilizationapp.models.Artist;

import av.tesktask.yamobilizationapp.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Artem on 31.03.2016.
 */
public class ArtistRVAdapter extends RecyclerView.Adapter<ArtistRVAdapter.ArtistsViewHolder> {
    private List<Artist> list = Collections.emptyList();

    public ArtistRVAdapter(List<Artist> artistList) {
        this.list = artistList;
    }

    @Override
    public ArtistsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cv_item, null);
        ArtistsViewHolder viewHolder = new ArtistsViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArtistsViewHolder artistViewHolder, int position) {
        Context c = artistViewHolder.image.getContext();

        Artist artist = list.get(position);
        artistViewHolder.setName(artist.getName());//FIXME single line
        artistViewHolder.setGenres(artist.getGenresSingleLine());//FIXME single line

        artistViewHolder.setSummary(artist.getSummary());

        Picasso.with(c)
                .load(artist.getSmallCover())
                .error(R.drawable.error_drawable)
                .placeholder(R.drawable.error_drawable)
                .into(artistViewHolder.image);
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

        @Bind(R.id.iv_artist_small_photo)
        ImageView image;
        @Bind(R.id.tv_artist_name)
        TextView name;
        @Bind(R.id.tv_artist_genres)
        TextView genres;
        @Bind(R.id.tv_artist_summary)
        TextView summary;

        public ArtistsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
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

        public void setDrawable(Drawable drawable) {
            image.setImageDrawable(drawable);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), getLayoutPosition() +
                    " clicked!", Toast.LENGTH_SHORT).show();//FIXME
        }
    }
}