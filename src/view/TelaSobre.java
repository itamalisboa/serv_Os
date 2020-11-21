package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class TelaSobre extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSobre frame = new TelaSobre();
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
	public TelaSobre() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 298);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblServOs = new JLabel("::::.SERV Os.::::");
		lblServOs.setForeground(new Color(255, 0, 0));
		lblServOs.setHorizontalAlignment(SwingConstants.CENTER);
		lblServOs.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblServOs.setBounds(180, 11, 160, 25);
		contentPane.add(lblServOs);
		
		JTextPane txtpnServOs = new JTextPane();
		txtpnServOs.setForeground(Color.WHITE);
		txtpnServOs.setBackground(new Color(32, 178, 170));
		txtpnServOs.setEditable(false);
		txtpnServOs.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnServOs.setText("    Serv Os \u00E9 um sistema para controle de ordem de servi\u00E7os, o mesmo permite o controle e  gerenciamento de estoque, de fornecedores, e um controle b\u00E1sico dos dados dos funcion\u00E1rios.\r\n\r\nDesenvolvedores:\r\n\r\n::: Itam\u00E1 S. Lisboa :::\r\n::: Cleverton :::");
		txtpnServOs.setBounds(10, 70, 489, 140);
		contentPane.add(txtpnServOs);
		
		JLabel lblCopy = new JLabel("\u00A9 Copyright - Todos os Direitos Reservados");
		lblCopy.setForeground(new Color(255, 0, 0));
		lblCopy.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopy.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCopy.setBounds(120, 233, 280, 25);
		contentPane.add(lblCopy);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sobre", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(10, 52, 489, 170);
		contentPane.add(panel);
		
		//Centralizando a janela
		
		this.setLocationRelativeTo(null);
		
	}
}
