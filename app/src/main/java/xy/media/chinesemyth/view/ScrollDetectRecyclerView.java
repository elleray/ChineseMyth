package xy.media.chinesemyth.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 自定义RecyelerView，可以监听滑动到底部的事件
 * 如果要监听滑动到底部的事件，需要调用setScrollCallback()方法设置回调
 * Created by Anchorer on 2017/12/21.
 */
public class ScrollDetectRecyclerView extends RecyclerView {

    private ScrollCallback mScrollCallback;
//    private GestureDetector mGestureDetector;
//    private int mBalanceTop;
//    private boolean mHasDownloadAction;

    public interface ScrollCallback {
        void onScrollToBottom();

        void onScrollStateChanged(int newState);
    }

    public ScrollDetectRecyclerView(Context context) {
        super(context);
        initScrollListener();
//        enableOverScroll();
    }

    public ScrollDetectRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initScrollListener();
//        enableOverScroll();
    }

    public ScrollDetectRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initScrollListener();
//        enableOverScroll();
    }

    /**
     * 设置滑动回调
     */
    public void setScrollCallback(ScrollCallback mScrollCallback) {
        this.mScrollCallback = mScrollCallback;
    }

    private void initScrollListener() {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                if (lastItem == -1) {
                    lastItem = layoutManager.findLastVisibleItemPosition();
                }

                if (lastItem == totalItemCount - 1) {
                    if (mScrollCallback != null) {
                        mScrollCallback.onScrollToBottom();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, final int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mScrollCallback != null) {
                    mScrollCallback.onScrollStateChanged(newState);
                }
            }
        });
    }

    // FIXME 暂时去掉该特性，目前实现的效果不够好
    /*private void enableOverScroll() {
        mGestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(final MotionEvent e) {
                return true;
            }

            @Override
            public void onShowPress(final MotionEvent e) {
            }

            @Override
            public boolean onSingleTapUp(final MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(final MotionEvent e1, final MotionEvent e2, final float distanceX, final float distanceY) {
                int dY = -(int) (distanceY / 1.5);

                if (getTop() == mBalanceTop && dY > 0) {
                    return false;
                }

                if (dY + getTop() > mBalanceTop) {
                    dY = mBalanceTop - getTop();
                }
                offsetTopAndBottom(dY);
                return true;
            }

            @Override
            public void onLongPress(final MotionEvent e) {
            }

            @Override
            public boolean onFling(final MotionEvent e1, final MotionEvent e2, final float velocityX, final float velocityY) {
                return false;
            }
        });
        addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                mBalanceTop = top;
            }
        });
        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View mTarget, final MotionEvent event) {
                //如果没有download事件直接跳到了move事件，则设置download事件
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mHasDownloadAction = true;
                }

                boolean consumed = false;
                if (getTop() != mBalanceTop) {
                    mGestureDetector.onTouchEvent(event);
                    consumed = true;
                } else if (!ViewCompat.canScrollVertically(mTarget, 1)) {
                    if (!mHasDownloadAction) {
                        int action = event.getAction();
                        event.setAction(MotionEvent.ACTION_DOWN);
                        consumed = mGestureDetector.onTouchEvent(event);
                        event.setAction(action);
                        mHasDownloadAction = true;
                    } else {
                        consumed = mGestureDetector.onTouchEvent(event);
                    }
                }

                // 如果没有消费，则需要清除gesturedetector状态。
                if (!consumed) {
                    int action = event.getAction();
                    event.setAction(MotionEvent.ACTION_UP);
                    mGestureDetector.onTouchEvent(event);
                    event.setAction(action);
                    mHasDownloadAction = false;
                }else {
                    if(event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP){
                        mHasDownloadAction = false;
                    }
                }

                // 手指起需要回复平衡位置
                switch (event.getAction()) {
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        resetRvPosition();
                        break;
                }

                if (consumed) {
                    event.setAction(MotionEvent.ACTION_CANCEL);
                }

                return false;
            }
        });
    }*/

    /*private void resetRvPosition() {
        if (getTop() == 0) {
            return;
        }

        // 1.调用ofInt(int...values)方法创建ValueAnimator对象
        ValueAnimator mAnimator = ValueAnimator.ofInt(getTop(), mBalanceTop);
        // 2.为目标对象的属性变化设置监听器
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 3.为目标对象的属性设置计算好的属性值
                int animatorValue = (int) animation.getAnimatedValue();
                offsetTopAndBottom(animatorValue - getTop());
            }
        });
        // 4.设置动画的持续时间、是否重复及重复次数等属性
        mAnimator.setDuration(300);
        mAnimator.setInterpolator(new DecelerateInterpolator());
        // 5.为ValueAnimator设置目标对象并开始执行动画
        mAnimator.start();
    }*/

}
