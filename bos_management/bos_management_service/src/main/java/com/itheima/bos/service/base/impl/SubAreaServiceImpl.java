package com.itheima.bos.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.SubAreaRepository;
import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.service.base.SubAreaService;

/**  
 * ClassName:SubAreaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月18日 下午2:38:28 <br/>       
 */
@Transactional
@Service
public class SubAreaServiceImpl implements SubAreaService {
	@Autowired
    private SubAreaRepository subAreaRepository;
	@Override
	public void save(SubArea model) {
        subAreaRepository.save(model);

	}
	@Override
	public Page<SubArea> findAll(Pageable pageable) {
		  
		
		return subAreaRepository.findAll(pageable);
	}
	@Override
	public List<SubArea> findUnAssociatedSubAreas() {
		  
		return subAreaRepository.findByFixedAreaIsNull();
	}
	@Override
	public List<SubArea> findAssociatedSubAreas(Long fixedAreaId) {
		FixedArea fixedArea = new FixedArea();
		fixedArea.setId(fixedAreaId);
		return subAreaRepository.findByFixedArea(fixedArea);
	}

}
  
