/**
 * 
 */
package controller;

/**
 * @author Itama S. Lisboa
 *
 */

/**
 * Importes para realizar a conex�o com o bd;
 */

import java.sql.*;


public class ConexaoDao {
	
	Connection conexao = null;
	
	//M�todo respons�vel por efetuar a conex�o com o BD;
	
	public static Connection conector() {
		
		java.sql.Connection conexao = null;
		
		//Carregando o driver Jdbc
		
		String driver = "org.postgresql.Driver";
		
		//Informa��es referente ao BD.
		
		String url = "jdbc:postgresql://localhost:5432/bd_dedetizadora";
		String user = "postgres";
		String password = "I24S02L";
		
		//Estabelecendo a conex�o com o BD
		
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);
			return conexao;
		} catch (Exception e) {
			// TODO: handle exception
			//Caso d� erro na conex�o, ser� mostrado esse erro!
			System.out.println(e);
			return null;
		}
		
	}
	
	//REALIZANDO UMA BUSCA NO BANCO

		public ResultSet executaBusca(String sql) {
			try {
				Statement stm = conexao.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				//con.close();
				return rs;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

}
