package com.example.service;

import com.example.interfaces.dto.HBaseEntity;
import com.example.interfaces.dto.JobEntity;
import com.example.interfaces.service.HBaseService;
import com.example.interfaces.service.JobService;
import com.example.interfaces.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.util.Map;

/**
 * Created by linghang.kong on 2016/12/27.
 */
public class JobServiceImpl implements JobService {

    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    @Autowired
//    @Qualifier("taskServiceImpl")
    private TaskService taskService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private HBaseService<JobEntity> hbaseService;

    private Map<String, JobEntity<HBaseEntity>> tasksMap;

    private String name;

    // admin 权限，在不知道任何job的情况下，进行启动，无需认证。

    public void doCreateTableJobs(){
        logger.info("Start all tasks of the created tables.");
        tasksMap = taskService.getTasksMap();
        if (tasksMap.size() > 0) {
            for (String name : tasksMap.keySet()
                    ) {
                logger.debug("Task name: {}.", name);
                final JobEntity job = tasksMap.get(name);
                // 开启任务
                job.setJobStartTime(System.currentTimeMillis());
                threadPoolTaskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        hbaseService.createTable(job);
                        //关闭任务
                        job.setJobEndTime(System.currentTimeMillis());
                    }
                });
            }
        } else {
            logger.info("Noting to do for this task list, because the list is empty.");
        }
    }

    public void doPartitionTableJobs() {
        logger.info("Start all tasks of the partition tables.");
        tasksMap = taskService.getTasksMap();
        long currentTime = System.currentTimeMillis();
        if (tasksMap.size() > 0) {
            for (String name : tasksMap.keySet()
                    ) {
                logger.debug("Task name: {}.", name);
                final JobEntity job = tasksMap.get(name);
                // 判断上一个任务是否执行完成
                // 由于没有job依赖，status并不能准确反映最后一次任务的情况，但不影响使用。
                // 可以确定的是，如果出现if情况，可能有其他任务在执行；有else中的情况就一定有某个任务没有完成。
                if (!job.isStatus()) {
                    job.setId(job.getName() + "-" + currentTime);
                    logger.info("Create the {} job.", job.getId());
                } else {
                    logger.info("The last job of the {} table is executing! The last job id is {}.", name,job.getId());
                    job.setId(job.getName() + "-" + currentTime);
                    logger.info("Create the {} job again.", job.getId());
                }
                // 开启任务
                job.setStatus(true);
                job.setCreateTime(currentTime);
                threadPoolTaskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        job.setStartTime(System.currentTimeMillis());
                        hbaseService.partition(job);
                        //关闭任务
                        job.setStatus(false);
                        job.setStopTime(System.currentTimeMillis());
                    }
                });
            }
        } else {
            logger.info("Noting to do for this task list, because the list is empty.");
        }
    }

    /**
     * excute job
     *
     * @param tableName table name
     * @return 0:successful 1:exits 2: can't find the table
     */
    public int doPartitionTableJob(String tableName) {
        for (String name : tasksMap.keySet()
                ) {
            if (tableName.equals(name)) {
                JobEntity job = tasksMap.get(tableName);
                if (!job.isStatus()) {
                    hbaseService.partition(job);
                    logger.info("create the {} job.", tableName);
                    return 0;
                } else {
                    logger.info("The table of {} is executing!", tableName);
                    return 1;
                }
            }
        }
        logger.info("Can't find the table of {}!", tableName);
        return 2;
    }

    public void doDeleteTableJobs(){
        logger.info("Start all tasks of the created tables.");
        tasksMap = taskService.getTasksMap();
        if (tasksMap.size() > 0) {
            for (String name : tasksMap.keySet()
                    ) {
                logger.debug("Task name: {}.", name);
                final JobEntity job = tasksMap.get(name);
                // 只删除天表
                if (job.getGranularity().equals("D")) {
                    threadPoolTaskExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            hbaseService.delete(job);
                        }
                    });
                }
            }
        } else {
            logger.info("Noting to do for this task list, because the list is empty.");
        }
    }

    public void stopJobs() {

    }

    public String getName() {
        return null;
    }

    public void setName() {

    }

    public void close() throws IOException {

    }
}
