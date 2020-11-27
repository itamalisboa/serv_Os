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
import java.util.HashMap;

import controller.ConexaoDao;
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

public class TelaOS extends JFrame {
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	//Essa vari�vel ir� pegar a informa��o se � OS ou Or�amento
	private String tipo;
	
	private float valorUnitServ = 0;
	private float areaAplic = 0;
	private float totalOS = 0;
	
	//M�todos
	
	//M�todo pesquisarClientes
	
	
	public void pesquisarClientes() {
		
		
		
		//Chamando o m�todo de conex�o com o BD;
		
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
	
	//M�todo setar campos para pegar o ID do cliente selecionado na tabela
	
	private void setar_campos() {
		int setar = jtbClienteOS.getSelectedRow();
		txtIdCliente.setText(jtbClienteOS.getModel().getValueAt(setar, 0).toString());
	}
	
	
	//M�todo para cadastro de uma OS
	
		private void emitir_OS() {
			
			
			
		}
	
	//Imprimindo uma OS
	
	private void imprimir_OS() {
		
		//Imprmindo Uma Ordens de Servi�os Geradas
		
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Impress�o da Ordem de Serivi�o?", "Aten��o", JOptionPane.YES_NO_OPTION);
		
		if(confirma == JOptionPane.YES_OPTION) {
			//Imprimindo o relat�rio com o JasperReport
			try {
				
				//Usando a Classe HashMap Para Criar um Filtro
				
				HashMap filtro = new HashMap();
				filtro.put("codOS", Integer.parseInt(txtNumOS.getText()));
				
				//Usando a classe JasperPrint para preparar a impress�o do relat�rio
				JasperPrint print = JasperFillManager.fillReport("Dependencias/Relatorios/ordemservico.jasper",filtro,conexao);
				//A classe a baixo exibe o relat�rio atrav�s da classe JasperView
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
		txtDataOS.setBounds(132, 36, 86, 20);
		panel.add(txtDataOS);
		txtDataOS.setColumns(10);
		
		txtNumOS = new JTextField();
		txtNumOS.setBounds(29, 36, 71, 20);
		panel.add(txtNumOS);
		txtNumOS.setColumns(10);
		
		JRadioButton rdbtnOrcamento = new JRadioButton("Or\u00E7amento");
		rdbtnOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Adicionando um valor a vari�vel tipo caso selecionado
				tipo = "Or�amento";
			}
		});
		rdbtnOrcamento.setBackground(new Color(64, 224, 208));
		rdbtnOrcamento.setSelected(true);
		buttonGroup.add(rdbtnOrcamento);
		rdbtnOrcamento.setBounds(6, 82, 94, 23);
		panel.add(rdbtnOrcamento);
		
		JRadioButton rdbtnOrdemDeServico = new JRadioButton("Ordem de Servi\u00E7o");
		rdbtnOrdemDeServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Adicionando um valor a vari�vel tipo caso selecionado
				tipo = "OS";
			}
		});
		rdbtnOrdemDeServico.setBackground(new Color(64, 224, 208));
		buttonGroup.add(rdbtnOrdemDeServico);
		rdbtnOrdemDeServico.setBounds(102, 82, 142, 23);
		panel.add(rdbtnOrdemDeServico);
		
		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o:");
		lblSituao.setBounds(10, 140, 55, 14);
		contentPane.add(lblSituao);
		
		JComboBox cboSituacao = new JComboBox();
		cboSituacao.setBackground(Color.WHITE);
		cboSituacao.setModel(new DefaultComboBoxModel(new String[] {"Or\u00E7amento", "Em Andamento", "Agendada", "Executada"}));
		cboSituacao.setBounds(70, 137, 165, 20);
		contentPane.add(cboSituacao);
		
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
				
				//Chamando o m�todo Setar_Campos
				
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
		txtAreaAplic.setBounds(62, 239, 107, 20);
		contentPane.add(txtAreaAplic);
		txtAreaAplic.setColumns(10);
		
		JLabel lblDataExecuo = new JLabel("Data Execu\u00E7\u00E3o:");
		lblDataExecuo.setBounds(179, 242, 92, 14);
		contentPane.add(lblDataExecuo);
		
		txtDataExecOS = new JTextField();
		txtDataExecOS.setBounds(270, 239, 126, 20);
		contentPane.add(txtDataExecOS);
		txtDataExecOS.setColumns(10);
		
		JLabel lblPreoUnitM = new JLabel("Pre\u00E7o Unit. M\u00B2:");
		lblPreoUnitM.setBounds(406, 199, 85, 14);
		contentPane.add(lblPreoUnitM);
		
		txtValUnitServ = new JTextField();
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
		lblCadastrarOS.setBounds(182, 10, 48, 48);
		panel_2.add(lblCadastrarOS);
		
		JLabel lblTotalOs = new JLabel("totalOS");
		
		lblCadastrarOS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String sql = "INSERT INTO public.ordemservico(\r\n" + 
						"	codo_serv, id_cliente, cod_serv, id_func, data_os, data_exec, status_serv, tipoos_orc)\r\n" + 
						"	VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
				
				areaAplic = Float.parseFloat(txtAreaAplic.getText());
				
				valorUnitServ = Float.parseFloat(txtValUnitServ.getText());
					
				totalOS = valorUnitServ * areaAplic;
					
				lblTotalOs.setText(Float.toString(totalOS));
				
				
				try {
					pst = conexao.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(txtNumOS.getText()));
					pst.setInt(2, Integer.parseInt(txtIdCliente.getText()));
					pst.setInt(3, cboServico.getSelectedIndex());
					pst.setInt(4, Integer.parseInt(txtTecResp.getText()));
					pst.setString(5, txtDataOS.getText());
					pst.setString(6, txtDataExecOS.getText());
					pst.setString(7, cboSituacao.getSelectedItem().toString());
					pst.setString(8, tipo);
					
					//Valida��o dos campos obrigat�rios
					
					if ((txtIdCliente.getText().isEmpty()) || (txtNumOS.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "Preencha os campos obrigat�rios!");
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
				
				model_OS os = new model_OS();
				
				os.setCodo_serv(Integer.parseInt(JOptionPane.showInputDialog("Informe o n� da OS!")));
				
				os_Dao dao = new os_Dao();
				
				dao.pesquisar_os(os);
				txtNumOS.setText(Integer.toString(os.getCodo_serv()));
				txtIdCliente.setText(Integer.toString(os.getId_cliente()));
				txtDataOS.setText(os.getData_os());
				
			}
		});
		lblConsultar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConsultar.setToolTipText("Pesquisar");
		lblConsultar.setBounds(245, 10, 48, 48);
		panel_2.add(lblConsultar);
		lblConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultar.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/servos/imgs/buscar_48x48.png")));
		
		JLabel lblEditar = new JLabel("");
		lblEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEditar.setToolTipText("Editar");
		lblEditar.setBounds(310, 10, 48, 48);
		panel_2.add(lblEditar);
		lblEditar.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/servos/imgs/editar_48x48.png")));
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblDeletar = new JLabel("");
		lblDeletar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDeletar.setToolTipText("Deletar");
		lblDeletar.setBounds(371, 10, 48, 48);
		panel_2.add(lblDeletar);
		lblDeletar.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/servos/imgs/deletar_48x48.png")));
		lblDeletar.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblImprimir = new JLabel("");
		lblImprimir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//Chamando o m�todo para gerar uma OS
				
				imprimir_OS();
				
			}
		});
		lblImprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImprimir.setToolTipText("Imprimir");
		lblImprimir.setBounds(434, 10, 48, 48);
		panel_2.add(lblImprimir);
		lblImprimir.setIcon(new ImageIcon(TelaOS.class.getResource("/br/com/servos/imgs/imprimir_48x48.png")));
		lblImprimir.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		lblTotalOs.setBounds(561, 287, 46, 14);
		contentPane.add(lblTotalOs);
		
		this.setLocationRelativeTo(null);
		
		
		//---------------------------------------
		
		//lblTotalOs.setText("0.0");
		
		
		
		
		
	}
}
