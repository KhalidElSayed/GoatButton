package android.widget;

import android.view.View;

/**
 * Created by a557114 on 20/06/2014.
 */
public class DirectionalScrollListener implements AbsListView.OnScrollListener {

    private int oldTop;
    private int oldFirstVisibleItem;

    private OnDetectScrollListener onDetectScrollListener;

    public DirectionalScrollListener(OnDetectScrollListener onDetectScrollListener) {
        this.onDetectScrollListener = onDetectScrollListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (onDetectScrollListener != null) {
            onDetectedListScroll(view, firstVisibleItem);
        }
    }

    private void onDetectedListScroll(AbsListView absListView, int firstVisibleItem) {
        View view = absListView.getChildAt(0);
        int top = (view == null) ? 0 : view.getTop();

        if (firstVisibleItem == oldFirstVisibleItem) {
            if (top > oldTop) {
                onDetectScrollListener.onUpScrolling();
            } else if (top < oldTop) {
                onDetectScrollListener.onDownScrolling();
            }
        } else {
            if (firstVisibleItem < oldFirstVisibleItem) {
                onDetectScrollListener.onUpScrolling();
            } else {
                onDetectScrollListener.onDownScrolling();
            }
        }

        oldTop = top;
        oldFirstVisibleItem = firstVisibleItem;
    }

    public interface OnDetectScrollListener {

        void onUpScrolling();

        void onDownScrolling();
    }
}

