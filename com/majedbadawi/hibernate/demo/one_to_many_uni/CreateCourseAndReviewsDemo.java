package com.majedbadawi.hibernate.demo.one_to_many_uni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.majedbadawi.hibernate.demo.one_to_many_uni.entity.Course;
import com.majedbadawi.hibernate.demo.one_to_many_uni.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//start a transaction
			session.beginTransaction();
			//create a course
			Course course = new Course("Java");
			//add reviews to it
			course.addReview(new Review("Great"));
			course.addReview(new Review("Bad"));
			//save the course and its reviews because of cascade all
			System.out.println("Adding course: "+course);
			System.out.println("And reviews: "+course.getReviews());
			session.save(course);
			//commit transaction
			session.getTransaction().commit();
		}finally {
			session.close();
			factory.close();
		}
	}

}
