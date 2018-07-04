import java.sql.*;
import java.util.Scanner;

public class ConexaoAPP {

	private static final String URL_DB = "jdbc:mysql://localhost/Academico"; 

	public static void main(String[] args) {

		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		int qtdColunas, qtdTentativas = 1;
		String dbUsuario, dbSenha, strConsulta;
		Scanner entrada = new Scanner(System.in);
		boolean conectado = false;
		
		
		Conexao cnx = new Conexao();
		do {
			System.out.print("Nome: ");
			dbUsuario = entrada.nextLine();
			System.out.print("Senha: ");
			dbSenha = entrada.nextLine();
			
			conectado = cnx.conecte(URL_DB, dbUsuario, dbSenha);
			qtdTentativas++;
		}while(!conectado && qtdTentativas <= 3);

		if(!conectado) {
			System.out.println("Esgotou as tentativas de conexão!");
		}else {
			System.out.println("Forneça instruções SQL para o prompt");
			System.out.print("myqsl> ");
			strConsulta = entrada.nextLine();
			while(strConsulta.compareToIgnoreCase("quit") != 0){
				rs = cnx.consulte(strConsulta);
		
				if (rs == null) {
					System.out.println("Consulta sem resultados!");
				}else {
					try {
						rsmd = rs.getMetaData();
						qtdColunas = rsmd.getColumnCount();
						for (int i=1;i<=qtdColunas;i++) {
							// TRUNCANDO TUDO COM 11 CARACTERES MAIS UMA TABULAÇÃO
							System.out.printf("%-11.11s\t", rsmd.getColumnLabel(i).toUpperCase());
						}
						System.out.println("");
						while(rs.next()) {
							for (int i=1;i<=qtdColunas;i++) {
								// TRUNCANDO TUDO COM 11 CARACTERES MAIS UMA TABULAÇÃO						
								System.out.printf("%-11.11s\t", rs.getObject(i));
							}
							System.out.println("");
						}
						System.out.println("");
					} catch (SQLException sqle) {
						System.out.printf("Erro # %d (%s)\n", 
								sqle.getErrorCode(), 
								sqle.getMessage());
					}
		
				}
				System.out.print("myqsl> ");
				strConsulta = entrada.nextLine();
			}
			cnx.desconecte();
			entrada.close();
		}
	}
}