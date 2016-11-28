package zs.com.tabpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    PagerTabStrip pagerTabStrip;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pagerTabStrip= (PagerTabStrip) findViewById(R.id.pagerTabStrip);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        initView();
    }

    private void initView() {
        viewPager.setAdapter(new PagerFragmentAdapter(getSupportFragmentManager()));
    }


    class PagerFragmentAdapter extends FragmentPagerAdapter{
        MainTab[] tabs;

        public PagerFragmentAdapter(FragmentManager fm) {
            super(fm);
           tabs= MainTab.values();
        }

        @Override
        public Fragment getItem(int position) {
            return tabs[position].getFragment();
        }

        @Override
        public int getCount() {
            return tabs.length;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position].getTitle();
        }
    }

}
