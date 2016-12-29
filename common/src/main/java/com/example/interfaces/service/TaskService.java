package com.example.interfaces.service;

import com.example.interfaces.dto.JobEntity;

import java.util.Map;

/**
 * Created by linghang.kong on 2016/12/29.
 */
public interface TaskService<T,E> extends BaseService<String>{
    public void putTask(T jobEntity);
    public JobEntity getTask(E tableName);
    Map<E, T> getTasksMap();
}
