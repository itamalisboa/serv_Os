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
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;

public class Cadastro_Empresa extends JFrame {

	private JPanel contentPane;
	private JTextField txtRazaoSocial;
	private JTextField txtCnpj;
	private JTextField txtEndereco;
	private JTextField txtCep;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtPesquisarCad;
	private JTextField txtIdEmp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro_Empresa frame = new Cadastro_Empresa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void CarregarID() {
		conexao con = new conexao();
		
		String sql = "select count(nome_emp) as total from empresa";
		
		ResultSet res = con.executaBusca(sql);
		
		int totalCad = 0;
		
		try {
			
			while(res.next()) {
				
				totalCad = Integer.parseInt(res.getString("total"));
				
				txtIdEmp.setText(Integer.toString(totalCad + 1));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Create the frame.
	 */
	public Cadastro_Empresa() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				CarregarID();
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 490, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Cadastro de Usu\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		
		this.setLocationRelativeTo(this);
		
		JLabel lblRazoSocial = new JLabel("Raz\u00E3o Social:");
		lblRazoSocial.setBounds(10, 129, 83, 14);
		
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setBounds(10, 155, 49, 14);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(10, 181, 83, 14);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 233, 59, 14);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 259, 83, 14);
		
		JLabel lblCep = new JLabel("Cep:");
		lblCep.setBounds(10, 207, 49, 14);
		
		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setBounds(103, 129, 344, 20);
		txtRazaoSocial.setColumns(10);
		
		txtCnpj = new JTextField();
		txtCnpj.setBounds(103, 155, 130, 20);
		txtCnpj.setColumns(10);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(103, 181, 319, 20);
		txtEndereco.setColumns(10);
		
		txtCep = new JTextField();
		txtCep.setBounds(103, 207, 131, 20);
		txtCep.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(103, 233, 344, 20);
		txtEmail.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(103, 259, 86, 20);
		txtTelefone.setColumns(10);
		
		JButton btnCadastrarUser = new JButton("Cadastrar");
		btnCadastrarUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				conexao con = new conexao();
				//CADASTRANDO OS DADOS NO BD
				
				try {
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM INSERIDOS NO BD
					String sql;
					sql = "insert into empresa(id_empresa, nome_emp,cnpj_emp,end_emp,cep_emp,email_emp,telefon_emp)"
							+ "values('"+txtIdEmp.getText()+"', '"+txtRazaoSocial.getText()+"','"+txtCnpj.getText()+"','"+txtEndereco.getText()+"','"+txtCep.getText()+"',"+
							"'"+txtEmail.getText()+"','"+txtTelefone.getText()+"');";
					con.executaSQL(sql);
					
					//LIMPANDO OS CAMPOS
					
					txtRazaoSocial.setText(null);
					txtCnpj.setText(null);
					txtEndereco.setText(null);
					txtCep.setText(null);
					txtEmail.setText(null);
					txtTelefone.setText(null);
					
					
					//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtRazaoSocial.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Cadastro Realizado com sucesso!!!");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
								
				
			}
		});
		btnCadastrarUser.setBounds(37, 334, 100, 23);
		
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
				txtIdEmp.setText(null);
				
				//FIM LIMPAR CAMPOS
				//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
				
				txtRazaoSocial.requestFocus();
				CarregarID();
				
			}
		});
		btnLimpar.setBounds(343, 334, 100, 23);
		contentPane.setLayout(null);
		contentPane.add(btnCadastrarUser);
		contentPane.add(btnLimpar);
		contentPane.add(lblRazoSocial);
		contentPane.add(lblCnpj);
		contentPane.add(lblEmail);
		contentPane.add(lblCep);
		contentPane.add(lblEndereo);
		contentPane.add(lblTelefone);
		contentPane.add(txtTelefone);
		contentPane.add(txtEmail);
		contentPane.add(txtCnpj);
		contentPane.add(txtRazaoSocial);
		contentPane.add(txtCep);
		contentPane.add(txtEndereco);
		
		JButton btnAlterar = new JButton("Alterar");
		
		btnAlterar.setBounds(241, 334, 100, 23);
		contentPane.add(btnAlterar);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//PEGANDO OS DADOS DO BD
				
				
				//FAZENDO A CONEXÃO AO BD
				
				conexao con = new conexao();
				
				
				//REALIZANDO UMA BUSCA NO BD
				
				String sql = "select * from empresa where cnpj_emp = '"+txtPesquisarCad.getText()+"'";
				ResultSet res = con.executaBusca(sql);
				
				try {
					
					while(res.next()) {
						
							txtIdEmp.setText(res.getString("id_empresa"));
							txtRazaoSocial.setText(res.getString("nome_emp"));
							 txtCnpj.setText(res.getString("cnpj_emp"));
							 txtEndereco.setText(res.getString("end_emp"));
							 txtCep.setText(res.getString("cep_emp"));
							 txtEmail.setText(res.getString("email_emp"));
							 txtTelefone.setText(res.getString("telefon_emp"));
							 
					}
					res.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnPesquisar.setBounds(357, 74, 94, 23);
		contentPane.add(btnPesquisar);
		
		txtPesquisarCad = new JTextField();
		txtPesquisarCad.setBounds(102, 75, 247, 20);
		contentPane.add(txtPesquisarCad);
		txtPesquisarCad.setColumns(10);
		
		JLabel lbl = new JLabel("Pesquisar dados de usu\u00E1rio para alter\u00E1-los!");
		lbl.setBounds(102, 97, 250, 14);
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
					sql = "delete from usuarios where cnpj_emp ='"+txtPesquisarCad.getText()+"';";
					con.executaSQL(sql);
					
					//LIMPANDO OS CAMPOS
					
					txtRazaoSocial.setText(null);
					txtCnpj.setText(null);
					txtEndereco.setText(null);
					txtCep.setText(null);
					txtEmail.setText(null);
					txtTelefone.setText(null);
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
		btnExcluir.setBounds(139, 334, 100, 23);
		contentPane.add(btnExcluir);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 26, 46, 14);
		contentPane.add(lblId);
		
		txtIdEmp = new JTextField();
		txtIdEmp.setEditable(false);
		txtIdEmp.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdEmp.setBounds(37, 23, 66, 20);
		contentPane.add(txtIdEmp);
		txtIdEmp.setColumns(10);
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//ALTERANDO OS DADOS DO USUÁRIO
				
				
				
				try {
					
					if(txtPesquisarCad.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o cnpj no campo Pesquisar!!!");
					}else {
					//EFETUANDO A CONEXÃO
					conexao con = new conexao();
					
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM ATUALIZADOS NO BD
					String sql;
					sql = "update empresa set nome_emp='"+txtRazaoSocial.getText()+"', cnpj_emp='"+txtCnpj.getText()+"', end_emp='"+txtEndereco.getText()+"', cep_emp='"+txtCep.getText()+"', email_emp='"+txtEmail.getText()+"', telefon_emp='"+txtTelefone.getText()+"' where id_empresa ='"+txtIdEmp.getText()+"';";
					con.executaSQL(sql);
					
					//LIMPANDO OS CAMPOS
					
					txtRazaoSocial.setText(null);
					txtCnpj.setText(null);
					txtEndereco.setText(null);
					txtCep.setText(null);
					txtEmail.setText(null);
					txtTelefone.setText(null);
					
					
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
