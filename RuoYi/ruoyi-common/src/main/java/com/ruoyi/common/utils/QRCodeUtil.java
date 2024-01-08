package com.ruoyi.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Hashtable;



/**
 * @author zx
 * @date 2020/9/7
 * 生成二维码工具类
 */
public class QRCodeUtil {

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;

    /**
     * 生成二维码图片
     * @param url 扫描二维码跳转地址
     * @return
     * @throws Exception
     */
    public static BufferedImage createImage(String url) throws Exception {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    /**
     * 输出二维码
     * @param url 扫描二维码跳转地址
     * @param response
     * @throws Exception
     */
    public static void encode(String url, HttpServletResponse response)
            throws Exception {
        BufferedImage image = QRCodeUtil.createImage(url);
        ImageIO.write(image, FORMAT_NAME, response.getOutputStream());
    }

    public static void main(String[] args) {
        try {
            encode("https://qr.alipay.com/bax05403irtkrbqfeqpf55e6",null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
