package zs.com.localservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import zs.com.remoteservice.aidl.IBinderFactory;

/**
 * Created by zhangshuqing on 16/10/2.
 */
public class BinderPool {

    Context context;

    IBinderFactory factory;


    BinderPool(Context context){
        this.context=context;
        connectService();
    }

    private void connectService() {
        Intent intent=new Intent();
        intent.setAction("zs.com.factory");
        context.bindService(intent,con,Context.BIND_AUTO_CREATE);

    }


    public IBinder getBinder(int qryCode) throws RemoteException {
        return  factory.queryBinder(qryCode);
    }

    ServiceConnection con=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            factory=IBinderFactory.Stub.asInterface(service);

            try {
                service.linkToDeath(deathRecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    IBinder.DeathRecipient deathRecipient=new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            //重连服务
            connectService();
        }
    };


}
