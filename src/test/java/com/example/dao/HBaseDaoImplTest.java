package com.example.dao;

import com.example.utils.HBaseUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Created by linghang.kong on 2017/1/6.
 */
public class HBaseDaoImplTest {
    @Test
    public void getConfiguration() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        HBaseDaoImpl hbaseDaoImpl = context.getBean("hbaseDaoImpl", HBaseDaoImpl.class);
        System.err.println(Arrays.asList(HBaseUtils.getConnection(hbaseDaoImpl.getConfiguration()).getTableNames()));
    }
}