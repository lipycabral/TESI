package br.ufac.bsi.tesi;

import javax.swing.*; 					//importando classes do Swing
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

	private JPanel pnlControles, pnlOperacoes, pnlRotulos, pnlCampos;
	private JComboBox cmbCentro;
	private JTextField fldMatricula, fldNome, fldRg, fldCpf;
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

		pnlRotulos = new JPanel(new GridLayout(5,1,5,5));
		pnlRotulos.add(new JLabel("Código"));
		pnlRotulos.add(new JLabel("Nome"));
		pnlRotulos.add(new JLabel("RG"));		
		pnlRotulos.add(new JLabel("CPF"));
		pnlRotulos.add(new JLabel("Centro"));		

		fldMatricula = new JTextField();
		fldNome = new JTextField();		
		fldRg = new JTextField();		
		fldCpf = new JTextField();

		rs = cnx.consulte("SELECT sigla, nome FROM centros ORDER BY nome;");
		String[] centros;
		try{					
			rs.last();
			numeroDeCentros = rs.getRow();
			idCentros =  new String[numeroDeCentros];
			centros = new String[numeroDeCentros];
			rs.beforeFirst();
			int i=0;
			while (rs.next()){
				idCentros[i] = rs.getString(1);
				centros[i] = rs.getString(2);
				i++;
			}
		}catch(SQLException sqle){
			centros = new String[] {};
			System.out.printf("Erro: #%d [%s]\n", 
					sqle.getErrorCode(), sqle.getMessage());
		}

		cmbCentro = new JComboBox(centros);

		pnlCampos = new JPanel(new GridLayout(5,1,5,5));
		pnlCampos.add(fldMatricula);
		pnlCampos.add(fldNome);
		pnlCampos.add(fldRg);
		pnlCampos.add(fldCpf);
		pnlCampos.add(cmbCentro);;

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
					new ImageIcon(imagesPath+"general/Search24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {


		}

	}

	class AcaoCancelar extends AbstractAction{

		AcaoCancelar(){
			super("Cancelar");
			putValue(MNEMONIC_KEY, KeyEvent.VK_L);
			putValue(SHORT_DESCRIPTION, 
					"Cancelar operação!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Search24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {


		}

	}

}//Fim da classe ProfessorCadastro



