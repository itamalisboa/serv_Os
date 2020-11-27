package controller;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class conexao {
	
	private String url;
	private String usuario;
	private String senha;
	private Connection con = null; // para gerenciamento da conexão com o BD
	private Statement stm = null; // instrução de consulta
	private ResultSet resultSet = null; // gerenciamento de resultados
	
	
	public conexao(){
		url = "jdbc:postgresql://localhost:5432/bd_dedetizadora";
		usuario = "postgres";
		senha = "I24S02L";
		
		try {
			Class.forName("org.postgresql.Driver");
			this.con = DriverManager.getConnection(this.url, this.usuario, this.senha);
			//EXIBINDO UMA MSG CASO O BD ESTEJA SE CONECTANDO
			System.out.println("Conexão realizada com sucesso!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
public int executaSQL(String sql) {
		
		try {
			ResultSet result;
			this.stm = con.createStatement();
			int res = stm.executeUpdate(sql);
			this.con.close();
			return res;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return 0;
		}

	}

//REALIZANDO UMA BUSCA NO BANCO

	public ResultSet executaBusca(String sql) {
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			//con.close();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}

