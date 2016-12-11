package zs.com.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangshuqing on 16/12/11.
 */
public  abstract class BaseViewPageFragment extends Fragment {

    private ViewPager mViewPager;
    private TabViewPageAdapter mAdapter;
    protected View mRoot;
    private PagerSlidingTabStrip pagerSlidingTabStrip;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRoot==null){
            mRoot=inflater.inflate(R.layout.fragment_pager,null);
            mViewPager= (ViewPager) mRoot.findViewById(R.id.viewPager);
            pagerSlidingTabStrip= (PagerSlidingTabStrip) mRoot.findViewById(R.id.pagerSlidingTabStrip);
            mAdapter=new TabViewPageAdapter(getChildFragmentManager(),mViewPager,pagerSlidingTabStrip);
        }
        onSetupTabAdapter(mAdapter);
        return mRoot;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void onSetupTabAdapter(TabViewPageAdapter adapter);
}
