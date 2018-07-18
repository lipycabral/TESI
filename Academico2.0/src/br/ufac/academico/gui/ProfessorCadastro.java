package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing

import br.ufac.academico.db.*;
import br.ufac.academico.entity.Centro;
import br.ufac.academico.entity.Professor;
import br.ufac.academico.logic.*;

import java.awt.*; 						//importando classes do AWT
import java.awt.event.*; 				//importando classes de EVENTOS do AWT
import java.sql.*;						//importando classes do JDBC

class ProfessorCadastro extends JFrame {

	private final int INCLUSAO = 0;
	private final int EDICAO = 1;
	private final int EXCLUSAO = 2;

	private int acao, numeroDeCentros;
	private String[] idCentros;

	private ProfessorConsulta pai;
	private Conexao cnx;
	private ResultSet rs;
	private CentroLogic cl;
	private ProfessorLogic pl;

	private JPanel pnlControles, pnlOperacoes, pnlRotulos, pnlCampos;
	private JComboBox cmbCentro;
	private JTextField fldMatricula, fldNome, fldRg, fldCpf, 
		fldEndereco, fldFone;
	private JButton btnConfirmar, btnCancelar;

	AcaoConfirmar actConfirmar = new AcaoConfirmar();
	AcaoCancelar actCancelar = new AcaoCancelar();

	static final String imagesPath = new String("images/");	

	ProfessorCadastro(JFrame framePai, Conexao conexao){ // método construtor
		super(""); // chamando construtor da classe mãe
		setSize(800, 600);				// definindo dimensões da janela		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		pai = (ProfessorConsulta)framePai;
		cnx = conexao;
		cl = new CentroLogic(cnx);
		pl = new ProfessorLogic(cnx);

		pnlRotulos = new JPanel(new GridLayout(7,1,5,5));
		pnlRotulos.add(new JLabel("Matrícula"));
		pnlRotulos.add(new JLabel("Nome"));
		pnlRotulos.add(new JLabel("RG"));
		pnlRotulos.add(new JLabel("CPF"));
		pnlRotulos.add(new JLabel("Endereço"));
		pnlRotulos.add(new JLabel("Fone"));
		pnlRotulos.add(new JLabel("Centro"));

		fldMatricula = new JTextField();
		fldNome = new JTextField();		
		fldRg = new JTextField();
		fldCpf = new JTextField();
		fldEndereco = new JTextField();
		fldFone = new JTextField();
		
		cmbCentro = new JComboBox<>(cl.getCentros().toArray());

		pnlCampos = new JPanel(new GridLayout(7,1,5,5));
		pnlCampos.add(fldMatricula);
		pnlCampos.add(fldNome);
		pnlCampos.add(fldRg);
		pnlCampos.add(fldCpf);
		pnlCampos.add(fldEndereco);
		pnlCampos.add(fldFone);
		pnlCampos.add(cmbCentro);

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
			int matricula, rg, cpf;
			String nome, endereco, fone, siglaCentro;
			Centro centro;
			
			matricula = Integer.parseInt(fldMatricula.getText());
			nome = fldNome.getText();
			rg = Integer.parseInt(fldRg.getText());
			cpf = Integer.parseInt(fldCpf.getText());
			endereco = fldEndereco.getText();
			fone = fldFone.getText();
			
			centro = (Centro)cmbCentro.getSelectedItem();
			siglaCentro = centro.getSigla();

			switch (acao) {
			case INCLUSAO:
				pl.addProfessor(matricula, nome, rg, cpf, 
						endereco, fone, siglaCentro);
				break;
			case EDICAO:
				pl.updProfessor(matricula, nome, rg, cpf, endereco, fone, siglaCentro);
				break;
			case EXCLUSAO:
				pl.delProfessor(matricula, nome, rg, cpf, endereco, fone, siglaCentro);
				break;
			}

			limparCampos();
			ProfessorCadastro.this.setVisible(false);
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
			ProfessorCadastro.this.setVisible(false);
			pai.setVisible(true);
			pai.buscar();
		}

	}

	public void incluir() {

		acao = INCLUSAO;
		setTitle("Inclusão de Professor");

		fldMatricula.setEnabled(true);
		fldNome.setEnabled(true);
		fldRg.setEnabled(true);
		fldCpf.setEnabled(true);
		fldEndereco.setEnabled(true);
		fldFone.setEnabled(true);
		cmbCentro.setEnabled(true);

		limparCampos();

		pai.setVisible(false);
		setVisible(true);

	}

	public void editar(int matricula) {

		acao = EDICAO;
		setTitle("Edição de Centro");

		fldMatricula.setEnabled(false);
		fldNome.setEnabled(true);
		fldRg.setEnabled(true);
		fldCpf.setEnabled(true);
		fldEndereco.setEnabled(true);
		fldFone.setEnabled(true);
		cmbCentro.setEnabled(true);

		carregarCampos(matricula);

		pai.setVisible(false);
		setVisible(true);

	}
	
	//PRATICAMENTE IGUAL AO editar
	public void excluir(int matricula) {

		acao = EXCLUSAO;
		setTitle("Exclusão de Centro");

		fldMatricula.setEnabled(false);
		fldNome.setEnabled(false);
		fldRg.setEnabled(false);
		fldCpf.setEnabled(false);
		fldEndereco.setEnabled(false);
		fldFone.setEnabled(false);
		cmbCentro.setEnabled(false);

		carregarCampos(matricula);

		pai.setVisible(false);
		setVisible(true);

	}	
	
	public void limparCampos() {

		fldMatricula.setText("");
		fldNome.setText("");
		fldRg.setText("");
		fldCpf.setText("");
		fldEndereco.setText("");
		fldFone.setText("");
		cmbCentro.setSelectedIndex(0);

	}
	
	public void carregarCampos(int matricula) {

		Professor p = pl.getProfessor(matricula);
		
		fldMatricula.setText(String.valueOf(p.getMatricula()));
		fldNome.setText(p.getNome());
		fldRg.setText(String.valueOf(p.getRg()));
		fldCpf.setText(String.valueOf(p.getCpf()));
		fldEndereco.setText(p.getEndereco());
		fldFone.setText(p.getFone());
		cmbCentro.setSelectedItem(p.getCentro().getNome());
	
	}
	

}//Fim da classe ProfessorCadastro
