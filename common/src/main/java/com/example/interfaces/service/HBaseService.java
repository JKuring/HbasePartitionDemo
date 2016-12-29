package com.example.interfaces.service;

import org.apache.hadoop.hbase.TableName;

/**
 * Created by linghang.kong on 2016/12/23.
 */
public interface HBaseService<T,E> extends BaseService<String> {

    public void createTable(TableName tableName, String[] columns);

    public boolean createSchedulerJob(T jobEntity);

    public boolean partition(E hBaseEntity);

}
