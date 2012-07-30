package com.sopovs.moradanen.hb;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sopovs.moradanen.hb.TestEntity.TestEnum;

public class EnumBindingLoggingTest {
	private SessionFactory sessionFactory;

	@Before
	public void setUp() throws Exception {
		// A SessionFactory is set up once for an application
		sessionFactory = new Configuration()
				.configure() // configures settings from hibernate.cfg.xml
				.buildSessionFactory();
	}

	@After
	public void tearDown() throws Exception {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

	@Test
	public void testInsertingEnum() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		TestEntity entity = new TestEntity();
		entity.setTestEnum(TestEnum.FIRST);
		session.save(entity);
		session.flush();
		Assert.assertNotNull(entity.getId());

		session.getTransaction().commit();
		session.close();
	}

}
