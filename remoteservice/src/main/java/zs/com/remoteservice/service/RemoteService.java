package zs.com.remoteservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import zs.com.remoteservice.aidl.Book;
import zs.com.remoteservice.aidl.IBookManager;
import zs.com.remoteservice.aidl.IOnNewBookArrivedListener;

/**
 *
 *
 * [Once you've implemented the interface for your service, you need to expose it to clients so they can
 * bind to it. To expose the interface for your service, extend Service and implement onBind() to return
 * an instance of your class that implements the generated Stub (as discussed in the previous section). ]
 *
 *
 *aidl通过service向外提供服务
 * Created by zhangshuqing on 16/9/10.
 */
public class RemoteService extends Service {

    //RemoteCallbackList 可以删除跨进程的listener对象
    private RemoteCallbackList<IOnNewBookArrivedListener>  mListenerList=new RemoteCallbackList<IOnNewBookArrivedListener>();

    private List<Book> mBookList=new CopyOnWriteArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1,"android"));
        mBookList.add(new Book(1,"ios"));

        //设置定时添加书
        //Timer timer=new Timer();
        //timer.scheduleAtFixedRate(new BookTask(),100,1000);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(RemoteService.class.getName(),"RemoteService onBind>>>>>>>>");
        return new BookBinder();
    }



    //这里要考虑多线程同步引起的问题
    private class BookBinder extends IBookManager.Stub {


        @Override
        public  List<Book> getBookList() throws RemoteException {
            return  mBookList;
        }

        @Override
        public   void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerOnNewBookArrivedListener(IOnNewBookArrivedListener listener) throws RemoteException {
                mListenerList.register(listener);

        }

        @Override
        public void unregisterOnNewBookArrivedListener(IOnNewBookArrivedListener listener) throws RemoteException {
                mListenerList.unregister(listener);
        }
    }

    private void notifyNewBookArrived(Book book) throws  RemoteException{

       int i= mListenerList.beginBroadcast();
        while(i>0){
            i--;
            mListenerList.getBroadcastItem(i).onNewBookArrived(book);
        }
        mListenerList.finishBroadcast();
    }


    class BookTask extends TimerTask{

        @Override
        public void run() {
            try {
                int index=mBookList.size();
                Book book=new Book(index,"remote service add book-----");
                //通知 有新的内容
                notifyNewBookArrived(book);
                mBookList.add(book);
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }

}
