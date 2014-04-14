package com.tutorial.hibernate.algorithms.uuid_5.oracle;

import org.hibernate.cfg.*;
import org.hibernate.*;

class BankClient {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg = cfg.configure("hibernate.oracle.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session ses = factory.openSession();


		Bank b = new Bank();
		String name;
		name = "SBI";
		// b.setSid(sid);
		b.setName(name);
		Transaction tx = ses.beginTransaction();
		ses.persist(b);
		tx.commit();
		ses.close();

		System.out.println("Record Inserted!");
		factory.close();
	}
}
