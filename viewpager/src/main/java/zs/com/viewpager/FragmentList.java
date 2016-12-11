package zs.com.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by zhangshuqing on 16/12/11.
 */
public class FragmentList extends Fragment {

    ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_item,null);
        mListView= (ListView) view.findViewById(R.id.fragement_pager_lv);
        Bundle bundle=getArguments();
        initFragment(bundle);
        return view;
    }

    private void initFragment(Bundle bundle) {
        if(bundle!=null){
            mListView.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_activated_1,
                    android.R.id.text1,bundle.getStringArrayList("value")));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
