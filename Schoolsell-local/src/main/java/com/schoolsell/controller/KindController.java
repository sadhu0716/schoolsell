package com.schoolsell.controller;

import com.schoolsell.entity.Bigkind;
import com.schoolsell.entity.Commodity;
import com.schoolsell.entity.Smallkind;
import com.schoolsell.entity.other.PictureCount;
import com.schoolsell.exception.MySqlException;
import com.schoolsell.service.Impl.BigKindImpl;
import com.schoolsell.service.Impl.CommodityServiceImpl;
import com.schoolsell.service.Impl.SmallKindImpl;
import com.schoolsell.util.CommodityUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类别（大类，小类）显示
 */

@Controller
@RequestMapping("/schoolsell")
public class KindController {
    @Autowired
    private BigKindImpl bigKindimpl;
    @Autowired
    private SmallKindImpl smallKindimpl;
    @Autowired
    public CommodityServiceImpl commodityServiceimpl;

    /**
     * 获取首页所有大类
     *
     * @return
     * @throws SQLException
     */
    @RequestMapping("/first")
    @ResponseBody
    public JSONArray bigKind() throws SQLException {
        List<Bigkind> bigkindList = bigKindimpl.selectAll();
        JSONArray jsonArray = JSONArray.fromObject(bigkindList);
        return jsonArray;
    }

    /**
     * 获取本大类的所有小类
     *
     * @param bigKindID
     * @return
     * @throws SQLException
     */
    @RequestMapping("/classifyGoods")
    @ResponseBody
    public JSONArray classifyGoods(Integer bigKindID) throws SQLException {
        List<Smallkind> smallkindList = smallKindimpl.kindquery(bigKindID);
        JSONArray jsonArray = JSONArray.fromObject(smallkindList);
        return jsonArray;
    }

    /**
     * 发布时获取所有大类和小类
     *
     * @return
     * @throws SQLException
     */
    @RequestMapping("/SelectionKind")
    @ResponseBody
    public Map<String, Object> selectionKInd() throws SQLException {
        Map<String, Object> map = new HashMap<>();
        List<Bigkind> bigkinds = bigKindimpl.selectAll();
        List<Smallkind> smallkinds = smallKindimpl.selectAll();
        JSONArray bigkindArray = JSONArray.fromObject(bigkinds);
        JSONArray smallkindArray = JSONArray.fromObject(smallkinds);
//       System.out.println(smallkindArray);
        map.put("bigkind", bigkindArray);
        map.put("smallkind", smallkindArray);
        return map;
    }

    /**
     * 获取这个大类下的所有小类
     *
     * @return
     * @throws SQLException
     */
    @RequestMapping("/smallkind")
    @ResponseBody
    public JSONArray getSmallkind(String bigkindID) throws SQLException {
        int int_bigkindID = Integer.valueOf(bigkindID);
        List<Smallkind> bigkindList = smallKindimpl.selectBybigKindID(int_bigkindID);
        JSONArray jsonArray = JSONArray.fromObject(bigkindList);
        return jsonArray;
    }

    /**
     * 显示大类下的所有商品
     * @return
     * @throws MySqlException
     */
    @RequestMapping("/bigkindAllByID")
    @ResponseBody
    public JSONArray bigkindByID(String bigkindID) throws MySqlException {
        int int_bigkindID = Integer.valueOf(bigkindID);
//        List<Commodity> commodityList = commodityServiceimpl.selectAll();
        List<Commodity> commodities = bigKindimpl.selecCommodityByBigkindID(int_bigkindID);       //获取大类下的所有商品
        List<Commodity> commodityList = CommodityUtil.getListPicture(commodities, "大类");       //传八张
        JSONArray jsonArray = JSONArray.fromObject(commodityList);
        return jsonArray;
    }

    /**
     * 大类跳转页面时，大类当前图片的位置指针清零
     */
    @RequestMapping(value = "cleanBigkindCurrentCount")
    @ResponseBody
    public void cleanBigkindCurrentCount() {
        PictureCount.setBigkindCount(0);
    }

}
