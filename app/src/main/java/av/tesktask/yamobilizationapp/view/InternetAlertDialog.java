package av.tesktask.yamobilizationapp.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import av.tesktask.yamobilizationapp.R;

/**
 * Created by Artem on 01.04.2016.
 */
public class InternetAlertDialog extends AlertDialog {
    private Context context;

    public InternetAlertDialog(final Context context, String message) {
        super(context);
        this.context = context;

        setMessage(message);

        setButton(AlertDialog.BUTTON_POSITIVE, context.getString(R.string.dialog_retry_button), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });
    }
}