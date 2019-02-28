package com.picture.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 在图片上编辑文本内容
 * 
 * @version 2018-2-27 上午11:12:09
 *
 */
public class Picture {
	//private Font font = new Font("YaHei Consolas Hybrid", Font.PLAIN, 26); // 添加字体的属性设置
	private Graphics2D g = null;
	/**
	 * 导入本地图片到缓冲区
	 */
	public BufferedImage loadImageLocal(String imgName) {
		try {
			System.out.println("读取："+imgName);
			return ImageIO.read(new File(imgName));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * 生成新图片到本地
	 */
	public void writeImageLocal(String newImage, BufferedImage img) {
		if (newImage != null && img != null) {
			try {
				File outputfile = new File(newImage);
				System.out.println("生成："+newImage);
				ImageIO.write(img, "png", outputfile);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
	 */
	public BufferedImage modifyImage(BufferedImage img, Object content) {
		int w = img.getWidth();
		int h = img.getHeight();
		int h1=img.getHeight()+30;
		//Color color1=new Color(0.0F,0.0F,0.0F,0.0F);
		BufferedImage img2 = new BufferedImage(w, h1, BufferedImage.TYPE_INT_RGB);
		
		try {
			g = img2.createGraphics();
	        g.fillRect(0, 0, w, h1);
	        
	        //Graphics graphicsQR = img.getGraphics();
	        g.drawImage(img, 0, 0, w, h, null);
	        
			Graphics2D textIntro = img2.createGraphics();
	        textIntro.setColor(new Color(51, 51, 51));//颜色
	        
			g.setBackground(Color.RED);// 设置背景颜色
			g.setColor(new Color(51, 51, 51));// 设置字体颜色
			
			//新建字体，初始化，计算文字宽度。
			Font font = new Font("YaHei Consolas Hybrid", Font.PLAIN, 26);
			g.setFont(font);
			int fontw=getTextWidth(g,content.toString(),font);
			
			int textx=0;
			int y=h+22;
			
			if(fontw>w) {
				for(int i=25;i>8;i--) {											//如果超过,一点一点
					font = new Font("YaHei Consolas Hybrid", Font.PLAIN, i);	//重新创建字体,调小字号
					g.setFont(font);
					fontw=getTextWidth(g,content.toString(),font);
					if(fontw<=w) {
						textx = (w - fontw) / 2;
						break;													//直到字体总长度小于图片宽度
					}
				}
			}else {
				textx = (w - fontw) / 2;
			}
			
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
			g.drawString(content.toString(), textx, y);
			g.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return img2;
	}
	
	
	public static int getTextWidth(Graphics2D g,String content,Font font){
		FontMetrics fm = g.getFontMetrics(font);
        int fontw = fm.stringWidth(content.toString());
        return fontw;
	}
	/**
	 * main方法测试用
	 * @param args
	 */
	public static void main(String[] args) {
		Picture tt = new Picture();
		BufferedImage d = tt.loadImageLocal("WebContent/files/aru/0141.png");
		tt.writeImageLocal("WebContent/files/new/1.png",tt.modifyImage(d, "笑不露齿"));
		System.out.println("success");
	}
}