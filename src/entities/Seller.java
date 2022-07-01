package entities;

import java.time.LocalDate;
import java.util.Date;

public class Seller {
	private Integer id;
	private String name;
	private String email;
	private LocalDate BirthDate;
	private double BaseSalary;
	private Departament depto;
	
	public Seller() {
		
	}

	public Seller(String name, String email, LocalDate birthDate, double baseSalary, Departament depto) {
		this.name = name;
		this.email = email;
		this.BirthDate = birthDate;
		BaseSalary = baseSalary;
		this.depto = depto;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate  getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		BirthDate = birthDate;
	}

	public double getBaseSalary() {
		return BaseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		BaseSalary = baseSalary;
	}

	public Departament getDepto() {
		return depto;
	}

	public void setDepto(Departament depto) {
		this.depto = depto;
	}
	
	

	
}
