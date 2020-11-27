package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.usuarioDao;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;
import java.sql.*;

public class TelaPrincipal extends JFrame {
	
	Connection conexao = null;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		
		conexao = controller.ConexaoDao.conector();
		

		JLabel lblData = new JLabel("data");
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				//A linha abaixo substitui a label lblData pela data atual do sistema assim que a tela principal inicializar;
				
				Date data = new Date();
				DateFormat formatar = DateFormat.getDateInstance(DateFormat.SHORT);
				lblData.setText(formatar.format(data));
			}
		});
		setResizable(false);
		setTitle("::::..SERV OS..::::");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 483);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuArquivo = new JMenu("Arquivo");
		menuBar.add(menuArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Criando uma variável para receber a confirmação do usuário se ele realmente quer sair do sistema!
				int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair do sistema?","Atenção!", JOptionPane.YES_NO_OPTION);
				//Se ele confirmar que quer sair será executado o comando abaixo e encerrará o sistema
				if(sair == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				
			}
		});
		menuArquivo.add(mntmSair);
		
		JMenu menuCadastros = new JMenu("Cadastros");
		menuBar.add(menuCadastros);
		
		JMenuItem menCadEmpresa = new JMenuItem("Empresa");
		menCadEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Cadastro_Empresa empresa = new Cadastro_Empresa();
				empresa.setVisible(true);
				
			}
		});
		menCadEmpresa.setEnabled(false);
		menuCadastros.add(menCadEmpresa);
		
		JMenuItem menCadUsuario = new JMenuItem("Usu\u00E1rio");
		menCadUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Cadastro_Usuario usuario = new Cadastro_Usuario();
				usuario.setVisible(true);
				
			}
		});
		menCadUsuario.setEnabled(false);
		menuCadastros.add(menCadUsuario);
		
		JMenuItem menCadFuncionario = new JMenuItem("Funcion\u00E1rio");
		menCadFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cadastro_Funcionario funcionario = new Cadastro_Funcionario();
				funcionario.setVisible(true);
				
			}
		});
		menCadFuncionario.setEnabled(false);
		menuCadastros.add(menCadFuncionario);
		
		JMenuItem menCadCliente = new JMenuItem("Cliente");
		menCadCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cadastro_Cliente cliente = new Cadastro_Cliente();
				cliente.setVisible(true);
				
			}
		});
		menuCadastros.add(menCadCliente);
		
		JMenuItem menCadFornecedor = new JMenuItem("Fornecedor");
		menCadFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cadastro_Fornecedor fornecedor = new Cadastro_Fornecedor();
				fornecedor.setVisible(true);
				
			}
		});
		menuCadastros.add(menCadFornecedor);
		
		JMenuItem menCadProduto = new JMenuItem("Produto");
		menCadProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CadProduto produto = new CadProduto();
				produto.setVisible(true);
				
			}
		});
		menuCadastros.add(menCadProduto);
		
		JMenuItem menCadServios = new JMenuItem("Servi\u00E7os");
		menCadServios.setEnabled(false);
		menuCadastros.add(menCadServios);
		
		JMenu menuOrdemDeServico = new JMenu("Ordem de Servi\u00E7o");
		menuBar.add(menuOrdemDeServico);
		
		JMenuItem menuGerarOs = new JMenuItem("Gerar OS");
		menuGerarOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Chamando a tela OS;
				TelaOS os = new TelaOS();
				os.setVisible(true);
			}
		});
		menuOrdemDeServico.add(menuGerarOs);
		
		JMenuItem menuConsultarOs = new JMenuItem("Consultar OS");
		menuOrdemDeServico.add(menuConsultarOs);
		
		JMenu menuRelatorios = new JMenu("Relat\u00F3rios");
		menuBar.add(menuRelatorios);
		
		JMenuItem menClientesCad = new JMenuItem("Clientes Cadastrados");
		menClientesCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Gerando Relatório de Clientes Cadastrados
				
				int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão do relatório Cliente?", "Atenção", JOptionPane.YES_NO_OPTION);
				
				if(confirma == JOptionPane.YES_OPTION) {
					//Imprimindo o relatório com o JasperReport
					try {
						//Usando a classe JasperPrint para preparar a impressão do relatório
						JasperPrint print = JasperFillManager.fillReport("Dependencias/Relatorios/cliente.jasper",null,conexao);
						//A classe a baixo exibe o relatório através da classe JasperView
						JasperViewer.viewReport(print, false);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		});
		menuRelatorios.add(menClientesCad);
		
		JMenuItem menuOsGeradas = new JMenuItem("OS Geradas");
		menuOsGeradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Gerando Relatório de Ordens de Serviços Geradas
				
				int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Emissão do relatório OS Geradas?", "Atenção", JOptionPane.YES_NO_OPTION);
				
				if(confirma == JOptionPane.YES_OPTION) {
					//Imprimindo o relatório com o JasperReport
					try {
						//Usando a classe JasperPrint para preparar a impressão do relatório
						JasperPrint print = JasperFillManager.fillReport("Dependencias/Relatorios/servicos.jasper",null,conexao);
						//A classe a baixo exibe o relatório através da classe JasperView
						JasperViewer.viewReport(print, false);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
				
			}
		});
		menuRelatorios.add(menuOsGeradas);
		
		JMenuItem menuOsPendentes = new JMenuItem("OS Pendentes");
		menuRelatorios.add(menuOsPendentes);
		
		JMenuItem menuOsExecutadas = new JMenuItem("OS Executadas");
		menuRelatorios.add(menuOsExecutadas);
		
		JMenuItem menuFornecCad = new JMenuItem("Fornecedores Cadastrados");
		menuRelatorios.add(menuFornecCad);
		
		JMenuItem menuEstoque = new JMenuItem("Estoque");
		menuRelatorios.add(menuEstoque);
		
		JMenuItem menuServios_1 = new JMenuItem("Servi\u00E7os");
		menuRelatorios.add(menuServios_1);
		
		JMenu menuAjuda = new JMenu("Ajuda");
		menuBar.add(menuAjuda);
		
		JMenuItem menuSobre = new JMenuItem("Sobre");
		menuSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaSobre sobre = new TelaSobre();
				sobre.setVisible(true);
				
			}
		});
		menuAjuda.add(menuSobre);
		
		JMenuItem menuSuporte = new JMenuItem("Suporte");
		menuSuporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Chamando a tela de suporte
				TelaSuporte suporte = new TelaSuporte();
				suporte.setVisible(true);
			}
		});
		menuAjuda.add(menuSuporte);
		
		JMenuItem menuManual = new JMenuItem("Manual");
		menuManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Chamando a tela Manual do Sistema
				
				TelaManual manual = new TelaManual();
				manual.setVisible(true);
				
			}
		});
		menuAjuda.add(menuManual);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_Lateral = new JPanel();
		panel_Lateral.setBackground(SystemColor.activeCaption);
		panel_Lateral.setBounds(591, 0, 193, 423);
		panel.add(panel_Lateral);
		panel_Lateral.setLayout(null);
		
		JLabel lblUsuIcon = new JLabel("Usu\u00E1rio");
		lblUsuIcon.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/com/servos/imgs/userBoy_48x48.png")));
		lblUsuIcon.setBounds(10, 46, 50, 55);
		panel_Lateral.add(lblUsuIcon);
		
		JLabel lblDataIcon = new JLabel("Data:");
		lblDataIcon.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/com/servos/imgs/calendar_48x48.png")));
		lblDataIcon.setBounds(10, 120, 50, 55);
		panel_Lateral.add(lblDataIcon);
		
		JLabel lblUsuario = new JLabel("usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(70, 69, 110, 14);
		panel_Lateral.add(lblUsuario);
		
		lblData.setBounds(70, 140, 80, 20);
		panel_Lateral.add(lblData);
		
		JLabel lblDesenvolvidoPoritam = new JLabel("Desenvolvedores:");
		lblDesenvolvidoPoritam.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblDesenvolvidoPoritam.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesenvolvidoPoritam.setBounds(0, 374, 83, 23);
		panel_Lateral.add(lblDesenvolvidoPoritam);
		
		JLabel lblItamCleverton = new JLabel("Itam\u00E1 & Cleverton");
		lblItamCleverton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblItamCleverton.setBounds(88, 378, 90, 14);
		panel_Lateral.add(lblItamCleverton);
		
		JLabel lblLogo = new JLabel("Logo");
		lblLogo.setBounds(58, 258, 46, 14);
		panel_Lateral.add(lblLogo);
		
		JLabel lblLogoCentral = new JLabel("");
		lblLogoCentral.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/com/servos/imgs/servos_01.PNG")));
		lblLogoCentral.setBounds(77, 141, 437, 271);
		panel.add(lblLogoCentral);
		
		this.setLocationRelativeTo(null);
		
		int nivel = usuarioDao.nivel_Acesso;
		String nome = usuarioDao.nomeUsuario;
		
		//System.out.println(nome);
		
		//System.out.println("O nível é: " + nivel);
		
		if(nivel == 1) {
			//System.out.println("Usuário ADMIN");
			menCadEmpresa.setEnabled(true);
			menCadFuncionario.setEnabled(true);
			menCadServios.setEnabled(true);
			menCadUsuario.setEnabled(true);
			lblUsuario.setText("Olá " + nome);
			lblUsuario.setForeground(Color.red);
		}else {
			//System.out.println("Usuário Comun");
			lblUsuario.setText("Olá " + nome);
		}
		
		
	}
}
