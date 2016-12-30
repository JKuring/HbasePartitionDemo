package com.example.interfaces.dto;

/**
 * Created by linghang.kong on 2016/12/27.
 */
public interface JobEntity<T> extends BaseEntity {

    public void setId(String id);

    public String getId();

    public long getCreateTime();

    public void setCreateTime(long createTime);

    public long getStartTime();

    public void setStartTime(long createTime);

    public boolean isStatus();

    public void setStatus(boolean status);

    public T getTableEntity();

    public void setTableEntity(T tableEntity);

    public long getStopTime();

    public void setStopTime(long stopTime);

    public long getJobStartTime();

    public void setJobStartTime(long jobStartTime);

    public long getJobEndTime();

    public void setJobEndTime(long jobEndTime);
}
