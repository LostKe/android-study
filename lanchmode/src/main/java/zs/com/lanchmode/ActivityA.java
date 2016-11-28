package zs.com.lanchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityA extends AppCompatActivity implements View.OnClickListener{

    Button bt;

    Button bt_a_startC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        bt= (Button) findViewById(R.id.bt_a);
        bt.setOnClickListener(this);

        bt_a_startC= (Button) findViewById(R.id.bt_a_startC);
        bt_a_startC.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.bt_a:
                intent.setClass(this,ActivityB.class);
                break;
            case R.id.bt_a_startC:
                intent.setClass(this,ActivityC.class);
                break;
        }

        startActivity(intent);
    }
}
