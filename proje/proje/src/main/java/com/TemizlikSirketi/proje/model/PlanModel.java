package com.TemizlikSirketi.proje.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name="plan")


public class PlanModel {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "name",nullable=false)
	    private String name;
	    
	    @Column(name = "price",nullable=false)
	    private Long price;

	    @Column(name = "telephonenumber",nullable=false)
	    private Long telephoneNumber;

	    @Column(name = "password",nullable=false)
	    private Long password;

	    public Long getPassword() {
			return password;
		}

		public void setPassword(Long password) {
			this.password = password;
		}

		@Column(name = "adress",nullable=false)
	    private String adress;

	    @Temporal(TemporalType.DATE)
	    @DateTimeFormat(pattern = "dd-MM-yyyy")
	    private Date arrivalTime;

	    @Temporal(TemporalType.DATE)
	    @DateTimeFormat(pattern = "dd-MM-yyyy")
	    private Date departureTime;

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

		public Long getPrice() {
			return price;
		}

		public void setPrice(Long price) {
			this.price = price;
		}

		public Long getTelephoneNumber() {
			return telephoneNumber;
		}

		public void setTelephoneNumber(Long telephoneNumber) {
			this.telephoneNumber = telephoneNumber;
		}

		public String getAdress() {
			return adress;
		}

		public void setAdress(String adress) {
			this.adress = adress;
		}

		public Date getArrivalTime() {
			return arrivalTime;
		}

		public void setArrivalTime(Date arrivalTime) {
			this.arrivalTime = arrivalTime;
		}

		public Date getDepartureTime() {
			return departureTime;
		}

		public void setDepartureTime(Date departureTime) {
			this.departureTime = departureTime;
		}

		public PlanModel(Long id, String name, Long price, Long telephoneNumber, String adress, Date arrivalTime,
				Date departureTime) {
			
			this.id = id;
			this.name = name;
			this.price = price;
			this.telephoneNumber = telephoneNumber;
			this.adress = adress;
			this.arrivalTime = arrivalTime;
			this.departureTime = departureTime;
		}

		public PlanModel(Long password) {
			
			this.password = password;
		}



}
