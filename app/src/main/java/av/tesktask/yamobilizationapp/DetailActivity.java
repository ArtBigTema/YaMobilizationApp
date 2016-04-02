package av.tesktask.yamobilizationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squareup.picasso.Picasso;

import av.tesktask.yamobilizationapp.models.Artist;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    private Artist artist;

    @Bind(R.id.iv_artist_big_photo)
    protected ImageView imageView;
    @Bind(R.id.tv_artist_genres)
    protected TextView genres;
    @Bind(R.id.tv_artist_summary)
    protected TextView summary;
    @Bind(R.id.tv_artist_description)
    protected TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        if (this.getIntent().getExtras() != null) {
            if (this.getIntent().getExtras().containsKey(Artist.class.getName())) {
                parseJson(this.getIntent().getExtras().getString(Artist.class.getName()));
                setData();
            }
        }
    }

    private void parseJson(String json) {
        Gson gson = new GsonBuilder().create();
        artist = gson.fromJson(json, Artist.class);
    }

    private void setData() {
        genres.setText(artist.getGenresSingleLine());
        summary.setText(artist.getSummary(this,
                " " + getString(R.string.divider_item_detail_summary) + " "));

        StringBuilder text = new StringBuilder();
        text.append(getString(R.string.artist_biography));
        text.append(System.getProperty(getString(R.string.line_separator)));
        text.append(artist.getDescription());
        text.append(System.getProperty(getString(R.string.line_separator)));//FIXME

        description.setText(text.toString());

        Picasso.with(this)
                .load(artist.getBigCover())
                .fit().centerCrop()
                .error(R.drawable.error_drawable)
                .placeholder(R.drawable.error_drawable)
                .into(imageView);
    }
}