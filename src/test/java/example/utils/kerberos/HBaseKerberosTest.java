package example.utils.kerberos;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by linghang.kong on 2016/12/19.
 */
public class HBaseKerberosTest {
    private static final Logger logger = LoggerFactory.getLogger(HBaseKerberosTest.class);


    @Test
    public void getConfiguration() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("conf/hbase/hbase-beans.xml");
//        HBaseKerberos hBaseKerberos = context.getBean("hBaseKerberos", HBaseKerberos.class);
//        Configuration configuration = context.getBean("hbaseSpringConfiguration", Configuration.class);
//        configuration = hBaseKerberos.getConfiguration(configuration);
//        HBaseAdmin admin = new HBaseAdmin(configuration);
//        admin.close();
//        logger.info("+++++++++++++++++" + File.separator);
//        HbaseTemplate hbaseTemplate = context.getBean("hbaseTemplate", HbaseTemplate.class);
//        hbaseTemplate.execute("MyTable", table -> {
//            Put p = new Put(Bytes.toBytes("SomeRow"));
//            p.add(Bytes.toBytes("SomeColumn"), Bytes.toBytes("SomeQualifier"), Bytes.toBytes("AValue"));
//            table.put(p);
//            return null;
//        });
    }

}