// IBinderFactory.aidl
package zs.com.remoteservice.aidl;

// Declare any non-default types here with import statements

interface IBinderFactory {

   IBinder queryBinder(in int code);
}
