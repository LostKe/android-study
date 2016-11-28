package zs.com.deleteview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import zs.com.deleteview.zs.com.deleteview.widget.DeleteRecyclerView;
import zs.com.deleteview.zs.com.deleteview.widget.RecyclerAdapter;

/**
 * 利用RecycleView实现  仿IOS 侧滑删除效果
 */

public class MainActivity extends AppCompatActivity {

    DeleteRecyclerView recyclerView;


    static List<String> dataList = new ArrayList<>();

    static {
        dataList.add("第一项");
        dataList.add("第二项");
        dataList.add("第三项");
        dataList.add("第四项");
        dataList.add("第五项");
        dataList.add("第六项");
        dataList.add("第七项");
        dataList.add("第八项");
        dataList.add("第九项");
        dataList.add("第十项");
    }

   RecyclerAdapter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (DeleteRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adpter=new RecyclerAdapter(this,dataList));
    }


}
