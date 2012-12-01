package com.cro.services.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.springframework.data.jpa.domain.support.CurrentDateTimeProvider;
import org.springframework.data.jpa.domain.support.DateTimeProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cro.dao.BuyerDao;
import com.cro.dao.impl.BaseDaoImpl;
import com.cro.model.QueryResult;
import com.cro.model.user.Buyer;
import com.cro.modules.utils.Encodes;
import com.cro.security.utils.Digests;
import com.cro.services.BuyerService;

@Service("BuyerService")
public class BuyerServiceImpl implements BuyerService<Buyer> {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	private DateTimeProvider dateTimeprovider = CurrentDateTimeProvider.INSTANCE;
	
	private BuyerDao buyerdao;

	@Override
	@Transactional(readOnly = false)
	public void registerBuyer(Buyer buyer) {
		entryptPassword(buyer);
		buyerdao.save(buyer);
		System.out.println(buyer.getUsername());
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(Buyer user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	@Override
	public void save(Buyer entity) {
		// TODO Auto-generated method stub
		buyerdao.save(entity);
	}

	@Override
	public void update(Buyer entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Buyer find(Serializable entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Serializable... ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public QueryResult<Buyer> getScrollData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult<Buyer> getScrollData(int startIndex, int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult<Buyer> getScrollData(int startIndex, int maxResult,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResult<Buyer> getScrollData(int startIndex, int maxResult,
			String whereJPQL, Object[] params,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Buyer findUserByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return buyerdao.findByLoginName(loginName);
	}
}
