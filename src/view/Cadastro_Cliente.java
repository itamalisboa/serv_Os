package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import controller.ConexaoDao;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowFocusListener;

public class Cadastro_Cliente extends JFrame {

	private JPanel contentPane;
	protected JTextField txtNome;
	protected JTextField txtCPF;
	protected JTextField txtEmail;
	protected JTextField txtRG;
	protected JTextField txtTelefone;
	protected JTextField txtPesquisarCad;
	private JTextField txtID;
	private JTextField txtEndereco;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro_Cliente frame = new Cadastro_Cliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void CarregarID() {
		conexao con = new conexao();
		
		String sql = "select count(nome_cliente) as total from clientes";
		
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
	public Cadastro_Cliente() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				CarregarID();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				
				
				
			}
		});
		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cadastro de Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		
		this.setLocationRelativeTo(this);
		
		JLabel lblRazoSocial = new JLabel("Nome:");
		lblRazoSocial.setBounds(11, 109, 89, 14);
		
		JLabel lblCnpj = new JLabel("CPF:");
		lblCnpj.setBounds(11, 137, 49, 14);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(11, 196, 49, 14);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(11, 224, 79, 14);
		
		JLabel lblCep = new JLabel("RG:");
		lblCep.setBounds(240, 137, 30, 14);
		
		txtNome = new JTextField();
		txtNome.setBounds(100, 106, 319, 20);
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(100, 134, 130, 20);
		txtCPF.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(100, 193, 319, 20);
		txtEmail.setColumns(10);
		
		txtRG = new JTextField();
		txtRG.setBounds(280, 134, 130, 20);
		txtRG.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(100, 221, 130, 20);
		txtTelefone.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		
		btnCadastrar.setBounds(45, 300, 94, 23);
		
		JButton btnLimpar = new JButton("Limpar");
		
		btnLimpar.setBounds(325, 300, 94, 23);
		contentPane.setLayout(null);
		contentPane.add(btnCadastrar);
		contentPane.add(btnLimpar);
		contentPane.add(lblRazoSocial);
		contentPane.add(lblCnpj);
		contentPane.add(lblEmail);
		contentPane.add(lblCep);
		contentPane.add(lblTelefone);
		contentPane.add(txtTelefone);
		contentPane.add(txtRG);
		contentPane.add(txtCPF);
		contentPane.add(txtNome);
		contentPane.add(txtEmail);
		
		JButton btnAlterar = new JButton("Alterar");
		
		btnAlterar.setBounds(230, 300, 94, 23);
		contentPane.add(btnAlterar);
		
		txtPesquisarCad = new JTextField();
		txtPesquisarCad.setColumns(10);
		txtPesquisarCad.setBounds(86, 49, 247, 20);
		contentPane.add(txtPesquisarCad);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//INSTANCIANDO UM OBJETO PARA PEGAR O CPF DO CLINTE PARA PODER FAZER A BUSCA NO BD	
				
				DadosCFU cliente = new DadosCFU();
				cliente.setCpf(txtPesquisarCad.getText());
				
				//FAZENDO A CONEXÃO COM O BANCO E CHAMANDO O MÉTODO QUE RETORNARÁ A CONSULTA COM OS DADOS
				
				CrudBDCliente dao = new CrudBDCliente();
				dao.BuscarCliente(cliente);
				txtID.setText(cliente.getIdC());
				txtNome.setText(cliente.getNome());;
				txtCPF.setText(cliente.getCpf());
				txtEndereco.setText(cliente.getEndereco());
				txtRG.setText(cliente.getRg());
				txtEmail.setText(cliente.getEmail());
				txtTelefone.setText(cliente.getTelefone());
				
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
				
				DadosCFU cliente = new DadosCFU();
				cliente.setIdC(txtID.getText());
				cliente.setNome(txtNome.getText());
				cliente.setCpf(txtCPF.getText());
				cliente.setEndereco(txtEndereco.getText());
				cliente.setRg(txtRG.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txtTelefone.getText());
				
				
				if(txtNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe o Nome do Cliente!!!");
				}else if(txtCPF.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Informe o CPF do Cliente!!!");
					}else if(txtEndereco.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Informe o Endereço do Cliente!!!");
				}else {
					CrudBDCliente dao = new CrudBDCliente();
					dao.InserirCliente(cliente);
				
				//LIMPANDO OS CAMPOS
				txtID.setText(null);
				txtPesquisarCad.setText(null);
				txtNome.setText(null);
				txtCPF.setText(null);
				txtEndereco.setText(null);
				txtEmail.setText(null);
				txtRG.setText(null);
				txtTelefone.setText(null);
				
				//FIM LIMPAR CAMPOS
					
					JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso!!!");
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
				txtEndereco.setText(null);
				txtEmail.setText(null);
				txtRG.setText(null);
				txtTelefone.setText(null);
				
				//FIM LIMPAR CAMPOS
				//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
				
				txtNome.requestFocus();
				CarregarID();
				
			}
		});
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//PEGANDO OS DADOS E SETANDO NOS ATRIBUTOS DA CLASSE DADOSCFU 
				
				DadosCFU cliente = new DadosCFU();
				cliente.setIdC(txtID.getText());
				cliente.setNome(txtNome.getText());
				cliente.setCpf(txtCPF.getText());
				cliente.setEndereco(txtEndereco.getText());
				cliente.setRg(txtRG.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txtTelefone.getText());
				
				
				//ALTERANDO OS DADOS DO USUÁRIO
				
				try {
					if(txtPesquisarCad.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o CPF no campo Pesquisar!!!");
					}else {
						CrudBDCliente dao = new CrudBDCliente();
						dao.Atualizar(cliente);
						
					//LIMPANDO OS CAMPOS
					
					txtID.setText(null);
					txtNome.setText(null);
					txtCPF.setText(null);
					txtEndereco.setText(null);
					txtEmail.setText(null);
					txtRG.setText(null);
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
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//EXCLUINDO OS DADOS DO USUÁRIO

					if(txtPesquisarCad.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o CPF no campo Pesquisar!!!");
					}else {
						//INSTANCIANDO UM OBJETO PARA PEGAR O CPF DO CLINTE PARA PODER FAZER A BUSCA NO BD	
						
						DadosCFU cliente = new DadosCFU();
						cliente.setCpf(txtPesquisarCad.getText());
						CrudBDCliente dao = new CrudBDCliente();
						dao.DeletarCliente(cliente);
						JOptionPane.showMessageDialog(null, "Exclusão Realizada com sucesso!!!");
						
						//LIMPANDO OS CAMPOS
						txtID.setText(null);
						txtPesquisarCad.setText(null);
						txtNome.setText(null);
						txtCPF.setText(null);
						txtEndereco.setText(null);
						txtEmail.setText(null);
						txtRG.setText(null);
						txtTelefone.setText(null);
						//FIM LIMPAR CAMPOS
						//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
						
						txtNome.requestFocus();
					}			

			}
		});
		btnExcluir.setBounds(140, 300, 89, 23);
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
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(100, 162, 319, 20);
		contentPane.add(txtEndereco);
		
		JLabel lblEnd = new JLabel("E-mail:");
		lblEnd.setBounds(11, 165, 49, 14);
		contentPane.add(lblEnd);
		
		
		//------------------------
		
		
		
	}
}
