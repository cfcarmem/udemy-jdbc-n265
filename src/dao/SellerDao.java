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

public class SellerDao {
	
	private static Connection connection;
	
	public SellerDao() {
		connection =  DB.getConnection();
	}
	
	//inserir
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
	
	

}
