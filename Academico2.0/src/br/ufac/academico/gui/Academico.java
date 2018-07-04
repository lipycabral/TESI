package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing

import br.ufac.academico.db.Conexao;

import java.awt.event.*; 				//importando classes de EVENTOS do AWT

class Academico extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Conexao cnx = null;

	AcaoCentro actCentro = new AcaoCentro();	
	AcaoProfessor actProfessor = new AcaoProfessor();	
	AcaoSair actSair = new AcaoSair();	

	static final String imagesPath = new String("images/");
	
	JMenuBar mbOpcoes;
	JMenu mnCadastro;

	Academico(Conexao conexao){ // método construtor
		super("Sistema de Controle Acadêmico"); // chamando construtor da classe mãe
		setSize(800, 400);				// definindo dimensões da janela
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		cnx = conexao;

		mbOpcoes = new JMenuBar();
		
		mnCadastro = new JMenu("Cadastro");
		mnCadastro.setMnemonic(KeyEvent.VK_D);
		
		mnCadastro.add(actCentro);
		mnCadastro.add(actProfessor);
		mnCadastro.addSeparator();
		mnCadastro.add(actSair);
		
		mbOpcoes.add(mnCadastro);

		setJMenuBar(mbOpcoes);
		
	} //Fim do método construtor

	class AcaoCentro extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		AcaoCentro(){
			super("Centro");
			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
			putValue(SHORT_DESCRIPTION, 
					"Gerenciar Centros");

		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}
	
	class AcaoProfessor extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		AcaoProfessor(){
			super("Professor");
			putValue(MNEMONIC_KEY, KeyEvent.VK_P);
			putValue(SHORT_DESCRIPTION, 
					"Gerenciar Professores");

		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}
	
	class AcaoSair extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		AcaoSair(){
			super("Sair");
			putValue(MNEMONIC_KEY, KeyEvent.VK_R);
			putValue(SHORT_DESCRIPTION, 
					"Sair da aplicação!");
//			putValue(SMALL_ICON, 
//					new ImageIcon(imagesPath+"general/Stop24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			cnx.desconecte();
			System.exit(0);
		}
	}

}//Fim da classe ProfessorConsulta



