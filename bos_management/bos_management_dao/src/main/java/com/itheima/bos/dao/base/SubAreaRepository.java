package com.itheima.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.domain.base.SubArea;

/**  
 * ClassName:SubAreaRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月18日 下午2:41:24 <br/>       
 */
public interface SubAreaRepository extends JpaRepository<SubArea, Long>{

	List<SubArea> findByFixedAreaIsNull();

	List<SubArea> findByFixedArea(FixedArea fixedArea);

	
}
  
