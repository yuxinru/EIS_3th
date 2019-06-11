package com.broker.dao;

import com.broker.entity.Orderblotter;

public interface OrderblotterDAO {
    int deleteByPrimaryKey(Integer tradeid);

    int insert(Orderblotter record);

    int insertSelective(Orderblotter record);

    Orderblotter selectByPrimaryKey(Integer tradeid);

    int updateByPrimaryKeySelective(Orderblotter record);

    int updateByPrimaryKey(Orderblotter record);
}