package com.tutorial.hibernate.algorithms.uuid_5.mysql;

// insertion of a record into student table

import org.hibernate.cfg.*;
import org.hibernate.*;

class BankClient {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg = cfg.configure("hibernate.mysql.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session ses = factory.openSession();
		Bank b = new Bank();
		String name;
		name = "HDFC1";
		// b.setAcno("....")
		b.setName(name);
		// creation of Transaction object as we are modifying database table
		Transaction tx = ses.beginTransaction();
		ses.persist(b);
		tx.commit();
		ses.close();
		System.out.println("Record Inserted!");
		factory.close();
	}
}
