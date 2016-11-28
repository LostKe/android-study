package zs.com.deleteview.zs.com.deleteview.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import zs.com.deleteview.R;

/**
 * Created by zhangshuqing on 16/11/27.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    private OnRecyclerItemClickListener listener;

    private List<String> list;
    private Context context;

    public RecyclerAdapter(Context context, List<String> list){
        this.list=list;
        this.context=context;

    }


    public void setOnItemClickListener(OnRecyclerItemClickListener listener){
        this.listener=listener;

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder holder=new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
//        if(listener!=null){
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos=holder.getLayoutPosition();
//                    listener.onItemClick(holder.itemView,pos);
//                }
//            });
//        }
        holder.item_recycler_text.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeRecycle(int position) {
        list.remove(position);
        notifyDataSetChanged();
        if (list.size() == 0) {
            Toast.makeText(context, "已经没数据啦", Toast.LENGTH_SHORT).show();
        }
    }
}
