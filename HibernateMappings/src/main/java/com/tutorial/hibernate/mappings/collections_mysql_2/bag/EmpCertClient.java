package com.tutorial.hibernate.mappings.collections_mysql_2.bag;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmpCertClient {
	private static SessionFactory factory;

	public static void main(String[] args) {
		try {
			factory = new Configuration().configure("hibernate.mysql.cfg.xml")
					.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		EmpCertClient ecc = new EmpCertClient();

		ArrayList bag = new ArrayList();
		bag.add(new Certificate("MCAA"));
		bag.add(new Certificate("MBAA"));
		bag.add(new Certificate("PMPA"));
		Integer empID1 = ecc.addEmployee("Manojj", "Kumarr", 4000, bag);
	}

	/* Method to add an employee record in the database */
	public Integer addEmployee(String fname, String lname, int salary, List cert) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;
		try {
			tx = session.beginTransaction();
			Employee employee = new Employee(fname, lname, salary);
			employee.setCertificates(cert);
			session.saveOrUpdate(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeID;
	}
}
