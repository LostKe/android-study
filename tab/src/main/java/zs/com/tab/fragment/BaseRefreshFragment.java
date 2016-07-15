package zs.com.tab.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import zs.com.tab.R;

/**
 * Created by zhangshuqing on 16/7/15.
 */
public abstract class BaseRefreshFragment extends Fragment {

     SwipeRefreshLayout swipeRefreshLayout;

     ListView listView;

    LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_page_common,null);
        listView= (ListView) view.findViewById(R.id.lv);
        init(listView,inflater);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                //刷新后所做得操作
                refresh();
                swipeRefreshLayout.setRefreshing(false);
            }


        });


        return view;
    }


    public abstract void refresh();

    public abstract void init(ListView listView,LayoutInflater inflater );



}
