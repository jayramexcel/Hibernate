package com.tutorial.hibernate.hql;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmployeeHQLClient {
	private static SessionFactory factory;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{ 
			factory = new Configuration().configure().buildSessionFactory(); 
		}catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex); 
			throw new ExceptionInInitializerError(ex); 
		}
		criteriaExamples();
	}
	
	public static void criteriaExamples(){
		Session session = factory.openSession();
		Transaction tx = null; 
		try{ 
			tx = session.beginTransaction();
			
//			String hql = "FROM Employees";
			//String hql = "FROM com.tutorial.hibernate.hql.Employees";
			//String hql = "FROM Employees E";
//			String hql = "FROM Employees E WHERE E.salary >= 24000";
//			String hql = "FROM Employees E WHERE E.salary > 10000 ORDER BY E.salary DESC";
			
//			String hql = "SELECT E.firstName, E.lastName, E.salary FROM Employees E";
//			String hql = "SELECT SUM(E.salary), E.firstName FROM Employees E GROUP BY E.firstName";
			
			String hql = "FROM Employees E WHERE E.id = :employee_id"; // query.setParameter("employee_id",100);
			
			Query query = session.createQuery(hql);
			
			List employees = query.list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext();){ 
				Employees employee = (Employees) iterator.next(); 
				System.out.print("First Name: " + employee.getFirstName()); 
				System.out.print(" Last Name: " + employee.getLastName()); 
				System.out.println(" Salary: " + employee.getSalary()); 
			}
			
			/*List<Object[]> recs = query.list();
			for (Object[] line : recs) {
	            System.out.println("Total entities under Master with id " + line[1] + " is " + line[0]);
	        }*/
			
			tx.commit();
		}catch(HibernateException exp){
			exp.printStackTrace();
		}
	}
}
