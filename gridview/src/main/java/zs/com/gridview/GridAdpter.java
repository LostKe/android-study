package zs.com.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhangshuqing on 16/7/17.
 */
public class GridAdpter extends BaseAdapter {
    private Context context;
    private List<Item> items;
    private ViewHolder holder;

    public GridAdpter(Context context, List<Item> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
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
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_img);
            holder.textView = (TextView) convertView.findViewById(R.id.comment);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        Item item=items.get(position);
        holder.imageView.setImageResource(item.getResId());
        holder.textView.setText(item.getText());
        return convertView;
    }

    final class ViewHolder {
        private ImageView imageView;
        private TextView textView;

    }


}
