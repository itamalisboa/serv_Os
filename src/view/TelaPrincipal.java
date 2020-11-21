package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;

public class TelaPrincipal extends JFrame {

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
		setResizable(false);
		setTitle("::::..SERV OS..::::");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 483);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuArquivo = new JMenu("Arquivo");
		menuBar.add(menuArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
				
			}
		});
		menuArquivo.add(mntmSair);
		
		JMenu menuCadastros = new JMenu("Cadastros");
		menuBar.add(menuCadastros);
		
		JMenuItem menCadEmpresa = new JMenuItem("Empresa");
		menuCadastros.add(menCadEmpresa);
		
		JMenuItem menCadUsuario = new JMenuItem("Usu\u00E1rio");
		menuCadastros.add(menCadUsuario);
		
		JMenuItem menCadFuncionario = new JMenuItem("Funcion\u00E1rio");
		menuCadastros.add(menCadFuncionario);
		
		JMenuItem menCadCliente = new JMenuItem("Cliente");
		menuCadastros.add(menCadCliente);
		
		JMenuItem menCadFornecedor = new JMenuItem("Fornecedor");
		menuCadastros.add(menCadFornecedor);
		
		JMenuItem menCadProduto = new JMenuItem("Produto");
		menuCadastros.add(menCadProduto);
		
		JMenuItem menCadServios = new JMenuItem("Servi\u00E7os");
		menuCadastros.add(menCadServios);
		
		JMenu menuOrdemDeServico = new JMenu("Ordem de Servi\u00E7o");
		menuBar.add(menuOrdemDeServico);
		
		JMenuItem menuGerarOs = new JMenuItem("Gerar OS");
		menuOrdemDeServico.add(menuGerarOs);
		
		JMenuItem menuConsultarOs = new JMenuItem("Consultar OS");
		menuOrdemDeServico.add(menuConsultarOs);
		
		JMenuItem menuGerenciarOs = new JMenuItem("Gerenciar OS");
		menuOrdemDeServico.add(menuGerenciarOs);
		
		JMenu menuRelatorios = new JMenu("Relat\u00F3rios");
		menuBar.add(menuRelatorios);
		
		JMenuItem menClientesCad = new JMenuItem("Clientes Cadastrados");
		menuRelatorios.add(menClientesCad);
		
		JMenuItem menuOsGeradas = new JMenuItem("OS Geradas");
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
		menuAjuda.add(menuSobre);
		
		JMenuItem menuSuporte = new JMenuItem("Suporte");
		menuAjuda.add(menuSuporte);
		
		JMenuItem menuManual = new JMenuItem("Manual");
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
		panel_Lateral.setBounds(550, 0, 178, 423);
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
		lblUsuario.setBounds(70, 69, 83, 14);
		panel_Lateral.add(lblUsuario);
		
		JLabel lblData = new JLabel("data");
		lblData.setBounds(70, 140, 46, 14);
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
		lblLogoCentral.setBounds(59, 141, 437, 271);
		panel.add(lblLogoCentral);
	}
}
