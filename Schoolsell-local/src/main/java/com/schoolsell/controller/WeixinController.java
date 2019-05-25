package com.schoolsell.controller;

import com.schoolsell.entity.User;
import com.schoolsell.entity.Wxuser;
import com.schoolsell.entity.other.PictureCount;
import com.schoolsell.service.Impl.CommodityServiceImpl;
import com.schoolsell.service.Impl.FeedbackServiceImpl;
import com.schoolsell.service.Impl.UserLoginServiceImpl;
import com.schoolsell.service.Impl.WxuserServiceImpl;
import com.schoolsell.util.HttpClientUtil;
import com.schoolsell.util.IMoocJSONResult;
import com.schoolsell.util.JsonUtils;
import com.schoolsell.util.WXSessionMode;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
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
import java.util.*;

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
    WxuserServiceImpl wxuserService;

    @Autowired
    FeedbackServiceImpl feedbackServiceimpl;

    private String appid="wx31174c6b66d0721a";

    private String secret="991bbc77a760a02f329e49e90d5fac10";

    private String grant_type="authorization_code";

    private String url="https://api.weixin.qq.com/sns/jscode2session";

//    public User user=new User();

    /*
    微信登录
     */
    @PostMapping("/wxLogin")
    @ResponseBody
    public Map<String,Object> wxLogin(String code){
        Map<String,Object> map_result=new HashMap<>();
        System.out.println("wxLogin.code: "+ code );

        //https://api.weixin.qq.com/sns/jscode2session?appid=APPID
        // &secret=SECRET
        // &js_code=JSCODE
        // &grant_type=authorization_code

        Map<String,String> map=new HashMap<>();
        map.put("appid",appid);
        map.put("secret",secret);
        map.put("js_code",code);
        map.put("grant_type",grant_type);

        //向微信服务器发送请求
        String wxResult=HttpClientUtil.doGet(url,map);
        System.out.println("wxResult: "+wxResult);

        //对微信登录服务器返回值进行序列化
        WXSessionMode wxSessionMode= JsonUtils.jsonToPojo(wxResult, WXSessionMode.class);

        //查询用户是否存在。如果存在，则不适用随即生成。
        String userID;
        boolean isExist=true;
        Wxuser wxuser_select=wxuserService.selectByOpenID(wxSessionMode.getOpenid());
        System.out.println("此用户信息: "+wxuser_select);
        if(wxuser_select!=null){
            userID=wxuser_select.getUserID();
            System.out.println("本地已存在此用户,用户ID---userID= "+userID);
        }else {
            //随机生成userID
            Random random = new Random(10000);
            userID = String.valueOf(random.nextInt()) + "@qq.com";
            System.out.println("随机生成的userID= " + userID);
            isExist=false;
        }
        map_result.put("userID",userID);
        PictureCount.setUserID(userID);

        //如果不存在，则添加用户openid和session_key进数据库
        if(!isExist) {
            //存入session到数据库
            Wxuser wxuser = new Wxuser(userID, wxSessionMode.getOpenid(), wxSessionMode.getSession_key());
            wxuserService.add(wxuser);
            map_result.put("isExist","no");
        }else {
            map_result.put("isExist", "yes");
        }
        return map_result;
    }

    /*
    微信用户注册信息
     */
    @RequestMapping("/userRegister")
    @ResponseBody
    public Map<String,Object> userRegister(String filePath,int gender,String userName){
        Map<String,Object> map_result=new HashMap<>();

//        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
//        MultipartFile file = multipartHttpServletRequest.getFile("file");               //用户头像,因MultipartFile使用不知如何转String，故此处未用
//        String filePath=request.getParameter("filePath");       //用户头像
//        String gender=request.getParameter("gender");           //性别
//        String userName=request.getParameter("userName");           //昵称

        System.out.println("注册用户信息中...");
        String userID=PictureCount.getUserID();                         //用户ID
        String gender_re;                                       //性别
        if(gender==1){
            gender_re="男";
        }else {
            gender_re="女";
        }

        User user=new User(userID,filePath,gender_re,userName);
        //注册用户
        userLoginServiceimpl.insert(user);
        map_result.put("result","yes");

        return map_result;
    }




