package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static final String URL = "jdbc:mysql://localhost:3306/aluguel_de_carros";
	private static final String USUARIO = "root";
	private static final String SENHA = "";
	
	
	public static Connection getConexao() {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(URL, USUARIO, SENHA);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return connection;
	}
}
