package com.ebook.demo.classloader;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class Caeser {
    public static void main(String[] args){
        if (args.length != 3) {
            System.out.println("USAGE: java Caesar in out key");
            return;
        }
        try { FileInputStream in = new FileInputStream(args[0]);
            FileOutputStream out = new FileOutputStream(args[1]);
            int key = Integer.parseInt(args[2]);
            int ch;
            while ((ch = in.read()) != -1) {
                byte c = (byte)(ch + key);
                out.write(c);
            }
            in.close();
            out.close();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void runClass(String name, String key) {
        try {
            ClassLoader loader = new myclassloader(Integer.parseInt(key));
            Class c = loader.loadClass(name);
            String[] args = new String[] {};
            Method m = c.getMethod("main", args.getClass());
            m.invoke(null, (Object) args);
        }
        catch (Throwable e) {
//            JOptionPane.showMessageDialog(this, e);
        }
    }
}

