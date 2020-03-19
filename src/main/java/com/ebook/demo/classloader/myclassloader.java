package com.ebook.demo.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class myclassloader extends ClassLoader{
    private int key;
    public myclassloader(int k){
        key=k;
    }
    protected Class findClass(String name) throws ClassNotFoundException{
        byte[] classBytes = null;
        try {
            classBytes = loadClassBytes(name);
        }
        catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
        Class cl = defineClass(name, classBytes, 0, classBytes.length);
        if (cl == null)
            throw new ClassNotFoundException(name);
        return cl;
    }
    private byte[] loadClassBytes(String name) throws IOException{
        String cname = name.replace('.', '/') + ".caesar";
        FileInputStream in = null;
        in = new FileInputStream(cname);
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int ch;
            while ((ch = in.read()) != -1) {
                byte b = (byte) (ch - key);
                buffer.write(b);
            }
            in.close();
            return buffer.toByteArray();
        }
        finally {
            in.close();
        }
    }

}
