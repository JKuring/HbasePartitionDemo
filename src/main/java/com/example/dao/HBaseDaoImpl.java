package com.example.dao;


import com.example.interfaces.dao.HBaseDao;
import com.example.interfaces.dto.HBaseEntity;
import com.example.utils.kerberos.HBaseKerberos;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import static java.lang.String.format;

/**
 * Created by linghang.kong on 2016/12/21.
 */
public class HBaseDaoImpl implements HBaseDao<HBaseEntity> {

    private static final Logger logger = LoggerFactory.getLogger(HBaseDaoImpl.class);

    private  static final int CONNECTION_POOL_SIZE = 10;

    public static final String ROWKEY_COLUMN_SPEC = "HBASE_ROW_KEY";
    public static final String TIMESTAMPKEY_COLUMN_SPEC = "HBASE_TS_KEY";
    public static final String ATTRIBUTES_COLUMN_SPEC = "HBASE_ATTRIBUTES_KEY";
    public static final String CELL_VISIBILITY_COLUMN_SPEC = "HBASE_CELL_VISIBILITY";
    public static final String CELL_TTL_COLUMN_SPEC = "HBASE_CELL_TTL";

    @Autowired
    @Qualifier("coprocessor")
    Object coprocessor;
    @Autowired
    @Qualifier("splitPolicy")
    Object splitPolicy;

    private Configuration configuration;
    private Connection connection;

    /**
     * The connection factory create a connection that is pool of multi threads in the Dao constructor.
     * When you connect a region server, it can be used without blocking IO.
     *
     * @param configuration
     */
    public HBaseDaoImpl(Configuration configuration) {
        this.configuration = configuration;
        if (this.configuration == null) {
            logger.error("configuration is null!");
            System.exit(1);
        } else {
            logger.info("successful Fetch configuration!");
            // add kerberos
            this.configuration = HBaseKerberos.getConfiguration(this.configuration);
//            // spring template handle, add configuration.
//            setConfiguration(this.configuration);
//            afterPropertiesSet();
        }
        try {
            this.connection = ConnectionFactory.createConnection(this.configuration, Executors.newFixedThreadPool(CONNECTION_POOL_SIZE));
//            this.connection = ConnectionFactory.createConnection(this.configuration, Executors.newFixedThreadPool(10));
        } catch (IOException e) {
            logger.debug("hbase.client.connection.impl={}",this.configuration.get("hbase.client.connection.impl"));
            logger.error("get connection false! Exception: {}.",e.getMessage());
            StringBuffer out = new StringBuffer();
            StackTraceElement[] trace = e.getStackTrace();
            out.append(" processResult: "+e.toString());
            for (StackTraceElement s : trace) {
                out.append("\tat " + s.toString() + "\r\n");
            }
            logger.debug(out.toString());
            System.exit(1);
        }
    }

    private Admin createAdmin() throws IOException {
        return this.connection.getAdmin();
    }

    public void createTable(TableName tableName, String[] columns) throws IOException {
        createTable(tableName, columns, this.coprocessor, this.splitPolicy);
    }

    public void createTable(TableName tableName, String[] columns, Object coprocessor, Object splitPolicy) throws IOException {
        createTable(tableName, columns, coprocessor.getClass().getName(), splitPolicy.getClass().getName());
    }


    public void createTable(TableName tableName, String[] columns, String coprocessor, String splitPolicy) throws IOException {
        Admin admin = createAdmin();
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
        Set<String> cfSet = getColumnFamilies(columns);
        for (String cf : cfSet) {
            HColumnDescriptor hcd = new HColumnDescriptor(Bytes.toBytes(cf));
            hTableDescriptor.addFamily(hcd);
        }
        // coprocessor
        if (coprocessor.length() > 0)
            hTableDescriptor.addCoprocessor(coprocessor);
        // split policy
        if (splitPolicy.length() > 0)
            hTableDescriptor.setRegionSplitPolicyClassName(splitPolicy);

        logger.warn(format("Creating table '{}' with '{}' columns and default descriptors.",
                tableName, cfSet));
        admin.createTable(hTableDescriptor);
    }

    private Set<String> getColumnFamilies(String[] columns) {
        Set<String> cfSet = new HashSet<String>();
        for (String aColumn : columns) {
            if (ROWKEY_COLUMN_SPEC.equals(aColumn)
                    || TIMESTAMPKEY_COLUMN_SPEC.equals(aColumn)
                    || CELL_VISIBILITY_COLUMN_SPEC.equals(aColumn)
                    || CELL_TTL_COLUMN_SPEC.equals(aColumn)
                    || ATTRIBUTES_COLUMN_SPEC.equals(aColumn))
                continue;
            // we are only concerned with the first one (in case this is a cf:cq)
            cfSet.add(aColumn.split(":", 2)[0]);
        }
        return cfSet;
    }

    public HBaseEntity get(Class<HBaseEntity> entityClazz, Serializable id) {
        return null;
    }

    public Serializable save(HBaseEntity entity) {
        return null;
    }

    public void update(HBaseEntity entity) {

    }

    public void delete(HBaseEntity entity) {

    }

    public void delete(Class<HBaseEntity> entityClazz, Serializable id) {

    }

    public List<HBaseEntity> findAll(Class<HBaseEntity> entityClazz) {
        return null;
    }

    public long findCount(Class<HBaseEntity> entityClazz) {
        return 0;
    }

    public void close() throws IOException {
        // close the connection, not a thread.
        this.connection.close();
        logger.debug("close connection!");
    }

    public TableName[] getTableNames() throws IOException {
        return this.connection.getAdmin().listTableNames();
    }

}
