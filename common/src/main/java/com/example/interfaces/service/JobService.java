package com.example.interfaces.service;

/**
 * Created by linghang.kong on 2016/12/27.
 */
public interface JobService extends BaseService<String> {

    /**
     * 同时执行所有job
     */
    public void doPartitionTableJobs();

}
