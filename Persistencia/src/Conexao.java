import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	
	private Connection con = null;
	private Statement snt = null;
	private boolean conectado = false;
	
	public Conexao() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver carregado com sucesso!");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não localizado!");
		}
		
	}
	public boolean conecte(String urlBanco, String userName, String userPasswd) {
		if(conectado) {
			System.out.println("Já está conectado!");
		}
		else {
			try {
				con = DriverManager.getConnection(urlBanco, userName, userPasswd);
				snt = con.createStatement();
				System.out.println("Conexão efetuada com sucesso!");
				conectado = true;
			} catch (SQLException sqle) {
				System.out.printf("Erro # %d (%s)\n", sqle.getErrorCode(),sqle.getMessage());
				conectado = false;
			}
		}
		return conectado;
	}
	public void desconecte() {
		if(!conectado) {
			System.out.println("Já está desconectado!");
		}
		else {
			try {
				con.close();
				System.out.println("Conexão encerrada com sucesso!");
				conectado = false;
			} catch (SQLException sqle) {
				System.out.printf("Erro # %d (%s)\n", sqle.getErrorCode(),sqle.getMessage());
			}
		}
	}
	public ResultSet consulte(String sqlQuery) {
		if(!conectado) {
			System.out.println("Não está conectado!");
			return null;
		}
		else {
			try {
				return snt.executeQuery(sqlQuery);
			}
			catch(SQLException sqle) {
				System.out.printf("Erro # %d (%s)\n", sqle.getErrorCode(),sqle.getMessage());
				return null;
			}
		}
	}
	public int atualize(String sqlUpdate) {
		if(!conectado) {
			System.out.println("Não está conectado!");
		}
		else {
			try {
				return snt.executeUpdate(sqlUpdate);
			}
			catch(SQLException sqle) {
				System.out.printf("Erro # %d (%s)\n", sqle.getErrorCode(),sqle.getMessage());
			}
		}
		return -1;
	}
}
