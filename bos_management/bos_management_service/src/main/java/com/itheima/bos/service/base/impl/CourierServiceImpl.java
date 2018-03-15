package com.itheima.bos.service.base.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.CourierRepository;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.service.base.CourierService;

/**  
 * ClassName:CourierServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午9:31:50 <br/>       
 */
@Transactional
@Service
public class CourierServiceImpl implements CourierService {
	@Autowired
	private CourierRepository courierRepository;

	@Override
	public void save(Courier courier) {
        courierRepository.save(courier);

	}

	@Override
	public Page<Courier> findAll(Pageable pageable) {

		return courierRepository.findAll(pageable);
	}

	@Override
	public void batchDel(String ids) {

		//判断数据是否为空
		if(StringUtils.isNotEmpty(ids)){
			//切割数据
			String[] split = ids.split(",");
			for (String id : split) {
				courierRepository.updateDelTagById(Long.parseLong(id));
			}
		}

	}

}
  
