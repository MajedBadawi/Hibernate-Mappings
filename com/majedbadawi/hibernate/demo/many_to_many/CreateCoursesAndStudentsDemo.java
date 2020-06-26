package com.majedbadawi.hibernate.demo.many_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.majedbadawi.hibernate.demo.many_to_many.entity.Course;
import com.majedbadawi.hibernate.demo.many_to_many.entity.Student;

public class CreateCoursesAndStudentsDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//start a transaction
			session.beginTransaction();
			//create a course
			Course course = new Course("Best Java Course!");
			//save the course
			System.out.println("Saving course: "+course);
			session.save(course);
			//create the students
			Student student1 = new Student("John","Doe","john.doe@outlook.com");
			Student student2 = new Student("Mary","Public","mary.p@outlook.com");
			//add students to the course
			course.addStudent(student1);
			course.addStudent(student2);
			//save the students
			session.save(student1);
			session.save(student2);
			System.out.println("Saving students: "+course.getStudents()+" to the course");
			//create another course and it to student1 courses
			Course course1 = new Course("Best C# Course");
			System.out.println("Adding another course"+course1+" to student: "+student1);
			session.save(course1);
			student1.addCourse(course1);
			//commit transaction
			session.getTransaction().commit();
		}finally {
			session.close();
			factory.close();
		}
	}

}
