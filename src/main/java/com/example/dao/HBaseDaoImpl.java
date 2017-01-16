package com.example.dao;


import com.example.interfaces.dao.HBaseDao;
import com.example.interfaces.dto.HBaseEntity;
import com.example.utils.HBaseUtils;
import com.example.utils.kerberos.HBaseKerberos;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

/**
 * Created by linghang.kong on 2016/12/21.
 */
public class HBaseDaoImpl implements HBaseDao<HBaseEntity> {

    public static final String ROWKEY_COLUMN_SPEC = "HBASE_ROW_KEY";
    public static final String TIMESTAMPKEY_COLUMN_SPEC = "HBASE_TS_KEY";
    public static final String ATTRIBUTES_COLUMN_SPEC = "HBASE_ATTRIBUTES_KEY";
    public static final String CELL_VISIBILITY_COLUMN_SPEC = "HBASE_CELL_VISIBILITY";
    public static final String CELL_TTL_COLUMN_SPEC = "HBASE_CELL_TTL";
    private static final Logger logger = LoggerFactory.getLogger(HBaseDaoImpl.class);
    private static final int CONNECTION_POOL_SIZE = 10;
//    @Autowired
//    @Qualifier("coprocessor")
//    Object coprocessor;
//    @Autowired
//    @Qualifier("splitPolicy")
//    Object splitPolicy;

    private Configuration configuration;
    private Connection connection;

    /**
     * The connection factory create a connection that is pool of multi threads in the Dao constructor.
     * When you connect a region server, it can be used without blocking IO.
     *
     * @param configuration
     */
    public HBaseDaoImpl(Configuration configuration) {
        // 屏蔽spring data装载的配置
        this.configuration = HBaseConfiguration.create();
        if (this.configuration == null) {
            logger.error("configuration is null!");
            System.exit(1);
        } else {
            logger.info("successful Fetch configuration!");
            // add kerberos
            this.configuration = HBaseKerberos.getConfiguration(this.configuration);
            // spring template handle, add configuration.
//            setConfiguration(this.configuration);
//            afterPropertiesSet();
        }
    }

    private synchronized Admin createAdmin(){
        try {
            if (this.connection == null || this.connection.isClosed()) {
//                this.connection = ConnectionFactory.createConnection(this.configuration);
                // multi connections
            this.connection = ConnectionFactory.createConnection(this.configuration, Executors.newCachedThreadPool());
//                this.admin = this.connection.getAdmin();
            }
            return this.connection.getAdmin();
        } catch (Exception e) {
            logger.debug("hbase.client.connection.impl={}", this.configuration.get("hbase.client.connection.impl"));
            logger.error("get connection false! Exception: {}.", e.getMessage());
            StringBuffer out = new StringBuffer();
            StackTraceElement[] trace = e.getStackTrace();
            out.append(" processResult: " + e.toString());
            for (StackTraceElement s : trace) {
                out.append("\tat " + s.toString() + "\r\n");
            }
            logger.debug(out.toString());
//            System.exit(1);
        }
        return null;
    }

    public void createTable(TableName tableName, String[] columns) throws IOException {
        createTable(tableName, columns,null,null,null);
    }

    public void createTable(TableName tableName, String[] columns, Object coprocessor, Object splitPolicy,File file) throws IOException {
        createTable(tableName, columns,1,0,"none",coprocessor.getClass().getName(), splitPolicy.getClass().getName(),file);
    }


    public void createTable(TableName tableName, String[] columns, int version, int ttl, String compressionType,
                            String coprocessor, String splitPolicy, File spiltKeysFile) throws IOException {
        Admin admin = createAdmin();
        if (!admin.tableExists(tableName)) {
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            hTableDescriptor.setCompactionEnabled(true);
            Set<String> cfSet = getColumnFamilies(columns);
            for (String cf : cfSet) {
                HColumnDescriptor hcd = new HColumnDescriptor(Bytes.toBytes(cf));
                hcd.setMinVersions(version);
                hcd.setTimeToLive(ttl);
                hcd.setCompactionCompressionType(Compression.getCompressionAlgorithmByName(compressionType));
                hTableDescriptor.addFamily(hcd);
            }
            // coprocessor
            if (coprocessor.length() > 0)
                hTableDescriptor.addCoprocessor(coprocessor);
            // split policy
            if (splitPolicy.length() > 0)
                hTableDescriptor.setRegionSplitPolicyClassName(splitPolicy);

            logger.info("Creating table {} with {} columns and default descriptors.",
                    tableName.getNameAsString(), cfSet.toArray());
            if (spiltKeysFile.exists()) {
                // add a split_keys file
                admin.createTable(hTableDescriptor, HBaseUtils.getSplitKeys(spiltKeysFile));
            } else {
                admin.createTable(hTableDescriptor);
            }
            admin.close();
        }else {
            logger.warn("the {} table is existence.", tableName.getNameAsString());
        }
    }

    public void deleteTable(TableName tableName) throws IOException {
        Admin admin = createAdmin();
        if (admin.tableExists(tableName)) {
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            logger.debug("delete the {} table.", tableName.getNameAsString());
        }else {
            logger.warn("the {} table is not existence.", tableName.getNameAsString());
        }
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
        // 一般默认会关闭
        this.connection.close();
        logger.debug("close connection!");
    }

    public TableName[] getTableNames() throws IOException {
        return this.connection.getAdmin().listTableNames();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
