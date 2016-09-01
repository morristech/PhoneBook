package ua.testapp.phonebook.ui.decorators;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private static final int SPACE = 20;

    public SpaceItemDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {

        outRect.left = SPACE;
        outRect.right = SPACE;
        outRect.bottom = SPACE;

        if(parent.getChildAdapterPosition(view) == 0)
            outRect.top = SPACE;

        if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = SPACE;
        }


    }
}
