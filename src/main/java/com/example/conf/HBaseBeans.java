package com.example.conf;

/**
 * Created by linghang.kong on 2016/12/21.
 */
//@Configuration
//@ImportResource("/conf/hbase/hadoop-hbase-beans.xml")
public class HBaseBeans {
//
//
//    @Autowired
//    private HBaseKerberos hBaseKerberos;
//    @Autowired
////    @Qualifier("hbaseConfiguration")
//    private org.apache.hadoop.conf.Configuration configuration;
//
//
////    @Bean(name = "hBaseConfiguration")
////    public org.apache.hadoop.conf.Configuration getSpringConfiguration() {
////        configuration = hBaseKerberos.getConfiguration(HBaseConfiguration.create());
////        return configuration;
////    }
//
//    @Bean(name = "hbaseConnection")
//    @Scope("prototype")
//    public Connection getConnection(org.apache.hadoop.conf.Configuration configuration) throws IOException {
//        configuration = HBaseKerberos.getConfiguration(HBaseConfiguration.create());
//        return ConnectionFactory.createConnection(configuration, Executors.newFixedThreadPool(10));
//    }
}
