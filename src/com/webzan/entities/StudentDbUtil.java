package com.webzan.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.omg.CORBA.SystemException;

import com.webzan.managedbeans.Student;

public class StudentDbUtil {
	private static final String PERSISTENCE_UNIT_NAME="JSFJPA";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	@PersistenceContext
	private static EntityManager em;
	
	@SuppressWarnings("unchecked")
	public static List<Student> getStudents(){
		em = factory.createEntityManager();
		List<Student> students = new ArrayList<Student>();
		List<StudentEntity> list;
		list = em.createQuery("SELECT s FROM StudentEntity AS s").getResultList();
		for(StudentEntity tmp:list) {
			students.add(new Student(tmp.getId(), tmp.getFirstname(), tmp.getLastname(), tmp.getEmail()));
		}
		return students;
	}
	
	public static void addStudent(Student s) {
		StudentEntity newStudent = new StudentEntity();
		newStudent.setId(s.getId());
		newStudent.setFirstname(s.getFirstname());
		newStudent.setLastname(s.getLastname());
		newStudent.setEmail(s.getEmail());
		try {
			em.getTransaction().begin();
			em.persist(newStudent);
			em.getTransaction().commit();
		}catch(SystemException e) {
			e.printStackTrace();
		}
	}
	
	public static Student fetchStudent(int id) {
		em = factory.createEntityManager();
		StudentEntity student = new StudentEntity();
		student = (StudentEntity)em.createQuery("SELECT s FROM StudentEntity AS s WHERE s.id =" + id).getSingleResult();
		Student new_s = new Student(student.getId(), student.getFirstname(), student.getLastname(), student.getEmail());
		return new_s;
	}
	
	public static void updateStudent(Student s) {
		em = factory.createEntityManager();
		StudentEntity student = new StudentEntity();
		student = (StudentEntity)em.createQuery("SELECT s FROM StudentEntity AS s WHERE s.id = " + s.getId()).getSingleResult();
		//System.out.println(s.getId());
		//student.setId(s.getId());
		student.setFirstname(s.getFirstname());
		student.setLastname(s.getLastname());
		student.setEmail(s.getEmail());
		try {
			em.getTransaction().begin();
			em.persist(student);
			em.getTransaction().commit();
		}catch(SystemException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteStudent(int id) {
		StudentEntity oldStudent = em.find(StudentEntity.class, id);
		try {
			em.getTransaction().begin();
			em.remove(oldStudent);
			em.getTransaction().commit();
		}catch(SystemException e) {
			e.printStackTrace();
		}
	}
	
	

	
}
