package com.ebook.demo.DAO;

import com.ebook.demo.base.book_info;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface  bookDao extends Remote {
    book_info getBook(String name)throws RemoteException;
}
