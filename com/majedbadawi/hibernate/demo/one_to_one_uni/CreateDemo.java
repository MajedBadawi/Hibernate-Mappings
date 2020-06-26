package com.majedbadawi.hibernate.demo.one_to_one_uni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.majedbadawi.hibernate.demo.one_to_one_uni.entity.Instructor;
import com.majedbadawi.hibernate.demo.one_to_one_uni.entity.InstructorDetail;

public class CreateDemo {

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
			//create the objects
			Instructor instructor = new Instructor("John","Smith","john.smith@outlook.com");
			InstructorDetail instructorDetail = new InstructorDetail("youtubeLink","Coding");
			//associate the objects
			instructor.setInstructorDetail(instructorDetail);
			//start a transaction
			session.beginTransaction();
			//save the instructor and its details object (because of cascade all)
			session.save(instructor); 
			//commit transaction
			session.getTransaction().commit();
		}finally {
			factory.close();
		}
	}

}
