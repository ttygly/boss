package com.itheima.bos.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.StandardRepository;
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.base.StandardService;

/**  
 * ClassName:StandardServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午4:43:34 <br/>       
 */
@Transactional
@Service
public class StandardServiceImpl implements StandardService {
	@Autowired
	private StandardRepository standardRepository;
    //保存
	@Override
	public void save(Standard standard) {
		standardRepository.save(standard);  
		
	}

}
  
