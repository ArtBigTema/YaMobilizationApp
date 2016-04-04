package av.tesktask.yamobilizationapp;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.os.Bundle;

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
    protected RecyclerView artistList;

    @Bind(R.id.pb_list)
    protected ProgressBar progressBar;

    @Bind(R.id.app_toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.activity_main_name));
            setSupportActionBar(toolbar);
        }
        execute();
    }

    private void execute() {
        if (HttpApi.getInstance().isOnline(this)) {
            progressBar.setVisibility(View.VISIBLE);
            HttpApi.getInstance().execute(this, getApplicationContext());
        } else {
            if (HttpApi.getInstance().fileIsExist(this)) {
                onSuccess(HttpApi.getInstance().readFromFile(this));
            } else {
                showAlertDialog(getString(R.string.dialog_internet_is_off));
            }
        }
    }

    @Override
    public void onSuccess(List<Artist> artists) {
        turnOfProgressBar();
        if (artists != null) {
            artistList.setAdapter(new ArtistRVAdapter(artists));
            // artistList.addItemDecoration(new DividerItemDecoration(7));
            artistList.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @Override
    public void onError(String message) {
        turnOfProgressBar();

        if (HttpApi.getInstance().fileIsExist(this)) {
            onSuccess(HttpApi.getInstance().readFromFile(this));
        } else {
            showAlertDialog(getString(R.string.dialog_internet_unavailable));
        }
    }

    private void turnOfProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void showAlertDialog(String message) {
        InternetAlertDialog dialog = new InternetAlertDialog(this, message);
        dialog.show();
    }
}