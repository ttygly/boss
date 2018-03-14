package com.itheima.bos.web.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.base.StandardService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**  
 * ClassName:StandardAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午4:06:24 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller//spring控制层代码的注解
@Scope("prototype")//action为多例
@SuppressWarnings("all")
public class StandardAction extends ActionSupport implements ModelDriven<Standard> {
	private Standard model = new Standard();

	@Override
	public Standard getModel() {
		  
		return model;
	}
	@Autowired
	private StandardService standardService;
	 // value : // 等价于struts.xml文件中action节点中的name属性
    // 多个结果就使用@Result注解标识
    // name : 结果
    // location: 跳转页面路径
    // type : 使用的方式,重定向或转发
	@Action(value = "standardAction_save",results = {@Result(name = "success",
			location = "/pages/base/standard.html",type = "redirect" )})
	public String save(){
		standardService.save(model);
		return SUCCESS;
	}

}
  