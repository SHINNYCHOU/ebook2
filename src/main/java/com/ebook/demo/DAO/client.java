package com.ebook.demo.DAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Scanner;

import com.ebook.demo.base.book_info;

public class client {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context namingContext = new InitialContext();

        String url = "rmi://localhost/central_warehouse";
        bookDao centralWarehouse = (bookDao) namingContext.lookup(url);

//        NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
        Scanner in = new Scanner(System.in);
        System.out.print("Enter keywords: ");
        String keyword=in.next();
        book_info book = centralWarehouse.getBook(keyword);
        System.out.println(book.toString());
    }
}
