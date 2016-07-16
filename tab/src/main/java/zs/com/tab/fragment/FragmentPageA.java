package zs.com.tab.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zs.com.tab.R;

/**
 * Created by zhangshuqing on 16/7/10.
 */
public class FragmentPageA extends BaseRefreshFragment {

   static List<String> values=new ArrayList<String>();
    private  MyAadpter myAadpter;
    static{
        values.add("aaaaa");
        values.add("bbbbb");
        values.add("ccccc");
        values.add("dddd");
        values.add("eeeee");
    }

    @Override
    public void refresh() {
        values.add("refresh add ");
        myAadpter.notifyDataSetChanged();

    }

    @Override
    public void init(ListView listView,LayoutInflater inflater) {
        myAadpter=new MyAadpter(inflater);
        listView.setAdapter(myAadpter);
        //对listview 添加点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });

    }


    class MyAadpter extends BaseAdapter{
        LayoutInflater inflater;
        public MyAadpter(LayoutInflater inflater){
            this.inflater=inflater;
        }
        ViewHolder viewHolder=null;
        @Override
        public int getCount() {
            return values.size();
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

            if(convertView==null){
                convertView= inflater.inflate(R.layout.item,null);
                viewHolder=new ViewHolder();
                viewHolder.tv_content= (TextView) convertView.findViewById(R.id.tv_content);
                viewHolder.tv_id= (TextView) convertView.findViewById(R.id.tv_id);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.tv_content.setText(values.get(position));
            viewHolder.tv_id.setText(String.valueOf(position));
            return convertView;
        }


        final  class ViewHolder {
            private TextView tv_id;
            private TextView tv_content;
        }
    }

}
