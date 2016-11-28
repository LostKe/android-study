// IOnNewBookArrivedListener.aidl
package zs.com.remoteservice.aidl;
import zs.com.remoteservice.aidl.Book;
// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
