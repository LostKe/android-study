package zs.com.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangshuqing on 16/12/11.
 */
public class PagerFragment extends BaseViewPageFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater,container,savedInstanceState);
    }




    /**
     * 初始化自己的tab
     *
     * @param adapter
     */
    @Override
    protected void onSetupTabAdapter(TabViewPageAdapter adapter) {
        ViewPagerDict[] pagerDicts = ViewPagerDict.values();
        for (int i = 0; i < pagerDicts.length; i++) {
            ViewPagerDict dict = pagerDicts[i];
            adapter.addTab(new TabInfo(dict.getTitle(), dict.getTag(), dict.getClz(), dict.getArg()));
        }
    }

}
