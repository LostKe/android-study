package zs.com.musicplayer.zs.com.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zs.com.musicplayer.R;

/**
 * Created by zhangshuqing on 16/7/9.
 */
public class FragmentWeixin extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_weixin,null);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
