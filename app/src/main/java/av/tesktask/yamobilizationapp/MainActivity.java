package av.tesktask.yamobilizationapp;

        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.RecyclerView;
        import android.os.Bundle;

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
}