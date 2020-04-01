package com.ebook.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.ebook.demo.Repository.order_itemRepository;
import com.ebook.demo.base.order_items;
import org.springframework.transaction.annotation.Transactional;

@Service
public class order_itemService implements order_itemServiceInter{
    @Autowired
    private order_itemRepository order_itemRepository;

    private <T> List<T> getList(String lists,Integer spec)
    {
        // if (lists.length()==0)
        List<T> sth = new ArrayList<>();
        for (int i=0;i<lists.length();i++)
        {
            StringBuilder sb = new StringBuilder();
            while (i<lists.length()&&lists.charAt(i)!=',')
            {
                sb.append(lists.charAt(i));
                i++;
            }
            switch (spec)
            {
                case 1: sth.add((T)Integer.valueOf(sb.toString()));break;
                case 2: sth.add((T)sb.toString());break;
                case 3: sth.add((T)Double.valueOf(sb.toString()));break;
                default:
                    break;
            }
            sb.delete(0,sb.length()-1);
        }
        return sth;
    }
    public List<order_items> findByOrder(String orderid){
        //System.out.println(order_itemRepository.findByOrderId(orderid));
        return order_itemRepository.findByOrderId(orderid);
    }

    public List<order_items> findAll(){
        return order_itemRepository.findAll();
    }

    public order_items insert(String oid,String book,double price,int number){
        order_items o=new order_items(oid,book,price,number);
        System.out.println(o);
        order_itemRepository.save(o);
        return o;
    }

    @Transactional
    public int insertList(String oid,String bookList,String priceList,String numberList){
        List<String> books=getList(bookList,2);
        List<Double> prices=getList(priceList,3);
        List<Integer> numbers=getList(numberList,1);
        List<order_items> insertList=new ArrayList<>();
        for(int i = 0;i<books.size();i++){
            order_items orderitem=new order_items(oid,books.get(i),prices.get(i),numbers.get(i));
            insertList.add(orderitem);
            order_itemRepository.save(orderitem);
        }
        //order_itemService.insert(insertList);
        System.out.println(insertList);
        return insertList.size();
    }
}
