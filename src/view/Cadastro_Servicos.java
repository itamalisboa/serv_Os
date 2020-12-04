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
import javax.swing.UIManager;
import java.awt.Color;

public class Cadastro_Servicos extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescServ;
	private JTextField txtPrecoServ;
	private JTextField txtAreaPadrao;
	private JTextField txtPesquisarCad;
	private JTextField txtIdServ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro_Servicos frame = new Cadastro_Servicos();
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
				
				txtIdServ.setText(Integer.toString(totalCad + 1));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Create the frame.
	 */
	public Cadastro_Servicos() {
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
		setBounds(100, 100, 490, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cadastro de Servi\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		
		this.setLocationRelativeTo(this);
		
		JLabel lblRazoSocial = new JLabel("Descri\u00E7\u00E3o:");
		lblRazoSocial.setBounds(10, 129, 83, 14);
		
		JLabel lblCnpj = new JLabel("Pre\u00E7o:");
		lblCnpj.setBounds(10, 155, 49, 14);
		
		JLabel lblEndereo = new JLabel("\u00C1rea Padr\u00E3o:");
		lblEndereo.setBounds(10, 181, 83, 14);
		
		txtDescServ = new JTextField();
		txtDescServ.setBounds(103, 129, 344, 20);
		txtDescServ.setColumns(10);
		
		txtPrecoServ = new JTextField();
		txtPrecoServ.setBounds(103, 155, 130, 20);
		txtPrecoServ.setColumns(10);
		
		txtAreaPadrao = new JTextField();
		txtAreaPadrao.setBounds(103, 181, 130, 20);
		txtAreaPadrao.setColumns(10);
		
		JButton btnCadastrarUser = new JButton("Cadastrar");
		btnCadastrarUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				conexao con = new conexao();
				//CADASTRANDO OS DADOS NO BD
				
				try {
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM INSERIDOS NO BD
					String sql;
					sql = "insert into servicos(cod_serv, descri_serv,preco_serv,areapadrao)"
							+ "values('"+txtIdServ.getText()+"', '"+txtDescServ.getText()+"','"+txtPrecoServ.getText()+"','"+txtAreaPadrao.getText()+"');";
					con.executaSQL(sql);
					
					//LIMPANDO OS CAMPOS
					
					txtDescServ.setText(null);
					txtPrecoServ.setText(null);
					txtAreaPadrao.setText(null);
					
					
					//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtDescServ.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Cadastro Realizado com sucesso!!!");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
								
				
			}
		});
		btnCadastrarUser.setBounds(37, 247, 100, 23);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//LIMPANDO OS CAMPOS
				
				txtPesquisarCad.setText(null);
				txtDescServ.setText(null);
				txtPrecoServ.setText(null);
				txtAreaPadrao.setText(null);
				txtIdServ.setText(null);
				
				//FIM LIMPAR CAMPOS
				//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
				
				txtDescServ.requestFocus();
				CarregarID();
				
			}
		});
		btnLimpar.setBounds(343, 247, 100, 23);
		contentPane.setLayout(null);
		contentPane.add(btnCadastrarUser);
		contentPane.add(btnLimpar);
		contentPane.add(lblRazoSocial);
		contentPane.add(lblCnpj);
		contentPane.add(lblEndereo);
		contentPane.add(txtPrecoServ);
		contentPane.add(txtDescServ);
		contentPane.add(txtAreaPadrao);
		
		JButton btnAlterar = new JButton("Alterar");
		
		btnAlterar.setBounds(241, 247, 100, 23);
		contentPane.add(btnAlterar);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//FAZENDO A CONEXÃO AO BD
				
				conexao con = new conexao();
				
				
				//REALIZANDO UMA BUSCA NO BD
				
				String sql = "select * from servicos where cod_serv = '"+txtPesquisarCad.getText()+"'";
				ResultSet res = con.executaBusca(sql);
				
				try {
					
					while(res.next()) {
						
							txtIdServ.setText(res.getString("cod_serv"));
							txtDescServ.setText(res.getString("descri_serv"));
							 txtPrecoServ.setText(res.getString("preco_serv"));
							 txtAreaPadrao.setText(res.getString("areapadrao"));
							 
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
		
		JLabel lbl = new JLabel("Pesquisar dados de servi\u00E7o para alter\u00E1-los!");
		lbl.setBounds(102, 97, 250, 14);
		contentPane.add(lbl);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//EXCLUÍNDO OS DADOS DO USUÁRIO
				
				
				try {
					
					if(txtPesquisarCad.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o código do serviço no campo Pesquisar!!!");
					}else {
					//EFETUANDO A CONEXÃO
					conexao con = new conexao();
					
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM EXCLUÍDOS DO BD
					String sql;
					sql = "delete from servicos where cod_serv ='"+txtPesquisarCad.getText()+"';";
					con.executaSQL(sql);
					
					//LIMPANDO OS CAMPOS
					
					txtDescServ.setText(null);
					txtPrecoServ.setText(null);
					txtAreaPadrao.setText(null);
					txtPesquisarCad.setText(null);
					//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtDescServ.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Exclusão Realizada com sucesso!!!");
					
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnExcluir.setBounds(139, 247, 100, 23);
		contentPane.add(btnExcluir);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 26, 46, 14);
		contentPane.add(lblId);
		
		txtIdServ = new JTextField();
		txtIdServ.setEditable(false);
		txtIdServ.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdServ.setBounds(37, 23, 66, 20);
		contentPane.add(txtIdServ);
		txtIdServ.setColumns(10);
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//ALTERANDO OS DADOS DO USUÁRIO
				
				
				
				try {
					
					if(txtPesquisarCad.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o código do serviço no campo Pesquisar!!!");
					}else {
					//EFETUANDO A CONEXÃO
					conexao con = new conexao();
					
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM ATUALIZADOS NO BD
					String sql;
					sql = "update servicos set descri_serv='"+txtDescServ.getText()+"', preco_serv='"+txtPrecoServ.getText()+"', areapadrao='"+txtAreaPadrao.getText()+"' where cod_serv ='"+txtIdServ.getText()+"';";
					con.executaSQL(sql);
					
					//LIMPANDO OS CAMPOS
					
					txtDescServ.setText(null);
					txtPrecoServ.setText(null);
					txtAreaPadrao.setText(null);
					
					
					//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtDescServ.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Alteração Realizada com sucesso!!!");
					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
