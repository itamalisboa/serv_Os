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
import javax.swing.JComboBox;

public class ProdutoNecessario extends JFrame {

	private JPanel contentPane;
	private JTextField txtPesquisarCad;
	private JTextField txtID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutoNecessario frame = new ProdutoNecessario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void CarregarID() {
		conexao con = new conexao();
		
		String sql = "select count(id_prodnec) as total from produtonecessario";
		
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
	public ProdutoNecessario() {
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
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cadastro de Produto Necess\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		
		this.setLocationRelativeTo(this);
		
		JLabel lblRazoSocial = new JLabel("Servi\u00E7o:");
		lblRazoSocial.setBounds(10, 129, 83, 14);
		
		JLabel lblCnpj = new JLabel("Produto:");
		lblCnpj.setBounds(10, 155, 49, 14);
		
		JComboBox cboServico = new JComboBox();
		JComboBox cboProduto = new JComboBox();
		
		JButton btnCadastrarUser = new JButton("Cadastrar");
		btnCadastrarUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				conexao con = new conexao();
				//CADASTRANDO OS DADOS NO BD
				
				try {
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM INSERIDOS NO BD
					String sql;
					sql = "insert into produtonecessario(id_prodnec, cod_prod, cod_serv)"
							+ "values('"+txtID.getText()+"', '"+cboProduto.getSelectedIndex()+"','"+cboServico.getSelectedIndex()+"');";
					con.executaSQL(sql);
					
					
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtPesquisarCad.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Cadastro Realizado com sucesso!!!");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
								
				
			}
		});
		btnCadastrarUser.setBounds(37, 247, 100, 23);
		contentPane.setLayout(null);
		contentPane.add(btnCadastrarUser);
		contentPane.add(lblRazoSocial);
		contentPane.add(lblCnpj);
		
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
					sql = "delete from produtonecessario where id_prodnec ='"+txtID.getText()+"';";
					con.executaSQL(sql);
					
					
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtPesquisarCad.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Exclusão Realizada com sucesso!!!");
					
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnExcluir.setBounds(139, 247, 100, 23);
		contentPane.add(btnExcluir);
		
		
		cboServico.setBounds(103, 126, 246, 20);
		contentPane.add(cboServico);
		
		
		cboProduto.setBounds(103, 152, 246, 20);
		contentPane.add(cboProduto);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 25, 23, 14);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setHorizontalAlignment(SwingConstants.CENTER);
		txtID.setBounds(37, 22, 66, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
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
					sql = "update produtonecessario set cod_prod='"+cboProduto.getSelectedItem()+"', cod_serv='"+cboServico.getSelectedIndex()+"' where id_prodnec ='"+txtID.getText()+"';";
					con.executaSQL(sql);
					
					
					
					
					//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtPesquisarCad.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Alteração Realizada com sucesso!!!");
					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
