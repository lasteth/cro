package com.cro.dao.impl;


import com.cro.dao.BuyerDao;
import com.cro.model.user.Buyer;

public class BuyerDaoImpl extends BaseDaoImpl<Buyer> implements BuyerDao {

	
	@Override
	public Buyer findByLoginName(String loginName) {
		// TODO Auto-generated method stub
		Buyer buyer = (Buyer)em.createQuery("select count(o) from Buyer o where o.username=?1").setParameter(1, loginName);
		System.out.println(buyer);
		return buyer;
	}

}
