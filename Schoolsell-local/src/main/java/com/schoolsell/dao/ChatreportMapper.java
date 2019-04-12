package com.schoolsell.dao;

import com.schoolsell.entity.Chatreport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
@Component
public interface ChatreportMapper {
    int deleteByPrimaryKey(Integer chatid) throws SQLException;

    int insert(Chatreport record)throws SQLException;

    Chatreport selectByPrimaryKey(Integer chatid)throws SQLException;

    List<Chatreport> selectAll()throws SQLException;

    int updateByPrimaryKey(Chatreport record)throws SQLException;

    List<Chatreport> selectByAccpetid(String accpetid)throws SQLException;

    /**
     * 根据输入的接受者id，与是否已经接收的Boolean来查询消息记录的集合
     * @param accpetid
     * @param isbrowse
     * @return
     * @throws SQLException
     */
    List<Chatreport> selectByAccpetidAndisBrowse(@Param("acceptid") String accpetid ,@Param("isbrowse") Boolean isbrowse)throws SQLException;


    int updateIsbrowse(@Param("chatid") Integer chatid,@Param("isbrowse") Boolean isbrowse)throws SQLException;

    /**
     * 传入发送者id和接受者id，将数据库里面的相关记录变为isbrowse=true
     * @param sendid
     * @param acceptid
     * @return
     * @throws SQLException
     */
    int updateIsbrowseBysendidandacceptid(@Param("sendid") String sendid,@Param("accepid") String acceptid)throws SQLException;

    /**
     * 根据传入的接受者id和是否浏览的布尔值来查询消息的数量
     * @param acceptid
     * @param isbrowse
     * @return
     * @throws SQLException
     */
    int chatAmountByacceptidAndisbrowse(@Param("acceptid") String acceptid,@Param("isbrowse") Boolean isbrowse)throws  SQLException;
}