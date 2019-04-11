package com.schoolsell.service;

import com.schoolsell.entity.Chatreport;

import java.util.List;

public interface ChatreportsService {


        int addReport(Chatreport chatreport) ;
        List<Chatreport> findreportbyacceptid(String acceptid) ;
        int delectReport(Integer chatid);

        /**
         * 根据输入的接受者id，与是否已经接收的Boolean来查询消息记录的集合
         * @param acceptid
         * @param isbrowse
         * @return
         */
        List<Chatreport> findByAcceptidaAndisbrowse(String acceptid, Boolean isbrowse);

        int updateIsbrowse(Integer chat, Boolean isbrowse);
        int makeIsbrowseToTrue(String sendid, String acceptid);
        int getAmountByacceptidAndisbrowse(String acceptid, Boolean isbrowse);

}
