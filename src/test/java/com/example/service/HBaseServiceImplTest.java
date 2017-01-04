package com.example.service;

import com.example.data.entity.HBaseEntityImpl;
import com.example.interfaces.dto.HBaseEntity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by linghang.kong on 2017/1/3.
 */
public class HBaseServiceImplTest {
    @Test
    public void getName() throws Exception {

    }

    @Test
    public void setName() throws Exception {

    }

    @Test
    public void createTable() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        HBaseServiceImpl hbaseServiceImpl = context.getBean("HBaseServiceImpl", HBaseServiceImpl.class);
        HBaseEntity hBaseEntity = context.getBean("xdr_data:ps_gn_http_event", HBaseEntityImpl.class);
//        JobEntity jobEntity = context.getBean("xdr_data:ps_gn_http_event_D", JobEntityImpl.class);
//        hbaseServiceImpl.createSchedulerJob(jobEntity);
        hbaseServiceImpl.createTable(hBaseEntity);
        logger.info("current time: {}.",System.currentTimeMillis());
        Thread.sleep(5*60*1000);
        hbaseServiceImpl.delete(hBaseEntity);

    }

    @Test
    public void getTableNames() throws Exception {

    }

    @Test
    public void partition() throws Exception {

    }

    @Test
    public void createSchedulerJob1() throws Exception {

    }

    @Test
    public void close() throws Exception {

    }

    private static final Logger logger = LoggerFactory.getLogger(HBaseServiceImplTest.class);
    @Test
    public void createSchedulerJob() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        HBaseServiceImpl hbaseServiceImpl = context.getBean("HBaseServiceImpl", HBaseServiceImpl.class);
        HBaseEntity hBaseEntity = context.getBean("xdr_data:ps_gn_http_event", HBaseEntityImpl.class);
//        JobEntity jobEntity = context.getBean("xdr_data:ps_gn_http_event_D", JobEntityImpl.class);
//        hbaseServiceImpl.createSchedulerJob(jobEntity);
        hbaseServiceImpl.partition(hBaseEntity);
        logger.info("current time: {}.",System.currentTimeMillis());
        Thread.sleep(60*60*1000);
    }

}