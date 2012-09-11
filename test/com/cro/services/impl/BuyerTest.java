package com.cro.services.impl;


import java.util.LinkedHashMap;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cro.model.QueryResult;
import com.cro.model.user.Buyer;
import com.cro.model.user.Gender;
import com.cro.services.BuyerService;


public class BuyerTest {
	private static BuyerService buyerService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
		buyerService = (BuyerService)act.getBean("BuyerService");
	}

	//@Test
	public void test() {
		buyerService.save(new Buyer("zhangsan1", "123", "jjj@aa.com"));
	}
	
	@Test
	public void getScrollData() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String> ();
		orderby.put("regTime", "desc");
		QueryResult<Buyer> qr = buyerService.getScrollData();
		System.out.println("×Ü¼ÇÂ¼Êý£º" + qr.getTotalrecord());
		for(Buyer b : qr.getRetultlist()) {
			System.out.println(b.getUsername());
		}
	}
}
