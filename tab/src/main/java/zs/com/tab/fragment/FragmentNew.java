package zs.com.tab.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zs.com.tab.adpter.ViewPageFragmentAdapter;

/**
 * Created by zhangshuqing on 16/7/10.
 */
public class FragmentNew extends BaseViewPageFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
//        TypedArray array=getResources().obtainTypedArray(R.array.news_pager_array);
        ViewPageDict [] dics=ViewPageDict.values();
        for (int i=0;i<dics.length;i++){
            ViewPageDict dict = dics[i];
            adapter.addTab(dict.getTitle(),dict.getTag(),dict.getClz(),dict.getArg());
        }

    }
}
