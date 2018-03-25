package com.itheima.bos.web.action.take_delivery;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.take_delivery.WayBill;
import com.itheima.bos.service.take_delivery.WayBillService;
import com.itheima.bos.web.action.ConmonAction;

/**  
 * ClassName:WaybillAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月25日 下午7:52:09 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope("prototype")
public class WaybillAction extends ConmonAction<WayBill> {

	public WaybillAction() {
		  
		super(WayBill.class);  
		
	}
	@Autowired
	private WayBillService wayBillService;
	@Action("waybillAction_save")
	public String save() throws IOException{
		String msg = "0";
		try {
//			int i = 1/0;
			wayBillService.save(getModel());
		} catch (Exception e) {	  
			e.printStackTrace();  
			msg = "1";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(msg);
		
		return NONE;
	}

}
  
