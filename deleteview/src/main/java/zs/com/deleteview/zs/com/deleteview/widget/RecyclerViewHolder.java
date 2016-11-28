package zs.com.deleteview.zs.com.deleteview.widget;

/**
 * Created by zhangshuqing on 16/11/27.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import zs.com.deleteview.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView item_recycler_text;
    public TextView item_delete_text;
    public LinearLayout layout;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        item_recycler_text = (TextView) itemView.findViewById(R.id.item_recycler_txt);
        item_delete_text = (TextView) itemView.findViewById(R.id.item_delete_txt);
        layout= (LinearLayout) itemView.findViewById(R.id.item_ll);
    }
}
