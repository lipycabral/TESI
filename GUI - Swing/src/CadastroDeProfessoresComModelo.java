import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CadastroDeProfessoresComModelo extends JFrame {

	private JTable tblProfessores;
	private TableModel tmProfessores;

	private JPanel pnlControles;
	private JTextField fldMatricula, fldNome;
	private JButton btnIncluir, btnExcluir;
	
	AcaoIncluir actIncluir = new AcaoIncluir();
	AcaoExcluir actExcluir = new AcaoExcluir();	
	
	static final String imagesPath = new String("images/");	
	
	public CadastroDeProfessoresComModelo() {
		
		setTitle("Cadastro de Professores");
		setSize(620, 320);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // SOMENTE A PARTIR JDK 1.4
		//setResizable(false);

		tmProfessores = new TableModel();
		
		tblProfessores = new JTable(tmProfessores);
		tblProfessores.addMouseListener(new HabilitarExcluir());
		tblProfessores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		pnlControles = new JPanel();
		fldMatricula = new JTextField(3);
		fldNome = new JTextField(20);
		btnIncluir = new JButton(actIncluir);
		btnExcluir = new JButton(actExcluir);
		btnExcluir.setEnabled(false);
		
		pnlControles.add(new JLabel("Matrícula: "));
		pnlControles.add(fldMatricula);
		pnlControles.add(new JLabel("Nome: "));
		pnlControles.add(fldNome);
		pnlControles.add(btnIncluir);
		pnlControles.add(btnExcluir);
		
		add(new JScrollPane(tblProfessores));
		add(pnlControles, BorderLayout.SOUTH);
		
	}
	
	class AcaoIncluir extends AbstractAction{

		AcaoIncluir(){
			super("Incluir");
			putValue(MNEMONIC_KEY, KeyEvent.VK_I);
			putValue(SHORT_DESCRIPTION, 
					"Incluir um professor na Tabela!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/New16.gif"));
					
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Professor professor = new Professor(Integer.parseInt(fldMatricula.getText()), 
					fldNome.getText());

			tmProfessores.addProfessor(professor);
			
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

		AcaoExcluir(){
			super("Excluir");
			putValue(MNEMONIC_KEY, KeyEvent.VK_X);
			putValue(SHORT_DESCRIPTION, 
					"Exluir o professor selecionado na Tabela!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Delete16.gif"));
					
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			tmProfessores.removeProfessor(tblProfessores.getSelectedRow());
			btnExcluir.setEnabled(false);
			
			JOptionPane.showMessageDialog(null, 
					"Professor excluído com sucesso!", 
					"Cadastro de Professores", 
					JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}
	
	class HabilitarExcluir extends MouseAdapter {
	
		public void mousePressed(MouseEvent e) {
			if (tblProfessores.getSelectedRow() >= 0) {
				btnExcluir.setEnabled(true);
			}else {
				btnExcluir.setEnabled(false);
			}
				
		}
		
	}

}





