package com.webzan.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.webzan.entities.StudentDbUtil;


@ManagedBean
@SessionScoped
public class StudentManager implements Serializable{

	// Properties
	private static final long serialVersionUID = 1L;
	private List<Student> students;
	
	// Getters and Setters
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	// Constructors
	public StudentManager() {
		super();
		students = new ArrayList<Student>();
	}
	
	// Methods
	public void loadStudents() throws Exception {
		students = StudentDbUtil.getStudents();
	}
	
	public String addStudent(Student stu) {
		StudentDbUtil.addStudent(stu);
		return "list-students";
	}
	
	public String loadStudent(int id) {
		Student stud = StudentDbUtil.fetchStudent(id);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();
		requestMap.put("student", stud);
		return "edit-student";
	}
	
	public String updateStudent(Student stu) {
		StudentDbUtil.updateStudent(stu);
		return "list-students";
	}
	
	public String deleteStudent(int id) {
		StudentDbUtil.deleteStudent(id);
		return "list-students";
	}

}
