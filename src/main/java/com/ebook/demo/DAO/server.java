package com.ebook.demo.DAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

import com.ebook.demo.base.book_info;

public class server {
    public static void main(String[] args) throws
            RemoteException, NamingException {
        bookDaoImpl centralWarehouse = new bookDaoImpl();
        Context namingContext = new InitialContext();
        namingContext.bind("rmi:central_warehouse", centralWarehouse);
    }
}
