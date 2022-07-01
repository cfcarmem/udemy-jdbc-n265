package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import entities.Departament;


public class DepartamentDao {

	private Connection connection;
	
	public  DepartamentDao() {
		connection =  DB.getConnection();
	}
	
	public void inserir(Departament d) {
		String sql = "Insert into udemy.departament(name)values(?)";
		try {
			PreparedStatement ps =  connection.prepareStatement(sql);
			ps.setString(1, d.getName());
			ps.execute();
			
		}catch(Exception e) {
			try {
				connection.rollback();
			}catch(Exception x) {
				throw new DBException(e.getMessage());
			}
			throw new DBException(e.getMessage());
		}
	}
	
	
	
	public List<Departament> listarTodos(){
		List<Departament> lista = new ArrayList<>();
		String sql = "SELECT * FROM udemy.department";
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			ResultSet resultado = p.executeQuery();
			while(resultado.next()) {
				Departament dp = new Departament(resultado.getInt("id"), resultado.getString("name"));
				lista.add(dp);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	//pesquisar pelo id
	public Departament buscarUm(Integer id) {
		Departament d = new Departament();
		String sql = "Select * From udemy.department where id = "+id;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				 d = new Departament(res.getInt("id"), res.getString("name"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	
	
	public void apagar(Integer id) {
		String sql = "Delete From udemy.department where id = "+id;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.execute();
			connection.commit();
		}
		catch(Exception e) {
			try {
				connection.rollback();
			}catch(Exception x) {
				throw new DBException(e.getMessage());
			}
			throw new DBException(e.getMessage());
		}
	}
	
	public void atualizar(Departament dep) {
		String sql = "Update udemy.departament set name= ? where id=" + dep.getId();
		try {
			//prepara a instrução
			PreparedStatement alterar = connection.prepareStatement(sql);
			//parametros
			//(posicao, valor do campo)
			alterar.setString(1, dep.getName());
			alterar.execute();
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
