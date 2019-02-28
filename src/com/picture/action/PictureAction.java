package com.picture.action;

import java.awt.image.BufferedImage;
import java.util.Date;

//import org.apache.struts2.ServletActionContext;

import com.picture.service.Picture;
import org.apache.struts2.ServletActionContext;

public class PictureAction {
	private String content;
	private String picNo;
	private String picName;
	
	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicNo() {
		return picNo;
	}

	public void setPicNo(String picNo) {
		this.picNo = picNo;
	}

	/**
	 * 时间戳生成方法
	 * @return 返回10位精确到秒的时间戳
	 */
	public static String set_timestamp() {
		Date date = new Date();
		long time = date.getTime();
		return (time + "").substring(0, 10);// 返回时间戳前十位。
	}
	
	public String execute() {
		Picture tt = new Picture();
		// BufferedImage d =
		// tt.loadImageLocal("E:/yswenp/表情包/aru/批量修改大小/QQ影像批量编辑结果/7040.png");
		String path=ServletActionContext.getRequest().getRealPath("/files");//获得现在项目的绝对路径
		System.out.println("path="+path);//打印供测试
		//String path = SevletActionContext.getServletContext().getRealPath("/")+"/upload/"+文件名；
		BufferedImage d = tt.loadImageLocal(path+"\\aru\\"+picNo+".png");//拼接成图片地址
		// 往图片上编辑内容
		System.out.println("picNo:"+picNo);
		String timestamp=set_timestamp();
		setPicName(timestamp);
		tt.writeImageLocal(path+"\\new\\"+timestamp+".png",d);
		System.out.println(timestamp);
		tt.writeImageLocal(path+"\\new\\"+timestamp+".png",tt.modifyImage(d, content));
		System.out.println("success:"+timestamp);
		
		return "success";
	}
}
