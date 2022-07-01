package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import entities.BeanSeller;
import entities.Seller;
import model.dao.SellerDaoInterface;

public class SellerDao implements SellerDaoInterface{
	
	private static Connection connection;
	
	public SellerDao() {
		connection =  DB.getConnection();
	}
	
	//inserir
	@Override
	public void inserir(Seller se) {
		String sql = "Insert into udemy.seller (Name, Email, BirthDate, BaseSalary, DepartmentId) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, se.getName());
			ps.setString(2, se.getEmail());
		    ps.setDate(3, (Date) Date.from(se.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			ps.setDouble(4, se.getBaseSalary());
			ps.setObject(5, se.getDepto());
			ps.execute();
			connection.commit();
		}catch(Exception e) {
			try {
				connection.rollback();
			}catch(Exception x) {
				throw new DBException(e.getMessage());
			}
			throw new DBException(e.getMessage());
		}
	}
	
	@Override
	public void apagar(Integer id) {
		String sql = "Delete from udemy.seller where id="+id;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.execute();
			connection.commit();
		}catch(Exception e) {
			try {
				connection.rollback();
			}catch(Exception x) {
				throw new DBException(e.getMessage());
			}
			throw new DBException(e.getMessage());
		}
	}
	
	@Override
	public void alterar(Seller se) {
		Integer id = se.getId();
		String sql = "Update udemy.seller set Name = ?, Email=?, BirthDate=?, BaseSalary=?, DepartmentId=? where id="+id;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, se.getName());
			ps.setString(2, se.getEmail());
		    ps.setDate(3, (Date) Date.from(se.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			ps.setDouble(4, se.getBaseSalary());
			ps.setObject(5, se.getDepto());
			ps.execute();
			connection.commit();
		}catch(Exception e) {
			try {
				connection.rollback();
			}catch(Exception x) {
				throw new DBException(e.getMessage());
			}
			throw new DBException(e.getMessage());
		}
	}
	
	@Override
	public List<BeanSeller> listarTodos(){
		List<BeanSeller>lista = new ArrayList<>();
		String sql = "Select id,name,email,BirthDate,baseSalary,b.name as nomeDepartamento from udemy.seller a inner join udemy.departament b on b.id = a.Departamentid";
		
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
