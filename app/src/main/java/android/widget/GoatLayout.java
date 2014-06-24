package android.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by a557114 on 20/06/2014.
 */
public class GoatLayout extends FrameLayout implements DirectionalScrollListener.OnCancelableDetectScrollListener {


    private View floatView;
    private ObjectAnimator upAnimation;
    private ObjectAnimator downAnimation;
    private float fraction = 1.0f;
    private FLOAT_STATE float_state;

    private int marginRight;
    private int marginBottom;
    private float density;
    private long timeShowOnStop;
    private long durationIn;
    private long durationOut;

    private AbsListView.OnScrollListener handlerScroll;
    private DirectionalScrollListener directionalScrollListener;

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

    protected void init() {
        isInEditMode();
        density = getResources().getDisplayMetrics().density;
        marginRight = 10;
        marginBottom = 10;
        timeShowOnStop = 400;
        durationIn = 300;
        durationOut = 300;
    }

    public void setFloatView(View floatView) {
        this.floatView = floatView;
        if (floatView.getLayoutParams() == null) {
            int squareSize = (int) (50 * density);
            LayoutParams layoutParams = new LayoutParams(squareSize, squareSize);
            floatView.setLayoutParams(layoutParams);
        }
        float_state = FLOAT_STATE.VISIBLE;
        addView(floatView);
        postInvalidate();
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        if (child instanceof AbsListView) {
            directionalScrollListener = new DirectionalScrollListener(this, handlerScroll, timeShowOnStop);
            ((AbsListView) child).setOnScrollListener(directionalScrollListener);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (floatView != null) {
            floatView.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (floatView != null) {
            floatView.layout(right - getMarginRight() - 400,
                    bottom - getMarginBottom() - floatView.getMeasuredHeight(),
                    right - getMarginRight(),
                    bottom - getMarginBottom());
        }
    }

    @Override
    public void onUpScrolling() {
        if (float_state == FLOAT_STATE.INVISIBLE) {
            getUpAnimation().start();
        }
    }

    @Override
    public void onDownScrolling() {
        if (float_state == FLOAT_STATE.VISIBLE) {
            getDownAnimation().start();
        }
    }

    @Override
    public void onScrollStop() {
        if (float_state == FLOAT_STATE.INVISIBLE) {
            getDownAnimation().cancel();
            getUpAnimation().start();
        }
    }

    private ObjectAnimator getUpAnimation() {
        if (upAnimation == null) {
            float initial = floatView.getY();
            float end = getBottom() - getMarginBottom() - floatView.getMeasuredHeight();

            PropertyValuesHolder pvhF = PropertyValuesHolder.ofFloat(Y, initial, end);

            upAnimation = ObjectAnimator.ofPropertyValuesHolder(floatView, pvhF);
        }

        upAnimation.setDuration(durationIn);
        upAnimation.setRepeatCount(0);

        FloatAnimatorListener floatAnimatorListener = new FloatAnimatorListener(FLOAT_STATE.VISIBLE);

        upAnimation.addUpdateListener(floatAnimatorListener);
        upAnimation.addListener(floatAnimatorListener);

        return upAnimation;
    }

    private ObjectAnimator getDownAnimation() {
        if (downAnimation == null) {

            float initial = floatView.getY() * fraction;
            float end = getBottom() + floatView.getMeasuredHeight();

            PropertyValuesHolder pvhF = PropertyValuesHolder.ofFloat(Y, initial, end);

            downAnimation = ObjectAnimator.ofPropertyValuesHolder(floatView, pvhF);
        }

        downAnimation.setDuration(durationOut);
        downAnimation.setRepeatCount(0);

        FloatAnimatorListener floatAnimatorListener = new FloatAnimatorListener(FLOAT_STATE.INVISIBLE);

        downAnimation.addUpdateListener(floatAnimatorListener);
        downAnimation.addListener(floatAnimatorListener);
        return downAnimation;
    }

    private enum FLOAT_STATE {
        VISIBLE,
        INVISIBLE,
        RUNING;
    }

    private class FloatAnimatorListener implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {

        private FLOAT_STATE stateEnd;

        public FloatAnimatorListener(FLOAT_STATE stateEnd) {
            this.stateEnd = stateEnd;
        }

        @Override
        public void onAnimationStart(Animator animation) {
            float_state = FLOAT_STATE.RUNING;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            float_state = stateEnd;
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            fraction = animation.getAnimatedFraction();
        }
    }

    public int getMarginRight() {
        return (int) (marginRight * density);
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        postInvalidate();
    }

    public int getMarginBottom() {
        return (int) (marginBottom * density);
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
        postInvalidate();
    }

    public long getTimeShowOnStop() {
        return timeShowOnStop;
    }

    public void setTimeShowOnStop(long timeShowOnStop) {
        this.timeShowOnStop = timeShowOnStop;
        directionalScrollListener.setCountdownStop(timeShowOnStop);
    }

    public AbsListView.OnScrollListener getHandlerScroll() {
        return handlerScroll;
    }

    public void setHandlerScroll(AbsListView.OnScrollListener handerScroll) {
        this.handlerScroll = handerScroll;
        directionalScrollListener.setHandlerScroll(handlerScroll);
    }

    public long getDurationIn() {
        return durationIn;
    }

    public void setDurationIn(long durationIn) {
        this.durationIn = durationIn;
    }

    public long getDurationOut() {
        return durationOut;
    }

    public void setDurationOut(long durationOut) {
        this.durationOut = durationOut;
    }
}
