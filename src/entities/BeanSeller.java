package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;

public class BeanSeller {
	private String name;
	private String email;
	private String Departamento;
	private double salario;
	private Integer id;
	
	private static Connection connection;
	
	public BeanSeller() {
		connection =  DB.getConnection();
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
	public String getDepartamento() {
		return Departamento;
	}
	public void setDepartamento(String departamento) {
		Departamento = departamento;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "BeanSeller [name=" + name + ", email=" + email + ", Departamento=" + Departamento + ", salario="
				+ salario + ", id=" + id + "]";
	}
	
	
	public List<BeanSeller> listarTodos(){
		List<BeanSeller>lista = new ArrayList<>();
		String sql = "Select a.id,a.name,a.email,a.BirthDate, a.baseSalary,b.name as nomeDepartamento from udemy.seller a inner join udemy.department b on b.id = a.DepartmentId";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BeanSeller s =new BeanSeller();
				s.setDepartamento(rs.getString("nomeDepartamento"));
				s.setEmail(rs.getString("email"));
				s.setName(rs.getString("name"));
				s.setSalario(rs.getDouble("baseSalary"));
				s.setId(rs.getInt("id"));
				
				lista.add(s);
			}
			
			
		}catch(Exception e) {
			throw new DBException(e.getMessage());
		}
		return lista;
		
	}
	

	

}
