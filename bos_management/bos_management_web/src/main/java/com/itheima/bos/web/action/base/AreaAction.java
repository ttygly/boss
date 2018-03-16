package com.itheima.bos.web.action.base;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Area;
import com.itheima.bos.service.base.AreaService;
import com.itheima.bos.web.action.ConmonAction;
import com.itheima.utils.PinYin4jUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JsonConfig;

/**  
 * ClassName:AreaAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 下午2:38:44 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class AreaAction extends ConmonAction<Area> {

	public AreaAction() {
		  
		super(Area.class);  
		
	}
	@Autowired
	private AreaService areaService;
    //使用属性驱动获取用户上传的文件
	private File file;
	public void setFile(File file) {
		this.file = file;
	}
	@Action(value = "areaAction_importXLS",results = {@Result(name = "success",
			location = "/pages/base/area.html",type = "redirect")})
	public String importXLS(){
		try {
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(file));
			//读取第一个工作簿
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
			// 储存对象的集合
			List<Area> list = new ArrayList<>();
			for (Row row : sheet) {
				//跳过第一行
				if (row.getRowNum() == 0) {
					continue;
				}
				 // 读取数据
                String province = row.getCell(1).getStringCellValue();
                String city = row.getCell(2).getStringCellValue();
                String district = row.getCell(3).getStringCellValue();
                String postcode = row.getCell(4).getStringCellValue();
                 // 截掉最后一个字符
                province = province.substring(0, province.length()-1);
                city = city.substring(0, city.length()-1);
                district = district.substring(0, district.length()-1);
                 // 获取城市编码和简码
                String citycode = PinYin4jUtils.hanziToPinyin(city,"").toUpperCase();
                String[] headByString = PinYin4jUtils.getHeadByString(postcode + city + district);
                String shortcode = PinYin4jUtils.stringArrayToString(headByString);
                //封装数据
                Area area = new Area();
                area.setProvince(province);
                area.setCity(city);
                area.setDistrict(district);
                area.setCitycode(citycode);
                area.setPostcode(postcode);
                area.setShortcode(shortcode);
                //添加到集合
                list.add(area);
                //保存
                areaService.save(list);
                //释放资源
                hssfWorkbook.close();
			}
		}  catch (IOException e) {
			  
			e.printStackTrace();  
			
		}
		return SUCCESS;
	}
	// AJAX请求不需要跳转页面
    @Action(value = "areaAction_pageQuery")
    public String pageQuery() throws IOException {

        Pageable pageable = new PageRequest(page - 1, rows);

        Page<Area> page = areaService.findAll(pageable);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] {"subareas"});

        page2json(page, jsonConfig);

        return NONE;
    }   
}
  