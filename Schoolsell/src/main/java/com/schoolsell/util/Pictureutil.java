package com.schoolsell.util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * 商品详情图工具类
 */
public class Pictureutil {
    public static void PictureFormatAsJpg(File file, String path) throws IOException {
        Thumbnails.Builder<File> builder = Thumbnails.of(file);
        if (!(file.getName().toLowerCase().endsWith("jpg"))) {
            builder.scale(1f).outputFormat("jpg").toFile(path);
            System.out.println("图片类型不是jpg");
        } else {
            System.out.println("图片类型是jpg");
        }

    }
}
