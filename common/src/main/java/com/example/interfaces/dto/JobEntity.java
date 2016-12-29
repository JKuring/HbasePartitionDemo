package com.example.interfaces.dto;

/**
 * Created by linghang.kong on 2016/12/27.
 */
public interface JobEntity<T> extends BaseEntity {

    public long getCreateTime();

    public void setCreateTime(long createTime);

    public long getStartTime();

    public void setStartTime(long createTime);

    public boolean isStatus();

    public void setStatus(boolean status);

    public T getTableEntity();

    public void setTableEntity(T tableEntity);
}
