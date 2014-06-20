package android.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.alorma.floatingbutton.R;

/**
 * Created by a557114 on 20/06/2014.
 */
public class GoatLayout extends FrameLayout implements DirectionalScrollListener.OnDetectScrollListener {

    private Button button;
    private AbsListView absListView;
    private boolean isHidden;
    private boolean isShown;

    public GoatLayout(Context context) {
        super(context);
        init();
    }

    public GoatLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GoatLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

    }

    @Override
    public void addView(View child) {
        super.addView(child);
        if (child instanceof AbsListView) {
            ((AbsListView) child).setOnScrollListener(new DirectionalScrollListener(this));
        }
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        if (child instanceof AbsListView) {
            absListView = (AbsListView) child;
            absListView.setOnScrollListener(new DirectionalScrollListener(this));
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (button != null) {
            button.layout(right - 10 - 200, bottom - 10 - 200, right - 10, bottom - 10);
        }
    }

    @Override
    public void onUpScrolling() {
        button.setText("U");
        if (isHidden) {
            PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(Y, button.getY(),
                    this.getBottom() - button.getMeasuredHeight() - 10);
            PropertyValuesHolder pvhA = PropertyValuesHolder.ofFloat(ALPHA, 0.0f, 1.0f);

            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(button, pvhY, pvhA);

            animator.setDuration(300);

            animator.setRepeatCount(0);

            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {

                }
            });

            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    isShown = true;
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            animator.start();
        }
    }

    @Override
    public void onDownScrolling() {
        button.setText("D");
        if (isShown) {

            PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(Y, button.getTop(), this.getBottom() + button.getMeasuredHeight());
            PropertyValuesHolder pvhA = PropertyValuesHolder.ofFloat(ALPHA, 1.0f, 0.0f);

            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(button, pvhY, pvhA);

            animator.setDuration(300);

            animator.setRepeatCount(0);

            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {

                }
            });

            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                        isHidden = true;
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            animator.start();
        }
    }

    public void setButton(Button button) {
        this.button = button;
        isShown = true;
        addView(button);
        postInvalidate();
    }
}
