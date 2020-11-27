package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conect {
	
	private final String HOST = "localhost";
	private final String SENHA = "I24S02L";
	private final String PORTA = "5432";
	private final String DATABASE = "bd_dedetizadora";
	private final String USER = "postgres";
	private final String URL = "jdbc:postgresql://" + HOST + ":" + PORTA + "/" + DATABASE;
	private Connection conexao;
	
	public Conect() {
		try {
			conexao = DriverManager.getConnection(URL, USER, SENHA);
			System.out.println("conectado");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void fecharConexao() {
		try {
			conexao.close();
			System.out.println("desconectado");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConexao() {
		return this.conexao;
	}

}
