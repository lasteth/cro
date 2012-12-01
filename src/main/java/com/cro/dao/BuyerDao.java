package com.cro.dao;

import com.cro.model.user.Buyer;

public interface BuyerDao extends BaseDao<Buyer> {

	Buyer findByLoginName(String loginName);

	
}
