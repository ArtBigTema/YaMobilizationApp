package av.tesktask.yamobilizationapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.os.Bundle;

import java.util.List;

import av.tesktask.yamobilizationapp.R;
import av.tesktask.yamobilizationapp.api.DownloadListener;
import av.tesktask.yamobilizationapp.api.DataManager;
import av.tesktask.yamobilizationapp.models.Artist;
import av.tesktask.yamobilizationapp.utils.Constants;
import av.tesktask.yamobilizationapp.utils.Utils;
import av.tesktask.yamobilizationapp.view.ArtistRVAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DownloadListener {
    private AlertDialog alertDialog;
    private boolean containsData = false;//true if recyclerView contain data

    @Bind(R.id.rv_artists_list)
    protected RecyclerView recyclerView;

    @Bind(R.id.pb_list)
    protected ProgressBar progressBar;

    @Bind(R.id.app_toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        containsData = false;

        setToolbar();

        if (savedInstanceState != null) {//извлекаем сохраненные данные, если повернули экран
            if (savedInstanceState.containsKey(Constants.EXTRA_ARTISTS)) {
                onSuccess(Utils.parseArtists(savedInstanceState.getString(Constants.EXTRA_ARTISTS)));
            }
        }
    }

    private void setToolbar() {
        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.activity_main_name));
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (containsData) {//Сохраняем данные, если есть, до поворота экрана
            savedInstanceState.putString(Constants.EXTRA_ARTISTS,
                    Utils.getJson(((ArtistRVAdapter) recyclerView.getAdapter()).getList()));
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissAlertDialog();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!containsData) {
            execute();
        }
    }

    private void execute() {
        if (DataManager.getInstance().isOnline(this)) {
            turnOnProgressBar();
            DataManager.getInstance().execute(this, getApplicationContext());
        } else {
            if (DataManager.getInstance().fileExist(this)) {
                turnOnProgressBar();
                onSuccess(DataManager.getInstance().readFromFile(this));
            } else {
                showAlertDialogForActivateWIFI();
            }
        }
    }

    @Override
    public void onSuccess(List<Artist> artists) {
        turnOffProgressBar();
        dismissAlertDialog();

        if (artists != null && artists.size() > 0) {
            containsData = true;
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new ArtistRVAdapter(artists));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            showAlertDialogForReTryDownload(getString(R.string.dialog_list_is_empty));
        }
    }

    @Override
    public void onError(String message) {
        turnOffProgressBar();
        containsData = false;

        if (DataManager.getInstance().fileExist(this)) {
            onSuccess(DataManager.getInstance().readFromFile(this));
        } else {
            showAlertDialogForReTryDownload(message);
        }
    }

    private void turnOnProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void turnOffProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void showAlertDialogForActivateWIFI() {
        dismissAlertDialog();

        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(getString(R.string.dialog_internet_and_file_unavailable));
        alertDialog.setCancelable(false);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,
                getString(R.string.dialog_goto_setting),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismissAlertDialog();
                        MainActivity.this.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                });

        showAlertDialog();
    }

    private void showAlertDialogForReTryDownload(String message) {
        dismissAlertDialog();

        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,
                getString(R.string.dialog_retry_button),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismissAlertDialog();
                        execute();
                    }
                });

        showAlertDialog();
    }

    private void showAlertDialog() {
        if (alertDialog != null) {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
            alertDialog.show();
        }
    }

    private void dismissAlertDialog() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }
}