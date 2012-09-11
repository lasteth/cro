package com.cro.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.cro.services.BuyerService;

@Controller
public class BuyerRegisterAction extends BaseAction {
	
	@Resource
	private BuyerService buyerService;
	
	public String execute() throws Exception {
		
		return SUCCESS;
	}
}
