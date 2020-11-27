package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.conexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Cadastro_Usuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtRazaoSocial;
	private JTextField txtCnpj;
	private JTextField txtEndereco;
	private JTextField txtCep;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtFax;
	private JTextField txtLogin;
	private JPasswordField passUser;
	private JTextField txtPesquisarCad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro_Usuario frame = new Cadastro_Usuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cadastro_Usuario() {
		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 484, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Cadastro de Usu\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		
		this.setLocationRelativeTo(this);
		
		JLabel lblRazoSocial = new JLabel("Raz\u00E3o Social:");
		lblRazoSocial.setBounds(10, 90, 83, 14);
		
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setBounds(10, 116, 49, 14);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(10, 142, 83, 14);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 194, 59, 14);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 220, 83, 14);
		
		JLabel lblFax = new JLabel("Fax:");
		lblFax.setBounds(204, 223, 32, 14);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 255, 49, 14);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 283, 49, 14);
		
		JLabel lblCep = new JLabel("Cep:");
		lblCep.setBounds(10, 168, 49, 14);
		
		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setBounds(103, 90, 344, 20);
		txtRazaoSocial.setColumns(10);
		
		txtCnpj = new JTextField();
		txtCnpj.setBounds(103, 116, 130, 20);
		txtCnpj.setColumns(10);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(103, 142, 319, 20);
		txtEndereco.setColumns(10);
		
		txtCep = new JTextField();
		txtCep.setBounds(103, 168, 131, 20);
		txtCep.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(103, 194, 344, 20);
		txtEmail.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(103, 220, 86, 20);
		txtTelefone.setColumns(10);
		
		txtFax = new JTextField();
		txtFax.setBounds(244, 220, 86, 20);
		txtFax.setColumns(10);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(103, 262, 130, 20);
		txtLogin.setColumns(10);
		
		passUser = new JPasswordField();
		passUser.setBounds(103, 283, 130, 20);
		
		JButton btnCadastrarUser = new JButton("Cadastrar");
		btnCadastrarUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				conexao con = new conexao();
				//CADASTRANDO OS DADOS NO BD
				
				try {
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM INSERIDOS NO BD
					String sql;
					sql = "insert into usuario(razao_social,cnpj_cpf,endereco,cep,email,telefone,fax,login,senha)"
							+ "values('"+txtRazaoSocial.getText()+"','"+txtCnpj.getText()+"','"+txtEndereco.getText()+"','"+txtCep.getText()+"',"+
							"'"+txtEmail.getText()+"','"+txtTelefone.getText()+"','"+txtFax.getText()+"','"+txtLogin.getText()+"',"+
							"'"+passUser.getPassword()+"');";
					con.executaSQL(sql);
					
					//LIMPANDO OS CAMPOS
					
					txtRazaoSocial.setText(null);
					txtCnpj.setText(null);
					txtEndereco.setText(null);
					txtCep.setText(null);
					txtEmail.setText(null);
					txtTelefone.setText(null);
					txtFax.setText(null);
					txtLogin.setText(null);
					passUser.setText(null);
					//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtRazaoSocial.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Cadastro Realizado com sucesso!!!");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
								
				
			}
		});
		btnCadastrarUser.setBounds(41, 341, 100, 23);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//LIMPANDO OS CAMPOS
				
				txtPesquisarCad.setText(null);
				txtRazaoSocial.setText(null);
				txtCnpj.setText(null);
				txtEndereco.setText(null);
				txtCep.setText(null);
				txtEmail.setText(null);
				txtTelefone.setText(null);
				txtFax.setText(null);
				txtLogin.setText(null);
				passUser.setText(null);
				
				//FIM LIMPAR CAMPOS
				//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
				
				txtRazaoSocial.requestFocus();
				
			}
		});
		btnLimpar.setBounds(347, 341, 100, 23);
		contentPane.setLayout(null);
		contentPane.add(btnCadastrarUser);
		contentPane.add(btnLimpar);
		contentPane.add(lblRazoSocial);
		contentPane.add(lblCnpj);
		contentPane.add(lblEmail);
		contentPane.add(lblCep);
		contentPane.add(lblEndereo);
		contentPane.add(lblTelefone);
		contentPane.add(lblLogin);
		contentPane.add(lblSenha);
		contentPane.add(txtTelefone);
		contentPane.add(lblFax);
		contentPane.add(txtFax);
		contentPane.add(txtEmail);
		contentPane.add(txtCnpj);
		contentPane.add(txtRazaoSocial);
		contentPane.add(txtCep);
		contentPane.add(txtEndereco);
		contentPane.add(passUser);
		contentPane.add(txtLogin);
		
		JButton btnAlterar = new JButton("Alterar");
		
		btnAlterar.setBounds(245, 341, 100, 23);
		contentPane.add(btnAlterar);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//PEGANDO OS DADOS DO BD
				
				
				//FAZENDO A CONEXÃO AO BD
				
				conexao con = new conexao();
				
				
				//REALIZANDO UMA BUSCA NO BD
				
				String sql = "select * from usuario where cnpj_cpf = '"+txtPesquisarCad.getText()+"'";
				ResultSet res = con.executaBusca(sql);
				
				try {
					
					while(res.next()) {
						
							// = res.getInt("id");
							txtRazaoSocial.setText(res.getString("razao_social"));
							 txtCnpj.setText(res.getString("cnpj_cpf"));
							 txtEndereco.setText(res.getString("endereco"));
							 txtCep.setText(res.getString("cep"));
							 txtEmail.setText(res.getString("email"));
							 txtTelefone.setText(res.getString("telefone"));
							 txtFax.setText(res.getString("fax"));
							 txtLogin.setText(res.getString("login"));
							 passUser.setText(res.getString("senha"));
					}
					res.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnPesquisar.setBounds(357, 35, 94, 23);
		contentPane.add(btnPesquisar);
		
		txtPesquisarCad = new JTextField();
		txtPesquisarCad.setBounds(102, 36, 247, 20);
		contentPane.add(txtPesquisarCad);
		txtPesquisarCad.setColumns(10);
		
		JLabel lbl = new JLabel("Pesquisar dados de usu\u00E1rio para alter\u00E1-los!");
		lbl.setBounds(102, 58, 250, 14);
		contentPane.add(lbl);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//EXCLUÍNDO OS DADOS DO USUÁRIO
				
				
				try {
					
					if(txtPesquisarCad.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o cnpj/cpf no campo Pesquisar!!!");
					}else {
					//EFETUANDO A CONEXÃO
					conexao con = new conexao();
					
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM EXCLUÍDOS DO BD
					String sql;
					sql = "delete from usuario where cnpj_cpf ='"+txtPesquisarCad.getText()+"';";
					con.executaSQL(sql);
					
					//LIMPANDO OS CAMPOS
					
					txtRazaoSocial.setText(null);
					txtCnpj.setText(null);
					txtEndereco.setText(null);
					txtCep.setText(null);
					txtEmail.setText(null);
					txtTelefone.setText(null);
					txtFax.setText(null);
					txtLogin.setText(null);
					passUser.setText(null);
					txtPesquisarCad.setText(null);
					//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtRazaoSocial.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Exclusão Realizada com sucesso!!!");
					
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnExcluir.setBounds(143, 341, 100, 23);
		contentPane.add(btnExcluir);
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//ALTERANDO OS DADOS DO USUÁRIO
				
				
				
				try {
					
					if(txtPesquisarCad.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o cnpj/cpf no campo Pesquisar!!!");
					}else {
					//EFETUANDO A CONEXÃO
					conexao con = new conexao();
					
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM ATUALIZADOS NO BD
					String sql;
					sql = "update usuario set razao_social='"+txtRazaoSocial.getText()+"', cnpj_cpf='"+txtCnpj.getText()+"', endereco='"+txtEndereco.getText()+"', cep='"+txtCep.getText()+"', email='"+txtEmail.getText()+"', telefone='"+txtTelefone.getText()+"', fax='"+txtFax.getText()+"', login='"+txtLogin.getText()+"', senha='"+passUser.getPassword()+"' where cnpj_cpf ='"+txtPesquisarCad.getText()+"';";
					con.executaSQL(sql);
					
					//LIMPANDO OS CAMPOS
					
					txtRazaoSocial.setText(null);
					txtCnpj.setText(null);
					txtEndereco.setText(null);
					txtCep.setText(null);
					txtEmail.setText(null);
					txtTelefone.setText(null);
					txtFax.setText(null);
					txtLogin.setText(null);
					passUser.setText(null);
					//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtRazaoSocial.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Alteração Realizada com sucesso!!!");
					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
