package av.tesktask.yamobilizationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import av.tesktask.yamobilizationapp.api.DownloadListener;
import av.tesktask.yamobilizationapp.api.HttpApi;
import av.tesktask.yamobilizationapp.models.Artist;
import av.tesktask.yamobilizationapp.view.ArtistRVAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements DownloadListener {
    @Bind(R.id.rv_artists_list)
    RecyclerView artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        execute();
    }

    private void execute() {
        if (HttpApi.getInstance().isOnline(this)) {
            HttpApi.getInstance().execute(this);
        }
    }

    @Override
    public void doFinalActions(List<Artist> artists) {
        artistList.setAdapter(new ArtistRVAdapter(artists));
        artistList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void doErrorActions(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();//FIXME
    }
}