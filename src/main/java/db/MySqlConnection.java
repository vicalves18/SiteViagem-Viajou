package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	private static final String url = "jdbc:mysql://localhost:3306/java_crud";
	private static final String user = "root";
	private static final String password = "Vivi110401@";
	
	public static Connection createConnection(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver encontrado");
			
		}catch(ClassNotFoundException e) {
			System.out.println("Driver não encontrado"+e.getMessage());
		}
		
		try {
			Connection connection = DriverManager.getConnection(url,user,password);
			System.out.println("Conectado ao Banco de Dados");
			return connection;
			
		}catch(SQLException e) {
			System.out.println("Não foi conectado ao Banco de Dados"+e.getMessage());
			return null;
		}
	}
}
