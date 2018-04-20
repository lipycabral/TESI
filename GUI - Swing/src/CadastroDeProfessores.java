import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CadastroDeProfessores extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblProfessores;
	private String[] cabecalhos = {"Matricula", "Nome"};
	private Object[][] dados = {
			{1,"Macilon"},
			{2,"Limeira"},
			{3,"Nasserala"},
			{4,"Raoni"},
	};
	public CadastroDeProfessores() {
		this.setTitle("Cadastro de professores");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(300,200);
		
		tblProfessores = new JTable(dados, cabecalhos);
		this.add(new JScrollPane(tblProfessores));
		
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new CadastroDeProfessores();
	}
}
