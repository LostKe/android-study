package zs.com.musicplayer;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import zs.com.musicplayer.zs.com.fragment.FragmentContact;
import zs.com.musicplayer.zs.com.fragment.FragmentDiscover;
import zs.com.musicplayer.zs.com.fragment.FragmentMe;
import zs.com.musicplayer.zs.com.fragment.FragmentWeixin;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button bt_weixin;
    private Button bt_contact;
    private Button bt_discover;
    private Button bt_me;
    private LinearLayout ll_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        bt_weixin=(Button) findViewById(R.id.bt_weixin);
        bt_weixin.setOnClickListener(this);
        bt_contact= (Button) findViewById(R.id.bt_contact);
        bt_contact.setOnClickListener(this);
        bt_discover= (Button) findViewById(R.id.bt_discover);
        bt_discover.setOnClickListener(this);
        bt_me= (Button) findViewById(R.id.bt_me);
        bt_me.setOnClickListener(this);

        ll_content = (LinearLayout) findViewById(R.id.ll_content);
    }

    @Override
    public void onClick(View view) {
       FragmentTransaction mTransaction= getFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.bt_weixin:
                Toast.makeText(this,"click",Toast.LENGTH_LONG).show();
                mTransaction.replace(R.id.ll_content,new FragmentWeixin(),"weixin");
                break;
            case R.id.bt_contact:
                mTransaction.replace(R.id.ll_content,new FragmentContact(),"contact");
                break;
            case R.id.bt_discover:
                mTransaction.replace(R.id.ll_content,new FragmentDiscover(),"discover");
                break;
            case R.id.bt_me:
                mTransaction.replace(R.id.ll_content,new FragmentMe(),"me");
                break;

        }
        mTransaction.commit();
    }
}
