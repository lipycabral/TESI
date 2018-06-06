package br.ufac.bsi.tesi;

import javax.swing.*; 					//importando classes do Swing
import java.awt.*; 						//importando classes do AWT
import java.awt.event.*; 				//importando classes de EVENTOS do AWT
import java.sql.*;						//importando classes do JDBC

class ProfessorConsulta extends JFrame {

	private Conexao cnx = null;
	private SQLTableModel tmQuery = null;
	private ResultSet rsQuery = null;

	ProfessorCadastro professorCadastro;

	JTable tblQuery;
	JPanel pnlSuperior, pnlControles, pnlBotoes, pnlOperacoes, pnlRotulos, pnlChaves;
	JComboBox cmbChaves;
	JTextField fldValor;
	JButton btnBuscar, btnSair, btnIncluir, btnEditar, btnExcluir;
	
	AcaoBuscar actBuscar = new AcaoBuscar();
	AcaoIncluir actIncluir = new AcaoIncluir();
	AcaoEditar actEditar = new AcaoEditar();	
	AcaoExcluir actExcluir = new AcaoExcluir();	
	AcaoSair actSair = new AcaoSair();	
	
	static final String imagesPath = new String("images/");	
		

	ProfessorConsulta(Conexao conexao){ // método construtor
		super("Consulta de Professor"); // chamando construtor da classe mãe
		setSize(800, 400);				// definindo dimensões da janela
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		cnx = conexao;

		professorCadastro = new ProfessorCadastro(this, cnx);

		tblQuery = new JTable(0,0);

		pnlRotulos = new JPanel(new GridLayout(2,1,5,5));
		pnlRotulos.add(new JLabel("Buscar por"));
		pnlRotulos.add(new JLabel("Valor"));

		cmbChaves = new JComboBox(new String[] {"Matrícula", "Nome"});
		fldValor = new JTextField();
		
		pnlChaves = new JPanel(new GridLayout(2,1,5,5));
		pnlChaves.add(cmbChaves);
		pnlChaves.add(fldValor);
		
		pnlControles = new JPanel(new BorderLayout(5,5));
		pnlControles.add(pnlRotulos, BorderLayout.WEST);
		pnlControles.add(pnlChaves);
		
		btnBuscar = new JButton(actBuscar);
		btnSair = new JButton(actSair);
		btnIncluir = new JButton(actIncluir);
		btnEditar = new JButton(actEditar);
		btnExcluir = new JButton(actExcluir);

		pnlOperacoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlOperacoes.add(btnIncluir);
		pnlOperacoes.add(btnEditar);
		pnlOperacoes.add(btnExcluir);

		pnlBotoes = new JPanel(new GridLayout(2,1));
		pnlBotoes.add(btnBuscar);
		pnlBotoes.add(btnSair);

		pnlSuperior = new JPanel(new BorderLayout());
		pnlSuperior.add(pnlBotoes, BorderLayout.EAST);
		pnlSuperior.add(pnlControles);

		add(pnlSuperior, BorderLayout.NORTH);
		add(new JScrollPane(tblQuery));
		add(pnlOperacoes, BorderLayout.SOUTH);		

   } //Fim do método construtor

	class AcaoBuscar extends AbstractAction{

		AcaoBuscar(){
			super("Buscar");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
			putValue(SHORT_DESCRIPTION, 
					"Buscar registro de professor!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Search24.gif"));
					
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			buscar();

		}
		
	}

	class AcaoIncluir extends AbstractAction{

		AcaoIncluir(){
			super("Incluir");
			putValue(MNEMONIC_KEY, KeyEvent.VK_I);
			putValue(SHORT_DESCRIPTION, 
					"Incluir registro de professor!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/New24.gif"));
					
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
		

		}
		
	}

	class AcaoEditar extends AbstractAction{

		AcaoEditar(){
			super("Editar");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, 
					"Editar registro de professor!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Edit24.gif"));
					
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
		

		}
		
	}
	
	class AcaoExcluir extends AbstractAction{

		AcaoExcluir(){
			super("Excluir");
			putValue(MNEMONIC_KEY, KeyEvent.VK_X);
			putValue(SHORT_DESCRIPTION, 
					"Excluir registro de professor!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Delete24.gif"));
					
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
		

		}
		
	}	
	
	class AcaoSair extends AbstractAction{

		AcaoSair(){
			super("Sair");
			putValue(MNEMONIC_KEY, KeyEvent.VK_R);
			putValue(SHORT_DESCRIPTION, 
					"Sair da aplicação!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Stop24.gif"));
					
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			cnx.desconecte();
			System.exit(0);
			
		}
		
	}
	public void buscar() {
		String strQuery = "select matricula as 'Matrícula', nome as 'Nome', rg as 'RG', cpf 'CPF' from professores ";
		switch(cmbChaves.getSelectedIndex()) {
		case 0:
			strQuery += "where matricula = "+fldValor.getText()+";";
			break;
		case 1:
			strQuery += "where nome like '%"+fldValor.getText()+"%';";
			break;
		}
		
		tblQuery.setModel(new SQLTableModel(cnx.consulte(strQuery)));
	}

}//Fim da classe ProfessorConsulta



