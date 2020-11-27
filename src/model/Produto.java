package model;

import model.DadosCFU;


public class Produto {
	private String descricao;
	private int quantidade;
	private String id_produto;
	private String uniMed;
	private String id_fornec;
	private float precoProd;
	
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public void setId_produto(String id_produto) {
		this.id_produto = id_produto;
	}
	
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public String getId_produto() {
		return this.id_produto;
	}
	public String getUniMed() {
		return uniMed;
	}
	public void setUniMed(String uniMed) {
		this.uniMed = uniMed;
	}
	public String getId_fornec() {
		return id_fornec;
	}
	public void setId_fornec(String id_fornec) {
		this.id_fornec = id_fornec;
	}
	public float getPrecoProd() {
		return precoProd;
	}
	public void setPrecoProd(float precoProd) {
		this.precoProd = precoProd;
	}
	
	
	
	
}
