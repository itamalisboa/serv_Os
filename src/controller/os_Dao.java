package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import model.model_OS;

public class os_Dao {
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public void pesquisar_os(model_OS OS) {
		
		String sql = "select * from ordemservico where codo_serv = '"+OS.getCodo_serv()+"'";
		
		
		
		
	}

}
