package com.ebook.demo.service;
import com.ebook.demo.base.order;
import com.ebook.demo.Repository.orderRepository;
import com.ebook.demo.base.order_items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class orderService implements orderServieInter{
    @Autowired
    private orderRepository orderrepo;
    @Autowired
    private order_itemService orderitemservice;

    public List<order> findAll(){

        return orderrepo.findAll();
    }
    public order findById(String id){

        return orderrepo.getOne(id);
    }
    public List<order> findAll(String uid){

        return orderrepo.findByBuyerId(uid);
    }
    public List<order> findOneTime(Timestamp time1,Timestamp time2,String uid){
        // return orderrepo.findByTimeAndBuyerId(time,uid);
        return orderrepo.findByTimeBetweenAndBuyerId(time1,time2,uid);
    }
//    public order insertByOrder(order order){
//
//        return orderrepo.save(order);
//    }

    @Transactional
    public order insert(String uid,double price,int number,String book){
        String id=getOrderIdByTime();
        Timestamp time= new Timestamp(System.currentTimeMillis());
        double Tprice=price*number;
        order o=new order(id,uid,Tprice,"unpay",time);
//        order_items orderitem=new order_items(id,book,price,number);
//        order_itemService order_itemservice=new order_itemService();
        orderitemservice.insert(id,book,price,number);
        return orderrepo.save(o);
    }

    @Transactional
    public order insertList(String uid,String bookList,String priceList,String numberList,double price){
        String oid=getOrderIdByTime();
        Timestamp time= new Timestamp(System.currentTimeMillis());
        order o=new order(oid,uid,price,"unpay",time);
        //detail
//        order_itemService order_itemservice=new order_itemService();
        orderitemservice.insertList(oid,bookList,priceList,numberList);

        return orderrepo.save(o);
    }

    public order update(order order){

        return orderrepo.save(order);
    }
    public void delete(String id){
        orderrepo.deleteById(id);
    }

    public static String getOrderIdByTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result=result+random.nextInt(10);
        }
        return newDate+result;
    }
}