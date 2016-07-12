package zs.com.tab.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zs.com.tab.R;

/**
 * Created by zhangshuqing on 16/7/10.
 */
public class FragmentPageD extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_page_common,null);
        TextView tv= (TextView) view.findViewById(R.id.common_content);
        tv.setText("ddddd");
        return view;
    }
}
