package com.itheima.bos.web.action.take_delivery;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

/**  
 * ClassName:WorkbillAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月25日 下午8:18:44 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope("prototype")
public class ImageAction extends ActionSupport{
	// 使用属性驱动获取用户上传的文件
	private File imgFile;
	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}
	// 使用属性驱动获取用户上传的文件名
	private String imgFileFileName;

	public void setImgFileFileName(String imgFileFileName) {
	    this.imgFileFileName = imgFileFileName;
	}
	public String upload() throws IOException{
		
		Map<String, Object> map = new HashMap<>();
		try {
			//指定保存文件的文件夹
			String dirPath = "/upload";
			// 获取保存图片的文件夹的绝对磁盘路径
			ServletContext servletContext = ServletActionContext.getServletContext();
			String dirRealPath = servletContext.getRealPath(dirPath);
			
			 // 获取文件名的后缀
			String suffix = imgFileFileName.substring(imgFileFileName.lastIndexOf("."));
			// 使用UUId生成文件名
			String fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix; 
			File destFile = new File(dirRealPath + "/" + fileName);
			//保存文件
			FileUtils.copyFile(imgFile, destFile);
			//获取本项目路径
			String contextPath = servletContext.getContextPath();
			map.put("error", 0);
			map.put("url", contextPath + "/upload/" + fileName);
		} catch (IOException e) {
			  
			e.printStackTrace();
			map.put("error", 1);
			map.put("message", e.getMessage());
			
		}
		String json = JSONObject.fromObject(map).toString();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);
		
		return NONE;
	  
	}

}
  
