package com.ebook.demo.controller;

import com.ebook.demo.util.visitorUpdate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:8081","null"},allowCredentials = "true")
@RestController
@RequestMapping(value="/visit",method = {RequestMethod.GET, RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.POST})
public class VisitorController {

    private visitorUpdate c=new visitorUpdate();

    public class counterRun implements Runnable{
        private long count;

        @Override
        public void run() {
           count= c.Get_Visit_Count();
        }

        public long getCount() {
            return count;
        }
    }

    @GetMapping("/index")
    public long Index() throws InterruptedException {
        // 获取访问量信息
        String txtFilePath = "E://third//ebook2//ebook-back//src//main//resources//visit.txt";
        //新线程读取更改访问次数
        counterRun cr=new counterRun();
        Thread t=new Thread(cr);
        t.start();
        t.join();
        Long count = cr.getCount();
        System.out.println(count);
         // 后台参数传递给前端
        return count;
    }

}