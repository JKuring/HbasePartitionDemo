package com.example.service;

import com.example.dao.HBaseDaoImpl;
import com.example.interfaces.dto.HBaseEntity;
import com.example.interfaces.dto.JobEntity;
import com.example.interfaces.service.HBaseService;
import com.example.interfaces.service.TaskService;
import com.example.utils.HBaseUtils;
import com.example.utils.time.TimeTransform;
import org.apache.hadoop.hbase.TableName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by linghang.kong on 2016/12/23.
 */
@Service
public class HBaseServiceImpl implements HBaseService<JobEntity,HBaseEntity> {

    private static final Logger logger = LoggerFactory.getLogger(HBaseServiceImpl.class);

    @Autowired
    HBaseDaoImpl hBaseDao;

    @Autowired
    private TaskService taskService;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName() {

    }

    public void createTable(TableName tableName, String[] columns) {
        try {
            this.hBaseDao.createTable(tableName, columns);
        } catch (IOException e) {
            logger.error("Failed to create table, Exception: {}", e.getMessage());
        }
    }


    public List<TableName> getTableNames() throws IOException {
        TableName[] tableName = this.hBaseDao.getTableNames();
        return Arrays.asList(tableName);
    }

    public boolean partition(HBaseEntity hBaseEntity) {
        boolean result = false;
        Map tableParametters = hBaseEntity.getPropertiesMap();
        String[] date = String.valueOf(TimeTransform.getDate(System.currentTimeMillis())).split("\\-");
        String year = date[0];
        String mouth = date[1];
        String day = date[2];
        String[] time = date[3].split(":");
        String minute;
        int mm = Integer.parseInt(time[1]);
        mm = mm - mm % 5;
        if (mm < 10) {
            minute = "0" + mm;
        } else
            minute = String.valueOf(mm);
        String tableName;

        if (hBaseEntity.getGranularity().equals("D")) {
            tableName = hBaseEntity.getName() + "_D_" + year + mouth + day;
        }else {
            tableName = hBaseEntity.getName();
        }
        String dataPath = hBaseEntity.getDataPath()+year+mouth+day+"/"+time[0]+"/"+Integer.parseInt(minute);
        tableParametters.put("importtsv_bulk_output",tableParametters.get("importtsv_bulk_output")+"/"+tableName+"/"+day+"/"+time[0]+"/"+minute);

        //加载为系统参数
        hBaseEntity.addSystemProperties();
        try {
            if (!HBaseUtils.uploadData(tableName, dataPath)) {
                logger.warn("Upload data failing! Please clean dirty data, and try again later.");
            }
            result = true;
        } catch (Exception e) {
            logger.error("Upload data failing! Upload data to {}, the load path is {}.", tableName, dataPath);
        }
        return result;
    }

    public boolean createSchedulerJob(JobEntity jobEntity) {
        boolean result = false;
        try {
            taskService.putTask(jobEntity);
        } catch (Exception e) {
            logger.error("Fails to create a scheduler job ", e.getMessage());
        }
        return result;
    }

    private JobEntity getJob(){

        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
