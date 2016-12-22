package zs.com.deleteview.zs.com.deleteview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * 具有删除功能的 RecyclerView
 * Created by zhangshuqing on 16/11/27.
 */
public class DeleteRecyclerView extends RecyclerView {

    private TextView item_recycler_text;//显示文本

    private TextView item_delete_text;//隐藏文本

    private int maxLength;
    private int mStartX = 0;
    private LinearLayout itemLayout;
    private int pos;
    private Rect mTouchFrame;
    private int xDown, xMove, yDown, yMove, mTouchSlop;
    private Scroller mScroller;

    private boolean isFirst = true;


    public DeleteRecyclerView(Context context) {
        this(context, null);
    }

    public DeleteRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DeleteRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //滑动到最小距离
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        //滑动的最大距离
        maxLength =dipToPx(context,100);
        mScroller = new Scroller(context, new LinearInterpolator(context, attrs));
    }

    private int dipToPx(Context context, int dip) {

        //WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN://手指按下

                xDown = x;
                yDown = y;
                //第一个可见条目的position
                int mFirstPosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
                if(mTouchFrame==null){
                    mTouchFrame=new Rect();
                }
                int count=getChildCount();//当前屏幕显示view的数量
                if(count<=0){
                    break;
                }
                for (int i = count - 1; i >= 0; i--) {
                    final View child = getChildAt(i);
                    if (child.getVisibility() == View.VISIBLE) {
                        child.getHitRect(mTouchFrame);
                        if (mTouchFrame.contains(x, y)) {
                            //确定当前点击的条目
                            pos = mFirstPosition + i;
                            break;
                        }
                    }
                }

                //通过position得到item的viewHolder
                View view = getChildAt(pos - mFirstPosition);
                RecyclerViewHolder viewHolder = (RecyclerViewHolder) getChildViewHolder(view);
                itemLayout = viewHolder.layout;
                item_recycler_text = viewHolder.item_recycler_text;
                item_delete_text = viewHolder.item_delete_text;
                break;
            case MotionEvent.ACTION_MOVE://手指移动
                xMove = x;
                yMove = y;
                int dx=xMove-xDown;
                int dy=yMove-yDown;

                if(Math.abs(dy) < mTouchSlop * 2 && Math.abs(dx)>mTouchSlop){//达到横向滑动滑动要求,上下滑动间距太大的不管
                    int scrollX = itemLayout.getScrollX();
                    int newScrollX = mStartX - x;
                    if (newScrollX < 0 && scrollX <= 0) {
                        newScrollX = 0;
                    } else if (newScrollX > 0 && scrollX >= maxLength) {
                        newScrollX = 0;
                    }
                    itemLayout.scrollBy(newScrollX, 0);
                }
                break;
            case MotionEvent.ACTION_UP://手指抬起
                int scrollX = itemLayout.getScrollX();
                if(scrollX>maxLength/2){
                    //若达到了最小滑动距离，显示删除view
                    mScroller.startScroll(scrollX, 0, maxLength-scrollX, 0);
                }else{
                    //未达到最小滑动距离，还原
                    mScroller.startScroll(scrollX, 0, -scrollX, 0);
                    invalidate();
                }
                break;
        }
        mStartX = x;
        return super.onTouchEvent(e);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            itemLayout.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
