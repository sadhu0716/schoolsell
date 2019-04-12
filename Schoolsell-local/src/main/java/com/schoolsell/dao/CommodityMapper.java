package com.schoolsell.dao;

import com.schoolsell.entity.Commodity;
import com.schoolsell.exception.MySqlException;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
@Component
public interface CommodityMapper {
    int deleteByPrimaryKey(Integer cid) throws SQLException;

    List<Commodity>  selectBycID(Integer cID)throws  SQLException;

    int insert(Commodity record) throws SQLException;

    Commodity selectByPrimaryKey(Integer cid) throws SQLException;

    List<Commodity> selectAll() throws SQLException;

    int updateByPrimaryKey(Commodity record) throws SQLException;

    int updateThumbnailByCommodity(Commodity record) throws SQLException;

    Commodity selectByUserIDWithCName(@Param("picturePath") String pirturePath)throws SQLException;

    List<Commodity> selectByKName(@Param("kname") String KName)throws SQLException;

    int updatemsg(Commodity record)throws SQLException;

    List<Commodity> selectByUserID(String sellerID)throws SQLException;

    /**
     * 模糊查询商品
     *
     * @param Cname
     * @return
     * @throws SQLException
     */
    List<Commodity> selectByCName(@Param("cname") String Cname) throws SQLException;


}