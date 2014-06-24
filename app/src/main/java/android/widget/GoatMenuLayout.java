package android.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by a557114 on 20/06/2014.
 */
public class GoatMenuLayout extends GoatLayout implements DirectionalScrollListener.OnCancelableDetectScrollListener {

    private MenuLayout menuView;

    public GoatMenuLayout(Context context) {
        super(context);
    }

    public GoatMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GoatMenuLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        menuView = new MenuLayout(getContext());
        setFloatView(menuView);
    }

    public void inflate(int menuRes, MenuItemView.OnMenuItemClickListener listener) {
        menuView.inflate(menuRes, listener);
    }
}
