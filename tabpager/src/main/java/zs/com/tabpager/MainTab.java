package zs.com.tabpager;

import android.support.v4.app.Fragment;

/**
 * Created by zhangshuqing on 16/10/22.
 */
public enum MainTab {
    TAB_01("Tab01",new BaseFragment("001")),
    TAB_02("Tab02",new BaseFragment("002")),
    TAB_03("Tab03",new BaseFragment("003")), TAB_04("Tab04",new BaseFragment("004"));

    private String title;
    private Fragment fragment;

    MainTab(String title,Fragment fragment){
        this.title=title;
        this.fragment=fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
