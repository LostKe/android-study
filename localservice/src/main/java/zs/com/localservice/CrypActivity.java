package zs.com.localservice;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import zs.com.remoteservice.aidl.IDecode;
import zs.com.remoteservice.aidl.IEncode;

/**
 * Created by zhangshuqing on 16/10/2.
 */
public class CrypActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText;

    Button decode;

    Button encode;

    BinderPool pool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryp);
        editText= (EditText) findViewById(R.id.et);
        decode= (Button) findViewById(R.id.decode);
        decode.setOnClickListener(this);
        encode= (Button) findViewById(R.id.encode);
        encode.setOnClickListener(this);
        pool=new BinderPool(this);

    }
    @Override
    public void onClick(View v) {
        String text=editText.getText().toString();
        if(TextUtils.isEmpty(text)){
            Toast.makeText(this,"请输入",Toast.LENGTH_SHORT).show();
            return;
        }
        IBinder binder=null;
        switch (v.getId()){

            case R.id.decode:
                try {
                    binder= pool.getBinder(1);
                    IDecode decode=  IDecode.Stub.asInterface(binder);
                    editText.setText(decode.deCodeString(text));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                //设置解密字符串
                break;
            case R.id.encode:
                //设置加密字符串
                try {
                    binder= pool.getBinder(2);
                    IEncode encode=IEncode.Stub.asInterface(binder);
                    editText.setText(encode.endCode(text));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
