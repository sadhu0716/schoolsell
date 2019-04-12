package com.schoolsell.service.Impl;

import com.schoolsell.dao.ChatreportMapper;
import com.schoolsell.entity.Chatreport;
import com.schoolsell.service.ChatreportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class ChatreportSerImpl implements ChatreportsService {

    @Autowired
    private ChatreportMapper chatreportMapper;

    public ChatreportMapper getChatreportMapper() {
        return chatreportMapper;
    }

    public void setChatreportMapper(ChatreportMapper chatreportMapper) {
        this.chatreportMapper = chatreportMapper;
    }

    /**
     * 添加聊天记录
     *
     * @param chatreport 一条聊天信息
     * @return int(0或1)
     */
    @Transactional
    @Override
    public int addReport(Chatreport chatreport) {
        int result = 0;
        try {

            result = chatreportMapper.insert(chatreport);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("向数据库中添加聊天记录出现异常");
        }
        return result;
    }

    /**
     * 获取acceptID的所有聊天记录
     *
     * @param acceptid
     * @return List<Chatreport>
     */
    @Transactional
    @Override
    public List<Chatreport> findreportbyacceptid(String acceptid) {
        List<Chatreport> chatreport = null;
        try {
            chatreport = chatreportMapper.selectByAccpetid(acceptid);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("根据sendid，查询数据库聊天记录出现异常");
        }

        return chatreport;
    }

    /**
     * 根据聊天表的主键chatID删除一条聊天记录
     *
     * @param chatid
     * @return int(0或1)
     */
    @Transactional
    @Override
    public int delectReport(Integer chatid) {
        int result = 0;
        try {
            result = chatreportMapper.deleteByPrimaryKey(chatid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取acceptId的所有未浏览的聊天记录
     *
     * @param acceptid
     * @param isbrowse 0/1  0为未浏览，1为已浏览
     * @return List<Chatreport>
     */
    @Transactional
    @Override
    public List<Chatreport> findByAcceptidaAndisbrowse(String acceptid, Boolean isbrowse) {
        List<Chatreport> list = null;
        try {
            list = chatreportMapper.selectByAccpetidAndisBrowse(acceptid, isbrowse);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("sql查询出现了错误");
        }
        return list;
    }

    /**
     * 根据主键chatID更新某条聊天记录的状态（已浏览，未浏览）
     *
     * @param chatid   主键，自增
     * @param isbrowse 0，1
     * @return int(0或1)
     */
    @Transactional
    @Override
    public int updateIsbrowse(Integer chatid, Boolean isbrowse) {
        int result = 0;
        try {
            result = chatreportMapper.updateIsbrowse(chatid, isbrowse);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }

    /**
     * 将属于发送方和接收方的所有未浏览的聊天记录状态改为已浏览
     * 更改一个List的聊天记录的状态，所以无法更改某一条聊天记录的状态
     * 这会有一个很大的弊端：在用户还未查看到所有的聊天记录时就已经改变了聊天记录的状态
     *
     * @param sendid
     * @param acceptid
     * @return
     */
    @Transactional
    @Override
    public int makeIsbrowseToTrue(String sendid, String acceptid) {
        int result = 0;
        try {
            result = chatreportMapper.updateIsbrowseBysendidandacceptid(sendid, acceptid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 获取接收方的所有未浏览的聊天记录
     *
     * @param acceptid
     * @param isbrowse
     * @return
     */
    @Transactional
    @Override
    public int getAmountByacceptidAndisbrowse(String acceptid, Boolean isbrowse) {
        int result = 0;
        try {
            result = chatreportMapper.chatAmountByacceptidAndisbrowse(acceptid, isbrowse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
