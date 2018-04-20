import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

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
	private AcaoSobre actSobre = new AcaoSobre();
	private JLabel lbVersão = new JLabel("Versão do programa 1.0");
	private JLabel nomeDesenvolvedor = new JLabel("Desenvolver: Felipy da Costa Cabral");
	
	private JPopupMenu mPopup;
	public JanelaComLayoutCartaoApp() {
		this.setTitle("Jogo de cartas");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setSize(500,300);
		
		cl = new CardLayout();
		
		pCartas = new JPanel(cl);
		pCartas.addMouseListener(new DisparadorDePopCartas());
		
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
		
		mnAjuda.add(actSobre);
		mnAjuda.setMnemonic(KeyEvent.VK_J);
		
		mB.add(mnCartas);
		mB.add(mnAjuda);
		
		mPopup = new JPopupMenu();
		mPopup.add(actPrimeiro);
		mPopup.add(actAnterior);
		mPopup.addSeparator();
		mPopup.add(actProximo);
		mPopup.add(actUltimo);
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
	public class janelaSobre extends JDialog{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public janelaSobre() {
			this.setTitle("Sobre");
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setResizable(false);
			this.setLayout(new GridLayout(2, 1));
			this.add(lbVersão);
			this.add(nomeDesenvolvedor);
			this.pack();
			this.setLocationRelativeTo(null);
			this.setModal(true);
			this.setVisible(true);
		}
	}
	class AcaoPrimeiro extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
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
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public AcaoProximo() {
			super("Próximo");
			putValue(MNEMONIC_KEY, KeyEvent.VK_R);
			putValue(SHORT_DESCRIPTION, "Vai para a próxima carta!");
			putValue(SMALL_ICON, new ImageIcon(imagesPath+"navigation/Forward16.gif"));
		}
		public void actionPerformed(ActionEvent e) {
			cl.next(pCartas);
		}
	}
	class AcaoAnterior extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
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
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public AcaoUltimo() {
			super("Último");
			putValue(MNEMONIC_KEY, KeyEvent.VK_L);
			putValue(SHORT_DESCRIPTION, "Vai para a última carta!");
			putValue(SMALL_ICON, new ImageIcon(imagesPath+"navigation/Down16.gif"));
		}
		public void actionPerformed(ActionEvent e) {
			cl.last(pCartas);
		}
	}
	class AcaoSobre extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public AcaoSobre() {
			super("Sobre");
			putValue(MNEMONIC_KEY, KeyEvent.VK_S);
			putValue(SHORT_DESCRIPTION, "Vai para a descrição do programa!");
			putValue(SMALL_ICON, new ImageIcon(imagesPath+"general/About16.gif"));
		}
		public void actionPerformed(ActionEvent e) {
			new janelaSobre();
		}
	}
	class DisparadorDePopCartas extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			if(e.isPopupTrigger()) {
				mPopup.show(pCartas, e.getX(), e.getY());
			}
		}
	}
	
	public static void main(String[] args) {
		new JanelaComLayoutCartaoApp().setVisible(true);
	}
}
