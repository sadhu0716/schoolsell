
package com.schoolsell.config;


import com.schoolsell.entity.Chatreport;
import com.schoolsell.entity.other.PictureCount;
import com.schoolsell.service.ChatreportsService;
import com.schoolsell.service.Impl.ChatreportSerImpl;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value = "/schoolsell/websocket/{userid}")
@Component
public class MyWebSocket {
    private ChatreportsService chatreportsServiceimpl;

    //ChatreportsSerinter chatreportsSerinter= ( ChatreportsSerinter)ContextLoader.getCurrentWebApplicationContext().getBean("chatreportSerimpl");

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
    //用AtomicInteger做线程安全的加减操作接口
    // private static final AtomicInteger connectionIds = new AtomicInteger(0);
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    //用户名
    private String userid;

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    //用来获取spring容器中的bean
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        MyWebSocket.applicationContext = applicationContext;
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) {
        for (MyWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    /**
     * @PathParam(value = "nickname") String nickname ,
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userid") String nickname, Session session) {
        //增加用户
        this.session = session;
        this.userid = nickname;
        webSocketSet.add(this);
       /* chatreportsSerinter = applicationContext.getBean(ChatreportSerimpl.class);

            //传入新连接的用户的id，调用chatreportsSerinter查询到数据库表中接受消息端为该id的Chatreport集合

            List<Chatreport> chatreportList = chatreportsSerinter.findByAcceptidaAndisbrowse(nickname,false);
            int c = chatreportList.size();

            //遍历该集合，并将每条消息记录封装成的chatreport转化为JSONObject，并发送给该连接用户
            for (int i = 0; i < c; i++) {
                Chatreport chatreport = chatreportList.get(i);
                JSONObject jsonObject = JSONObject.fromObject(chatreport);
                jsonObject.put("type", 0);
                session.getAsyncRemote().sendText(jsonObject.toString());
                int chatid = chatreport.getChatid();
                chatreportsSerinter.updateIsbrowse(chatid,true);
                //chatreportsSerinter.delectReport(chatid);
            }
            */


        System.out.println("用户为：" + nickname);
        System.out.println("有新连接加入！当前在线人数为" + webSocketSet.size());

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
        PictureCount.setGetPictureCount(0);
        PictureCount.setBigkindCount(0);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        Boolean isSend = false;
        System.out.println("来自客户端的消息:" + message);
        chatreportsServiceimpl = applicationContext.getBean(ChatreportSerImpl.class);
        JSONObject jobj = JSONObject.fromObject(message);
        jobj.put("date", df.format(new Date()));
        jobj.put("type", 0);
        String acceptid = jobj.getString("acceptid");
        String context = jobj.getString("context");
        System.out.println("接受人为：" + acceptid);
        String sendid = jobj.getString("sendid");
        String date = jobj.getString("date");

        //查询当前在线用户中是否存在接受该消息的用户，若果不存在，则把这条记录存入数据库中
        for (MyWebSocket ms : webSocketSet) {               //在线
            if (ms.userid.equals(acceptid)) {
                ms.session.getAsyncRemote().sendText(jobj.toString());
                isSend = true;
            }
        }
        if (!isSend) {              //不在线

            Chatreport chatreport = new Chatreport(sendid, acceptid, context, date, false);
            System.out.println(chatreport);
            System.out.println(chatreportsServiceimpl);
            chatreportsServiceimpl.addReport(chatreport);
        } else {

            Chatreport chatreport = new Chatreport(sendid, acceptid, context, date, false);
            System.out.println(chatreport);
            System.out.println(chatreportsServiceimpl);
            chatreportsServiceimpl.addReport(chatreport);
        }

    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
