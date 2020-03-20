package com.ebook.demo.classloader;

public class Main {
    public static void main(String[] args)throws ClassNotFoundException, IllegalAccessException, InstantiationException{
        if (args.length != 2) {
            System.out.println("USAGE: java decode in key");
            return;
        }
        int i = Integer.parseInt(args[1]);
        myclassloader classloader=new myclassloader(i);
        Class c=classloader.findClass(args[0]);
//        Object o=c.
        System.out.println(c);
    }
}
