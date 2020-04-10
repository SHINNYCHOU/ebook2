package com.ebook.demo.service;

import com.ebook.demo.base.book_info;
import com.ebook.demo.Repository.bookRepository;
import com.ebook.demo.dao.bookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class bookServiceImpl implements bookService{
    @Autowired
    private bookDao bookDao;

    @Override
    public List<book_info> findAll() {
        return bookDao.findAll();
    }

    @Override
    public book_info insertByBook(book_info book) {
        bookDao.insertByBook(book);
        return book;
    }

    @Override
    public book_info update(book_info book) {
        bookDao.update(book);
        return book;
    }

   @Override
    public void delete(String isbn) {
        System.out.println("ser:");
        System.out.println(isbn);
        bookDao.delete(isbn);
    }

    @Override
    public book_info findByName(String name){
        return bookDao.findByName(name);
    }

    @Override
    public book_info findByIsbn(String id) {
        return bookDao.findByIsbn(id);
    }


}
