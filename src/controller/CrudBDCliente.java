package controller;

import java.sql.ResultSet;

import javax.swing.JOptionPane;


public class CrudBDCliente {
	
	DadosCFU dados = new DadosCFU();
		
		public void InserirCliente(DadosCFU dados) {
			
			//CADASTRANDO OS DADOS NO BD
			
			try {
				//CONECTANDO AO BD
				
				conexao con = new conexao();
				
				//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM INSERIDOS NO BD
				String sql;
				sql = "insert into clientes(id_cliente, nome_cliente,cpf_cliente,rg_cliente, email_cliente,telefone_cliente, end_cliente)"
						+ "values('"+dados.getIdC()+"', '"+dados.getNome()+"','"+dados.getCpf()+"','"+dados.getRg()+"', '"+dados.getEmail()+"','"+dados.getTelefone()+"', '"+dados.getEndereco()+"');";
				con.executaSQL(sql);
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
					
		}

		//EFETUANDO A BUSCA DO CLIENTE NO BD
		
		public void BuscarCliente(DadosCFU dados) {
			
			//FAZENDO A CONEXÃO AO BD
			
			conexao con = new conexao();
			
			//REALIZANDO UMA BUSCA NO BD
			
			String sql = "select * from clientes where cpf_cliente = '"+dados.getCpf()+"'";
			ResultSet res = con.executaBusca(sql);
			
			try {
				
				while(res.next()) {
					
					dados.setIdC(res.getString("id_cliente"));
					dados.setNome(res.getString("nome_cliente"));
					dados.setCpf(res.getString("cpf_cliente"));
					dados.setEndereco(res.getString("end_cliente"));
					dados.setRg(res.getString("rg_cliente"));
					dados.setEmail(res.getString("email_cliente"));
					dados.setTelefone(res.getString("telefone_cliente"));
					
				}
				res.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void Atualizar(DadosCFU dados) {
			
			//ATUALIZANDO OS DADOS DO USUÁRIO
			
			try {
				//EFETUANDO A CONEXÃO
				conexao con = new conexao();
				
				//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM ATUALIZADOS NO BD
				String sql;
				sql = "update clientes set nome_cliente='"+dados.getNome()+"', cpf_cliente='"+dados.getCpf()+"', end_cliente='"+dados.getEndereco()+"', rg_cliente='"+dados.getRg()+"', email_cliente='"+dados.getEmail()+"', telefone_cliente='"+dados.getTelefone()+"' where id_cliente ='"+dados.getIdC()+"';";
				con.executaSQL(sql);
				
				
				
				} catch (Exception e) {
					e.printStackTrace();
			}
		}
		
		public void DeletarCliente(DadosCFU dados) {
			//EXCLUINDO OS DADOS DO USUÁRIO
			
			try {
				//EFETUANDO A CONEXÃO
				conexao con = new conexao();
				
				//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM ATUALIZADOS NO BD
				String sql;
				sql = "delete from clientes where id_cliente ='"+dados.getIdC()+"';";
				con.executaSQL(sql);
				
				} catch (Exception e) {
					e.printStackTrace();
			}
		}
		
		//Contando o número de registros no BD para pegar o último id
		
		public void PegarId() {
			
//FAZENDO A CONEXÃO AO BD
			DadosCFU dados = new DadosCFU();
			
			conexao con = new conexao();
			
			//REALIZANDO UMA BUSCA NO BD
			
			String sql = "select count(*) as total from clientes";
			ResultSet res = con.executaBusca(sql);
			
			try {
				
				while(res.next()) {
					
					dados.setRegistros(res.getString("total"));
					System.out.println(res.getString("total"));
				}
				
				res.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		

}
