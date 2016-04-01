package av.tesktask.yamobilizationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import av.tesktask.yamobilizationapp.models.Artist;
import av.tesktask.yamobilizationapp.view.ArtistRVAdapter;
import av.tesktask.yamobilizationapp.view.DividerItemDecoration;
import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.rv_artists_list)
    RecyclerView artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPostResume() {//FIXME
        super.onPostResume();
        List<Artist> artists = new ArrayList<>();
        artists.add(Artist.constructArtist());
        artists.add(Artist.constructArtist());
        artists.add(Artist.constructArtist());
        artists.add(Artist.constructArtist());
        // artistList.add
        artistList.setAdapter(new ArtistRVAdapter(artists));
        artistList.addItemDecoration(new DividerItemDecoration(7));//FIXME
        artistList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}