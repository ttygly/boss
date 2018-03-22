package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.TakeTime;
import com.itheima.bos.service.base.TakeTimeService;
import com.itheima.bos.web.action.ConmonAction;

/**  
 * ClassName:TakeTimeAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月21日 下午5:52:54 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope("prototype")
public class TakeTimeAction extends ConmonAction<TakeTime> {

	public TakeTimeAction() {
		  
		super(TakeTime.class);  
		
	}
	@Autowired
	private TakeTimeService takeTimeService;
	@Action("takeTimeAction_listajax")
	public String listajax() throws IOException{
		// 查询所有的在职的快递员
		List<TakeTime> list= takeTimeService.findAll();
		
		list2json(list, null);
		return NONE;
	}

}
  
