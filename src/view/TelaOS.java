package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import controller.ConexaoDao;
import controller.conexao;
import controller.os_Dao;
import model.model_OS;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.Date;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;;

public class TelaOS extends JFrame {
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	//Essa variável irá pegar a informação se é OS ou Orçamento
	private String tipo;
	
	private float valorUnitServ = 0;
	private float areaAplic = 0;
	private float totalOS = 0;
	
	//Métodos
	
	//Método pesquisarClientes
	
	
	public void pesquisarClientes() {
		
		
		
		//Chamando o método de conexão com o BD;
		
		conexao = ConexaoDao.conector();
		
		String sql = "select id_cliente as Id, nome_cliente as Nome, telefone_cliente as Telefone from clientes where nome_cliente like ?";
		
		try {
			
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtPesquisarCliente.getText() + "%");
			rs = pst.executeQuery();
			
			jtbClienteOS.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	//Método setar campos para pegar o ID do cliente selecionado na tabela
	
	private void setar_campos() {
		int setar = jtbClienteOS.getSelectedRow();
		txtIdCliente.setText(jtbClienteOS.getModel().getValueAt(setar, 0).toString());
	}
	
	public void CarregarID() {
		conexao con = new conexao();
		
		String sql = "select count(codo_serv) as total from ordemservico";
		
		ResultSet res = con.executaBusca(sql);
		
		int totalCad = 0;
		
		try {
			
			while(res.next()) {
				
				totalCad = Integer.parseInt(res.getString("total"));
				
				txtNumOS.setText(Integer.toString(totalCad + 1));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	//Método para cadastro de uma OS
	
		private void emitir_OS() {
			
			
			
		}
	
	//Imprimindo uma OS
	
	private void imprimir_OS() {
		
		conexao = ConexaoDao.conector();
		
		//Imprmindo Uma Ordens de Serviços Geradas
		
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Impressão da Ordem de Seriviço?", "Atenção", JOptionPane.YES_NO_OPTION);
		
		if(confirma == JOptionPane.YES_OPTION) {
			//Imprimindo o relatório com o JasperReport
			try {
				
				//Usando a Classe HashMap Para Criar um Filtro
				
				HashMap filtro = new HashMap();
				filtro.put("codOS", Integer.parseInt(txtNumOS.getText()));
				
				//Usando a classe JasperPrint para preparar a impressão do relatório
				JasperPrint print = JasperFillManager.fillReport("Dependencias/Relatorios/ordemservico.jasper",filtro,conexao);
				//A classe a baixo exibe o relatório através da classe JasperView
				JasperViewer.viewReport(print, false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
		
	}
	

	private JPanel contentPane;
	private JTextField txtDataOS;
	private JTextField txtNumOS;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtPesquisarCliente;
	private JTextField txtIdCliente;
	private JTable jtbClienteOS;
	private JTextField txtAreaAplic;
	private JTextField txtDataExecOS;
	private JTextField txtValUnitServ;
	private JTextField txtTecResp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaOS frame = new TelaOS();
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
	public TelaOS() {
		
		JLabel lblStatus = new JLabel("status");
		JLabel lblAutorizar = new JLabel("");
		JLabel lblTotalOs = new JLabel("totalOS");
		
		
		lblAutorizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				lblStatus.setText("Em Andamento");
				
				//ALTERANDO OS DADOS DO USUÁRIO
				
				
				
				try {
					
					if(txtNumOS.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o código do serviço no campo Nº OS!!!");
					}else {
					//EFETUANDO A CONEXÃO
					conexao con = new conexao();
					
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM ATUALIZADOS NO BD
					String sql;
					sql = "update ordemservico set status_serv='"+lblStatus.getText()+"' where codo_serv ='"+txtNumOS.getText()+"';";
					con.executaSQL(sql);
					
					txtPesquisarCliente.setText(null);
					txtDataOS.setText(null);
					txtDataExecOS.setText(null);
					txtIdCliente.setText(null);
					txtNumOS.setText(null);
					txtTecResp.setText(null);
					txtAreaAplic.setText(null);
					txtValUnitServ.setText(null);
					lblTotalOs.setText("0.0");
					
					//RETORNANDO O FOCU NO Campo NUm OS
					
					txtNumOS.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Alteração Realizada com sucesso!!!");
					lblAutorizar.setEnabled(false);
					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				lblStatus.setText("Em Andamento");
				
			}
		});
		JLabel lblExecutaros = new JLabel("");
		lblExecutaros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				//ALTERANDO OS DADOS DO USUÁRIO
				
				
				
				try {
					
					if(txtNumOS.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o código do serviço no campo Nº OS!!!");
					}else {
						
						lblStatus.setText("OS Executada");
						
					//EFETUANDO A CONEXÃO
					conexao con = new conexao();
					
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM ATUALIZADOS NO BD
					String sql;
					sql = "update ordemservico set status_serv='"+lblStatus.getText()+"' where codo_serv ='"+txtNumOS.getText()+"';";
					con.executaSQL(sql);
					
					txtPesquisarCliente.setText(null);
					txtDataOS.setText(null);
					txtDataExecOS.setText(null);
					txtIdCliente.setText(null);
					txtNumOS.setText(null);
					txtTecResp.setText(null);
					txtAreaAplic.setText(null);
					txtValUnitServ.setText(null);
					lblTotalOs.setText("0.0");
					
					//RETORNANDO O FOCU NO Campo NUm OS
					
					txtNumOS.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Alteração Realizada com sucesso!!!");
					lblAutorizar.setEnabled(false);
					
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				lblStatus.setText("Executada");
				
			}
		});
		
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				CarregarID();
				Date data = new Date();
				SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
				txtDataOS.setText(formato.format(data));
				lblStatus.setText("Orçamento");
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		setResizable(false);
		
			
		setTitle(":::.Ordem de Servi\u00E7o.:::");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 224, 208));
		panel.setBorder(new TitledBorder(null, "O.S.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 250, 112);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNOs = new JLabel("N\u00BA OS");
		lblNOs.setBounds(29, 15, 46, 14);
		panel.add(lblNOs);
		
		JLabel lblDataOs = new JLabel("Data OS");
		lblDataOs.setBounds(132, 15, 46, 14);
		panel.add(lblDataOs);
		
		txtDataOS = new JTextField();
		txtDataOS.setHorizontalAlignment(SwingConstants.CENTER);
		txtDataOS.setEditable(false);
		txtDataOS.setBounds(132, 36, 86, 20);
		panel.add(txtDataOS);
		txtDataOS.setColumns(10);
		
		txtNumOS = new JTextField();
		txtNumOS.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumOS.setBounds(29, 36, 71, 20);
		panel.add(txtNumOS);
		txtNumOS.setColumns(10);
		
		JLabel lblSituao = new JLabel("Status:");
		lblSituao.setBounds(10, 87, 55, 14);
		panel.add(lblSituao);
		
		
		lblStatus.setBounds(62, 87, 156, 14);
		panel.add(lblStatus);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(64, 224, 208));
		panel_1.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_1.setBounds(270, 11, 404, 168);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtPesquisarCliente = new JTextField();
		txtPesquisarCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				pesquisarClientes();
				
			}
		});
		txtPesquisarCliente.setBounds(10, 29, 172, 20);
		panel_1.add(txtPesquisarCliente);
		txtPesquisarCliente.setColumns(10);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(282, 32, 26, 14);
		panel_1.add(lblId);
		
		txtIdCliente = new JTextField();
		txtIdCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdCliente.setEditable(false);
		txtIdCliente.setBounds(318, 29, 76, 20);
		panel_1.add(txtIdCliente);
		txtIdCliente.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 384, 98);
		panel_1.add(scrollPane);
		
		jtbClienteOS = new JTable();
		jtbClienteOS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Chamando o método Setar_Campos
				
				setar_campos();
				
			}
		});
		jtbClienteOS.setBackground(Color.WHITE);
		jtbClienteOS.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "Fone"
			}
		));
		jtbClienteOS.getColumnModel().getColumn(0).setPreferredWidth(54);
		jtbClienteOS.getColumnModel().getColumn(1).setPreferredWidth(180);
		jtbClienteOS.getColumnModel().getColumn(2).setPreferredWidth(88);
		scrollPane.setViewportView(jtbClienteOS);
		
		JLabel lblPesquisar = new JLabel("");
		lblPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPesquisar.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/servos/imgs/buscar_32x32.png")));
		lblPesquisar.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisar.setBounds(192, 20, 34, 34);
		panel_1.add(lblPesquisar);
		
		JLabel lblServios = new JLabel("Servi\u00E7o:");
		lblServios.setBounds(10, 199, 46, 14);
		contentPane.add(lblServios);
		
		JComboBox cboServico = new JComboBox();
		cboServico.setModel(new DefaultComboBoxModel(new String[] {"Servi\u00E7o de Desratiza\u00E7\u00E3o", "Servi\u00E7o de Dedetiza\u00E7\u00E3o", "Servi\u00E7o de Descupiniza\u00E7\u00E3o"}));
		cboServico.setBackground(Color.WHITE);
		cboServico.setBounds(62, 196, 334, 20);
		contentPane.add(cboServico);
		
		JLabel lblrea = new JLabel("\u00C1rea:");
		lblrea.setBounds(10, 242, 46, 14);
		contentPane.add(lblrea);
		
		
		
		txtAreaAplic = new JTextField();
		txtAreaAplic.setHorizontalAlignment(SwingConstants.CENTER);
		txtAreaAplic.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
				if(txtAreaAplic.getText().isEmpty()) {
					areaAplic = 0;
					JOptionPane.showMessageDialog(null, "Informe a área de aplicação para calcular o valor da OS/Orçamento!");
					txtAreaAplic.requestFocus();
				}else {
					areaAplic = Float.parseFloat(txtAreaAplic.getText());
					
					valorUnitServ = Float.parseFloat(txtValUnitServ.getText());
				}
				
					
				totalOS = valorUnitServ * areaAplic;
					
				lblTotalOs.setText(Float.toString(totalOS));
				
			}
		});
		txtAreaAplic.setBounds(62, 239, 107, 20);
		contentPane.add(txtAreaAplic);
		txtAreaAplic.setColumns(10);
		
		JLabel lblDataExecuo = new JLabel("Data Execu\u00E7\u00E3o:");
		lblDataExecuo.setBounds(179, 242, 92, 14);
		contentPane.add(lblDataExecuo);
		
		txtDataExecOS = new JTextField();
		txtDataExecOS.setHorizontalAlignment(SwingConstants.CENTER);
		txtDataExecOS.setBounds(270, 239, 126, 20);
		contentPane.add(txtDataExecOS);
		txtDataExecOS.setColumns(10);
		
		JLabel lblPreoUnitM = new JLabel("Pre\u00E7o Unit. M\u00B2:");
		lblPreoUnitM.setBounds(406, 199, 85, 14);
		contentPane.add(lblPreoUnitM);
		
		txtValUnitServ = new JTextField();
		txtValUnitServ.setHorizontalAlignment(SwingConstants.CENTER);
		txtValUnitServ.setBounds(497, 196, 122, 20);
		contentPane.add(txtValUnitServ);
		txtValUnitServ.setColumns(10);
		
		JLabel lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValorTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTotal.setBounds(461, 286, 90, 14);
		contentPane.add(lblValorTotal);
		
		JLabel lblTcnico = new JLabel("T\u00E9cnico:");
		lblTcnico.setBounds(406, 242, 53, 14);
		contentPane.add(lblTcnico);
		
		txtTecResp = new JTextField();
		txtTecResp.setBounds(462, 239, 212, 20);
		contentPane.add(txtTecResp);
		txtTecResp.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setBackground(new Color(64, 224, 208));
		panel_2.setBounds(0, 344, 684, 67);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCadastrarOS = new JLabel("");
		lblCadastrarOS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCadastrarOS.setToolTipText("Cadastrar");
		lblCadastrarOS.setBounds(154, 11, 48, 48);
		panel_2.add(lblCadastrarOS);
		
		
		
		lblCadastrarOS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String sql = "INSERT INTO public.ordemservico(\r\n" + 
						"	codo_serv, id_cliente, cod_serv, id_func, data_os, data_exec, areaaplic, precounit, status_serv)\r\n" + 
						"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
				
				
				try {
					pst = conexao.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(txtNumOS.getText()));
					pst.setInt(2, Integer.parseInt(txtIdCliente.getText()));
					pst.setInt(3, cboServico.getSelectedIndex());
					pst.setInt(4, Integer.parseInt(txtTecResp.getText()));
					pst.setString(5, txtDataOS.getText());
					pst.setString(6, txtDataExecOS.getText());
					pst.setFloat(7, Float.parseFloat(txtAreaAplic.getText()));
					pst.setFloat(8, Float.parseFloat(txtValUnitServ.getText()));
					pst.setString(9,  lblStatus.getText());
					
					//Validação dos campos obrigatórios
					
					if ((txtIdCliente.getText().isEmpty()) || (txtNumOS.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
					} else {
						int adicionado = pst.executeUpdate();
						if(adicionado > 0) {
							JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
							
							txtPesquisarCliente.setText(null);
							txtDataOS.setText(null);
							txtDataExecOS.setText(null);
							txtIdCliente.setText(null);
							txtNumOS.setText(null);
							txtTecResp.setText(null);
							txtAreaAplic.setText(null);
							txtValUnitServ.setText(null);
							lblTotalOs.setText("0.0");
							
						}
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		lblCadastrarOS.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/servos/imgs/salvar_48x48.png")));
		
		
		
		JLabel lblConsultar = new JLabel("");
		lblConsultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Pesquisando uma OS
				
				//Chamando o método de conexão com o BD;
				
				conexao con = new conexao();
				
				//CADASTRANDO OS DADOS NO BD
				
				model_OS status = new model_OS();
				
				String sql = "select * from ordemservico where codo_serv = '"+txtNumOS.getText()+"'";
				
				
				ResultSet res = con.executaBusca(sql);
				
				try {
					
					while(res.next()) {
						
							//txtNumOS.setText(res.getString("codo_serv"));
							txtIdCliente.setText(res.getString("id_cliente"));
							 cboServico.setSelectedItem(res.getString("cod_serv"));
							 txtTecResp.setText(res.getString("id_func"));
							 txtDataOS.setText(res.getString("data_os"));
							 txtDataExecOS.setText(res.getString("data_exec"));
							 lblStatus.setText(res.getString("status_serv"));
							 txtValUnitServ.setText(res.getString("precounit"));
							 txtAreaAplic.setText(res.getString("areaaplic"));
							 
							 status.setStatus(res.getString("status_serv"));
							 
					}
					res.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(txtAreaAplic.getText().isEmpty()) {
					areaAplic = 0;
					
				}else {
					areaAplic = Float.parseFloat(txtAreaAplic.getText());
					
					valorUnitServ = Float.parseFloat(txtValUnitServ.getText());
				}
				
					
				totalOS = valorUnitServ * areaAplic;
					
				lblTotalOs.setText(Float.toString(totalOS));
				System.out.println(status.getStatus());
				
				if(status.getStatus().equals("Orçamento")) {
					lblAutorizar.setEnabled(true);	
				}else {
					lblExecutaros.setEnabled(true);
				}
				
				
				
			}
		});
		lblConsultar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConsultar.setToolTipText("Pesquisar");
		lblConsultar.setBounds(217, 11, 48, 48);
		panel_2.add(lblConsultar);
		lblConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultar.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/servos/imgs/buscar_48x48.png")));
		
		JLabel lblEditar = new JLabel("");
		lblEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//ALTERANDO OS DADOS DO USUÁRIO
				
				
				
				try {
					
					if(txtNumOS.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe o código do serviço no campo Nº OS!!!");
					}else {
					//EFETUANDO A CONEXÃO
					conexao con = new conexao();
					
					//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM ATUALIZADOS NO BD
					String sql;
					sql = "update ordemservico set id_cliente='"+txtIdCliente.getText()+"', cod_serv='"+cboServico.getSelectedIndex()+"', id_func='"+txtTecResp.getText()+"', data_os='"+txtDataOS.getText()+"', data_exec='"+txtDataExecOS.getText()+"', status_serv='"+lblStatus.getText()+"', areaaplic='"+txtAreaAplic.getText()+"', precounit='"+txtValUnitServ.getText()+"' where codo_serv ='"+txtNumOS.getText()+"';";
					con.executaSQL(sql);
					
					//LIMPANDO OS CAMPOS
					
					txtPesquisarCliente.setText(null);
					txtDataOS.setText(null);
					txtDataExecOS.setText(null);
					txtIdCliente.setText(null);
					txtNumOS.setText(null);
					txtTecResp.setText(null);
					txtAreaAplic.setText(null);
					txtValUnitServ.setText(null);
					lblTotalOs.setText("0.0");
					
					
					//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtNumOS.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Alteração Realizada com sucesso!!!");
					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		lblEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEditar.setToolTipText("Editar");
		lblEditar.setBounds(282, 11, 48, 48);
		panel_2.add(lblEditar);
		lblEditar.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/servos/imgs/editar_48x48.png")));
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblDeletar = new JLabel("");
		lblDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String os = "";
				
				//EXCLUÍNDO OS DADOS DO USUÁRIO
				
				//FAZENDO A CONEXÃO AO BD
				
				conexao con = new conexao();
				
				
				//REALIZANDO UMA BUSCA NO BD
				
				String sql = "select * from ordemservico where codo_serv = '"+txtNumOS.getText()+"'";
				ResultSet res = con.executaBusca(sql);
				
				try {
					
					while(res.next()) {
						
							os = res.getString("codo_serv");
							 
					}
					res.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(os != "") {
					try {
						
						//VARIÁVEL QUE PEGARÁ OS DADOS PARA SEREM EXCLUÍDOS DO BD
						String sql2;
						sql2 = "delete from ordemservico where codo_serv ='"+txtNumOS.getText()+"';";
						con.executaSQL(sql2);
						
						//LIMPANDO OS CAMPOS
						
						txtDataExecOS.setText(null);
						txtDataOS.setText(null);
						txtNumOS.setText(null);
						txtTecResp.setText(null);
						txtAreaAplic.setText(null);
						txtValUnitServ.setText(null);
						lblTotalOs.setText("0.0");
						
						//FIM LIMPAR CAMPOS
						//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
						
						txtPesquisarCliente.requestFocus();
						
						JOptionPane.showMessageDialog(null, "Exclusão Realizada com sucesso!!!");
						
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Número de OS não encontrada!");
				}
				
				
				
				
			}
		});
		lblDeletar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDeletar.setToolTipText("Deletar");
		lblDeletar.setBounds(343, 11, 48, 48);
		panel_2.add(lblDeletar);
		lblDeletar.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/servos/imgs/deletar_48x48.png")));
		lblDeletar.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblImprimir = new JLabel("");
		lblImprimir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//Chamando o método para gerar uma OS
				
				imprimir_OS();
				
			}
		});
		lblImprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImprimir.setToolTipText("Imprimir");
		lblImprimir.setBounds(406, 11, 48, 48);
		panel_2.add(lblImprimir);
		lblImprimir.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/servos/imgs/imprimir_48x48.png")));
		lblImprimir.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		lblAutorizar.setEnabled(false);
		lblAutorizar.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/servos/imgs/aprovar_48x48.png")));
		lblAutorizar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutorizar.setBounds(464, 11, 48, 48);
		panel_2.add(lblAutorizar);
		
		
		lblExecutaros.setEnabled(false);
		lblExecutaros.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/servos/imgs/executarOS_48x48.png")));
		lblExecutaros.setHorizontalAlignment(SwingConstants.CENTER);
		lblExecutaros.setBounds(522, 11, 48, 48);
		panel_2.add(lblExecutaros);
		
		
		lblTotalOs.setBounds(561, 287, 46, 14);
		contentPane.add(lblTotalOs);
		
		this.setLocationRelativeTo(null);
		
		
		//---------------------------------------
		
		//lblTotalOs.setText("0.0");
		
		
		
		
		
	}
}
