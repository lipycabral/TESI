import java.sql.*;
import java.util.Scanner;

public class TestaAtualizaAPP {

	private static final String URL_DB = "jdbc:mysql://localhost/Academico"; 

	public static void main(String[] args) {
		
		Conexao cnx = new Conexao();
		cnx.conecte(URL_DB, "aluno", "aluno");
		String sqlUpdate = "update alunos set nome = 'Mateus' where matricula = 20120300042";
		System.out.printf("Linhas afetadas %d\n", cnx.atualize(sqlUpdate));
		cnx.desconecte();
	}
}