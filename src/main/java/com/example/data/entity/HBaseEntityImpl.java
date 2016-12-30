package com.example.data.entity;

import com.example.interfaces.dto.HBaseEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by linghang.kong on 2016/12/21.
 */
@Component
@Scope("prototype")
public class HBaseEntityImpl implements HBaseEntity {


    private String importtsv_columns;
    private String importtsv_bulk_output;
    private String importtsv_mapper_class;
    private String mapreduce_map_memory_mb;
    private String hbase_client_retries_number;
    private String importtsv_rowkey_indexs; // row key 的位置
    private String importtsv_rowkey_strategies; // 操作
    private String importtsv_rowkey_encrypts; // 加密

    private String tableName;
    private String dataPath;
    private String loadDate;
    private String granularity;

    private Map<String, String> propertiesMap = new HashMap<String, String>();

    public HBaseEntityImpl(String tableName, String dataPath, Map<String, String> propertiesMap) {
        this.tableName = tableName;
        this.dataPath = dataPath;
        this.propertiesMap = propertiesMap;
    }

    public String getName() {
        return this.tableName;
    }



    public void setName(String name) {
        this.tableName = name;


    }

    public String getGranularity() {
        return granularity;
    }

    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public String getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(String loadDate) {
        this.loadDate = loadDate;
    }

    public void addProperties(String key, String value) {
        propertiesMap.put(key, value);
    }

    public String getProperties(String key) {
        return propertiesMap.get(key);
    }

    public Map<String, String> getPropertiesMap() {
        return propertiesMap;
    }

    public void setPropertiesMap(Map<String, String> propertiesMap) {
        this.propertiesMap = propertiesMap;
    }

    public void addSystemProperties(String key, String value) {
        System.setProperty(key, value);
    }

    public void addSystemProperties() {
        for (String key : propertiesMap.keySet()
                ) {
            System.setProperty(key, propertiesMap.get(key));
        }
    }

}
