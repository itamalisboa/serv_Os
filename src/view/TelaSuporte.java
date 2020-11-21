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

public class TelaSuporte extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSuporte frame = new TelaSuporte();
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
	public TelaSuporte() {
		setResizable(false);
		setTitle(":::.Suporte.:::");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 298);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblServOs = new JLabel("::::.Suporte SERV Os.::::");
		lblServOs.setForeground(new Color(255, 0, 0));
		lblServOs.setHorizontalAlignment(SwingConstants.CENTER);
		lblServOs.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblServOs.setBounds(122, 11, 240, 38);
		contentPane.add(lblServOs);
		
		JLabel lblCopy = new JLabel("\u00A9 Copyright - Todos os Direitos Reservados");
		lblCopy.setForeground(new Color(255, 69, 0));
		lblCopy.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopy.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCopy.setBounds(114, 230, 290, 25);
		contentPane.add(lblCopy);
		
		JLabel lblSiteWwwsitecombr = new JLabel("Site: www.site.com.br");
		lblSiteWwwsitecombr.setForeground(Color.WHITE);
		lblSiteWwwsitecombr.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSiteWwwsitecombr.setBounds(62, 84, 250, 14);
		contentPane.add(lblSiteWwwsitecombr);
		
		JLabel lblFone = new JLabel("Fone: (79) 3333-5555");
		lblFone.setForeground(Color.WHITE);
		lblFone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFone.setBounds(62, 117, 250, 14);
		contentPane.add(lblFone);
		
		JLabel lblEmailYtamalisboagmailcom = new JLabel("Email: ytamalisboa@gmail.com");
		lblEmailYtamalisboagmailcom.setForeground(Color.WHITE);
		lblEmailYtamalisboagmailcom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmailYtamalisboagmailcom.setBounds(62, 152, 250, 14);
		contentPane.add(lblEmailYtamalisboagmailcom);
		
		JLabel lblEmailClevertonoliveiragmailcom = new JLabel("Email: clevertonoliveira99@gmail.com");
		lblEmailClevertonoliveiragmailcom.setForeground(Color.WHITE);
		lblEmailClevertonoliveiragmailcom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmailClevertonoliveiragmailcom.setBounds(62, 188, 250, 14);
		contentPane.add(lblEmailClevertonoliveiragmailcom);
		
		JLabel lblSite = new JLabel("");
		lblSite.setHorizontalAlignment(SwingConstants.CENTER);
		lblSite.setIcon(new ImageIcon(TelaSuporte.class.getResource("/br/com/servos/imgs/site_32x32.png")));
		lblSite.setBounds(10, 75, 46, 35);
		contentPane.add(lblSite);
		
		JLabel lblFoneIcon = new JLabel("");
		lblFoneIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoneIcon.setIcon(new ImageIcon(TelaSuporte.class.getResource("/br/com/servos/imgs/phone_32x32.png")));
		lblFoneIcon.setBounds(10, 108, 46, 35);
		contentPane.add(lblFoneIcon);
		
		JLabel lblEmailIcon1 = new JLabel("");
		lblEmailIcon1.setIcon(new ImageIcon(TelaSuporte.class.getResource("/br/com/servos/imgs/email_32x32.png")));
		lblEmailIcon1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailIcon1.setBounds(10, 142, 46, 35);
		contentPane.add(lblEmailIcon1);
		
		JLabel lblEmailIcon2 = new JLabel("");
		lblEmailIcon2.setIcon(new ImageIcon(TelaSuporte.class.getResource("/br/com/servos/imgs/email_32x32.png")));
		lblEmailIcon2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailIcon2.setBounds(10, 176, 46, 35);
		contentPane.add(lblEmailIcon2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setForeground(new Color(0, 191, 255));
		panel.setBorder(new TitledBorder(null, "Contato", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(10, 60, 489, 160);
		contentPane.add(panel);
		
		//Abrindo a tela centralizada
		
		this.setLocationRelativeTo(null);
		
	}
}
