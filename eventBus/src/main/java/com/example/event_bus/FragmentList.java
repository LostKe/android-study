package com.example.event_bus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youx on 2016-12-05.
 */
public class FragmentList extends Fragment implements AdapterView.OnItemClickListener{

    ListView listView;

    private static List<String> items=new ArrayList<String>();

    static{
        items.add("第一项");
        items.add("第二项");
        items.add("第三项");
        items.add("第四项");
        items.add("第五项");
        items.add("第六项");
        items.add("第七项");
        items.add("第八项");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list,container);
        listView= (ListView) view.findViewById(R.id.fragment_list_lv);
        listView.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_activated_1,android.R.id.text1,items));
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
       // EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        EventBus.getDefault().post(new ItemEvent(position,items.get(position)));
    }
}
