package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing

import br.ufac.academico.db.*;
import br.ufac.academico.entity.Centro;
import br.ufac.academico.entity.Professor;
import br.ufac.academico.logic.*;

import java.awt.*; 						//importando classes do AWT
import java.awt.event.*; 				//importando classes de EVENTOS do AWT
import java.sql.*;						//importando classes do JDBC
import java.util.List;						//importando classes do JDBC
import java.util.ArrayList;						//importando classes do JDBC

class ProfessorConsulta extends JFrame {

	private Conexao cnx = null;
	Academico pai;
	ProfessorLogic pl;

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

	ProfessorConsulta(JFrame framePai, Conexao conexao){ // método construtor
		super("Consulta de Professor"); // chamando construtor da classe mãe
		setSize(800, 400);				// definindo dimensões da janela
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		pai = (Academico) framePai;		
		cnx = conexao;
		pl = new ProfessorLogic(cnx);

		professorCadastro = new ProfessorCadastro(this, cnx);

		tblQuery = new JTable(0,0);
		tblQuery.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblQuery.addMouseListener(new HabilitarEdicaoExclusao());

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
		btnEditar.setEnabled(false);
		btnExcluir = new JButton(actExcluir);
		btnExcluir.setEnabled(false);

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
					"Buscar registros de Professores!");
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
					"Incluir registro de Centro!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/New24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			professorCadastro.incluir();

		}

	}

	class AcaoEditar extends AbstractAction{

		AcaoEditar(){
			super("Editar");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, 
					"Editar registro de Centro!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Edit24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {

//			String sigla;
//			sigla = (String) tblQuery.getValueAt(tblQuery.getSelectedRow(), 0);
//			centroCadastro.editar(sigla);

		}

	}

	class AcaoExcluir extends AbstractAction{

		AcaoExcluir(){
			super("Excluir");
			putValue(MNEMONIC_KEY, KeyEvent.VK_X);
			putValue(SHORT_DESCRIPTION, 
					"Excluir registro de Centro!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Delete24.gif"));

		}

		// PATRICAMENTE IGUAL AO DA AcaoEditar
		@Override
		public void actionPerformed(ActionEvent e) {

//			String sigla;
//			sigla = (String) tblQuery.getValueAt(tblQuery.getSelectedRow(), 0);
//			centroCadastro.excluir(sigla);

		}

	}	

	class AcaoSair extends AbstractAction{

		AcaoSair(){
			super("Sair");
			putValue(MNEMONIC_KEY, KeyEvent.VK_R);
			putValue(SHORT_DESCRIPTION, 
					"Fecha consulta de professores!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Stop24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			ProfessorConsulta.this.setVisible(false);
			pai.setVisible(true);

		}

	}

	public void buscar() {

		List<Professor> professores = new ArrayList<Professor>();
		int matricula;
		
		if(fldValor.getText().equals("")) {
			professores = pl.getProfessores();
		}else{
			switch (cmbChaves.getSelectedIndex()) {
			case 0:
				matricula = Integer.parseInt(fldValor.getText());
				professores.add(pl.getProfessor(matricula));
				break;
			case 1:
				professores = pl.getProfessoresPorNome(fldValor.getText());
				break;
			}

		}

		tblQuery.setModel(new ProfessorTableModel(professores));
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);

	}
	
	class HabilitarEdicaoExclusao extends MouseAdapter {
		
		public void mousePressed(MouseEvent e) {
			if (tblQuery.getSelectedRow() >= 0) {
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
			}else {
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
			}
				
		}
		
	}	

}//Fim da classe ProfessorConsulta



