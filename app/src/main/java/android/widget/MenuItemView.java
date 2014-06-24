package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Bernat on 24/06/2014.
 */
public class MenuItemView extends View implements View.OnClickListener {
    private MenuItem menuItem;
    private OnMenuItemClickListener onMenuItemClickListener;
    private Rect clipBounds;

    public MenuItemView(Context context) {
        super(context);
        init();
    }

    public MenuItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MenuItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setOnClickListener(this);
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size48 = (int) (48 * getDensity());
        setMeasuredDimension(size48, size48);
    }

    public float getDensity() {
        return getResources().getDisplayMetrics().density;
    }

    @Override
    public void onClick(View v) {
        if (menuItem != null) {
            if (onMenuItemClickListener != null) {
                onMenuItemClickListener.onMenuItemClick(menuItem);
            }
        }
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    public interface OnMenuItemClickListener {
        /**
         * This method will be invoked when a menu item is clicked if the item itself did
         * not already handle the event.
         *
         * @param item {@link android.view.MenuItem} that was clicked
         * @return <code>true</code> if the event was handled, <code>false</code> otherwise.
         */
        public boolean onMenuItemClick(MenuItem item);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        clipBounds = canvas.getClipBounds();
        if (getMenuItem() != null) {
            if (getMenuItem().getIcon() != null) {
                drawMenuIcon(canvas, clipBounds, getMenuItem().getIcon());
            }
        }
    }

    private void drawMenuIcon(Canvas canvas, Rect bounds, Drawable icon) {
        icon.setBounds(bounds.left, bounds.top, bounds.right, bounds.bottom);
        icon.draw(canvas);
    }
}
