import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class JanelaComLayoutCartaoApp extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel p1,p2,p3, pCartas, pBotoes;
	private JButton bPrimeiro, bProximo, bAnterior, bUltimo;
	private JMenuBar mB;
	private JMenu mnCartas, mnAjuda;
	private CardLayout cl;
	static final String imagesPath = new String("images/"); 
	private AcaoPrimeiro actPrimeiro = new AcaoPrimeiro();
	private AcaoUltimo actUltimo = new AcaoUltimo();
	private AcaoAnterior actAnterior = new AcaoAnterior();
	private AcaoProximo actProximo = new AcaoProximo();
	public JanelaComLayoutCartaoApp() {
		this.setTitle("Jogo de cartas");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setSize(500,300);
		
		cl = new CardLayout();
		
		pCartas = new JPanel(cl);
		
		p1 = new JPanel();
		p1.setBackground(Color.BLACK);
		
		p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		
		p3 = new JPanel();
		p3.setBackground(Color.RED);
		
		pCartas.add(p1);
		pCartas.add(p2);
		pCartas.add(p3);
		
		mB = new JMenuBar();
		
		mnCartas = new JMenu("Cartas");
		mnCartas.add(actPrimeiro);
		mnCartas.setMnemonic(KeyEvent.VK_C);
		mnCartas.add(actAnterior);
		mnCartas.addSeparator();
		mnCartas.add(actProximo);
		mnCartas.add(actUltimo);
		mnAjuda = new JMenu("Ajuda");
		
		mnAjuda.add(new JMenuItem("Sobre"));
		mnAjuda.setMnemonic(KeyEvent.VK_A);
		
		mB.add(mnCartas);
		mB.add(mnAjuda);
		
		pBotoes = new JPanel(new GridLayout(1, 4));
		
		bPrimeiro = new JButton(actPrimeiro);
		bAnterior = new JButton(actAnterior);
		bProximo = new JButton(actProximo);
		bUltimo = new JButton(actUltimo);
		
		pBotoes.add(bPrimeiro);
		pBotoes.add(bAnterior);
		pBotoes.add(bProximo);
		pBotoes.add(bUltimo);
		
		this.setJMenuBar(mB);
		this.add(pCartas);
		this.add(pBotoes, BorderLayout.SOUTH);
		
		this.setLocationRelativeTo(null);
	}
	class AcaoPrimeiro extends AbstractAction{
		public AcaoPrimeiro() {
			super("Primeiro");
			putValue(MNEMONIC_KEY, KeyEvent.VK_P);
			putValue(SHORT_DESCRIPTION, "Vai para a primeira carta!");
			putValue(SMALL_ICON, new ImageIcon(imagesPath+"navigation/Up16.gif"));
		}
		public void actionPerformed(ActionEvent e) {
			cl.first(pCartas);
		}
	}
	class AcaoProximo extends AbstractAction{
		public AcaoProximo() {
			super("Próximo");
			putValue(MNEMONIC_KEY, KeyEvent.VK_N);
			putValue(SHORT_DESCRIPTION, "Vai para a próxima carta!");
			putValue(SMALL_ICON, new ImageIcon(imagesPath+"navigation/Forward16.gif"));
		}
		public void actionPerformed(ActionEvent e) {
			cl.next(pCartas);
		}
	}
	class AcaoAnterior extends AbstractAction{
		public AcaoAnterior() {
			super("Anterior");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
			putValue(SHORT_DESCRIPTION, "Vai para a carta anterior!");
			putValue(SMALL_ICON, new ImageIcon(imagesPath+"navigation/Back16.gif"));
		}
		public void actionPerformed(ActionEvent e) {
			cl.previous(pCartas);
		}
	}
	class AcaoUltimo extends AbstractAction{
		public AcaoUltimo() {
			super("Último");
			putValue(MNEMONIC_KEY, KeyEvent.VK_U);
			putValue(SHORT_DESCRIPTION, "Vai para a última carta!");
			putValue(SMALL_ICON, new ImageIcon(imagesPath+"navigation/Down16.gif"));
		}
		public void actionPerformed(ActionEvent e) {
			cl.last(pCartas);
		}
	}
	
	public static void main(String[] args) {
		new JanelaComLayoutCartaoApp().setVisible(true);
	}
}
