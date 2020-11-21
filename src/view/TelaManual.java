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
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class TelaManual extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaManual frame = new TelaManual();
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
	public TelaManual() {
		setResizable(false);
		setTitle(":::.Manual.:::");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 255);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblServOs = new JLabel("::::.Manual SERV Os.::::");
		lblServOs.setForeground(new Color(255, 0, 0));
		lblServOs.setHorizontalAlignment(SwingConstants.CENTER);
		lblServOs.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblServOs.setBounds(122, 11, 240, 38);
		contentPane.add(lblServOs);
		
		JLabel lblCopy = new JLabel("\u00A9 Copyright - Todos os Direitos Reservados");
		lblCopy.setForeground(new Color(255, 69, 0));
		lblCopy.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopy.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCopy.setBounds(119, 191, 290, 25);
		contentPane.add(lblCopy);
		
		JLabel lblSiteWwwsitecombr = new JLabel("Acessar Manual do Sistema");
		lblSiteWwwsitecombr.setForeground(Color.WHITE);
		lblSiteWwwsitecombr.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSiteWwwsitecombr.setBounds(62, 84, 250, 14);
		contentPane.add(lblSiteWwwsitecombr);
		
		JLabel lblFone = new JLabel("Obs.: Em caso de d\u00FAvidas t\u00E9cnicas acesse a aba Suporte e fale conosco!");
		lblFone.setForeground(Color.WHITE);
		lblFone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFone.setBounds(62, 119, 421, 14);
		contentPane.add(lblFone);
		
		JLabel lblSite = new JLabel("");
		lblSite.setHorizontalAlignment(SwingConstants.CENTER);
		lblSite.setIcon(new ImageIcon(TelaManual.class.getResource("/br/com/servos/imgs/site_32x32.png")));
		lblSite.setBounds(10, 75, 46, 35);
		contentPane.add(lblSite);
		
		JLabel lblFoneIcon = new JLabel("");
		lblFoneIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoneIcon.setIcon(new ImageIcon(TelaManual.class.getResource("/br/com/servos/imgs/phone_32x32.png")));
		lblFoneIcon.setBounds(10, 112, 46, 35);
		contentPane.add(lblFoneIcon);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setForeground(new Color(0, 191, 255));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Manual do Sistema", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 60, 489, 109);
		contentPane.add(panel);
		
		//Abrindo a tela centralizada
		
		this.setLocationRelativeTo(null);
		
	}
}
