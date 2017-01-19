package com.example;


import com.example.data.entity.JobEntityImpl;
import com.example.service.HBaseServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws InterruptedException {
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
//        HBaseServiceImpl hbaseServiceImpl = context.getBean("HBaseServiceImpl", HBaseServiceImpl.class);
////        HBaseEntity hBaseEntity = context.getBean("xdr_data:ps_gn_http_event_K", HBaseEntityImpl.class);
//        JobEntity jobEntity = context.getBean("xdr_data:ps_gn_http_event_job", JobEntityImpl.class);
////        hbaseServiceImpl.createSchedulerJob(jobEntity);
//        hbaseServiceImpl.createTable(jobEntity);
////        hbaseServiceImpl.partition(jobEntity);
//        Thread.sleep(3*60*1000);

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
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

        Thread.currentThread().join();

    }
}
