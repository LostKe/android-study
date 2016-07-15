package zs.com.tab.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zhangshuqing on 16/7/15.
 */
public class CommonListAdapter<T> extends BaseAdapter {

    private Context context;
    private List<T> list;
    private Class clazz;


    public CommonListAdapter(Context context, List<T> list,Class clazz){
        this.context=context;
        this.list=list;
        this.clazz=clazz;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


    public final class ViewHolder{

    }
}
