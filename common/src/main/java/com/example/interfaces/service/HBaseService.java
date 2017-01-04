package com.example.interfaces.service;

/**
 * Created by linghang.kong on 2016/12/23.
 */
public interface HBaseService<T,E> extends BaseService<String> {

    public void createTable(E hBaseEntity) ;

    public boolean createSchedulerJob(T jobEntity);

    public boolean partition(E hBaseEntity);

}
