package com.example.interfaces.dto;

import org.apache.hadoop.conf.Configuration;

import java.io.File;
import java.util.Map;

/**
 * Created by linghang.kong on 2016/12/22.
 */
public interface HBaseEntity extends BaseEntity {


    public String getDataPath();

    public void setDataPath(String dataPath);

    public String getGranularity();

    public void setGranularity(String granularity);

    public int getDelay();

    public void setDelay(int delay);

    public String[] getColumns();

    public void setColumns(String[] columns);

    public int getVersion();

    public void setVersion(int version);

    public String getCompressionType();

    public void setCompressionType(String compressionType);

    public int getTtl();

    public void setTtl(int ttl);

    public String getSplitPolicy();

    public void setSplitPolicy(String splitPolicy);

    public File getSpiltKeysFile();

    public void setSpiltKeysFile(File spiltKeysFile);

    public String getCoprocessor();

    public void setCoprocessor(String coprocessor);

    public Map<String, String> getPropertiesMap();

    public void setPropertiesMap(Map<String, String> propertiesMap);

    public void addSystemProperties(Configuration configuration);

}
