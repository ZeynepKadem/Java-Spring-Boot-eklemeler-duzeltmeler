package com.TemizlikSirketi.proje.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="home")
public class HomeModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "adress",nullable=false)
    private String adress;

    @Column(name = "isDone",nullable=false)
    private boolean isDone;

    @Column(name = "description",nullable=false)
    private String description;

    @Column(name = "price",nullable = false)
    private Long price;

    @Column(name = "hometype",nullable = false)
    private String homeType;

	public HomeModel(Long id, String adress, boolean isDone, String description, Long price, String homeType) {
		
		this.id = id;
		this.adress = adress;
		this.isDone = isDone;
		this.description = description;
		this.price = price;
		this.homeType = homeType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getHomeType() {
		return homeType;
	}

	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}
}
