import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel p1 = new JPanel(new GridLayout(2, 1));
	private JPanel p2 = new JPanel(new GridLayout(2, 1));
	private JPanel p3 = new JPanel(new GridLayout(1, 2));
	
	private JLabel lbNome = new JLabel("Nome");
	private JTextField tfNome = new JTextField();
	
	private JLabel lbSenha = new JLabel("Senha");
	private JPasswordField tfSenha = new JPasswordField();
	
	private JButton btnEntrar = new JButton("Entrar");
	private JButton btnCancelar = new JButton("Cancelar");
	
	public Login() {
		this.setTitle("Login");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		p1.add(lbNome);
		p1.add(lbSenha);
		
		p2.add(tfNome);
		p2.add(tfSenha);
		
		p3.add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String senha = new String(tfSenha.getPassword()).trim();
				if(tfNome.getText().equals(senha))
					JOptionPane.showMessageDialog(Login.this, "Entrou","Autenticação", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(Login.this, "Erro","Autenticação", JOptionPane.ERROR_MESSAGE);
			}
		});
		p3.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		this.add(p1, BorderLayout.LINE_START);
		this.add(p2, BorderLayout.CENTER);
		this.add(p3, BorderLayout.SOUTH);
		
		this.pack();
		//this.setLocationRelativeTo(null);
		centralize();
	}
	public void centralize() {
		Dimension dT = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dJ = getSize();
		setLocation((dT.width-dJ.width)/2, (dT.height-dJ.height)/2);
	}
	public static void main(String[] args) {
		new Login().setVisible(true);
	}
}
