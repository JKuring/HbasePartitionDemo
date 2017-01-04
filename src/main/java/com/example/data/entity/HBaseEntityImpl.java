package com.example.data.entity;

import com.example.interfaces.dto.HBaseEntity;
import org.apache.hadoop.conf.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by linghang.kong on 2016/12/21.
 */
@Component
@Scope("prototype")
public class HBaseEntityImpl implements HBaseEntity {


//    private String importtsv_columns;
//    private String importtsv_bulk_output;
//    private String importtsv_mapper_class;
//    private String mapreduce_map_memory_mb;
//    private String hbase_client_retries_number;
//    private String importtsv_rowkey_indexs; // row key 的位置
//    private String importtsv_rowkey_strategies; // 操作
//    private String importtsv_rowkey_encrypts; // 加密

    private String tableName;
    private String dataPath;
    //    private String loadDate;
    private String granularity;
    private int delay;

    private String[] columns;
    private int version;
    private String compressionType;
    private int ttl;
    private String splitPolicy;
    private File spiltKeysFile;
    private String coprocessor;


    private Map<String, String> propertiesMap = new HashMap<String, String>();

    public HBaseEntityImpl(String tableName, String dataPath, String granularity, int delay, Map<String, String> propertiesMap) {
        this.tableName = tableName;
        this.dataPath = dataPath;
        this.granularity = granularity;
        this.delay = delay;
        this.propertiesMap = propertiesMap;
    }

    public String getName() {
        return this.tableName;
    }

    public void setName(String name) {
        this.tableName = name;


    }

    @Override
    public String getDataPath() {
        return dataPath;
    }

    @Override
    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

//    public String getLoadDate() {
//        return loadDate;
//    }
//
//    public void setLoadDate(String loadDate) {
//        this.loadDate = loadDate;
//    }

    @Override
    public String getGranularity() {
        return granularity;
    }

    @Override
    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    @Override
    public int getDelay() {
        return delay;
    }

    @Override
    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCompressionType() {
        return compressionType;
    }

    public void setCompressionType(String compressionType) {
        this.compressionType = compressionType;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public String getSplitPolicy() {
        return splitPolicy;
    }

    public void setSplitPolicy(String splitPolicy) {
        this.splitPolicy = splitPolicy;
    }

    public File getSpiltKeysFile() {
        return spiltKeysFile;
    }

    public void setSpiltKeysFile(File spiltKeysFile) {
        this.spiltKeysFile = spiltKeysFile;
    }

    public String getCoprocessor() {
        return coprocessor;
    }

    public void setCoprocessor(String coprocessor) {
        this.coprocessor = coprocessor;
    }

    @Override
    public Map<String, String> getPropertiesMap() {
        return propertiesMap;
    }

    @Override
    public void setPropertiesMap(Map<String, String> propertiesMap) {
        this.propertiesMap = propertiesMap;
    }

    @Override
    public void addSystemProperties(Configuration configuration) {
        for (String key : propertiesMap.keySet()
                ) {
            configuration.set(key, propertiesMap.get(key));
        }
    }
}