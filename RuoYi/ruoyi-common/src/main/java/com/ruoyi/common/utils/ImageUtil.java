package com.ruoyi.common.utils;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.uuid.UUID;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

public class ImageUtil {

    /**
     * 图片转Base64字符串
     * @param imageFileName
     * @return
     */
    public static String convertImageToBase64Str(String imageFileName) {
        ByteArrayOutputStream baos = null;
        try {
            //获取图片类型
            String suffix = imageFileName.substring(imageFileName.lastIndexOf(".") + 1);
            //构建文件
            File imageFile = new File(imageFileName);
            //通过ImageIO把文件读取成BufferedImage对象
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            //构建字节数组输出流
            baos = new ByteArrayOutputStream();
            //写入流
            ImageIO.write(bufferedImage, suffix, baos);
            //通过字节数组流获取字节数组
            byte[] bytes = baos.toByteArray();
            //获取JDK8里的编码器Base64.Encoder转为base64字符
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Base64字符串转图片
     * @param base64String
     * @param imageFileName
     */
    public static void convertBase64StrToImage(String base64String, String imageFileName) {
        ByteArrayInputStream bais = null;
        try {
            //获取图片类型
            String suffix = imageFileName.substring(imageFileName.lastIndexOf(".") + 1);
            //获取JDK8里的解码器Base64.Decoder,将base64字符串转为字节数组
            byte[] bytes = Base64.getDecoder().decode(base64String);
            //构建字节数组输入流
            bais = new ByteArrayInputStream(bytes);
            //通过ImageIO把字节数组输入流转为BufferedImage
            BufferedImage bufferedImage = ImageIO.read(bais);
            //构建文件
            File imageFile = new File(imageFileName);
            //写入生成文件
            ImageIO.write(bufferedImage, suffix, imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载图片
     * @param imageUrl
     * @return
     */
    public static String downUrl(String imageUrl){
        String localPath = RuoYiConfig.getDallE3Path();
        String savePath = DateUtils.dateTimeNow()+MathUtils.generateRandomString(5)+".jpg"; // 图片保存路径
        File directory = new File(localPath);

        try {

            if (!directory.exists()) {
                boolean created = directory.mkdirs(); // 创建文件夹
                if (created) {
                    System.out.println("文件夹创建成功");
                } else {
                    System.out.println("文件夹创建失败");
                }
            }

            URL url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();

            FileOutputStream outputStream = new FileOutputStream(localPath+savePath);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("图片下载完成");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("图片下载失败");
        }
        return Constants.RESOURCE_PREFIX+RuoYiConfig.getDallE3()+savePath;
    }

}
