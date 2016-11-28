package zs.com.study;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editText= (EditText) findViewById(R.id.et);
        Log.e("zsq>>>>>","onCreate");
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e("zsq>>>>>","onSaveInstanceState");
        outState.putString("save","test");
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e("zsq>>>>>","onRestoreInstanceState");
        String save=savedInstanceState.getString("save");
        Log.e("zsq>>>>>",save);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("zsq>>>>>","onConfigurationChanged:"+newConfig.orientation);
    }
}
