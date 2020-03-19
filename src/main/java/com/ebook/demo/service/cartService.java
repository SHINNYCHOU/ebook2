package com.ebook.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import com.ebook.demo.base.cart;
import com.ebook.demo.Repository.cartRepository;

@Service
@Transactional
@Scope("session")
public class cartService implements cartServiceInter{
    @Autowired
    private cartRepository cartRepository;

    private List<cart> cartNow = new ArrayList<>();
    public cart findByNameAndBook(String name,String book){
//        cartNow=cartRepository.findByNameAndBook(name,book);
        return cartRepository.findByNameAndBook(name,book);
    }
    public List<cart> findByName(String name){
        if (this.cartNow.size()!=0){
            return this.cartNow;
        }
        else{
            this.cartNow=cartRepository.findByName(name);
            return cartRepository.findByName(name);
        }
    }
    public cart insert(cart cart){
        //if cart not exist
        this.cartNow.add(cart);
        //cartlist have this cart
        return cartRepository.save(cart);
    }
    public cart update(cart cart){
        //遍历更改旧cart数目
        for (cart c:cartNow){
            if (c.getName()==cart.getName()&&c.getName()==cart.getName()){
                c.setNumber(cart.getNumber());
            }
        }
        return cartRepository.save(cart);
    }

    @Modifying
    public cart delete(String name,String book){
       cart cart=cartRepository.findByNameAndBook(name,book);
        int id=cart.getId();
       cartRepository.deleteById(id);
       this.cartNow.remove(cart);
       return cart;
    }

}
