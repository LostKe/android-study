package zs.com.viewpager;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 滑动选项卡页面
 * Created by zhangshuqing on 16/12/11.
 */
public class PagerSlidingTabStrip extends HorizontalScrollView implements View.OnClickListener {

    private ViewGroup tabsLayout; // 标题项布局

    private List<View> tabViews;

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setHorizontalScrollBarEnabled(false);//隐藏横向滑动提示条

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup tabsLayout = getTabsLayout();
        if (tabsLayout == null || tabsLayout.getMeasuredWidth() >= getMeasuredWidth())
            return;
        if (tabsLayout.getChildCount() <= 0)
            return;

        if (tabViews == null) {
            tabViews = new ArrayList<View>();
        } else {
            tabViews.clear();
        }
        for (int w = 0; w < tabsLayout.getChildCount(); w++) {
            tabViews.add(tabsLayout.getChildAt(w));
        }

        adjustChildWidthWithParent(
                tabViews,
                getMeasuredWidth() - tabsLayout.getPaddingLeft()
                        - tabsLayout.getPaddingRight(), widthMeasureSpec,
                heightMeasureSpec);

    }

    /**
     * 调整views集合中的View，让所有View的宽度加起来正好等于parentViewWidth
     *
     * @param views
     *            子View集合
     * @param parentViewWidth
     *            父Vie的宽度
     * @param parentWidthMeasureSpec
     *            父View的宽度规则
     * @param parentHeightMeasureSpec
     *            父View的高度规则
     */
    private void adjustChildWidthWithParent(List<View> views,
                                            int parentViewWidth, int parentWidthMeasureSpec,
                                            int parentHeightMeasureSpec) {
        // 先去掉所有子View的外边距
        for (View view : views) {
            if (view.getLayoutParams() instanceof MarginLayoutParams) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
                parentViewWidth -= lp.leftMargin + lp.rightMargin;
            }
        }

        // 去掉宽度大于平均宽度的View后再次计算平均宽度
        int averageWidth = parentViewWidth / views.size();
        int bigTabCount = views.size();
        while (true) {
            Iterator<View> iterator = views.iterator();
            while (iterator.hasNext()) {
                View view = iterator.next();
                if (view.getMeasuredWidth() > averageWidth) {
                    parentViewWidth -= view.getMeasuredWidth();
                    bigTabCount--;
                    iterator.remove();
                }
            }
            averageWidth = parentViewWidth / bigTabCount;
            boolean end = true;
            for (View view : views) {
                if (view.getMeasuredWidth() > averageWidth) {
                    end = false;
                }
            }
            if (end) {
                break;
            }
        }

        // 修改宽度小于新的平均宽度的View的宽度
        for (View view : views) {
            if (view.getMeasuredWidth() < averageWidth) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                layoutParams.width = averageWidth;
                view.setLayoutParams(layoutParams);
                // 再次测量让新宽度生效
                if (layoutParams instanceof MarginLayoutParams) {
                    measureChildWithMargins(view, parentWidthMeasureSpec, 0, parentHeightMeasureSpec, 0);
                } else {
                    measureChild(view, parentWidthMeasureSpec, parentHeightMeasureSpec);
                }
            }
        }
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void addTab(View tabView, int index) {
        if (tabView != null) {
            getTabsLayout().addView(tabView, index);
            requestLayout();
        }
    }

    public  void addTab(View tabView){
        addTab(tabView,-1);
    }

    private ViewGroup getTabsLayout() {
        if (tabsLayout == null) {
            if (getChildCount() > 0) {
                tabsLayout = (ViewGroup) getChildAt(0);
            } else {
                removeAllViews();
                tabsLayout = new LinearLayout(getContext());
                addView(tabsLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        }
        return tabsLayout;
    }

    @Override
    public void onClick(View v) {

    }
}
