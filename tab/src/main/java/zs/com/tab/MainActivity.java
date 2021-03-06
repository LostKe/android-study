package zs.com.tab;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener ,TabHost.OnTabChangeListener{

    @BindView(R.id.tabHost)
    FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTabHost.setup(this,getSupportFragmentManager(),R.id.tab_content);
        initTabs();
        mTabHost.setCurrentTab(0);

    }

    private void initTabs() {
        MainTab[] tabs= MainTab.values();
        for (int i=0;i<tabs.length;i++){
            MainTab mainTab=tabs[i];
            TabHost.TabSpec mTabSpec=mTabHost.newTabSpec(mainTab.getName());
            View indicator= LayoutInflater.from(this).inflate(R.layout.tab_indicator,null);
            ImageView icon= (ImageView) indicator.findViewById(R.id.iv_icon);
            TextView title= (TextView) indicator.findViewById(R.id.tab_title);
            Drawable drawable = this.getResources().getDrawable(mainTab.getResIcon());
            icon.setImageDrawable(drawable);
            title.setText(mainTab.getName());

            mTabSpec.setIndicator(indicator);

            mTabHost.addTab(mTabSpec,mainTab.getClz(),null);
            mTabHost.setOnTabChangedListener(this);

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouchEvent(event);
        boolean consumed = false;

        if (event.getAction() == MotionEvent.ACTION_DOWN  && v.equals(mTabHost.getCurrentTabView())) {
            // use getTabHost().getCurrentView() to get a handle to the view
            // which is displayed in the tab - and to get this views context
            Fragment currentFragment = getCurrentFragment();
//            if (currentFragment != null
//                    && currentFragment instanceof OnTabReselectListener) {
//                OnTabReselectListener listener = (OnTabReselectListener) currentFragment;
//                listener.onTabReselect();
//                consumed = true;
//            }
        }
        return consumed;
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag(mTabHost.getCurrentTabTag());
    }

    @Override
    public void onTabChanged(String tabId) {
        Toast.makeText(this,tabId,Toast.LENGTH_SHORT).show();
    }


    /**
     * 实现快速点击两次返回键退出程序
     *
     * 记录上一次点击的时间 --两个时间做比较
     * 在指定范围内finish Activity
     */
    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
