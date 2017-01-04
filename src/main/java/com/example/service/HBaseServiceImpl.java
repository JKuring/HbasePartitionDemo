package com.example.service;

import com.example.dao.HBaseDaoImpl;
import com.example.interfaces.dto.HBaseEntity;
import com.example.interfaces.dto.JobEntity;
import com.example.interfaces.service.HBaseService;
import com.example.interfaces.service.TaskService;
import com.example.utils.HBaseUtils;
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
public class HBaseServiceImpl implements HBaseService<JobEntity, HBaseEntity> {

    private static final Logger logger = LoggerFactory.getLogger(HBaseServiceImpl.class);

    private final String HADOOP_USER_ROOT = "/DOMAIN_B/WANGGUAN/XINGNENG/MANAGE";


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

    public void createTable(HBaseEntity hBaseEntity) {
        try {
            this.hBaseDao.createTable(TableName.valueOf(HBaseUtils.getCurrentTimeTableName(hBaseEntity.getName(),
                    System.currentTimeMillis(), 0, hBaseEntity.getGranularity())), hBaseEntity.getColumns(),
                    hBaseEntity.getVersion(), hBaseEntity.getTtl(), hBaseEntity.getCompressionType(),
                    hBaseEntity.getCoprocessor(), hBaseEntity.getSplitPolicy(), hBaseEntity.getSpiltKeysFile());
        } catch (IOException e) {
            logger.error("Failed to create table, Exception: {}", e.getMessage());
        }
    }

    public void delete(HBaseEntity hBaseEntity){
        try{
            String tableName = HBaseUtils.getCurrentTimeTableName(hBaseEntity.getName(),System.currentTimeMillis()-hBaseEntity.getTtl()*1000,0,hBaseEntity.getGranularity());
            this.hBaseDao.deleteTable(TableName.valueOf(tableName));
        }catch (Exception e){
            logger.error("Failed to delete the {} table, exception: {}.",hBaseEntity.getName(),e.getMessage());
        }
    }


    public List<TableName> getTableNames(){
        try {
            TableName[] tableName = this.hBaseDao.getTableNames();
            return Arrays.asList(tableName);
        }catch (IOException e){
            logger.error("");
        }
        return null;
    }

    public boolean partition(HBaseEntity hBaseEntity) {
        boolean result = false;
        String name = hBaseEntity.getName();
        Map tableParametters = hBaseEntity.getPropertiesMap();
        long currentTime = System.currentTimeMillis();

        String currentTimeTableName = HBaseUtils.getCurrentTimeTableName(name,currentTime,hBaseEntity.getDelay(),hBaseEntity.getGranularity());
        String[] tmpTableName = name.split(":");
        String tmpPath = HBaseUtils.getCurrentTimePath(currentTime,hBaseEntity.getDelay());
        String dataPath = HADOOP_USER_ROOT + hBaseEntity.getDataPath() + tmpPath;
        tableParametters.put("importtsv.bulk.output", tableParametters.get("importtsv.bulk.output1") + "/" + tmpTableName[0] + "_" + tmpTableName[1] + tmpPath);

        //加载为系统参数
        hBaseEntity.addSystemProperties(hBaseDao.getConfiguration());
        try {
            if (!HBaseUtils.uploadData(hBaseDao.getConfiguration(), currentTimeTableName, dataPath)) {
                logger.warn("Upload data failing! Please clean dirty data, and try again later.");
            }
            result = true;
        } catch (Exception e) {
            logger.error("Upload data failing! Upload data to {}, the load path is {}.", currentTimeTableName, dataPath);
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

    private JobEntity getJob() {

        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
