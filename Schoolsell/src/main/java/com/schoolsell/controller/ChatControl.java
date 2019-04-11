package com.schoolsell.controller;

import com.schoolsell.entity.Chatreport;
import com.schoolsell.service.Impl.ChatreportSerImpl;
import com.schoolsell.util.ChatUtil;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping(value = "/schoolsell")
public class ChatControl {

    @Autowired
    ChatreportSerImpl chatreportsService;

    /**
     * 接受用户消息列表的请求，传入用户id，返回list集合
     *
     * @param userid
     * @return
     */
    @RequestMapping("/chatlist")
    @ResponseBody
    public List chatlist(String userid) {
        List<Chatreport> list = chatreportsService.findByAcceptidaAndisbrowse(userid, false);
        Map map = ChatUtil.SortBySender(list);
        List<Map<String, String>> mapList = new ArrayList<>();
        for (Object k : map.keySet()) {
            Map<String, String> m = new TreeMap<>();
            m.put("name", k.toString());
            m.put("onechatlist", JSONArray.fromObject(map.get(k)).toString());
            mapList.add(m);
        }
        return mapList;
    }

    /**
     * 接受用户聊天界面请求，将聊天记录变为isbrowse=true
     *
     * @param userid
     * @param otherid
     * @return
     */
    @RequestMapping("/chatInterface")
    @ResponseBody
    public String chatInterface(String userid, String otherid) {
        chatreportsService.makeIsbrowseToTrue(otherid, userid);
        //System.out.println("收到前端发来的消息！！！！！");
        return "消息已标记为false";
    }

    @RequestMapping("/logingetmsgnum")
    @ResponseBody
    public String logingetmsgnum(String userid) {
        //int tr=  chatreportsSerinter.getAmountByacceptidAndisbrowse(userid,true);
        int fa = chatreportsService.getAmountByacceptidAndisbrowse(userid, false);
        Map map = new TreeMap();
        map.put("amount", fa);
        JSON json = JSONObject.fromObject(map);
        return json.toString();
    }


}
