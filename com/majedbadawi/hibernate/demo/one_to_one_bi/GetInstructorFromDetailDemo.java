package com.majedbadawi.hibernate.demo.one_to_one_bi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.majedbadawi.hibernate.demo.one_to_one_bi.entity.Instructor;
import com.majedbadawi.hibernate.demo.one_to_one_bi.entity.InstructorDetail;

public class GetInstructorFromDetailDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//start a transaction
			session.beginTransaction();
			//get the instructor detail object
			int id = 2;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
			//print detail
			System.out.println("Instructor detail: "+instructorDetail);
			//print the associated instructor
			System.out.println("Associated instructor: "+instructorDetail.getInstructor());
			//commit transaction
			session.getTransaction().commit();
		}catch(Exception e) { //catch not found/null get results
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}

}
