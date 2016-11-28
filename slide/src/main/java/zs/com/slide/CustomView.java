package zs.com.slide;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by zhangshuqing on 16/10/6.
 */
public class CustomView extends TextView {
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    private Scroller scroller=new Scroller(getContext());


    public void smoothScrollTo(int destX, int destY){
        int scrollX=getScrollX();
        int scrollY=getScrollY();
        int deltaX=destX-scrollX;//x 平移
        int deltaY=destY-scrollX;//y平移
        scroller.startScroll(scrollX,scrollY,deltaX,deltaY,1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            ObjectAnimator objectAnimator = new ObjectAnimator();
            //objectAnimator.ofFloat(this,"translationX",scroller.getCurrX(),scroller.getCurrY()).start();
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
        }
        postInvalidate();
    }
}
