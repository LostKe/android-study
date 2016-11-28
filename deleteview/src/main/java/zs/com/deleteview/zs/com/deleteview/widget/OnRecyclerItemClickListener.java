package zs.com.deleteview.zs.com.deleteview.widget;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Created by zhangshuqing on 16/11/27.
 */
public class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
