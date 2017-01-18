package mytest;

import com.example.utils.HBaseUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by linghang.kong on 2016/12/29.
 */
public class MyTest {

    public static void main(String[] args) throws IOException {
//        Test.testIP("shyp-bigdata-b-cn01", 2181);
        String configurationPath= new File("").getAbsolutePath()+System.getProperty("file.separator");
        Configuration configuration = HBaseConfiguration.create();
//        configuration.addResource(new Path(configurationPath+"target/conf/hbase/conf/core-site.xml"));
//        configuration.addResource(new Path(configurationPath+"target/conf/hbase/conf/hbase-policy.xml"));
//        configuration.addResource(new Path(configurationPath+"target/conf/hbase/conf/hbase-site.xml"));
//        configuration.addResource(new Path(configurationPath+"target/conf/hbase/conf/hdfs-site.xml"));
//        configuration.addResource(new Path(configurationPath+"target/conf/hbase/conf/mapred-site.xml"));
//        configuration.addResource(new Path(configurationPath+"target/conf/hbase/conf/yarn-site.xml"));
        System.out.println("++++++++++++++++++++++++++");
        System.err.println(Arrays.asList(HBaseUtils.getConnection(configuration).getTableNames()));
        System.out.println("++++++++++++++++++++++++++");
    }
}
