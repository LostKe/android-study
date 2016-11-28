package zs.com.listview2scrollerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * Listview 和ScrollerView 合用时 冲突问题
 *
 */
public class MainActivity extends AppCompatActivity {

    ListView listView;

    ScrollView scrollView;

    private static List<String> values=new ArrayList<String>();

    static {
        values.add("android");
        values.add("ios");
        values.add("c#");
        values.add("felix");
        values.add("osgi");
        values.add("linux");
        values.add("ios");
        values.add("windows");
        values.add("nokia");
        values.add("php");
        values.add("java");
        values.add("mysql");
        values.add("html");
        values.add("git");
        values.add("cdn");
        values.add("yun");
        values.add("centors");
        values.add("db2");
        values.add("3d");
        values.add("git");
        values.add("cdn");
        values.add("yun");
        values.add("centors");
        values.add("db2");
        values.add("3d");
        values.add("math");
        values.add("ff");
        values.add("zz");
        values.add("kk");
        values.add("3d");
        values.add("3d");
        values.add("3d");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ListView) findViewById(R.id.lv);
        listView.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        listView.setAdapter(new MyAdapter());

        scrollView= (ScrollView) findViewById(R.id.sv);
        scrollView.smoothScrollTo(0,0);
    }



    class MyAdapter extends BaseAdapter{
        ViewHolder viewHolder;
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
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView =LayoutInflater.from(MainActivity.this).inflate(R.layout.item,null);
                viewHolder=new ViewHolder();
                viewHolder.textView= (TextView) convertView.findViewById(R.id.item_value);
            }
            viewHolder.textView.setText(values.get(position));
            return convertView;
        }
    }

    final  class ViewHolder{
        public TextView textView;
    }


}
