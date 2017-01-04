package com.example.interfaces.service;

import com.example.interfaces.dto.HBaseEntity;
import com.example.interfaces.dto.JobEntity;

import java.util.Map;

/**
 * Created by linghang.kong on 2016/12/29.
 */
public interface TaskService extends BaseService<String>{
    public void putTask(JobEntity jobEntity);
    public JobEntity getTask(String tableNamTe);
    Map<String, JobEntity<HBaseEntity>> getTasksMap();
}
