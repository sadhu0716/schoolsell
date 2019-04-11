package com.schoolsell.controller;

import com.schoolsell.entity.User;
import com.schoolsell.service.Impl.CommodityServiceImpl;
import com.schoolsell.service.Impl.FeedbackServiceImpl;
import com.schoolsell.service.Impl.UserLoginServiceImpl;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户登录，注册，显示，更改信息的Controller类
 */

@Controller
@RequestMapping(value = "/schoolsell")
public class WeixinController {

//    String watermark=ResourceUtils.CLASSPATH_URL_PREFIX + "static/png/1.png";

    @Autowired
    UserLoginServiceImpl userLoginServiceimpl;

    @Autowired
    CommodityServiceImpl commodityServiceimpl;

    @Autowired
    FeedbackServiceImpl feedbackServiceimpl;

//    public User user=new User();

    /**
     * 用户登录
     *
     * @param userID
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login")
    public Map<String, Object> weixinLogin(String userID, String password) {
        Map<String, Object> loginMap = new HashMap<String, Object>();
//        loginMap.put("name",2);
        System.out.println("用户名:" + userID + "密码:" + password);
        loginMap.put("result", userLoginServiceimpl.userLogin(userID, password));
        return loginMap;
    }

    /**
     * 注册
     *
     * @param request
     * @return
     * @throws FileNotFoundException
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public Map<String, Object> weixinRegister(HttpServletRequest request) throws FileNotFoundException {
        Map<String, Object> registerMap = new HashMap<String, Object>();
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("file");
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));    //获取文件后缀名
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        String realName = request.getParameter("realName");
//        String credibility = request.getParameter("credibility");         //信誉值
        String idCard = request.getParameter("idCard");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        fileName = userID + suffixName;             //设置头像名为用户ID

//        String filePath = ResourceUtils.getURL("classpath:").getPath() + "static/userImg/" +
//                fileName;

        //获取跟目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) path = new File("");
        System.out.println("path:"+path.getAbsolutePath());
        System.out.println("path(不加绝对路径):"+path);

        //如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(path.getAbsolutePath(),"static/userImg/");
        if(!upload.exists()) upload.mkdirs();
        System.out.println("upload url:"+upload.getAbsolutePath());
//        String upPath=upload.getAbsolutePath().replace("file:","");
        String filePath=upload.getAbsolutePath()+"\\"+fileName;                  //详情图路径
//在开发测试模式时，得到的地址为：{项目跟目录}/target/static/thumbnail/
//在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/thumbnail/

        File newFile = new File(filePath);

        //开发环境下
//        String watermark = ResourceUtils.getURL("classpath:").getPath() +
//                "static/png/1.png";
        //jar包下
//        InputStream watermark= getClass().getClassLoader().getResourceAsStream("classpath:static/png/1.png");

        //war包下
        File watermark=new File(ResourceUtils.getURL("classpath:").getPath(),"static/png/1.png");

        System.out.println("缩略图(不加绝对路径)的路径:"+watermark);
//        System.out.println("缩略图(加绝对)的路径:"+watermark);
//        String isChecked=request.getParameter("isChecked");
        System.out.println("用户名:" + userID + "密码：" + password);
        System.out.println("电话号码:" + phoneNumber);
        System.out.println(filePath);
        try {
            //压缩
//            Thumbnails.of(file.getInputStream()).scale(1f).toFile(newFile);
            //水印
            Thumbnails.of(file.getInputStream()).size(1280, 1024).watermark(
                    Positions.BOTTOM_RIGHT,
                    ImageIO.read(watermark), 0.5f)
                    .outputQuality(0.8f).toFile(newFile);
        } catch (IOException e) {
            try {
                file.transferTo(newFile);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        User user = new User(userID, password, userName, realName, idCard, phoneNumber, address, 10,
                filePath, -1);
        //-1为未审核，0为未通过，1为已通过
        //信誉值默认为10
        registerMap.put("result", userLoginServiceimpl.insert(user));
        return registerMap;
    }

    /**
     * 显示用户信息
     *
     * @param userID
     * @return
     * @throws SQLException
     */
    @RequestMapping("/profilemsg")
    @ResponseBody
    public JSONObject msgDate(String userID) throws SQLException {
        User users = userLoginServiceimpl.selectByPrimaryKey(userID);
        System.out.println(users);
        JSONObject jsonObject = JSONObject.fromObject(users);
        return jsonObject;
    }

    /**
     * 更改用户信息
     *
     * @param userID
     * @param mark
     * @param newmsg
     * @return
     * @throws SQLException
     */
    @RequestMapping("/modifyInfor")
    @ResponseBody
    public Map<String, Object> modifyInfor(String userID, String mark, String newmsg) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        Integer tip = userLoginServiceimpl.updatemsg(userID, mark, newmsg);
        map.put("result", tip);
        return map;
    }

    //设置时间格式
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 反馈
     *
     * @param feedbackID
     * @param description
     * @return
     */
    @RequestMapping("/feedback")
    @ResponseBody
    public Integer feedback(String feedbackID, String description) {
        List<String> list = feedbackServiceimpl.selectQuery(feedbackID);
        String datetime = df.format(new Date());
        Date date = null;
        try {
            date = df.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer tip = feedbackServiceimpl.insert(feedbackID, description, date, list);
        return tip;
    }

}
