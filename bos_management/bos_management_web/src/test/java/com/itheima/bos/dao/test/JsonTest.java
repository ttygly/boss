package com.itheima.bos.dao.test;

import java.util.Collection;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import com.itheima.crm.domain.Customer;

/**  
 * ClassName:JsonTest <br/>  
 * Function:  <br/>  
 * Date:     2018年3月22日 上午10:00:05 <br/>       
 */
public class JsonTest {
	public static void main(String[] ages){
		 Collection<? extends Customer> collection = WebClient.create(
		"http://localhost:8180/crm/webService/customerService/findAll")
		.type(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.getCollection(Customer.class);
		for (Customer customer : collection) {
			System.out.println(customer);
		}
	}

}
  
