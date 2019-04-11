package com.schoolsell.dao;

import com.schoolsell.entity.Commodity;
import com.schoolsell.entity.Ordercommodity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface OrdercommodityMapper {
    int deleteByPrimaryKey(Integer ocid);

    int insert(Ordercommodity record);

    Ordercommodity selectByPrimaryKey(Integer ocid);

    List<Ordercommodity> selectAll();

    int updateByPrimaryKey(Ordercommodity record);

    List<Ordercommodity> selectByorderID(@Param("orderID") int orderID);

}