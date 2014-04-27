package com.tutorial.hibernate.mappings.collections_mysql_2.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmpCertClient {
	private static SessionFactory factory;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{ 
			factory = new Configuration().configure("hibernate.mysql.cfg.xml").buildSessionFactory(); 
		}catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex); 
			throw new ExceptionInInitializerError(ex); 
		}
		HashMap set = new HashMap(); 
		set.put("ComputerScience", new Certificate("MCA")); 
		set.put("BusinessManagement", new Certificate("MBA")); 
		set.put("ProjectManagement", new Certificate("PMP"));

		EmpCertClient ec = new EmpCertClient();
		ec.addEmployee("Jayram", "Rout", 5000, set);
	}
	public Integer addEmployee(String fname, String lname, int salary, Map cert){ 
		Session session = factory.openSession(); 
		Transaction tx = null; 
		Integer employeeID = null; 
		try{ 
			tx = session.beginTransaction(); 
			Employee employee = new Employee(fname, lname, salary); 
			employee.setCertificates(cert); 
			employeeID = (Integer) session.save(employee); 
			tx.commit(); 
		}catch (HibernateException e) { 
			if (tx!=null) 
				tx.rollback(); 
			e.printStackTrace(); 
		}finally { 
			session.close(); 
		} 
		return employeeID; 
	}
}