import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.*;

public class SQLConsultator extends JFrame {
	private Conexao cnx = new Conexao();
	private ResultSet rs;
	private JTable tblQuery;
	private JPanel pnlControles, pnlBotoes;
	private JTextArea taQuery;
	private JButton btnConsultar, btnSair;
	AcaoConsultar actConsultar = new AcaoConsultar();
	AcaoSair actSair = new AcaoSair();	
	static final String imagesPath = new String("images/");	
	public SQLConsultator() {
		setTitle("SQl Consulteitor Tabajara");
		setSize(620, 320);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // SOMENTE A PARTIR JDK 1.4
		
		tblQuery = new JTable(0,0);
		taQuery = new JTextArea();
		
		pnlControles = new JPanel(new BorderLayout());
		pnlBotoes = new JPanel(new GridLayout(2, 1));
		
		btnConsultar = new JButton(actConsultar);
		btnSair = new JButton(actSair);
		
		
		pnlBotoes.add(btnConsultar);
		pnlBotoes.add(btnSair);
		
		pnlControles.add(taQuery);
		pnlControles.add(pnlBotoes, BorderLayout.EAST);
		
		add(new JScrollPane(tblQuery));
		add(pnlControles, BorderLayout.NORTH);
		cnx.conecte("jdbc:mysql://localhost/Academico", "aluno", "aluno");
	}
	class AcaoConsultar extends AbstractAction{
		AcaoConsultar(){
			super("Consultar");
			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
			putValue(SHORT_DESCRIPTION, 
					"Executar consulta!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Search24.gif"));
		}
		public void actionPerformed(ActionEvent e) {
			rs = cnx.consulte(taQuery.getText());
			tblQuery.setModel(new TableModel(rs));
		}
	}
	class AcaoSair extends AbstractAction{
		AcaoSair(){
			super("Sair");
			putValue(MNEMONIC_KEY, KeyEvent.VK_S);
			putValue(SHORT_DESCRIPTION, 
					"Sair do Consultator!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Stop24.gif"));
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			cnx.desconecte();
		}
	}
}