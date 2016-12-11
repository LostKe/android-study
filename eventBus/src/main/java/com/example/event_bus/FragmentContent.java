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
public class FragmentContent extends Fragment {

    private TextView fragment_content_tv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_content,container);
        fragment_content_tv= (TextView) view.findViewById(R.id.fragment_content_tv);
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
                fragment_content_tv.setText("");
                break;
            case CONTENT:
                fragment_content_tv.setText(event.getContent());
                break;
        }

    }
}
