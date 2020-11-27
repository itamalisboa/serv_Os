package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Produto;
import controller.ProdutoDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodProduto;
	private JTextField txtQtd;
	private JTextField txtPesquisar;
	private JTextField txtUniMed;
	private JTextField txtCodFornec;
	private JTextField txtPrecoProd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadProduto frame = new CadProduto();
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
	public CadProduto() {
		setTitle("Cadastrar Produtos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//CENTRALIZANDO A TELA
		this.setLocationRelativeTo(null);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(10, 11, 59, 14);
		contentPane.add(lblCodigo);
		
		txtCodProduto = new JTextField();
		txtCodProduto.setBounds(66, 8, 66, 20);
		contentPane.add(txtCodProduto);
		txtCodProduto.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(10, 165, 89, 14);
		contentPane.add(lblDescrio);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(196, 109, 89, 14);
		contentPane.add(lblQuantidade);
		
		txtQtd = new JTextField();
		txtQtd.setBounds(270, 106, 96, 20);
		contentPane.add(txtQtd);
		txtQtd.setColumns(10);
		
		JTextArea textAreaDescricao = new JTextArea();
		textAreaDescricao.setBounds(10, 190, 414, 81);
		contentPane.add(textAreaDescricao);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				//PEGANDO OS DADOS E SETANDO NOS ATRIBUTOS DA CLASSE PRODUTO
				
				Produto produto = new Produto();
				produto.setId_produto(txtCodProduto.getText());
				produto.setUniMed(txtUniMed.getText());
				produto.setId_fornec(txtCodFornec.getText());
				produto.setPrecoProd(Float.parseFloat(txtPrecoProd.getText()));
				produto.setDescricao(textAreaDescricao.getText());
				produto.setQuantidade(Integer.parseInt(txtQtd.getText()));
				
				
				if(txtCodFornec.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe o Código do Fornecedor!!!");
				}else if(txtQtd.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Informe a Quantidade do Produto!!!");
					}else if(textAreaDescricao.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Informe a Descrição do Produto!!!");
				}else {
					ProdutoDAO prod = new ProdutoDAO();
					
					prod.InserirProduto(produto);
					
				
				//LIMPANDO OS CAMPOS
				txtCodProduto.setText(null);
				textAreaDescricao.setText(null);
				txtQtd.setText(null);
				txtCodFornec.setText(null);
				txtPrecoProd.setText(null);
				txtUniMed.setText(null);
				txtPesquisar.setText(null);
				
				//FIM LIMPAR CAMPOS
					
					JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso!!!");
				}
				
			}
		});
		btnCadastrar.setBounds(148, 291, 96, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
 
				
				//PEGANDO OS DADOS E SETANDO NOS ATRIBUTOS DA CLASSE PRODUTO
				
				Produto produto = new Produto();
				produto.setId_produto(txtCodProduto.getText());
				produto.setDescricao(textAreaDescricao.getText());
				produto.setQuantidade(Integer.parseInt(txtQtd.getText()));
				produto.setUniMed(txtUniMed.getText());
				produto.setId_fornec(txtCodFornec.getText());
				produto.setPrecoProd(Float.parseFloat(txtPrecoProd.getText()));
				
				//ALTERANDO OS DADOS DO PRODUTO
				
				try {
					if(txtCodProduto.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Nenhum produto pesquisado, busque primeiro um produto!!!");
					}else {
						ProdutoDAO dao = new ProdutoDAO();
						dao.Atualizar(produto);
						
						//LIMPANDO OS CAMPOS
						txtCodProduto.setText(null);
						textAreaDescricao.setText(null);
						txtQtd.setText(null);
						txtCodFornec.setText(null);
						txtPrecoProd.setText(null);
						txtUniMed.setText(null);
						txtPesquisar.setText(null);
						
						//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtCodFornec.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Alteração Realizada com sucesso!!!");
					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnAlterar.setBounds(245, 291, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//EXCLUINDO OS DADOS DO USUÁRIO
				
				Produto produto = new Produto();
				produto.setId_produto(txtCodProduto.getText());
				
				//ALTERANDO OS DADOS DO PRODUTO
				
				try {
					if(txtCodProduto.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Nenhum produto pesquisado, busque primeiro um produto!!!");
					}else {
						ProdutoDAO dao = new ProdutoDAO();
						dao.DeletarProduto(produto);
						
						//LIMPANDO OS CAMPOS
						txtCodProduto.setText(null);
						textAreaDescricao.setText(null);
						txtQtd.setText(null);
						txtCodFornec.setText(null);
						txtPrecoProd.setText(null);
						txtUniMed.setText(null);
						txtPesquisar.setText(null);
						
						//FIM LIMPAR CAMPOS
					//RETORNANDO O FOCU NO CAMPO RAZÃO SOCIAL
					
					txtCodFornec.requestFocus();
					
					JOptionPane.showMessageDialog(null, "Exclusão Realizada com sucesso!!!");
					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnExcluir.setBounds(335, 291, 89, 23);
		contentPane.add(btnExcluir);
		
		txtPesquisar = new JTextField();
		txtPesquisar.setBounds(10, 39, 304, 20);
		contentPane.add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//INSTANCIANDO UM OBJETO PARA PEGAR O CPF DO CLINTE PARA PODER FAZER A BUSCA NO BD	
				
				Produto produto = new Produto();
				produto.setId_produto(txtPesquisar.getText());
				
				//FAZENDO A CONEXÃO COM O BANCO E CHAMANDO O MÉTODO QUE RETORNARÁ A CONSULTA COM OS DADOS
				
				ProdutoDAO dao = new ProdutoDAO();
				dao.BuscarProtudo(produto);
				txtCodProduto.setText(produto.getId_produto());
				textAreaDescricao.setText(produto.getDescricao());
				txtQtd.setText(Integer.toString(produto.getQuantidade()));
				txtCodFornec.setText(produto.getId_fornec());
				txtPrecoProd.setText(Float.toString(produto.getPrecoProd()));
				txtUniMed.setText(produto.getUniMed());
				
				
			}
		});
		btnPesquisar.setBounds(322, 38, 100, 23);
		contentPane.add(btnPesquisar);
		
		JLabel lblUnidMedida = new JLabel("Unid. Medida");
		lblUnidMedida.setBounds(10, 109, 74, 14);
		contentPane.add(lblUnidMedida);
		
		txtUniMed = new JTextField();
		txtUniMed.setBounds(90, 106, 96, 20);
		contentPane.add(txtUniMed);
		txtUniMed.setColumns(10);
		
		JLabel lblCodFornecedor = new JLabel("Cod. Fornecedor");
		lblCodFornecedor.setBounds(10, 81, 100, 14);
		contentPane.add(lblCodFornecedor);
		
		txtCodFornec = new JTextField();
		txtCodFornec.setBounds(110, 78, 76, 20);
		contentPane.add(txtCodFornec);
		txtCodFornec.setColumns(10);
		
		JLabel lblPreoUni = new JLabel("Pre\u00E7o Unit.:");
		lblPreoUni.setBounds(10, 137, 66, 14);
		contentPane.add(lblPreoUni);
		
		txtPrecoProd = new JTextField();
		txtPrecoProd.setBounds(90, 134, 96, 20);
		contentPane.add(txtPrecoProd);
		txtPrecoProd.setColumns(10);
	}
}
