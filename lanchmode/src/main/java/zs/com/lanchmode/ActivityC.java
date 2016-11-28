package zs.com.lanchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActivityC extends AppCompatActivity implements View.OnClickListener {

    Button bt;

    Button bt_startB;

    final static String TAG="ActivityC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        bt= (Button) findViewById(R.id.bt_c);
        bt.setOnClickListener(this);

        bt_startB= (Button) findViewById(R.id.bt_c_startB);
        bt_startB.setOnClickListener(this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent =new Intent();
        switch (v.getId()){
            case R.id.bt_c:
                intent.setClass(this,ActivityC.class);
                break;
            case R.id.bt_c_startB:
                intent.setClass(this,ActivityB.class);
                break;
        }


        startActivity(intent);
    }
}
