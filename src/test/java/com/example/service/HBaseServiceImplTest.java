package com.example.service;

import com.example.data.entity.JobEntityImpl;
import com.example.interfaces.dto.JobEntity;
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
    public void delete() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/hbase/conf/beans.xml");
        HBaseServiceImpl hbaseServiceImpl = context.getBean("HBaseServiceImpl", HBaseServiceImpl.class);
//        HBaseEntity hBaseEntity = context.getBean("xdr_data:ps_gn_http_event_K", HBaseEntityImpl.class);
        JobEntity jobEntity = context.getBean("xdr_data:ps_gn_http_event_job", JobEntityImpl.class);
//        hbaseServiceImpl.createSchedulerJob(jobEntity);
//        hbaseServiceImpl.createTable(hBaseEntity);
//        logger.info("current time: {}.",System.currentTimeMillis());
        hbaseServiceImpl.delete(jobEntity);
                Thread.sleep(3*60*1000);
    }


    @Test
    public void getName() throws Exception {

    }

    @Test
    public void setName() throws Exception {

    }

    @Test
    public void createTable() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/hbase/conf/beans.xml");
        HBaseServiceImpl hbaseServiceImpl = context.getBean("HBaseServiceImpl", HBaseServiceImpl.class);
//        HBaseEntity hBaseEntity = context.getBean("xdr_data:ps_gn_http_event_K", HBaseEntityImpl.class);
        JobEntity jobEntity = context.getBean("xdr_data:ps_gn_http_event_job", JobEntityImpl.class);
//        hbaseServiceImpl.createSchedulerJob(jobEntity);
        hbaseServiceImpl.createTable(jobEntity);
        logger.info("current time: {}.",System.currentTimeMillis());
        Thread.sleep(3*60*1000);

    }

    @Test
    public void getTableNames() throws Exception {

    }

    @Test
    public void partition() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/hbase/conf/beans.xml");
        HBaseServiceImpl hbaseServiceImpl = context.getBean("HBaseServiceImpl", HBaseServiceImpl.class);
//        HBaseEntity hBaseEntity = context.getBean("xdr_data:ps_gn_http_event_K", HBaseEntityImpl.class);
        JobEntity jobEntity = context.getBean("xdr_data:ps_gn_http_event_job", JobEntityImpl.class);
//        hbaseServiceImpl.createSchedulerJob(jobEntity);
        hbaseServiceImpl.partition(jobEntity);
        logger.info("current time: {}.",System.currentTimeMillis());
        Thread.sleep(3*60*1000);
    }


    @Test
    public void close() throws Exception {

    }

    private static final Logger logger = LoggerFactory.getLogger(HBaseServiceImplTest.class);
    @Test
    public void createSchedulerJob() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/hbase/conf/beans.xml");
        HBaseServiceImpl hbaseServiceImpl = context.getBean("HBaseServiceImpl", HBaseServiceImpl.class);

        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:ps_gn_pdp_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:ps_gn_pdp_event_ipapn_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:ps_gn_dns_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:ps_gn_ftp_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:ps_gn_general_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:ps_gn_http_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:ps_gn_im_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:ps_gn_mms_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:ps_gn_p2p_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:ps_gn_rtsp_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:ps_gn_voip_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:mc_switch_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:mc_poweronoff_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:mc_locationupdate_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:mc_voicecall_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:mc_voicecall_event_callee_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:mc_paging_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:mc_paging_event_callee_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:mc_smssendrecieve_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:mc_smssendrecieve_event_callee_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:nc_bicc_event_job", JobEntityImpl.class));
        hbaseServiceImpl.createSchedulerJob(context.getBean("xdr_data:nc_bicc_event_callee_job", JobEntityImpl.class));

//        hbaseServiceImpl.partition(jobEntity);
        logger.info("current time: {}.",System.currentTimeMillis());
        Thread.sleep(20*60*1000);
    }

}