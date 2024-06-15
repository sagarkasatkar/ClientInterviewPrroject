package com.data.spring;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Checks {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="check")
	private List<Guest> guest;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Guest> getGuest() {
		return guest;
	}


	public void setGuest(List<Guest> guest) {
		this.guest = guest;
	}
	
	
	
	

}
