package controller;

public class usuarioDao {
	
	public static int nivel_Acesso;
	public static String nomeUsuario;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public int getNivel_Acesso() {
		return this.nivel_Acesso;
	}

	public void setNivel_Acesso(int nivel_Acesso) {
		this.nivel_Acesso = nivel_Acesso;
	}
	
	
	

}
