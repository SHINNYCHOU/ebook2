package com.ebook.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import com.ebook.demo.base.book_info;
import com.ebook.demo.Repository.bookRepository;

public class bookDaoImpl extends UnicastRemoteObject implements bookDao{
    @Autowired
    private bookRepository bookRepository;

    public bookDaoImpl() throws RemoteException {
        super();
    }

    @Override
    public book_info getBook(String name) throws RemoteException {
        return bookRepository.findByName(name);
    }
}
