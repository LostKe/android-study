package zs.com.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    @BindView(R.id.bt_add)
    Button bt_add;


    @BindView(R.id.lv)
    ListView listView;

    private MyAdpter adpter;

    private static List<String> values = new ArrayList<String>();

    static {
        values.add("aaaa");
        values.add("bbbb");
        values.add("cccc");
        values.add("dddd");
        values.add("eeee");
        values.add("ffff");
        values.add("gggg");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //bt_add = (Button) findViewById(R.id.bt_add);
        bt_add.setOnClickListener(this);
        //listView = (ListView) findViewById(R.id.lv);
        adpter = new MyAdpter(this);
        listView.setAdapter(adpter);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                values.add("add item");
                adpter.notifyDataSetChanged();
                listView.setSelection(values.size() - 1);
                break;
        }

    }

    class MyAdpter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;

        MyAdpter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return values.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = inflater.inflate(R.layout.item, null);
                viewHolder.tv_id = (TextView) view.findViewById(R.id.tv_id);
                viewHolder.tv_content = (TextView) view.findViewById(R.id.tv_content);
                view.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) view.getTag();

            }
            viewHolder.tv_content.setText(values.get(i));
            viewHolder.tv_id.setText(String.valueOf(i));
            return view;
        }


        private final class ViewHolder {
            private TextView tv_id;
            private TextView tv_content;
        }
    }


}
