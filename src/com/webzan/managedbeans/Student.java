package com.webzan.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean
@RequestScoped
public class Student implements Serializable{
	
	// Properties
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	
	
	// Getters and setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	// Constructors
	
	public Student() {
		super();
	}
	
	public Student(int id, String firstname, String lastname, String email) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	
	public Student(String firstname, String lastname, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	
	// Custom validation
	
	public void validateStudentEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException{
		if(value == null) {
			return;
		}
		String data = value.toString();
		if(!data.contains("@")) {
			FacesMessage message = new FacesMessage("Email should contain \'@\'");
			throw new ValidatorException(message);
		}
	}

}
