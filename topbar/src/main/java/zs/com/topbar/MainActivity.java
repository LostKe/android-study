package zs.com.topbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import zs.com.topbar.widget.TopBar;

public class MainActivity extends Activity implements TopBar.TopBarClickListener,View.OnClickListener{

    TopBar topBar;


    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topBar= (TopBar) findViewById(R.id.topbar);
        topBar.setOnTopBarClickListener(this);
        bt= (Button) findViewById(R.id.bt);
        bt.setOnClickListener(this);
    }

    @Override
    public void leftButtionClick() {
        Toast.makeText(this,"左边按钮被点击",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void rightButtonClick() {
        Toast.makeText(this,"右边按钮被点击",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt:
                Intent intent=new Intent();
                intent.setClass(getApplicationContext(),OtherActivity.class);
                startActivity(intent);
                break;
        }
    }
}
