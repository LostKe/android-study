package zs.com.topbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import zs.com.topbar.widget.TopBar;

public class OtherActivity extends AppCompatActivity implements TopBar.TopBarClickListener{

    TopBar topBar;


    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        topBar= (TopBar) findViewById(R.id.topbar);
        topBar.setOnTopBarClickListener(this);
        topBar.setVisable(TopBar.TITLE,false);


    }

    @Override
    public void leftButtionClick() {
        Toast.makeText(this,"左边按钮被点击",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void rightButtonClick() {
        Toast.makeText(this,"右边按钮被点击",Toast.LENGTH_SHORT).show();
    }


}
