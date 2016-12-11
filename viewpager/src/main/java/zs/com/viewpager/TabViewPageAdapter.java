package zs.com.viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zhangshuqing on 16/12/11.
 */
public class TabViewPageAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

    private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
    private Map<String, Fragment> mFragments = new ArrayMap<>();

    private Context mContext;

    private ViewPager mViewPager;

    private PagerSlidingTabStrip pagerSlidingTabStrip;



    public TabViewPageAdapter(FragmentManager fm, ViewPager viewPager,PagerSlidingTabStrip pagerSlidingTabStrip) {
        super(fm);
        this.mViewPager=viewPager;
        this.pagerSlidingTabStrip=pagerSlidingTabStrip;
        mContext=mViewPager.getContext();
        mViewPager.setAdapter(this);
        mViewPager.addOnPageChangeListener(this);

    }

    public void setmTabs(ArrayList<TabInfo> tabs){
        for(TabInfo tab:tabs){
            addFragment(tab);
        }
    }

    public void addTab(TabInfo info){
        addFragment(info);
    }

    private  void addFragment(TabInfo tabInfo){
        //设置滑动选项卡

        View mTabStripItemView=LayoutInflater.from(mContext).inflate(R.layout.base_viewpage_fragment_tab_item,null,false);
        TextView tabTitle= (TextView) mTabStripItemView.findViewById(R.id.tab_title);
        tabTitle.setText(tabInfo.getTitle());
        pagerSlidingTabStrip.addTab(mTabStripItemView);
        mTabs.add(tabInfo);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }


    @Override
    public Fragment getItem(int position) {
        TabInfo tabInfo = mTabs.get(position);
        Fragment fm=mFragments.get(tabInfo.getTag());
        if(fm==null){
            fm=Fragment.instantiate(mContext,tabInfo.getClz().getName(),tabInfo.getArg());
            mFragments.put(tabInfo.getTag(),fm);
        }
        return fm;
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        TabInfo info = mTabs.get(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public CharSequence getPageTitle(int position) {
       return  mTabs.get(position).getTitle();
    }
}
