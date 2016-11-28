package zs.com.gridview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    GridView gridView;

    GridAdpter adpter;

    SwipeRefreshLayout swipeRefreshLayout;

    public List<Item> ITEMS = new ArrayList<Item>();


    private void init() {
        for (int i = 0; i < 40; i++) {
            Item item = new Item();
            item.setResId(R.drawable.pic12);
            item.setText("图片" + i);
            ITEMS.add(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(this);
        init();
        adpterItems();
        gridView = (GridView) findViewById(R.id.gridView);
        adpter = new GridAdpter(this,ITEMS);
//        adpter.setData(ITEMS);
        gridView.setAdapter(adpter);

    }

    private void adpterItems() {
        int layout_width = (int) getResources().getDimension(R.dimen.itemsize);
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int desplay_width = windowManager.getDefaultDisplay().getWidth();
        int cloum = desplay_width / layout_width;
        //不足一行的个数
        int more = ITEMS.size() % cloum;
        //集合中去掉最后不足一行的
//        if (more > 0) {
//            int maxIndex = ITEMS.size() - 1;
//            for (int i = 0; i < more; i++) {
//                ITEMS.remove(maxIndex - i);
//            }
//        }
        Toast.makeText(this, String.valueOf(cloum), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {

        Item item = new Item();
        item.setResId(R.drawable.pic8);
        item.setText("add图片");
        ITEMS.add(item);
        adpterItems();

        adpter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}
