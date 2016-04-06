package av.tesktask.yamobilizationapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import av.tesktask.yamobilizationapp.R;
import av.tesktask.yamobilizationapp.models.Artist;

import av.tesktask.yamobilizationapp.utils.Constants;
import av.tesktask.yamobilizationapp.utils.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @Bind(R.id.iv_artist_big_photo)
    protected ImageView imageView;

    @Bind(R.id.tv_artist_genres)
    protected TextView genres;

    @Bind(R.id.tv_artist_summary)
    protected TextView summary;

    @Bind(R.id.tv_artist_description)
    protected TextView description;

    @Bind(R.id.app_toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setToolbar();

        if (this.getIntent().getExtras() != null) {
            if (this.getIntent().getExtras().containsKey(Constants.EXTRA_DETAIL_ARTIST)) {
                Artist artist = parseJson(getIntent().getExtras().getString(Constants.EXTRA_DETAIL_ARTIST));
                setData(artist);
            }
        }
    }

    private void setToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private Artist parseJson(String json) {
        return Utils.parseArtist(json);
    }

    private void setData(Artist artist) {
        getSupportActionBar().setTitle(artist.getName());

        genres.setText(artist.getGenresSingleLine());
        summary.setText(artist.getSummary(this,
                " " + getString(R.string.divider_item_detail_summary) + " "));

        StringBuilder text = new StringBuilder();
        text.append(getString(R.string.artist_biography));
        text.append(System.getProperty(getString(R.string.line_separator)));
        text.append(artist.getDescription());
        text.append(System.getProperty(getString(R.string.line_separator)));

        description.setText(text.toString());

        Picasso.with(this)
                .load(artist.getBigCover())
                .fit().centerCrop()
                .error(R.drawable.error_drawable)
                .placeholder(R.drawable.error_drawable)
                .into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}