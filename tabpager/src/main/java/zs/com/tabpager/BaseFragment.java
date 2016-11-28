package zs.com.tabpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhangshuqing on 16/10/22.
 */
public class BaseFragment extends Fragment {
    TextView tv;

    String text;

    public BaseFragment(String text){
        super();
        this.text=text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.base_fragment_layout,null);
        tv= (TextView) view.findViewById(R.id.text);
        tv.setText(text);
        return view;
    }


}
