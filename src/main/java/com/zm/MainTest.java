package com.zm;


import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Description: main测试
 * @Author: zhoumin
 * @Date: created in 2018-01-18 10:31
 **/
public class MainTest {
    public static void main(String[] args) {
        /*List<Integer> list = Lists.newArrayList();
        for (int i=1 ; i<11 ; i++){
            list.add(i);
        }*/

        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String [] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

    }
}
