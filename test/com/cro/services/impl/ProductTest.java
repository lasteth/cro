package com.cro.services.impl;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cro.model.ProductType;

public class ProductTest {
	EntityManagerFactory emf = null;
	EntityManager em = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		emf = Persistence.createEntityManagerFactory("itcast");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(new ProductType());
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
