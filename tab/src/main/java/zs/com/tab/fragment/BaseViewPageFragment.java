package zs.com.tab.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zs.com.tab.R;
import zs.com.tab.adpter.ViewPageFragmentAdapter;
import zs.com.tab.widget.PagerSlidingTabStrip;

/**
 *
 *
 * Created by zhangshuqing on 16/7/11.
 */
public abstract class BaseViewPageFragment extends Fragment {

    protected PagerSlidingTabStrip mTabStrip;
    protected ViewPager mViewPager;
    protected ViewPageFragmentAdapter mTabsAdapter;
    protected View mRoot;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        if (mRoot == null) {

            View root = inflater.inflate(R.layout.base_viewpage_fragment, null);
            //导航滑动卡
            mTabStrip = (PagerSlidingTabStrip) root.findViewById(R.id.pager_tabstrip);
            //滑动页面
            mViewPager = (ViewPager) root.findViewById(R.id.pager);

            mTabsAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(), mTabStrip, mViewPager);
            setScreenPageLimit();

            //让实现的类去填充数据
            onSetupTabAdapter(mTabsAdapter);

            mRoot = root;
        }
        return mRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            int pos = savedInstanceState.getInt("position");
            mViewPager.setCurrentItem(pos, true);
        }
    }

    protected void setScreenPageLimit() {
    }

    protected abstract void onSetupTabAdapter(ViewPageFragmentAdapter adapter);
}
