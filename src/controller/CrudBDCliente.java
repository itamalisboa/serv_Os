package controller;

import java.sql.ResultSet;


public class CrudBDCliente {
	
	DadosCFU dados = new DadosCFU();
		
		public void InserirCliente(DadosCFU dados) {
			
			//CADASTRANDO OS DADOS NO BD
			
			try {
				//CONECTANDO AO BD
				
				conexao con = new conexao();
				
				//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM INSERIDOS NO BD
				String sql;
				sql = "insert into clientes(id_cliente, nome_cliente,cpf_cliente,rg_cliente, email_cliente,telefone_cliente)"
						+ "values('"+dados.getIdC()+"', '"+dados.getNome()+"','"+dados.getCpf()+"','"+dados.getRg()+"', '"+dados.getEmail()+"','"+dados.getTelefone()+"');";
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
			
			String sql = "select * from cliente where cpf = '"+dados.getCpf()+"'";
			ResultSet res = con.executaBusca(sql);
			
			try {
				
				while(res.next()) {
					
					dados.setIdC(res.getString("id_cliente"));
					dados.setNome(res.getString("nome"));
					dados.setCpf(res.getString("cpf"));
					dados.setEndereco(res.getString("endereco"));
					dados.setCep(res.getString("cep"));
					dados.setEmail(res.getString("email"));
					dados.setTelefone(res.getString("telefone"));
					dados.setFax(res.getString("fax"));
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
				sql = "update cliente set nome='"+dados.getNome()+"', cpf='"+dados.getCpf()+"', endereco='"+dados.getEndereco()+"', cep='"+dados.getCep()+"', email='"+dados.getEmail()+"', telefone='"+dados.getTelefone()+"', fax='"+dados.getFax()+"' where id ='"+dados.getIdC()+"';";
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
				sql = "delete from cliente where cpf ='"+dados.getCpf()+"';";
				con.executaSQL(sql);
				
				} catch (Exception e) {
					e.printStackTrace();
			}
		}
		

}
