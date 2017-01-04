package com.example.interfaces.dao;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/**
 * Created by linghang.kong on 2016/12/21.
 * add the option of table level.
 */
public interface HBaseDao<T> extends BaseDao<T>, Closeable {

    public void createTable(TableName tableName, String[] columns, int version, int ttl, String compressionType,
                            String coprocessor, String splitPolicy, File spiltKeysFile) throws IOException;

    public Configuration getConfiguration();

    public void setConfiguration(Configuration configuration);
}
