package zs.com.remoteservice.service;

import android.os.RemoteException;
import android.util.Log;

import zs.com.remoteservice.aidl.IDecode;

/**
 * Created by zhangshuqing on 16/10/2.
 */
public class DecodeImpl extends IDecode.Stub {
    @Override
    public String deCodeString(String str) throws RemoteException {
        Log.e("DecodeImpl>>>>>>>>>","解密系统远程服务被调用");
        return str.toLowerCase();
    }
}
