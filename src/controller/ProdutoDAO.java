package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Produto;

public class ProdutoDAO {
	private Conect conexao = new Conect();
	private ArrayList<Produto> produtos;
	
	public ArrayList selectTudo() {
		String sql = "SELECT * FROM produto;";
		
		try {
			PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			produtos = new ArrayList();
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setId_produto(rs.getString("cod_prod"));
				produto.setQuantidade(rs.getInt("quant_prod"));
				produto.setDescricao(rs.getString("descricao_prod"));
				produto.setId_fornec(rs.getString("id_fornec"));
				produto.setPrecoProd(rs.getFloat("precounit_prod"));
				produto.setUniMed(rs.getString("unidmedida"));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexao.fecharConexao();
		return produtos;
	}
	
	public void InserirProduto(Produto dados) {
		
		//CADASTRANDO OS DADOS NO BD
		
		try {
			//CONECTANDO AO BD
			
			conexao con = new conexao();
			
			//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM INSERIDOS NO BD
			String sql;
			sql = "insert into produtos(cod_prod, unidmedida, quant_prod, precounit_prod, id_fornec, descricao_prod)"
					+ "values('"+dados.getId_produto()+"','"+dados.getUniMed()+"','"+dados.getQuantidade()+"','"+dados.getPrecoProd()+"','"+dados.getId_fornec()+"','"+dados.getDescricao()+"');";
			con.executaSQL(sql);
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
				
	}
	
	//EFETUANDO A BUSCA DO PRODUTO NO BD
	
			public void BuscarProtudo(Produto dados) {
				
				//FAZENDO A CONEXÃO AO BD
				
				conexao con = new conexao();
				
				//REALIZANDO UMA BUSCA NO BD
				
				String sql = "select * from produtos where cod_prod = '"+dados.getId_produto()+"'";
				ResultSet res = con.executaBusca(sql);
				
				try {
					
					while(res.next()) {
						
						dados.setId_produto(res.getString("cod_prod"));
						dados.setDescricao(res.getString("descricao_prod"));
						dados.setUniMed(res.getString("unidmedida"));
						dados.setQuantidade(Integer.parseInt(res.getString("quant_prod")));
						dados.setId_fornec(res.getString("id_fornec").toString());
						dados.setPrecoProd(res.getFloat("precounit_prod"));
						
					}
					res.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			public void Atualizar(Produto dados) {
				
				//ATUALIZANDO OS DADOS DO PRODUTO
				
				try {
					//EFETUANDO A CONEXÃO
					conexao con = new conexao();
					
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM ATUALIZADOS NO BD
					String sql;
					sql = "update produtos set descricao_prod='"+dados.getDescricao()+"', quant_prod='"+dados.getQuantidade()+"', precounit_prod='"+dados.getPrecoProd()+"', id_fornec='"+dados.getId_fornec()+"', unidmedida='"+dados.getUniMed()+"' where cod_prod ='"+dados.getId_produto()+"';";
					con.executaSQL(sql);
					
					} catch (Exception e) {
						e.printStackTrace();
				}
			}
			
			public void DeletarProduto(Produto dados) {
				//EXCLUINDO OS DADOS DO PRODUTO
				
				try {
					//EFETUANDO A CONEXÃO
					conexao con = new conexao();
					
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM ATUALIZADOS NO BD
					String sql;
					sql = "delete from produtos where cod_prod ='"+dados.getId_produto()+"';";
					con.executaSQL(sql);
					
					} catch (Exception e) {
						e.printStackTrace();
				}
			}
	
}
