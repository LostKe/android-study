package zs.com.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
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

    private ViewPager mViewPager;

    private OnClickTabListener mClickTabListener;

    private int currentPosition; // 当前位置

    private Drawable slidingBlockDrawable; // 滑块

    private View currentSelectedTabView; // 当前标题项

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setHorizontalScrollBarEnabled(false);//隐藏横向滑动提示条

        if(attrs!=null){
            TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.PagerSlidingTabStrip);
            slidingBlockDrawable=array.getDrawable(R.styleable.PagerSlidingTabStrip_slidingBlock);
        }
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
            tabViews.add(tabsLayout.getChildAt(w));//获取所有的子view
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


        int averageWidth = parentViewWidth / views.size();//平均宽度
        int bigTabCount = views.size();

        // 去掉宽度大于平均宽度的View后再次计算平均宽度(目的是为了考虑特殊情况下相对较大的子view存在，可以更好的分配空间)
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
                    measureChild(view, parentWidthMeasureSpec,   parentHeightMeasureSpec);
                }
            }
        }
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        ViewGroup tabViewGroup=getTabsLayout();


        if(tabViewGroup!=null){
           currentPosition=mViewPager!=null?mViewPager.getCurrentItem():0;
            selectedTab(currentPosition);
            scrollToChild(currentPosition,0);
            for (int i=0;i<tabViewGroup.getChildCount();i++){
                View item = tabViewGroup.getChildAt(i);
                item.setTag(i);
                item.setOnClickListener(this);
            }
        }
    }

    float currentPositionOffset ;//当前位置偏移量
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制滑块
        ViewGroup tabsLayout = getTabsLayout();
        if(tabsLayout!=null && tabsLayout.getChildCount()>0 && slidingBlockDrawable!=null){
            View currentTab = tabsLayout.getChildAt(currentPosition);
            if(currentTab!=null){
                float slidingBlockLeft = currentTab.getLeft();
                float slidingBlockRight = currentTab.getRight();
                if (currentPositionOffset > 0f && currentPosition < tabsLayout.getChildCount() - 1) {
                    View nextTab = tabsLayout.getChildAt(currentPosition + 1);
                    if (nextTab != null) {
                        final float nextTabLeft = nextTab.getLeft();
                        final float nextTabRight = nextTab.getRight();
                        slidingBlockLeft = (currentPositionOffset * nextTabLeft + (1f - currentPositionOffset) * slidingBlockLeft);
                        slidingBlockRight = (currentPositionOffset * nextTabRight + (1f - currentPositionOffset) * slidingBlockRight);
                    }
                }
                //滑块的位置
                slidingBlockDrawable.setBounds((int) slidingBlockLeft, 0, (int) slidingBlockRight, getHeight());
                slidingBlockDrawable.draw(canvas);
            }
        }


    }

    public void addTab(View tabView, int index) {
        if (tabView != null) {
            getTabsLayout().addView(tabView, index);
            requestLayout();
        }
    }




    /**
     * 滚动到指定的位置
     * @param position 指定位置
     * @param offset 偏移量
     */
    private void scrollToChild(int position, int offset) {
        ViewGroup tabsLayout = getTabsLayout();
        if (tabsLayout != null && tabsLayout.getChildCount() > 0 && position < tabsLayout.getChildCount()) {
            View view = tabsLayout.getChildAt(position);
            if (view != null) {
                // 计算新的X坐标
                int newScrollX = view.getLeft() + offset;
                if (position > 0 || offset > 0) {
                    newScrollX -= 240 - getOffset(view.getWidth()) / 2;
                }

                // 如果同上次X坐标不一样就执行滚动
                if (newScrollX != lastScrollX) {
                    lastScrollX = newScrollX;
                    scrollTo(newScrollX, 0);
                }
            }
        }
    }


    private int lastOffset;
    private int lastScrollX = 0;
    private boolean start;
    /**
     * 获取偏移量
     */
    private int getOffset(int newOffset) {
        if (lastOffset < newOffset) {
            if (start) {
                lastOffset += 1;
                return lastOffset;
            } else {
                start = true;
                lastOffset += 1;
                return lastOffset;
            }
        }
        if (lastOffset > newOffset) {
            if (start) {
                lastOffset -= 1;
                return lastOffset;
            } else {
                start = true;
                lastOffset -= 1;
                return lastOffset;
            }
        } else {
            start = true;
            lastOffset = newOffset;
            return lastOffset;
        }
    }

    public  void addTab(View tabView){
        addTab(tabView,-1);
    }

    /**
     * 选中指定位置的TAB
     */
    private void selectedTab(int currentSelectedTabPosition) {
        ViewGroup tabsLayout = getTabsLayout();
        if (currentSelectedTabPosition > -1 && tabsLayout != null && currentSelectedTabPosition < tabsLayout.getChildCount()) {
            if (currentSelectedTabView != null) {
                currentSelectedTabView.setSelected(false);
            }
            currentSelectedTabView = tabsLayout.getChildAt(currentSelectedTabPosition);
            if (currentSelectedTabView != null) {
                currentSelectedTabView.setSelected(true);
            }
        }
    }


    int nextPagePosition;
    public void setViewPager(ViewPager pager){
        this.mViewPager=pager;

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ViewGroup tabsLayout = getTabsLayout();
                if (nextPagePosition < tabsLayout.getChildCount()) {
                    View view = tabsLayout.getChildAt(nextPagePosition);
                    if (view != null) {
                        currentPosition = nextPagePosition;
                        currentPositionOffset = positionOffset;
                        scrollToChild(nextPagePosition, (int) (positionOffset * view.getWidth()));
                        invalidate();
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(">>>>","pageSelect:"+position);
                selectedTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        requestLayout();
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
        int tag= (int) v.getTag();
        if(mClickTabListener!=null){
            mClickTabListener.onClickTab(v,tag);
        }
        if (mViewPager!=null){
            mViewPager.setCurrentItem(tag,true);
        }
    }


    interface  OnClickTabListener{
        void  onClickTab(View tab,int index);
    }

    public void setOnClickTabListener(OnClickTabListener l){
        this.mClickTabListener=l;

    }
}
