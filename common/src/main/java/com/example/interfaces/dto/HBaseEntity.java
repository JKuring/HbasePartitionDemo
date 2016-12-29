package com.example.interfaces.dto;

/**
 * Created by linghang.kong on 2016/12/22.
 */
public interface HBaseEntity extends BaseEntity {

    public String getDataPath();

    public void setDataPath(String dataPath);

    public void addSystemProperties();

}
