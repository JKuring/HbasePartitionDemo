package mytest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.mapreduce.ImportTsv;
import org.apache.hadoop.util.ToolRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by linghang.kong on 2016/12/26.
 */
public class HBaseUtils {

    private static final Logger logger = LoggerFactory.getLogger(HBaseUtils.class);

    public static boolean uploadData(String tableName, String loadingPath) throws Exception {
        boolean result = false;
        logger.info("Upload data to {}, the load path is {}.", tableName, loadingPath);
//        int status = ToolRunner.run(new ImportTsv(), new String[]{tableName, loadPath});
        int status = ToolRunner.run(new ImportTsv(), new String[]{tableName, loadingPath});

        if (status == 0) {
            result = true;
        }
        return result;
    }

    public static HConnection getConnection() {
        Configuration configuration = HBaseConfiguration.create();
        HConnection connection = null;
        if (configuration == null) {
            logger.error("configuration is null!");
            System.exit(1);
        } else {
            logger.info("successful Fetch configuration!");
            // add kerberos
            configuration = HBaseKerberos.getConfiguration(configuration);
        }
        try {
            connection = HConnectionManager.createConnection(configuration);
//            this.connection = ConnectionFactory.createConnection(this.configuration, Executors.newFixedThreadPool(10));
        } catch (IOException e) {
            logger.debug("hbase.client.connection.impl={}", configuration.get("hbase.client.connection.impl"));
            logger.error("get connection false! Exception: {}.", e.getMessage());
            StringBuffer out = new StringBuffer();
            StackTraceElement[] trace = e.getStackTrace();
            out.append(" processResult: " + e.toString());
            for (StackTraceElement s : trace) {
                out.append("\tat " + s.toString() + "\r\n");
            }
            logger.debug(out.toString());
            System.exit(1);
        }
        return connection;
    }
}
