package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;

import java.sql.*;
import controller.ConexaoDao;
import controller.usuarioDao;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public void logar() {
		//String sql = "select * from usuario where \"Login_Usu\" = ? and \"Senha_Usu\" = ?;";
		String sql = "select * from usuarios where login_usu = ? and senha_usu = ?;";
		
		
		//Pegando os dados  dos campos para efetuar consulta no BD;
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtLogin.getText());
			pst.setString(2, txtSenha.getText());
			
			//Executando a query/consulta
			
			rs = pst.executeQuery();
			//Se existir usuario e senha compatíveis:
			if(rs.next()) {
				
				String nivel = rs.getString(4);
				String nomeUsu = rs.getString(2);
				//System.out.println(nivel);
				//System.out.println(nomeUsu);
				
				usuarioDao.nivel_Acesso = Integer.parseInt(nivel);
				usuarioDao.nomeUsuario = nomeUsu;
				
				TelaPrincipal principal = new TelaPrincipal();
				principal.setVisible(true); //Exibindo a tela principal!
				this.dispose(); //Fechando a tela de login!
				conexao.close(); //Fechando a conexão!
			}else {
				JOptionPane.showMessageDialog(null, "Usuário e/ou senha não encontrados!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		
	}

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		
		setTitle(":::.Login.:::");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 402, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setForeground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setBounds(45, 54, 60, 20);
		panel.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(45, 103, 60, 20);
		panel.add(lblSenha);
		
		txtLogin = new JTextField();
		txtLogin.setForeground(new Color(0, 204, 255));
		txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtLogin.setBounds(115, 49, 220, 35);
		panel.add(txtLogin);
		txtLogin.setColumns(10);
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Chamando o método que efetuará o login no BD; 
				
				logar();
			}
		});
		btnLogar.setBackground(new Color(192, 192, 192));
		btnLogar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogar.setBounds(235, 165, 100, 35);
		panel.add(btnLogar);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSenha.setBounds(115, 105, 220, 35);
		panel.add(txtSenha);
		
		JLabel lblStatusbd = new JLabel("");
		lblStatusbd.setIcon(new ImageIcon(TelaLogin.class.getResource("/br/com/servos/imgs/DB_OK.png")));
		lblStatusbd.setBounds(10, 165, 60, 72);
		panel.add(lblStatusbd);
		
		
		conexao = ConexaoDao.conector();
		
		//Exibindo o status da conexão
		//System.out.println(conexao);
		
		if(conexao != null) {
			lblStatusbd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servos/imgs/DB_OK.png")));
		}else {
			lblStatusbd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servos/imgs/DB_ERROR.png")));
		}
		
		this.setLocationRelativeTo(null);
		
	}
}
