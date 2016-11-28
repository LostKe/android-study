package zs.com.slide;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textView;

    Button bt_move;
    Button bt_animator;
    Button bt_scroller;

    CustomView customView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.tv);
        bt_move= (Button) findViewById(R.id.bt_move);
        bt_animator= (Button) findViewById(R.id.bt_animator);
        bt_scroller= (Button) findViewById(R.id.bt_scroller);
        customView= (CustomView) findViewById(R.id.customView);
        bt_move.setOnClickListener(this);
        bt_animator.setOnClickListener(this);
        bt_scroller.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_move:
                //移动的是内容
                textView.scrollTo(-100,0);
                //textView.scrollBy(-100,0);
                break;
            case R.id.bt_animator:
                ObjectAnimator objectAnimator = new ObjectAnimator();
                objectAnimator.ofFloat(textView,"translationX",100).setDuration(1000).start();
                break;
            case R.id.bt_scroller://需要配合自定义view使用
                customView.smoothScrollTo(-100,0);

                break;

        }
    }


}
