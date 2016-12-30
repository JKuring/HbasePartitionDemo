package com.example.interfaces.dto;

import java.util.Map;

/**
 * Created by linghang.kong on 2016/12/22.
 */
public interface HBaseEntity extends BaseEntity {

    public String getGranularity();

    public void setGranularity(String granularity);
    public String getDataPath();

    public void setDataPath(String dataPath);

    public Map<String, String> getPropertiesMap();

    public void setPropertiesMap(Map<String, String> propertiesMap);

    public void addSystemProperties();

}
