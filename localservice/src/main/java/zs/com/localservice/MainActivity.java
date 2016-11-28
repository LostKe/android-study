package zs.com.localservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import zs.com.remoteservice.aidl.Book;
import zs.com.remoteservice.aidl.IBookManager;
import zs.com.remoteservice.aidl.IOnNewBookArrivedListener;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    ListView lv;
    Button bt;
    Button cryp;
    BookAdpter adpter;
    List<Book> bookList;
    ServiceConnection conn;
    IBookManager bookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBinder();
        initLayout();
        initContent();
    }

    private void initContent() {
        adpter=new BookAdpter();
        lv.setAdapter(adpter);

    }

    private void initLayout() {
        lv= (ListView) findViewById(R.id.lv);
        bt= (Button) findViewById(R.id.bt);
        bt.setOnClickListener(this);
        cryp= (Button) findViewById(R.id.cryp);
        cryp.setOnClickListener(this);

    }

    private void initBinder() {
        Intent intent=new Intent();
        intent.setAction("zs.com.remote");
        conn=new BookServiceConn();
        bindService(intent,conn,BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        try {

            switch (v.getId()){
                case R.id.bt:
                   // Toast.makeText(this,"click",Toast.LENGTH_SHORT).show();
                    bookList=bookManager.getBookList();
                    int index=bookList.size()+1;
                    //如果远程方法是一个耗时操作不能在UI线程中发起此请求
                    bookManager.addBook(new Book(index,"hero---"+index));

                    adpter.notifyDataSetChanged();

                    break;
                case R.id.cryp:
                    Intent intent=new Intent();
                    intent.setClass(this,CrypActivity.class);
                    startActivity(intent);
                    break;

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Book book= (Book) msg.obj;
                    Log.d("local-client----->",book
                            .getBookName());
                    adpter.notifyDataSetChanged();
                    break;
            }
        }
    };

    class BookServiceConn implements ServiceConnection{


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager= IBookManager.Stub.asInterface(service);

            try {
                //设置死亡代理
                service.linkToDeath(mDeathRecipient,0);
                bookManager.registerOnNewBookArrivedListener(new OnNewBookArrivedListener());
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }


    class OnNewBookArrivedListener extends IOnNewBookArrivedListener.Stub {

        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {


            handler.obtainMessage(1,newBook).sendToTarget();
        }

    }

    class BookAdpter extends BaseAdapter{

        ViewHolder viewHolder;

        @Override
        public int getCount() {
            try{
                bookList=bookManager.getBookList();
            }catch (Exception e){
                e.printStackTrace();
            }

            if(bookList==null){
                return  0;
            }else{
                return bookList.size();
            }

        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.item,null);
                viewHolder=new ViewHolder();
                viewHolder.item_bookName= (TextView) convertView.findViewById(R.id.item_bookName);
                viewHolder.item_id= (TextView) convertView.findViewById(R.id.item_id);
            }
            Book book=bookList.get(position);
            viewHolder.item_bookName.setText(book.getBookName());
            viewHolder.item_id.setText(String.valueOf(book.getBookId()));
            return convertView;
        }

        final class ViewHolder{
            public TextView item_id;
            public  TextView item_bookName;

        }
    }


    private IBinder.DeathRecipient mDeathRecipient=new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if(bookManager==null){
                return;
            }else{
                bookManager.asBinder().unlinkToDeath(mDeathRecipient,0);
                bookManager=null;
                //重新绑定远程
                initBinder();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
