package com.majedbadawi.hibernate.demo.one_to_many_bi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.majedbadawi.hibernate.demo.one_to_many_bi.entity.Course;
import com.majedbadawi.hibernate.demo.one_to_many_bi.entity.Instructor;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create the objects
			Instructor instructor = new Instructor("Susan","Public","susan.p@outlook.com");
			Course course1 = new Course("Java Course");
			Course course2 = new Course("Web Course");
			//start a transaction
			session.beginTransaction();
			//save instructor
			session.save(instructor);
			//get instructor and save courses
			instructor = session.get(Instructor.class, instructor.getId());
			instructor.addCourse(course1);
			instructor.addCourse(course2);
			session.save(course1);
			session.save(course2);
			//print added courses of the instructor
			System.out.println("Added courses "+instructor.getCourses());
			//commit transaction
			session.getTransaction().commit();
		}finally {
			session.close();
			factory.close();
		}
	}

}
