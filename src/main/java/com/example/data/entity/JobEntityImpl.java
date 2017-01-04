package com.example.data.entity;

import com.example.interfaces.dto.HBaseEntity;
import com.example.interfaces.dto.JobEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by linghang.kong on 2016/12/27.
 */
@Component
@Scope("prototype")
public class JobEntityImpl implements JobEntity<HBaseEntity> {

    private static final Logger logger = LoggerFactory.getLogger(JobEntityImpl.class);

    private String id;
    private String jobName;
    private long createTime = System.currentTimeMillis();
    private long startTime;
    private long stopTime;
    private long jobStartTime;
    private long jobEndTime;
    private long interval;
    private boolean status = false;

    private HBaseEntity tableEntity;

    public JobEntityImpl(String jobName, HBaseEntity tableEntity) {
        this.jobName = jobName;
        this.tableEntity = tableEntity;
    }


    public HBaseEntity getTableEntity() {
        return tableEntity;
    }

    public void setTableEntity(HBaseEntity tableEntity) {
        this.tableEntity = tableEntity;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public long getCreateTime() {
        return 0;
    }

    public void setCreateTime(long createTime) {

    }

    public long getStartTime() {
        return 0;
    }

    public void setStartTime(long createTime) {

    }

    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }

    public long getJobStartTime() {
        return jobStartTime;
    }

    public void setJobStartTime(long jobStartTime) {
        this.jobStartTime = jobStartTime;
    }

    public long getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(long jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    public String getName() {
        return this.jobName;
    }

    public void setName(String name) {
        this.jobName = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
