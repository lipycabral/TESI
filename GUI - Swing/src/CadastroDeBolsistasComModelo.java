import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class CadastroDeBolsistasComModelo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblBolsistas;
	private BolsistaTableModel tmBolsistas;

	private JPanel pnlControles, pnlRotulos, pnlCampos, pnlBotoes, pnlAuxilio, pnlIniciacao, pnlSexo;
	private JTextField fldMatricula, fldNome;
	private JRadioButton rbMasculino, rbFeminino;
	private ButtonGroup bgSexo;
	private JCheckBox chkPasse, chkAlimentacao, chkCreche;
	private JCheckBox chkPibic, chkPivic, chkPibit;
	private JComboBox<String> cmbCurso;
	private JButton btnSalvar, btnExcluir;
	
	AcaoSalvar actSalvar = new AcaoSalvar();
	AcaoExcluir actExcluir = new AcaoExcluir();	
	
	private static final int INCLUINDO = 0;
	private static final int EDITANDO = 1;
	
	private int status = INCLUINDO;
	static final String imagesPath = new String("images/");	
	
	public CadastroDeBolsistasComModelo() {
		
		setTitle("Cadastro de Bolsistas");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // SOMENTE A PARTIR JDK 1.4
		setResizable(false);

		tmBolsistas = new BolsistaTableModel();
		
		tblBolsistas = new JTable(tmBolsistas);
		tblBolsistas.addMouseListener(new Habilitar());
		tblBolsistas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		pnlControles = new JPanel(new BorderLayout());
		pnlRotulos = new JPanel(new GridLayout(6, 1));
		pnlCampos = new JPanel(new GridLayout(6, 1));
		pnlSexo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlAuxilio = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlIniciacao = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlBotoes = new JPanel();
		
		rbMasculino = new JRadioButton("Masculino");
		rbFeminino = new JRadioButton("Feminino");
		bgSexo = new ButtonGroup();
		bgSexo.add(rbMasculino);
		bgSexo.add(rbFeminino);
		pnlSexo.add(rbMasculino);
		pnlSexo.add(rbFeminino);
		
		chkPasse = new JCheckBox("Passe Livre");
		chkAlimentacao = new JCheckBox("Alimentação");;
		chkCreche = new JCheckBox("Creche");
		pnlAuxilio.add(chkPasse);
		pnlAuxilio.add(chkAlimentacao);
		pnlAuxilio.add(chkCreche);
		
		chkPibic = new JCheckBox("PIBIC");
		chkPivic = new JCheckBox("PIVIC");
		chkPibit = new JCheckBox("PIBIT");
		pnlIniciacao.add(chkPibic);
		pnlIniciacao.add(chkPivic);
		pnlIniciacao.add(chkPibit);
		
		
		cmbCurso = new JComboBox<String>(new String[] {"Sistemas de informação", "Direito", "Esquerdo"});
		
		pnlRotulos.add(new JLabel("Matricula"));
		pnlRotulos.add(new JLabel("Nome"));
		pnlRotulos.add(new JLabel("Sexo"));
		pnlRotulos.add(new JLabel("Auxilio"));
		pnlRotulos.add(new JLabel("Iniciação"));
		pnlRotulos.add(new JLabel("Curso"));
		
		fldMatricula = new JTextField();
		fldNome = new JTextField();
		
		pnlCampos.add(fldMatricula);
		pnlCampos.add(fldNome);
		pnlCampos.add(pnlSexo);
		pnlCampos.add(pnlAuxilio);
		pnlCampos.add(pnlIniciacao);
		pnlCampos.add(cmbCurso);
		
		btnSalvar = new JButton(actSalvar);
		btnExcluir = new JButton(actExcluir);
		
		pnlBotoes.add(btnSalvar);
		pnlBotoes.add(btnExcluir);
		
		pnlControles.add(pnlRotulos, BorderLayout.WEST);
		pnlControles.add(pnlCampos);
		pnlControles.add(pnlBotoes, BorderLayout.SOUTH);
		
		
		btnExcluir.setEnabled(false);
		
		
		add(new JScrollPane(tblBolsistas));
		add(pnlControles, BorderLayout.SOUTH);
		
	}
	
	public void limparCampos() {
		fldMatricula.setText("");
		fldNome.setText("");
		bgSexo.clearSelection();
		chkPasse.setSelected(false);
		chkAlimentacao.setSelected(false);
		chkCreche.setSelected(false);
		chkPibic.setSelected(false);
		chkPivic.setSelected(false);
		chkPibit.setSelected(false);
		cmbCurso.setSelectedIndex(0);
	}
	private Bolsista DescarregarCampos() {
		Bolsista bolsista;
		
		long matricula;
		
		String nome, sexo = null, auxilio, iniciacao, curso;
		
		ArrayList<String> lAuxilio, lIniciacao;
		
		matricula = Long.parseLong(fldMatricula.getText());
		
		nome = fldNome.getText();
		
		if(rbMasculino.isSelected()){
	         sexo = "Masculino";
		}
		if(rbFeminino.isSelected()){
		     sexo = "Feminino";
		}
		
		lAuxilio = new ArrayList<String>();
		if(chkPasse.isSelected())
			lAuxilio.add(chkPasse.getText());
		if(chkAlimentacao.isSelected())
			lAuxilio.add(chkAlimentacao.getText());
		if(chkCreche.isSelected())
			lAuxilio.add(chkCreche.getText());
		
		auxilio = lAuxilio.toString();
		auxilio = auxilio.substring(1, auxilio.length()-1);
		
		lIniciacao = new ArrayList<String>();
		if(chkPibic.isSelected())
			lIniciacao.add(chkPibic.getText());
		if(chkPivic.isSelected())
			lIniciacao.add(chkPivic.getText());
		if(chkPibit.isSelected())
			lIniciacao.add(chkPibit.getText());
		
		iniciacao = lIniciacao.toString();
		iniciacao = iniciacao.substring(1, iniciacao.length()-1);
		
		curso = cmbCurso.getSelectedItem().toString();
		
		bolsista = new Bolsista(matricula, nome, sexo, auxilio, iniciacao, curso);
		
		return bolsista;
	}
	private void CarregarCampos(Bolsista bolsista) {
		
		fldMatricula.setText(bolsista.getMatricula()+"");
		
		fldNome.setText(bolsista.getNome());
		
		if(bolsista.getSexo().equals("Masculino")) 
			rbMasculino.setSelected(true);
		else
			rbFeminino.setSelected(true);
		
		chkPasse.setSelected(bolsista.getAuxilio().contains(chkPasse.getText()));
		chkAlimentacao.setSelected(bolsista.getAuxilio().contains(chkAlimentacao.getText()));
		chkCreche.setSelected(bolsista.getAuxilio().contains(chkCreche.getText()));
		chkPibic.setSelected(bolsista.getIniciacao().contains(chkPibic.getText()));
		chkPibit.setSelected(bolsista.getIniciacao().contains(chkPibit.getText()));
		chkPivic.setSelected(bolsista.getIniciacao().contains(chkPivic.getText()));
		
		cmbCurso.setSelectedItem(bolsista.getCurso());
		
		
	}
	
	class AcaoSalvar extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		AcaoSalvar(){
			super("Salvar");
			putValue(MNEMONIC_KEY, KeyEvent.VK_S);
			putValue(SHORT_DESCRIPTION, 
					"Salvar dados do bolsista!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Save16.gif"));
					
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Bolsista bolsista = DescarregarCampos();
			if(status==INCLUINDO) {
				tmBolsistas.addBolsista(bolsista);
				JOptionPane.showMessageDialog(null, 
						"Bolsista incluído com sucesso!", 
						"Cadastro de Bolsistas", 
						JOptionPane.INFORMATION_MESSAGE);
			}
			if(status==EDITANDO) {
				tmBolsistas.updBolsista(bolsista, tblBolsistas.getSelectedRow());
				JOptionPane.showMessageDialog(null, 
						"Bolsista editado com sucesso!", 
						"Cadastro de Bolsistas", 
						JOptionPane.INFORMATION_MESSAGE);
			}
			
			
			limparCampos();
			fldMatricula.requestFocus();
			status = INCLUINDO;
			btnExcluir.setEnabled(false);
		}
		
	}
	
	class AcaoExcluir extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		AcaoExcluir(){
			super("Excluir");
			putValue(MNEMONIC_KEY, KeyEvent.VK_X);
			putValue(SHORT_DESCRIPTION, 
					"Excluir o bolsista selecionado na Tabela!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Delete16.gif"));
					
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			tmBolsistas.delBolsista(tblBolsistas.getSelectedRow());
			btnExcluir.setEnabled(false);
			limparCampos();
			JOptionPane.showMessageDialog(null, 
					"Bolsita excluído com sucesso!", 
					"Cadastro de Bolsistas", 
					JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}
	
	class Habilitar extends MouseAdapter {
	
		public void mousePressed(MouseEvent e) {
			if (tblBolsistas.getSelectedRow() >= 0) {
				status = EDITANDO;
				Bolsista bolsista = tmBolsistas.getBolsista(tblBolsistas.getSelectedRow());
				CarregarCampos(bolsista);
				btnExcluir.setEnabled(true);
			}else {
				btnExcluir.setEnabled(false);
			}
				
		}
		
	}

}





