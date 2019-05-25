package com.schoolsell.controller;

import com.schoolsell.entity.Bigkind;
import com.schoolsell.entity.Commodity;
import com.schoolsell.entity.Picture;
import com.schoolsell.entity.User;
import com.schoolsell.entity.other.PictureCount;
import com.schoolsell.exception.MyOtherException;
import com.schoolsell.exception.MySqlException;
import com.schoolsell.service.Impl.BigKindImpl;
import com.schoolsell.service.Impl.CommodityServiceImpl;
import com.schoolsell.service.Impl.PictureSerimpl;
import com.schoolsell.service.Impl.UserLoginServiceImpl;
import com.schoolsell.util.CommodityUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品类
 */

@Controller
@RequestMapping(value = "/schoolsell")
public class CommodityController {

    @Autowired
    UserLoginServiceImpl userLoginServiceimpl;

    @Autowired
    CommodityServiceImpl commodityServiceimpl;
    @Autowired
    BigKindImpl bigKindimpl;

    @Autowired
    PictureSerimpl pictureSerimpl;

    /**
     * 添加商品
     * 一张商品缩略图
     * 商品具体信息
     * 使用cid来作为缩略图的名称
     *
     * @return
     */
    @RequestMapping(value = "/releaseThumbnail")
    @ResponseBody
    public Map<String, Object> CommodityRelease(HttpServletRequest request) throws IOException {
        System.out.println("开始发布缩略图***");
        Map<String, Object> commodityMap = new HashMap<String, Object>();
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("file");

        //获取前端formdata
        String cName = request.getParameter("cName");
        String kName = request.getParameter("kName");
        String cPrice = request.getParameter("cPrice");
        BigDecimal bd_CPrice = new BigDecimal(cPrice);                  //价格转换成decimal类型
        String userTime = request.getParameter("useTime");
        String userID = request.getParameter("userID");
        String cDescription = request.getParameter("cDescription");
        String count = request.getParameter("count");
        int int_count = Integer.valueOf(count);                         //数量转换成int类型
        String bargain = request.getParameter("bargain");
        Boolean bool_bargain;                                           //bargain讨价还价转换为boolean类型
        if (bargain.equals("是")) {
            bool_bargain = true;
        } else {
            bool_bargain = false;
        }

        //此处获取fileName用于先插入进数据库，之后再update缩略图名称
        //获取前端图片路径名（包括图片名和后缀）
        String fileName = file.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));          //获取图片后缀名
        prefix = ".jpg";                                                //jpg相对于jpeg更流行
//        String filePath = ResourceUtils.getURL("classpath:").getPath() +
//                "static/thumbnail/" + fileName;                                     //缩略图相对路径

        //打包jar包时路径
        //获取跟目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) path = new File("");
        System.out.println("path:" + path.getAbsolutePath());

        //如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(path.getAbsolutePath(), "static/thumbnail/");
        if (!upload.exists()) upload.mkdirs();
        System.out.println("upload url:" + upload.getAbsolutePath());
//        String upPath=upload.getAbsolutePath().replace("file:","");
        String filePath=upload.getAbsolutePath()+"\\"+fileName;                  //缩略图相对路径  在resources目录下
        System.out.println("缩略图的getAbsolutePath:"+upload.getAbsolutePath());
        System.out.println("缩略图首次存储路径:"+filePath);
        //在开发测试模式时，得到的地址为：{项目跟目录}/target/static/thumbnail/
        //在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/thumbnail/

        //isChecked: -1 未审核  ； 0 已审核（未通过）； 1 已审核（已通过）
        //因还未有管理员后台审核，故默认商品为已审核（已通过）
        Commodity commodity = new Commodity(bd_CPrice, bool_bargain, userTime, cDescription,
                1, userID, cName, kName, filePath, int_count);
        commodityMap.put("result", commodityServiceimpl.insert(commodity));         //插入商品，返回结果为SqlEnum的值
        int cID = commodity.getCid();                                               //获取插入后的commodity的自增值主键cID
        System.out.println("新增商品缩略图的cID:" + cID);                             //****************

        String String_cID = upload.getAbsolutePath() +"\\"+ String.valueOf(cID) + prefix;                  //重新定义缩略图的地址+名称
        commodityServiceimpl.updateThumbnailByCommodity(cID, String_cID);           //更新缩略图地址+名称
        System.out.println("缩略图第二次存储路径update:"+String_cID);

        //使用工具类PictureCount中的属性值来保存更新后的缩略图地址+名称，用于上传详情图时获取对应缩略图的cid
        PictureCount.setPicturePath(commodityServiceimpl.selectByThumbnail(String_cID).getThumbnail());
        System.out.println("Picture的PicturePath:" + PictureCount.getPicturePath());  //更新后的缩略图地址+名称**************

        filePath = commodityServiceimpl.selectByThumbnail(String_cID).getThumbnail();    //获取更新后的缩略图地址+名称
        File newFile = new File(filePath);

        //开发环境下获取水印图
