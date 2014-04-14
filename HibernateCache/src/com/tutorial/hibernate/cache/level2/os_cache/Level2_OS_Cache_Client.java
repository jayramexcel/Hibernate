package com.tutorial.hibernate.cache.level2.os_cache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class Level2_OS_Cache_Client {
	public static void main(String[] args) throws Exception {
		Configuration cfg = new Configuration();
		cfg  = cfg.configure("hibernate.oscache.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session ses1 = factory.openSession();
		Session ses2 = factory.openSession();

		StudentBean st1, st2;
		
		st1 = (StudentBean) ses1.get(StudentBean.class, new Integer(105));
		ses1.close();	
		System.out.println(st1.getSid()+" "+st1.getSname()+" "+st1.getTot_m());
		
		Thread.sleep(5000);
		
		st2 = (StudentBean) ses2.get(StudentBean.class, new Integer(105));
		ses2.close();
		System.out.println(st2.getSid()+" "+st2.getSname()+" "+st2.getTot_m());
		factory.close();
	}
}