package android.widget;

import android.content.Context;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.util.AttributeSet;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.alorma.floatingbutton.R;

/**
 * Created by Bernat on 24/06/2014.
 */
public class MenuLayout extends LinearLayout {

    private MenuBuilder mMenu;

    public MenuLayout(Context context) {
        super(context);
        init();
    }

    public MenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mMenu = new MenuBuilder(getContext());
        if (isInEditMode()) {
            mMenu.add("Title 1");
            mMenu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_launcher));
            mMenu.add("Title 2");
            mMenu.getItem(1).setIcon(getResources().getDrawable(R.drawable.ic_launcher_2));
            inflate(0, null);
        }

        setHorizontalScrollBarEnabled(getOrientation() == HORIZONTAL);
        setVerticalScrollBarEnabled(getOrientation() == VERTICAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int minWidth = 0;
        int minHeight = 0;

        int width = 0;
        int height = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View at = getChildAt(i);
            at.measure(widthMeasureSpec, heightMeasureSpec);

            height += at.getMeasuredHeight();
            width += at.getMeasuredWidth();

            minHeight = minHeight < at.getMeasuredHeight() ? at.getMeasuredHeight() : minHeight;
            minWidth = minWidth < at.getMeasuredWidth() ? at.getMeasuredWidth() : minHeight;
        }

        if (getOrientation() == HORIZONTAL) {
            setMeasuredDimension(width, minHeight);
        } else if (getOrientation() == VERTICAL) {
            setMeasuredDimension(minWidth, height);
        } else {
            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        }

    }

    public MenuInflater getMenuInflater() {
        return new SupportMenuInflater(getContext());
    }

    public void inflate(int menuRes, MenuItemView.OnMenuItemClickListener onMenuItemClickListener) {
        if (!isInEditMode()) {
            getMenuInflater().inflate(menuRes, mMenu);
        }
        removeAllViews();
        for (int i = 0; i < mMenu.size(); i++) {
            MenuItem item = mMenu.getItem(i);
            if (item.isVisible()) {
                MenuItemView menuItemView = createMenuItemView(item, onMenuItemClickListener);
                addView(menuItemView);
            }
        }
    }

    private MenuItemView createMenuItemView(MenuItem item, MenuItemView.OnMenuItemClickListener onMenuItemClickListener) {
        MenuItemView menuItemView = new MenuItemView(getContext());
        menuItemView.setOnMenuItemClickListener(onMenuItemClickListener);
        menuItemView.setMenuItem(item);
        return menuItemView;
    }

    public float getDensity() {
        return getResources().getDisplayMetrics().density;
    }
}
