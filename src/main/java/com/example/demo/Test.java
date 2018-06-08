package com.example.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.util.LinkedList;

/**
 * @Author TAO
 * @Date 2018/3/16 18:45
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<Object> objects = new LinkedList<>();
        FileInputStream f = new FileInputStream("");
        FileChannel channel = f.getChannel();
    }
}
