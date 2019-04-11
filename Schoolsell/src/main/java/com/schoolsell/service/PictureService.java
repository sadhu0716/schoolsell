package com.schoolsell.service;

import java.io.IOException;
import java.util.List;

public interface PictureService {
    /**
     * 根据商品id来获得该商品的路径列表List<String>
     * @param cid
     * @return
     */
     List<String> selectPictureByCid(Integer cid) throws IOException;


}
