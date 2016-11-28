package zs.com.remoteservice.service;

import android.os.RemoteException;
import android.util.Log;

import zs.com.remoteservice.aidl.IEncode;

/**
 * Created by zhangshuqing on 16/10/2.
 */
public class EncodeImpl extends IEncode.Stub {
    @Override
    public String endCode(String str) throws RemoteException {
        Log.e("EncodeImpl>>>>>>>>>","加密系统远程服务被调用");
        return str.toUpperCase();
    }
}
