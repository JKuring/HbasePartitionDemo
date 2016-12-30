package com.example;


import com.example.dao.HBaseDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by linghang.kong on 2016/12/21.
 */
public class HBaseDaoImplTest {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        HBaseDaoImpl hbaseDaoImpl = context.getBean("hbaseDaoImpl", HBaseDaoImpl.class);
        hbaseDaoImpl.close();

    }

}