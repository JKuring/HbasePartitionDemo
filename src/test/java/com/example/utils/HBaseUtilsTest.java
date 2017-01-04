package com.example.utils;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Arrays;

/**
 * Created by linghang.kong on 2017/1/4.
 */
public class HBaseUtilsTest {
    @Test
    public void getSplitKeys() throws Exception {
        File file = ResourceUtils.getFile("classpath:conf/hbase/table/splits.txt");
        byte[][] spiltKeys = HBaseUtils.getSplitKeys(file);
        int i = 0;
        for (byte[] key: spiltKeys
             ) {
            System.out.println(Arrays.toString(key));
            System.out.println(i++);
        }
    }

}