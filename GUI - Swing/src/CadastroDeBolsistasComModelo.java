import java.awt.*;
import java.awt.event.*;
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
		setSize(620, 320);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // SOMENTE A PARTIR JDK 1.4
		setResizable(false);

		tmBolsistas = new BolsistaTableModel();
		
		tblBolsistas = new JTable(tmBolsistas);
		tblBolsistas.addMouseListener(new HabilitarExcluir());
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
		
		fldMatricula = new JTextField(3);
		fldNome = new JTextField(20);
		btnSalvar = new JButton(actSalvar);
		btnExcluir = new JButton(actExcluir);
		btnExcluir.setEnabled(false);
		
		
		add(new JScrollPane(tblBolsistas));
		add(pnlControles, BorderLayout.SOUTH);
		
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
			
			//Bolsista bolsista = new Bolsista(Integer.parseInt(fldMatricula.getText()), 
				//	fldNome.getText());

			//tmBolsistas.addBolsista(bolsista);
			
			JOptionPane.showMessageDialog(null, 
					"Professor incluído com sucesso!", 
					"Cadastro de Professores", 
					JOptionPane.INFORMATION_MESSAGE);
			
			fldMatricula.setText("");
			fldNome.setText("");
			fldMatricula.requestFocus();
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
			
			//tmBolsistas.delBolsista(tblBolsistas.getSelectedRow());
			//btnExcluir.setEnabled(false);
			
			JOptionPane.showMessageDialog(null, 
					"Bolsita excluído com sucesso!", 
					"Cadastro de Bolsistas", 
					JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}
	
	class HabilitarExcluir extends MouseAdapter {
	
		public void mousePressed(MouseEvent e) {
			if (tblBolsistas.getSelectedRow() >= 0) {
				btnExcluir.setEnabled(true);
			}else {
				btnExcluir.setEnabled(false);
			}
				
		}
		
	}

}





