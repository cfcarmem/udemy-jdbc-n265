package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if(conn == null) {
			Properties props = rodarPropriedades();
			//do arquivo db.properties
			String url= props.getProperty("dburl");
			try {
				conn = DriverManager.getConnection(url,props);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBException(e.getMessage());
			}
			
		}
		return conn;
	}
	
	
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBException(e.getMessage());
			}
		}
	}
	
	//metodos estaticos para conectar e desconectar
	private static Properties rodarPropriedades() {
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
			
		}catch(IOException e) {
			throw new DBException(e.getMessage());
		}
		
	}
	
	
	

}
