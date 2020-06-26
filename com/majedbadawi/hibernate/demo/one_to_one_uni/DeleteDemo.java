package com.majedbadawi.hibernate.demo.one_to_one_uni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.majedbadawi.hibernate.demo.one_to_one_uni.entity.Instructor;
import com.majedbadawi.hibernate.demo.one_to_one_uni.entity.InstructorDetail;

public class DeleteDemo {

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
			///start a transaction
			session.beginTransaction();
			//get instructor by primary key / id
			int id = 1;
			Instructor instructor = session.get(Instructor.class, id);
			//delete the instructor and its dependency / detail
			if(instructor != null) {
				System.out.println("Deleting instructor: "+instructor);
				session.delete(instructor);
			}
			//commit transaction
			session.getTransaction().commit();
		}finally {
			factory.close();
		}
	}

}
