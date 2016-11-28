package zs.com.remoteservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import zs.com.remoteservice.aidl.IBinderFactory;

/**
 * Created by zhangshuqing on 16/10/2.
 */
public class BinderFactoryService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new BinderFacory();
    }

    class BinderFacory extends IBinderFactory.Stub {
        IBinder binder;
        @Override
        public IBinder queryBinder(int code) throws RemoteException {
            switch (code) {
                case 1:
                    binder=new DecodeImpl();

                    break;
                case 2:
                    binder=new EncodeImpl();

                    break;
                default:
                    break;
            }
            return binder;
        }
    }
}
