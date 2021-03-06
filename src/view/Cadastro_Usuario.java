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
import javax.swing.JComboBox;

public class Cadastro_Usuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField passUser;
	private JTextField txtPesquisarCad;
	private JTextField txtID;

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
		setBounds(100, 100, 484, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Cadastro de Usu\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		
		this.setLocationRelativeTo(this);
		
		JLabel lblRazoSocial = new JLabel("N\u00EDvel:");
		lblRazoSocial.setBounds(10, 90, 83, 14);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 124, 49, 14);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 152, 49, 14);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(103, 121, 249, 20);
		txtLogin.setColumns(10);
		
		passUser = new JPasswordField();
		passUser.setBounds(103, 152, 130, 20);
		
		JComboBox cboTipoUsu = new JComboBox();
		
		JButton btnCadastrarUser = new JButton("Cadastrar");
		btnCadastrarUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				conexao con = new conexao();
				//CADASTRANDO OS DADOS NO BD
				
				try {
					//VARI�VEL QUE PEGAR� OS DADOS PARA SEREM INSERIDOS NO BD
					String sql;
					sql = "insert into usuarios(tipo, login,senha)"
							+ "values('"+cboTipoUsu.getSelectedIndex()+"','"+txtLogin.getText()+"',"+
							"'"+passUser.getPassword()+"');";
					con.executaSQL(sql);
					
					//LIMPANDO OS CAMPOS
					
					
					txtLogin.setText(null);
					passUser.setText(null);
					//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZ�O SOCIAL
					
					txtLogin.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Cadastro Realizado com sucesso!!!");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
								
				
			}
		});
		btnCadastrarUser.setBounds(45, 221, 100, 23);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//LIMPANDO OS CAMPOS
				
				txtPesquisarCad.setText(null);
				txtLogin.setText(null);
				passUser.setText(null);
				
				//FIM LIMPAR CAMPOS
				//RETORNANDO O FOCU NO CAMPO RAZ�O SOCIAL
				
				txtLogin.requestFocus();
				
			}
		});
		btnLimpar.setBounds(351, 221, 100, 23);
		contentPane.setLayout(null);
		contentPane.add(btnCadastrarUser);
		contentPane.add(btnLimpar);
		contentPane.add(lblRazoSocial);
		contentPane.add(lblLogin);
		contentPane.add(lblSenha);
		contentPane.add(passUser);
		contentPane.add(txtLogin);
		
		JButton btnAlterar = new JButton("Alterar");
		
		btnAlterar.setBounds(249, 221, 100, 23);
		contentPane.add(btnAlterar);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//PEGANDO OS DADOS DO BD
				
				
				//FAZENDO A CONEX�O AO BD
				
				conexao con = new conexao();
				
				
				//REALIZANDO UMA BUSCA NO BD
				
				String sql = "select * from usuario where login_usu = '"+txtPesquisarCad.getText()+"'";
				ResultSet res = con.executaBusca(sql);
				
				try {
					
					while(res.next()) {
						
							// = res.getInt("id");

							 cboTipoUsu.setSelectedItem(res.getString("nivel_acesso"));
							 txtLogin.setText(res.getString("login_usu"));
							 passUser.setText(res.getString("senha_usu"));
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
				
				//EXCLU�NDO OS DADOS DO USU�RIO
				
				
				try {
					
					if(txtPesquisarCad.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o login no campo Pesquisar!!!");
					}else {
					//EFETUANDO A CONEX�O
					conexao con = new conexao();
					
					//VARI�VEL QUE PEGAR� OS DADOS PARA SEREM EXCLU�DOS DO BD
					String sql;
					sql = "delete from usuarios where id_usu ='"+txtID.getText()+"';";
					con.executaSQL(sql);
					
					//LIMPANDO OS CAMPOS
					
					
					txtLogin.setText(null);
					passUser.setText(null);
					txtPesquisarCad.setText(null);
					//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZ�O SOCIAL
					
					txtLogin.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Exclus�o Realizada com sucesso!!!");
					
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnExcluir.setBounds(147, 221, 100, 23);
		contentPane.add(btnExcluir);
		
		
		cboTipoUsu.setBounds(103, 87, 130, 20);
		contentPane.add(cboTipoUsu);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 22, 46, 14);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(33, 19, 62, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//ALTERANDO OS DADOS DO USU�RIO
				
				
				
				try {
					
					if(txtPesquisarCad.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o cnpj/cpf no campo Pesquisar!!!");
					}else {
					//EFETUANDO A CONEX�O
					conexao con = new conexao();
					
					//VARI�VEL QUE PEGAR� OS DADOS PARA SEREM ATUALIZADOS NO BD
					String sql;
					sql = "update usuario set nivel_acesso='"+cboTipoUsu.getSelectedIndex()+"', login_usu='"+txtLogin.getText()+"', senha_usu='"+passUser.getPassword()+"' where id_usu ='"+txtID.getText()+"';";
					con.executaSQL(sql);
					
					//LIMPANDO OS CAMPOS
					
					
					txtLogin.setText(null);
					passUser.setText(null);
					//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZ�O SOCIAL
					
					txtLogin.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Altera��o Realizada com sucesso!!!");
					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
