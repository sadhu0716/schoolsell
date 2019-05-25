package com.schoolsell.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

@Configuration
public class MyPictureConfigration extends WebMvcConfigurationSupport {

    /**
     * 首页图片映射target目录下图片路径
     * 首页大类图片的路径
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //jar包下改变路径的string形式，
            String gitPath = path.getParentFile().getParentFile().getParent() +
                    File.separator + "static" +
                    File.separator + "thumbnail" + File.separator;
            /**************************开发环境下,war包环境下都可运行***************************************/
            //获取缩略图
            registry.addResourceHandler("/getThumbnail/**").addResourceLocations("file:" +
                    ResourceUtils.getURL("classpath:").getPath() + "static/thumbnail/");
            //获取大类图片
            registry.addResourceHandler("/getBigkindImg/**").addResourceLocations("file:" +
                    ResourceUtils.getURL("classpath:").getPath() + "static/bigkindImg/");
            //获取小类图片
            registry.addResourceHandler("getSmallkindImg/**").addResourceLocations("file:" +
                    ResourceUtils.getURL("classpath:").getPath() + "static/smallkind/");
            //映射静态资源，获取用户头像
            registry.addResourceHandler("/getimage/**").
                    addResourceLocations("file:" + ResourceUtils.getURL("classpath:").
                            getPath() + "static/userImg/");
            //获取商品详情图
            registry.addResourceHandler("/getCommoditydetail/**").
                    addResourceLocations("file:" + ResourceUtils.getURL("classpath:").
                            getPath() + "static/commodityImg/");
            //获取商品缩略图
            registry.addResourceHandler("/getCommodityThumbnail/**").
                    addResourceLocations("file:" + ResourceUtils.getURL("classpath:").
                            getPath() + "static/thumbnail/");
        }catch(IOException e){
            e.printStackTrace();
        }

            /**************************jar包下运行*****************************************/
//
//            //获取缩略图
//            registry.addResourceHandler("/getThumbnail/**").addResourceLocations(gitPath);
//            //获取大类图片
//            gitPath=path.getParentFile().getParentFile().getParent()+
//                    File.separator+"static"+
//                    File.separator+"bigkindImg"+File.separator;
//            registry.addResourceHandler("/getBigkindImg/**").addResourceLocations(gitPath);
//            //获取小类图片
//            gitPath=path.getParentFile().getParentFile().getParent()+
//                    File.separator+"static"+
//                    File.separator+"smallkind"+File.separator;
//            registry.addResourceHandler("getSmallkindImg/**").addResourceLocations(gitPath);
//            //映射静态资源，获取用户头像
//            gitPath=path.getParentFile().getParentFile().getParent()+
//                    File.separator+"static"+
//                    File.separator+"userImg"+File.separator;
//            registry.addResourceHandler("/getimage/**").
//                    addResourceLocations(gitPath);
//            //获取商品详情图
//            gitPath=path.getParentFile().getParentFile().getParent()+
//                    File.separator+"static"+
//                    File.separator+"commodityImg"+File.separator;
//            registry.addResourceHandler("/getCommoditydetail/**").
//                    addResourceLocations(gitPath);
//            //获取商品缩略图
//            gitPath=path.getParentFile().getParentFile().getParent()+
//                    File.separator+"static"+
//                    File.separator+"thumbnail"+File.separator;
//            registry.addResourceHandler("/getCommodityThumbnail/**").
//                    addResourceLocations(gitPath);

        super.addResourceHandlers(registry);
    }

    /**
     * 解决ajax跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/**");
    }


}