package com.example.interfaces.dao;

import java.io.Closeable;

/**
 * Created by linghang.kong on 2016/12/21.
 */
public interface HBaseDao<T> extends BaseDao<T>, Closeable {

}
