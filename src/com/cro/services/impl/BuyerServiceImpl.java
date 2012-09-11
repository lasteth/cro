package com.cro.services.impl;

import org.springframework.stereotype.Service;

import com.cro.dao.impl.BaseDAOImpl;
import com.cro.model.QueryResult;
import com.cro.model.user.Buyer;
import com.cro.services.BuyerService;

@Service("BuyerService")
public class BuyerServiceImpl extends BaseDAOImpl<Buyer> implements BuyerService {
	
}
