package com.example.event_bus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by youx on 2016-12-05.
 */
public class FragmentDetail extends Fragment {

    private TextView fragmentDetail_tv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_detail,container);
        fragmentDetail_tv= (TextView) view.findViewById(R.id.fragment_detail_tv);
        return view;
    }



    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ItemEvent event) {
        switch (event.getType()){
            case DETAIL:
                fragmentDetail_tv.setText(event.getContent());
                break;
            case CONTENT:
                fragmentDetail_tv.setText("");
                break;
        }
    }
}
