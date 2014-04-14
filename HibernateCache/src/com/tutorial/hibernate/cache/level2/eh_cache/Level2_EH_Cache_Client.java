package com.tutorial.hibernate.cache.level2.eh_cache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class Level2_EH_Cache_Client {
	public static void main(String[] args) throws Exception {
		Configuration cfg = new Configuration();
		cfg = cfg.configure("hibernate.ehcache.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		StudentBean st1, st2;

		Session ses1 = factory.openSession();
		Session ses2 = factory.openSession();

		st1 = (StudentBean) ses1.get(StudentBean.class, new Integer(1));
		System.out.println(st1.getSid() + " " + st1.getSname() + " "
				+ st1.getTot_m());
		ses1.close();

		Thread.sleep(4000);

		st2 = (StudentBean) ses2.get(StudentBean.class, new Integer(1));
		System.out.println(st2.getSid() + " " + st2.getSname() + " "
				+ st2.getTot_m());
		ses2.close();

		factory.close();
	}
}
