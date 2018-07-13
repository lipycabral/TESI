package br.ufac.academico.db;
import java.sql.*;

public class Conexao {

	private Connection con = null;
	private Statement smt = null;
	private boolean conectado = false;
		
	public boolean conecte (String urlBanco, String userName, String userPasswd) {

		if (conectado) {
			System.out.println("Já está conectado!");
		}else{
			try {
				con = DriverManager.getConnection(urlBanco, userName, userPasswd);
				
				System.out.println("Conexão efetuada com sucesso!");
				conectado = true;
			} catch (SQLException sqle) {
				System.out.printf("Erro # %d (%s)\n", 
						sqle.getErrorCode(), 
						sqle.getMessage());
				conectado = false;
			}
		}
		return conectado;
	}
	
	public void desconecte () {

		if(!conectado) {
			System.out.println("Já está desconectado!");
		}else {
			try {
				con.close();
				System.out.println("Conexão encerrada com sucesso!");
				conectado = false;
			} catch (SQLException sqle) {
				System.out.printf("Erro # %d (%s)\n", 
						sqle.getErrorCode(), 
						sqle.getMessage());
			}
		}
	}

	public ResultSet consulte (String sqlQuery) {

		if(!conectado) {
			System.out.println("Não está conectado!");
			return null;
		}else {
			try {
				smt = con.createStatement();
				return smt.executeQuery(sqlQuery);
			} catch (SQLException sqle) {
				System.out.printf("Erro # %d (%s)\n", 
						sqle.getErrorCode(), 
						sqle.getMessage());
				return null;
			}
		}
	}
	
	public int atualize (String sqlUpdate) {

		if(!conectado) {
			System.out.println("Não está conectado!");
		}else {
			try {
				smt = con.createStatement();
				return smt.executeUpdate(sqlUpdate);
			} catch (SQLException sqle) {
				System.out.printf("Erro # %d (%s)\n", 
						sqle.getErrorCode(), 
						sqle.getMessage());
			}
		}
		return -1;
	}	

}
