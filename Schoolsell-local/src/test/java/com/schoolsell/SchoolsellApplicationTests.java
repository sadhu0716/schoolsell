package com.schoolsell;

import com.schoolsell.service.Impl.UserLoginServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolsellApplicationTests {

    @Autowired
    UserLoginServiceImpl userLoginService;

    @Test
    public void contextLoads() {
        try {
            File file=new File(ResourceUtils.getURL("classpath:").getPath());
            System.out.println(ResourceUtils.getURL("classpath:").getPath());
            System.out.println(file.getAbsolutePath());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取static目录下文件测试
     */
    @Test
    public void getStaticFile() throws IOException {
//        Resource resource = new ClassPathResource("static/png/1.png");
//        System.out.println(((ClassPathResource) resource).getPath());

//        File file = ResourceUtils.getFile("static/png/1.png");
//        Resource resource = new UrlResource(file.toURI());
//        System.out.println(resource);

        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) path = new File("");
        System.out.println("path:" + path.getAbsolutePath());

        //如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(path.getAbsolutePath(), "static/thumbnail/");
        if (!upload.exists()) upload.mkdirs();
        System.out.println("upload url:" + upload.getAbsolutePath());

    }

    @Test
    public void TestAbsolutePath(){
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            System.out.println("根目录:"+path);
            File upload=new File(path.getAbsolutePath(),"static/test/");
            if(!upload.exists()) upload.mkdir();
            System.out.println("绝对路径:"+upload);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

//    @Test
//    public void TestPicture(){
//        String filePath=userLoginService.selectByPrimaryKey("891993651@qq.com").getProfilephoto();
//        ClassPathResource resource=new ClassPathResource(filePath);
//        System.out.println(filePath);
//    }

    @Test
    public void getUserPicTimes(){
        System.out.println(ClassUtils.getDefaultClassLoader().getResource("classpath").getPath());
    }

    @Test
    public void getPNGPath(){
        String watermark=ResourceUtils.CLASSPATH_URL_PREFIX + "static/png/1.png";
        File file=new File(watermark);

        System.out.println(file.getName());
    }

}
