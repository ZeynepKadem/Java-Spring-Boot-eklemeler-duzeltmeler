package com.TemizlikSirketi.proje.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="users")
public class UserModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable=false)
    private String name;

    @Column(name = "surname",nullable=false)
    private String surName;

    @Column(name = "telephoneNumber",nullable=false)
    private String telephoneNumber;
    
    @Column(name = "password",nullable=false)
    private Long password;



	public UserModel(Long password) {
		
		this.password = password;
	}

	public Long getPassword() {
		return password;
	}

	public void setPassword(Long password) {
		this.password = password;
	}

	public UserModel(String telephoneNumber) {
		
		this.telephoneNumber = telephoneNumber;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Column(name = "isAdmin",nullable=true)
    private boolean isAdmin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public UserModel(Long id, String name, String surName, String telephoneNumber, boolean isAdmin) {
	
		this.id = id;
		this.name = name;
		this.surName = surName;
		this.telephoneNumber = telephoneNumber;
		this.isAdmin = isAdmin;
	}

	public UserModel() {
		
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
