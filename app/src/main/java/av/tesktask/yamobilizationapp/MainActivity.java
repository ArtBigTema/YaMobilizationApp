package av.tesktask.yamobilizationapp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import av.tesktask.yamobilizationapp.api.DownloadListener;
import av.tesktask.yamobilizationapp.api.HttpApi;
import av.tesktask.yamobilizationapp.models.Artist;
import av.tesktask.yamobilizationapp.view.ArtistRVAdapter;

import av.tesktask.yamobilizationapp.view.InternetAlertDialog;
import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements DownloadListener {
    @Bind(R.id.rv_artists_list)
    RecyclerView artistList;

    @Bind(R.id.pb_list)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        execute();
    }

    private void execute() {
        if (HttpApi.getInstance().isOnline(this)) {
            progressBar.setVisibility(View.VISIBLE);
            HttpApi.getInstance().execute(this);
        } else {
            showAlertDialog(getString(R.string.dialog_internet_is_off));
        }
    }

    @Override
    public void doFinalActions(List<Artist> artists) {
        turnOfProgressBar();
        artistList.setAdapter(new ArtistRVAdapter(artists));
        // artistList.addItemDecoration(new DividerItemDecoration(7));
        artistList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void doErrorActions(String message) {
        turnOfProgressBar();
        showAlertDialog(getString(R.string.dialog_internet_unavailable));
    }

    private void turnOfProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void showAlertDialog(String message) {
        InternetAlertDialog dialog = new InternetAlertDialog(this, message);
        dialog.show();
    }
}