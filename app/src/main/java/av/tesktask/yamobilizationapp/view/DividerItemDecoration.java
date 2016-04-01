package av.tesktask.yamobilizationapp.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Artem on 01.04.2016.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private final int mVerticalSpaceHeight;

    public DividerItemDecoration(int mVerticalSpaceHeight) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = mVerticalSpaceHeight;
    }
}