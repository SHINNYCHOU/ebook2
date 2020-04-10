package com.ebook.demo.dao.daoImpl;

import com.alibaba.fastjson.JSONArray;
import com.ebook.demo.Repository.bookRepository;
import com.ebook.demo.base.book_info;
import com.ebook.demo.dao.bookDao;
import com.ebook.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class bookDaoImpl implements bookDao {
    @Autowired
    private bookRepository bookrepository;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<book_info> findAll() {

        return bookrepository.findAll();
    }

    @Override
    public book_info insertByBook(book_info book) {
        bookrepository.save(book);
        return book;
    }

    @Override
    public book_info update(book_info book) {
        bookrepository.save(book);
        return book;
    }

    @Override
    public void delete(String isbn) {
        System.out.println("ser:");
        System.out.println(isbn);
        bookrepository.deleteById(isbn);

    }

    @Override
    public book_info findByName(String name) {
        return bookrepository.findByName(name);
    }

    @Override
    public book_info findByIsbn(String isbn) {
        book_info book=null;
        Object p = redisUtil.get("book" + isbn);
        if (p == null) {
//            System.out.println("book: " + isbn + " is not in Redis");
//            System.out.println("Searching book: " + isbn + " in DB");
            book = bookrepository.getOne(isbn);
            redisUtil.set("book" + isbn, JSONArray.toJSON(book));
        } else {
            book = JSONArray.parseObject(p.toString(), book_info.class);
            System.out.println("book: " + isbn + " is in Redis");

        }
        return book;
    }
}