//
//    /**
//     * 用户登录
//     *
//     * @param userID
//     * @param password
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/login")
//    public Map<String, Object> weixinLogin(String userID, String password) {
//        Map<String, Object> loginMap = new HashMap<String, Object>();
////        loginMap.put("name",2);
//        System.out.println("用户名:" + userID + "密码:" + password);
//        loginMap.put("result", userLoginServiceimpl.userLogin(userID, password));
//        return loginMap;
//    }
//
//    /**
//     * 注册
//     *
//     * @param request
//     * @return
//     * @throws FileNotFoundException
//     */
//    @RequestMapping(value = "/register")
//    @ResponseBody
//    public Map<String, Object> weixinRegister(HttpServletRequest request) throws FileNotFoundException {
//        Map<String, Object> registerMap = new HashMap<String, Object>();
//        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
//        MultipartFile file = multipartHttpServletRequest.getFile("file");
//        String fileName = file.getOriginalFilename();
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));    //获取文件后缀名
//        String userID = request.getParameter("userID");
//        String password = request.getParameter("password");
//        String userName = request.getParameter("userName");
//        String realName = request.getParameter("realName");
////        String credibility = request.getParameter("credibility");         //信誉值
//        String idCard = request.getParameter("idCard");
//        String phoneNumber = request.getParameter("phoneNumber");
//        String address = request.getParameter("address");
//        fileName = userID + suffixName;             //设置头像名为用户ID
//
////        String filePath = ResourceUtils.getURL("classpath:").getPath() + "static/userImg/" +
////                fileName;
//
//        //获取跟目录
//        File path = new File(ResourceUtils.getURL("classpath:").getPath());
//        if(!path.exists()) path = new File("");
//        System.out.println("path:"+path.getAbsolutePath());
//        System.out.println("path(不加绝对路径):"+path);
//
//        //如果上传目录为/static/images/upload/，则可以如下获取：
//        File upload = new File(path.getAbsolutePath(),"static/userImg/");
//        if(!upload.exists()) upload.mkdirs();
//        System.out.println("upload url:"+upload.getAbsolutePath());
////        String upPath=upload.getAbsolutePath().replace("file:","");
//        String filePath=upload.getAbsolutePath()+"\\"+fileName;                  //详情图路径
////在开发测试模式时，得到的地址为：{项目跟目录}/target/static/thumbnail/
////在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/thumbnail/
//
//        File newFile = new File(filePath);
//
//        //开发环境下
////        String watermark = ResourceUtils.getURL("classpath:").getPath() +
////                "static/png/1.png";
//        //jar包下
////        InputStream watermark= getClass().getClassLoader().getResourceAsStream("classpath:static/png/1.png");
//
//        //war包下
//        File watermark=new File(ResourceUtils.getURL("classpath:").getPath(),"static/png/1.png");
//
//        System.out.println("缩略图(不加绝对路径)的路径:"+watermark);
////        System.out.println("缩略图(加绝对)的路径:"+watermark);
////        String isChecked=request.getParameter("isChecked");
//        System.out.println("用户名:" + userID + "密码：" + password);
//        System.out.println("电话号码:" + phoneNumber);
//        System.out.println(filePath);
//        try {
//            //压缩
////            Thumbnails.of(file.getInputStream()).scale(1f).toFile(newFile);
//            //水印
//            Thumbnails.of(file.getInputStream()).size(1280, 1024).watermark(
//                    Positions.BOTTOM_RIGHT,
//                    ImageIO.read(watermark), 0.5f)
//                    .outputQuality(0.8f).toFile(newFile);
//        } catch (IOException e) {
//            try {
//                file.transferTo(newFile);
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
//        User user = new User(userID, password, userName, realName, idCard, phoneNumber, address, 10,
//                filePath, -1);
//        //-1为未审核，0为未通过，1为已通过
//        //信誉值默认为10
//        registerMap.put("result", userLoginServiceimpl.insert(user));
//        return registerMap;
//    }





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
//
//    /**
//     * 更改用户信息
//     *
//     * @param userID
//     * @param mark
//     * @param newmsg
//     * @return
//     * @throws SQLException
//     */
//    @RequestMapping("/modifyInfor")
//    @ResponseBody
//    public Map<String, Object> modifyInfor(String userID, String mark, String newmsg) throws SQLException {
//        Map<String, Object> map = new HashMap<>();
//        Integer tip = userLoginServiceimpl.updatemsg(userID, mark, newmsg);
//        map.put("result", tip);
//        return map;
//    }

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
