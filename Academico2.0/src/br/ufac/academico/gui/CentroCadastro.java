package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing

import br.ufac.academico.db.*;
import br.ufac.academico.entity.Centro;
import br.ufac.academico.logic.*;

import java.awt.*; 						//importando classes do AWT
import java.awt.event.*; 				//importando classes de EVENTOS do AWT
import java.sql.*;						//importando classes do JDBC

class CentroCadastro extends JFrame {

	private final int INCLUSAO = 0;
	private final int EDICAO = 1;
	private final int EXCLUSAO = 2;

	private int acao, numeroDeCentros;
	private String[] idCentros;

	private CentroConsulta pai;
	private Conexao cnx;
	private ResultSet rs;
	private CentroLogic cl;

	private JPanel pnlControles, pnlOperacoes, pnlRotulos, pnlCampos;
	private JComboBox cmbCentro;
	private JTextField fldSigla, fldNome;
	private JButton btnConfirmar, btnCancelar;

	AcaoConfirmar actConfirmar = new AcaoConfirmar();
	AcaoCancelar actCancelar = new AcaoCancelar();

	static final String imagesPath = new String("images/");	

	CentroCadastro(JFrame framePai, Conexao conexao){ // método construtor
		super(""); // chamando construtor da classe mãe
		setSize(800, 600);				// definindo dimensões da janela		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		pai = (CentroConsulta)framePai;
		cnx = conexao;
		cl = new CentroLogic(cnx);

		pnlRotulos = new JPanel(new GridLayout(2,1,5,5));
		pnlRotulos.add(new JLabel("Sigla"));
		pnlRotulos.add(new JLabel("Nome"));

		fldSigla = new JTextField();
		fldNome = new JTextField();		

		pnlCampos = new JPanel(new GridLayout(2,1,5,5));
		pnlCampos.add(fldSigla);
		pnlCampos.add(fldNome);

		pnlControles = new JPanel(new BorderLayout(5,5));
		pnlControles.add(pnlRotulos, BorderLayout.WEST);
		pnlControles.add(pnlCampos);

		btnConfirmar = new JButton(actConfirmar);
		btnCancelar = new JButton(actCancelar);

		pnlOperacoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlOperacoes.add(btnConfirmar);
		pnlOperacoes.add(btnCancelar);

		add(pnlControles);
		add(pnlOperacoes, BorderLayout.SOUTH);		

		pack();

	} //Fim do método construtor

	class AcaoConfirmar extends AbstractAction{

		AcaoConfirmar(){
			super("Confirmar");
			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
			putValue(SHORT_DESCRIPTION, 
					"Confirmar operação!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Save24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String strAtualize = "";

			switch (acao) {
			case INCLUSAO:
				cl.addCentro(fldSigla.getText(), fldNome.getText());
				break;
			case EDICAO:
				cl.updCentro(fldSigla.getText(), fldNome.getText());
				break;
			case EXCLUSAO:
				cl.delCentro(fldSigla.getText(), fldNome.getText());
				break;
			}

			limparCampos();
			CentroCadastro.this.setVisible(false);
			pai.setVisible(true);
			pai.buscar();
		}
	}

	class AcaoCancelar extends AbstractAction{

		AcaoCancelar(){
			super("Cancelar");
			putValue(MNEMONIC_KEY, KeyEvent.VK_L);
			putValue(SHORT_DESCRIPTION, 
					"Cancelar operação!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Stop24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			limparCampos();
			CentroCadastro.this.setVisible(false);
			pai.setVisible(true);
			pai.buscar();
		}

	}

	public void incluir() {

		acao = INCLUSAO;
		setTitle("Inclusão de Centro");

		fldSigla.setEnabled(true);
		fldNome.setEnabled(true);

		limparCampos();

		pai.setVisible(false);
		setVisible(true);

	}

	public void editar(String sigla) {

		acao = EDICAO;
		setTitle("Edição de Centro");

		fldSigla.setEnabled(false);
		fldNome.setEnabled(true);

		carregarCampos(sigla);

		pai.setVisible(false);
		setVisible(true);

	}
	
	//PRATICAMENTE IGUAL AO editar
	public void excluir(String sigla) {

		acao = EXCLUSAO;
		setTitle("Exclusão de Centro");

		fldSigla.setEnabled(false);
		fldNome.setEnabled(false);

		carregarCampos(sigla);

		pai.setVisible(false);
		setVisible(true);

	}	
	
	
	public void limparCampos() {

		fldSigla.setText("");
		fldNome.setText("");

	}
	
	public void carregarCampos(String sigla) {

		Centro c = cl.getCentro(sigla);
		
		fldSigla.setText(c.getSigla());
		fldNome.setText(c.getNome());
	
	}
	

}//Fim da classe ProfessorCadastro