//        String inputStream = ResourceUtils.getURL("classpath:").getPath() +
//                "static/png/1.png";
        //jar包时使用这种方式获取resource下文件资源,即获取水印图
//        InputStream inputStream =  getClass().getClassLoader().getResourceAsStream("classpath:static/png/1.png");

        //war包下获取1.png
        File watermark=new File(ResourceUtils.getURL("classpath:").getPath(),"static/png/1.png");

//        System.out.println("缩略图jar包下的水印图:"+inputStream.toString());

        System.out.println("缩略图的最终filepath:"+filePath);
        //保存更新后的缩略图地址+名称
        //添加水印并保存到相对路径下
        try {
            //水印
            Thumbnails.of(file.getInputStream()).size(700, 700).watermark(
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
        return commodityMap;
    }

    /**
     * 添加商品详情图
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/releaseDetail")
    @ResponseBody
    public Map<String, Object> commodityDetailUpload(HttpServletRequest request) throws IOException {
        Map<String, Object> pictureMap = new HashMap<String, Object>();
        MultipartHttpServletRequest request1 = (MultipartHttpServletRequest) request;
        MultipartFile file = request1.getFile("file");

        //获取formdata
        String userID = request.getParameter("userID");
        String cName = request.getParameter("cName");
        String userName = userLoginServiceimpl.selectByPrimaryKey(userID).getUserName();
        String fileName = file.getOriginalFilename();
        System.out.println("传详情图时的PicturePath:" + PictureCount.getPicturePath());  //*************

        //通过更新后的缩略图：cid.后缀名 查找刚添加商品的cid
        //此处也可保存cid,用cid来查询
        Commodity commodity1 = commodityServiceimpl.selectByThumbnail(PictureCount.getPicturePath());
        int cID = commodity1.getCid();
        System.out.println(cID);                //********************

//        String filePath = ResourceUtils.getURL("classpath:").getPath() +
//                "static/commodityImg/" + fileName;                         //详情图路径

        //获取跟目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) path = new File("");
        System.out.println("path:"+path.getAbsolutePath());

//如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(path.getAbsolutePath(),"static/commodityImg/");
        if(!upload.exists()) upload.mkdirs();
        System.out.println("upload url:"+upload.getAbsolutePath());
//        String upPath=upload.getAbsolutePath().replace("file:","");
        String filePath=upload.getAbsolutePath()+"/"+fileName;                  //详情图路径
//在开发测试模式时，得到的地址为：{项目跟目录}/target/static/thumbnail/
//在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/thumbnail/

        File newFile = new File(filePath);
        //开发环境下
//        String inputStream = ResourceUtils.getURL("classpath:").getPath() +
//                "static/png/1.png";
        //jar包时只能使用流的方式获取resource下文件资源
//        InputStream inputStream =  getClass().getClassLoader().getResourceAsStream("classpath:static/png/1.png");

        //war包下获取1.png
        File watermark=new File(ResourceUtils.getURL("classpath:").getPath(),"static/png/1.png");

//        System.out.println("详情图jar包下的水印图:"+inputStream.toString());


        System.out.println("详情图的filepath"+filePath);//详情图相对路径
        //添加水印并保存到相对路径下
        try {
            Thumbnails.of(file.getInputStream()).size(700, 700).watermark(
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
        Picture picture = new Picture(cID, filePath);
        pictureMap.put("result", commodityServiceimpl.insert_Detail(picture));
        return pictureMap;
    }

    /**
     * 首页显示图片与轮播图
     *
     * @return
     * @throws MySqlException
     */
    @RequestMapping("/firstpic")
    @ResponseBody
    public Map<String,Object> firstpic() throws MySqlException {
        //轮播图
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> pictureList = pictureSerimpl.select();
        map.put("result", pictureList);

        //首页图片
//        List<Commodity> commodityList = commodityServiceimpl.selectAll();
//        List<Commodity> commodities = CommodityUtil.getListPicture(commodityList, "首页");        //获取八张图片
//
//        map.put("pic",commodities);
        return map;
    }

    /*
   获取所有首页商品信息和首页大类
    */
    @RequestMapping("/getAllPicture")
    @ResponseBody
    public Map<String,Object> getAllPicture() throws MySqlException{
        Map<String,Object> map=new HashMap<>();
        List<Commodity> commodities=commodityServiceimpl.selectAll();
        map.put("all",commodities);

        List<Bigkind> bigkindList = bigKindimpl.selectAll();
        map.put("first",bigkindList);
        return map;
    }

    @RequestMapping("/leaveFirst")
    @ResponseBody
    public Map<String,Object> leaveFirst() throws MySqlException{
        PictureCount.setGetPictureCount(0);
        Map<String,Object> map=new HashMap<>();
        map.put("result","清零");
//        JSONArray jsonArray=JSONArray.fromObject(map);
        return map;
    }

    /**
     * 首页对应商品的价格
     *
     * @param cid
     * @return
     */
    @RequestMapping(value = "firstprice")
    @ResponseBody
    public Map<String, Object> firstPrice(String cid) {
        Map<String, Object> priceMap = new HashMap<String, Object>();
        int int_cid = Integer.valueOf(cid);
        BigDecimal big_price = commodityServiceimpl.selectByPrimaryKey(int_cid).getCprice();
        String price = big_price.toString();
        priceMap.put("result", price);
        return priceMap;
    }

    //前端传入商品id，获取商品信息
    @ResponseBody
    @RequestMapping("/getCommodityinfo")
    public Object getCommodityinfo(Integer cid) {
        Commodity commodity = commodityServiceimpl.selectByPrimaryKey(cid);
        List<String> stringList;
        try {
            stringList = pictureSerimpl.selectPictureByCid(cid);
        } catch (IOException e) {
            throw new MyOtherException("获取详情图片异常!");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("commodityDetail", commodity);
        map.put("detailPic", stringList);

        return map;


    }

//购物车


//    //传入userid返回信誉值
//    @ResponseBody
//    @RequestMapping("/getStoreCredit")
//    public Object getStoreCredit(String userid) {
//        System.out.println("userID:" + userid);
//        User user = userLoginServiceimpl.selectByPrimaryKey(userid);
//        System.out.println("信誉值User:" + user);
//        Integer credity = user.getCredibility();
//        System.out.println("信誉值credity:" + credity);
//        Map<String, Object> map = new HashMap<>();
//        map.put("credit", credity);
//        return map;
//    }

    /**
     * 获取所有userID的商品发布
     *
     * @param userID
     * @return
     * @throws SQLException
     */
    @RequestMapping("/MyReleasemsg")
    @ResponseBody
    public JSONArray MyReleasemsg(String userID) throws SQLException {
        List<Commodity> commodity = commodityServiceimpl.selectByUserID(userID);
        JSONArray jsonObject = JSONArray.fromObject(commodity);
        return jsonObject;
    }

    /**
     * 下架商品
     *
     * @param cID
     * @return
     * @throws SQLException
     */
    @RequestMapping("/MyReleaseObtained")
    @ResponseBody
    public Map<String, Object> MyReleaseObtained(Integer cID) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        try {
            String filename = cID + ".jpg";
            String path = ResourceUtils.getURL("classpath:") + "static/thumbnail/" + filename;
            String pathname = path.substring(5, path.length());
            File file = new File(pathname);
            boolean isdelete = file.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Integer value = commodityServiceimpl.deleteByPrimaryKey(cID);
        map.put("result", value);
        return map;
    }

    /**
     * 修改我的发布的商品的数量，价格
     *
     * @param cID
     * @param cCount
     * @param cPrice
     * @return
     * @throws SQLException
     */
    @RequestMapping("/modify")
    @ResponseBody
    public Map<String, Object> modify(Integer cID, Integer cCount, BigDecimal cPrice) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        String updateresult = commodityServiceimpl.updateMsg(cID, cCount, cPrice);
        map.put("result", updateresult);
        return map;
    }

    /**
     * 显示小类
     *
     * @param kName
     * @return
     * @throws SQLException
     */
    @RequestMapping("/browsegoods")
    @ResponseBody
    public JSONArray browsegoods(String kName) throws SQLException {
        List<Commodity> commodityList = commodityServiceimpl.selectByKName(kName);
        JSONArray jsonArray = JSONArray.fromObject(commodityList);
        return jsonArray;
    }

    /**
     * 轮播图
     *
     * @return
     * @throws SQLException
     */
    @RequestMapping("/headline")
    @ResponseBody
    public Map<String, Object> headline() throws SQLException {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> pictureList = pictureSerimpl.select();
        map.put("result", pictureList);
        return map;
    }


}
