import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	
	private Connection con = null;
	private Statement snt = null;
	
	public Conexao() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver carregado com sucesso!");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não localizado!");
		}
		
	}
	public void conecte(String urlBanco, String userName, String userPasswd) {
		try {
			con = DriverManager.getConnection(urlBanco, userName, userPasswd);
			System.out.println("Conexão efetuada com sucesso!");
		} catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", sqle.getErrorCode(),sqle.getMessage());
		}
	}
	public void desconecte() {
		try {
			con.close();
			System.out.println("Conexão encerrada com sucesso!");
		} catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", sqle.getErrorCode(),sqle.getMessage());
		}
	}
}
