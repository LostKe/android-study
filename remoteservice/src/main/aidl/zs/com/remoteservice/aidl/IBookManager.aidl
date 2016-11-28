// IBookManager.aidl
package zs.com.remoteservice.aidl;

// Declare any non-default types here with import statements
import zs.com.remoteservice.aidl.Book;

import zs.com.remoteservice.aidl.IOnNewBookArrivedListener;
interface IBookManager {
   List<Book> getBookList();

   void addBook(in Book book);

   void registerOnNewBookArrivedListener(IOnNewBookArrivedListener listener);

   void unregisterOnNewBookArrivedListener(IOnNewBookArrivedListener listener);
}
