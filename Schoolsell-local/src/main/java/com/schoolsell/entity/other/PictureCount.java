package com.schoolsell.entity.other;

/**
 * 临时存储商品商品详情信息
 */
public class PictureCount {

//    public static int pictureCount=0;

    public static int getPictureCount=0;            //首页获取图片时从数据库第几张图片获取

    public static int BigkindCount=0;           //大类图片加载更多时图片位置

    public static String userID;            //临时保存的用户ID

    public static void setUserID(String userID) {
        PictureCount.userID = userID;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setBigkindCount(int bigkindCount) {
        BigkindCount = bigkindCount;
    }

    public static int getBigkindCount() {

        return BigkindCount;
    }

    public static void setGetPictureCount(int getPictureCount) {
        PictureCount.getPictureCount = getPictureCount;
    }

    public static int getGetPictureCount() {
//        getPictureCount++;
        return getPictureCount;
    }

    public static String picturePath="";

    public static void setPicturePath(String picturePath) {
        PictureCount.picturePath = picturePath;
    }

    public static String getPicturePath() {

        return picturePath;
    }

    public PictureCount() {
    }

//    public static int getPictureCount() {
//        pictureCount++;
//        return pictureCount;
//    }

//    public static void setPictureCount(int pictureCount) {
//        PictureCount.pictureCount = pictureCount;
//    }
}
