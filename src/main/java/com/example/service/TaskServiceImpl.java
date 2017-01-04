package com.example.service;

import com.example.interfaces.dto.HBaseEntity;
import com.example.interfaces.dto.JobEntity;
import com.example.interfaces.service.TaskService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by linghang.kong on 2016/12/29.
 */
@Component
class TaskServiceImpl implements TaskService {

    private Map<String, JobEntity<HBaseEntity>> tasksMap;
    private boolean start_optition = true;
    private boolean stop_optition = true;

    public TaskServiceImpl() {
        this.tasksMap = new HashMap<String, JobEntity<HBaseEntity>>();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName() {

    }

    public void close() throws IOException {

    }

    public boolean isStart_optition() {
        return start_optition;
    }

    public void setStart_optition(boolean start_optition) {
        this.start_optition = start_optition;
    }

    public boolean isStop_optition() {
        return stop_optition;
    }

    public void setStop_optition(boolean stop_optition) {
        this.stop_optition = stop_optition;
    }

    public void putTask(JobEntity jobEntity) {
        this.tasksMap.put(jobEntity.getName(), jobEntity);
    }

    public JobEntity getTask(String tableName) {
        return this.tasksMap.get(tableName);
    }

    public Map<String, JobEntity<HBaseEntity>> getTasksMap() {
        return this.tasksMap;
    }


}
