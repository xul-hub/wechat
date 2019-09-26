package com.mybatis.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;


/**
 * 
* @ClassName: ThumbnailatorUtil.java
* @Description: 图片操作工具类
*
* @version: v1.0.0
* @author: xul
* @date: 2018年12月6日 上午9:49:49
 */
public class ThumbnailatorUtil {
	
	
	public static void main(String[] args) {
		
	}
	
	/**
     * 指定大小进行缩放
     * 
     * @throws IOException
     */
    public void appointSizeCompress(String path,int width,int height,String outFilepath) throws IOException {
        /**
         * size(width,height) 若图片横比200小，高比300小，不变
         * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
         * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
         */
        Thumbnails.of(path).size(width, height).toFile(outFilepath);
    }

    /**
     * 按照比例进行缩放
     * 
     * @throws IOException
     */
    public void scaleCompress(String path,double scale,String outFilepath) throws IOException {
        /**
         * scale(比例) 如： 0.25f  1.10f
         */
        Thumbnails.of(path).scale(scale).toFile(outFilepath);
    }

    /**
     * 不按照比例，指定大小进行缩放
     * 
     * @throws IOException
     */
    public void appointSizeZoom(String path,int width, int height,String outFilepath) throws IOException {
        /**
         * keepAspectRatio(false) 默认是按照比例缩放的
         */
        Thumbnails.of(path).size(width, height).keepAspectRatio(false).toFile(outFilepath);
    }

    /**
     * 旋转
     * 
     * @throws IOException
     */
    public void rotateImage(String path,int width, int height,double angle,String outFilepath) throws IOException {
        /**
         * rotate(角度),正数：顺时针 负数：逆时针 如：90
         */
        Thumbnails.of(path).size(width, height).rotate(angle).toFile(outFilepath);
    }

    /**
     * 水印
     * 
     * @throws IOException
     */
    public void watermark(String path,int width, int height,String watermarkPath,String outFilepath) throws IOException {
        /**
         * watermark(位置，水印图，透明度)
         */
        Thumbnails.of(path).size(width, height).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(watermarkPath)), 0.5f).outputQuality(0.8f).toFile(outFilepath);
    }

    /**
     * 裁剪
     * 
     * @throws IOException
     */
    public void tailoring(String path,Position position, int width, int height,int imageWidth, int imageHeight,String watermarkPath,String outFilepath) throws IOException {
        /**
         * 图片中心400*400的区域  sourceRegion(Positions.CENTER, 400, 400)
         * 图片右下400*400的区域 sourceRegion(Positions.BOTTOM_RIGHT, 400, 400)
         * 指定坐标  如：sourceRegion(600, 500, 400, 400)
         */
        Thumbnails.of(path).sourceRegion(Positions.CENTER, width, height).size(imageWidth, imageHeight).keepAspectRatio(false).toFile(outFilepath);
    }

    /**
     * 转化图像格式
     * 
     * @throws IOException
     */
    public void transformImageFormat(String path,int width, int height,String format,String outFilepath) throws IOException {
        /**
         * outputFormat(图像格式) 如：png gif
         */
        Thumbnails.of(path).size(width, height).outputFormat(format).toFile(outFilepath);
    }

    /**
     * 输出到OutputStream
     * 
     * @throws IOException
     */
    public void outputStreamObj(String path,int width, int height,String outFilepath) throws IOException {
        /**
         * toOutputStream(流对象)
         */
        OutputStream os = new FileOutputStream(outFilepath);
        Thumbnails.of(path).size(width, height).toOutputStream(os);
    }

    /**
     * 输出到BufferedImage
     * 
     * @throws IOException
     */
    public void bufferedImageObj(String path,int width, int height,String outFilepath) throws IOException {
        /**
         * asBufferedImage() 返回BufferedImage
         */
        BufferedImage thumbnail = Thumbnails.of(path).size(width, height).asBufferedImage();
        ImageIO.write(thumbnail, "jpg", new File(outFilepath));//RenderedImage im, String formatName, File output
    }
    
    /*public static void main(String[] args) {
    	scaleCompress("",1.0f,"");
	}*/
}
