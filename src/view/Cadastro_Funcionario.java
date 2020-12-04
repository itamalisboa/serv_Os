package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import controller.CrudBDCliente;
import controller.DadosCFU;
import controller.conexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;

public class Cadastro_Funcionario extends JFrame {

	private JPanel contentPane;
	protected JTextField txtNome;
	protected JTextField txtCPF;
	protected JTextField txtRG;
	protected JTextField txtEmail;
	protected JTextField txtTelefone;
	protected JTextField txtPesquisarCad;
	private JTextField txtID;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro_Funcionario frame = new Cadastro_Funcionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void CarregarID() {
		conexao con = new conexao();
		
		String sql = "select count(nome_Func) as total from funcionarios";
		
		ResultSet res = con.executaBusca(sql);
		
		int totalCad = 0;
		
		try {
			
			while(res.next()) {
				
				totalCad = Integer.parseInt(res.getString("total"));
				
				txtID.setText(Integer.toString(totalCad + 1));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Create the frame.
	 */
	public Cadastro_Funcionario() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				CarregarID();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cadastro de Funcion\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		
		this.setLocationRelativeTo(this);
		
		JLabel lblRazoSocial = new JLabel("Nome:");
		lblRazoSocial.setBounds(11, 109, 89, 14);
		
		JLabel lblCnpj = new JLabel("CPF:");
		lblCnpj.setBounds(11, 137, 49, 14);
		
		JLabel lblRG = new JLabel("RG:");
		lblRG.setBounds(11, 165, 79, 14);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(11, 193, 49, 14);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(11, 221, 79, 14);
		
		txtNome = new JTextField();
		txtNome.setBounds(100, 106, 319, 20);
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(100, 134, 130, 20);
		txtCPF.setColumns(10);
		
		txtRG = new JTextField();
		txtRG.setBounds(100, 162, 130, 20);
		txtRG.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(100, 190, 319, 20);
		txtEmail.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(100, 218, 130, 20);
		txtTelefone.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		
		btnCadastrar.setBounds(45, 302, 94, 23);
		
		JButton btnLimpar = new JButton("Limpar");
		
		btnLimpar.setBounds(325, 302, 94, 23);
		contentPane.setLayout(null);
		contentPane.add(btnCadastrar);
		contentPane.add(btnLimpar);
		contentPane.add(lblRazoSocial);
		contentPane.add(lblCnpj);
		contentPane.add(lblEmail);
		contentPane.add(lblRG);
		contentPane.add(lblTelefone);
		contentPane.add(txtTelefone);
		contentPane.add(txtCPF);
		contentPane.add(txtNome);
		contentPane.add(txtEmail);
		contentPane.add(txtRG);
		
		JButton btnAlterar = new JButton("Alterar");
		
		btnAlterar.setBounds(230, 302, 94, 23);
		contentPane.add(btnAlterar);
		
		txtPesquisarCad = new JTextField();
		txtPesquisarCad.setColumns(10);
		txtPesquisarCad.setBounds(86, 49, 247, 20);
		contentPane.add(txtPesquisarCad);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//FAZENDO A CONEXÃO AO BD
				
				conexao con = new conexao();
				
				
				//REALIZANDO UMA BUSCA NO BD
				
				String sql = "select * from funcionarios where cpf_func = '"+txtPesquisarCad.getText()+"'";
				ResultSet res = con.executaBusca(sql);
				
				try {
					
					while(res.next()) {
						
							txtID.setText(res.getString("id_func"));
							txtNome.setText(res.getString("nome_func"));
							 txtCPF.setText(res.getString("cpf_func"));
							 txtRG.setText(res.getString("rg_func"));
							 txtEmail.setText(res.getString("email_func"));
							 
					}
					res.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnPesquisar.setBounds(341, 49, 94, 23);
		contentPane.add(btnPesquisar);
		
		JLabel label = new JLabel("Pesquisar dados de usu\u00E1rio para alter\u00E1-los!");
		label.setBounds(86, 71, 250, 14);
		contentPane.add(label);
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//PEGANDO OS DADOS E SETANDO NOS ATRIBUTOS DA CLASSE DADOSCFU 
				
				if(txtNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe o Nome do Cliente!!!");
				}else if(txtCPF.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Informe o CPF do Cliente!!!");
					}else if(txtRG.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Informe o Endereço do Cliente!!!");
				}else {
					conexao con = new conexao();
					//CADASTRANDO OS DADOS NO BD
					
					try {
						//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM INSERIDOS NO BD
						String sql;
						sql = "insert into funcionarios(id_func, nome_func, cpf_func, rg_func, email_func)"
								+ "values('"+txtID.getText()+"', '"+txtNome.getText()+"','"+txtCPF.getText()+"', '"+txtRG.getText()+"', '"+txtEmail.getText()+"');";
						con.executaSQL(sql);
						
						
						//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
						
						txtNome.requestFocus();
						
						JOptionPane.showMessageDialog(null, "Cadastro Realizado com sucesso!!!");
						
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				
				//LIMPANDO OS CAMPOS
				txtID.setText(null);
				txtPesquisarCad.setText(null);
				txtNome.setText(null);
				txtCPF.setText(null);
				txtRG.setText(null);
				txtEmail.setText(null);
				txtTelefone.setText(null);
				
				//FIM LIMPAR CAMPOS
					
				}
			}
		});
		
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//LIMPANDO OS CAMPOS
				txtID.setText(null);
				txtPesquisarCad.setText(null);
				txtNome.setText(null);
				txtCPF.setText(null);
				txtRG.setText(null);
				txtEmail.setText(null);
				txtTelefone.setText(null);

				//FIM LIMPAR CAMPOS
				//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
				
				txtNome.requestFocus();
				CarregarID();
			}
		});
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe o Nome do Cliente!!!");
				}else if(txtCPF.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Informe o CPF do Cliente!!!");
					}else if(txtRG.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Informe o Endereço do Cliente!!!");
				}else {
				
				//ALTERANDO OS DADOS DO USUÁRIO
				
				try {
					
					if(txtPesquisarCad.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o CPF no campo Pesquisar!!!");
					}else {
						
						conexao con = new conexao();
						//CADASTRANDO OS DADOS NO BD
						
						//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM ATUALIZADOS NO BD
						String sql;
						sql = "update funcionarios set nome_func='"+txtNome.getText()+"', cpf_func='"+txtCPF.getText()+"', rg_func='"+txtRG.getText()+"', email_func='"+txtEmail.getText()+"' where id_func ='"+txtID.getText()+"';";
						con.executaSQL(sql);
						
					//LIMPANDO OS CAMPOS
					
					txtID.setText(null);
					txtNome.setText(null);
					txtCPF.setText(null);
					txtRG.setText(null);
					txtEmail.setText(null);
					txtTelefone.setText(null);
					txtPesquisarCad.setText(null);
					//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtNome.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Alteração Realizada com sucesso!!!");
					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
				
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//EXCLUÍNDO OS DADOS DO USUÁRIO
				
				
				try {
					
					if(txtPesquisarCad.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o cpf no campo Pesquisar!!!");
					}else {
					//EFETUANDO A CONEXÃO
					conexao con = new conexao();
					
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM EXCLUÍDOS DO BD
					String sql;
					sql = "delete from funcionarios where cpf_func ='"+txtPesquisarCad.getText()+"';";
					con.executaSQL(sql);
					
					//LIMPANDO OS CAMPOS
					
					txtID.setText(null);
					txtNome.setText(null);
					txtCPF.setText(null);
					txtRG.setText(null);
					txtEmail.setText(null);
					txtTelefone.setText(null);
					txtPesquisarCad.setText(null);
					//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtNome.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Exclusão Realizada com sucesso!!!");
					
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}			

			}
		});
		btnExcluir.setBounds(140, 302, 89, 23);
		contentPane.add(btnExcluir);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(11, 21, 30, 14);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setHorizontalAlignment(SwingConstants.CENTER);
		txtID.setEditable(false);
		txtID.setBounds(31, 18, 58, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
	}
}
