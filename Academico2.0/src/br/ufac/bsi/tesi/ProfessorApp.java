package br.ufac.bsi.tesi;

import java.util.*;

class ProfessorApp {

	static final String DB_URL = "jdbc:mysql://localhost/academico";

	// Método main inicia execução do aplicativo
	public static void main(String args[]){
	
		Scanner leitor = new Scanner(System.in);
		String dbUsuario, dbSenha;
		int qtdTentativas=0;
		boolean conectado;
		
		Conexao cnx =  new Conexao();
		
		do {
			System.out.print("Usuário: "); dbUsuario = leitor.nextLine();
			System.out.print("Senha: "); dbSenha = leitor.nextLine();
			conectado = cnx.conecte(DB_URL, dbUsuario, dbSenha);	
			qtdTentativas ++;
		} while (!conectado && qtdTentativas < 3);
		
		leitor.close();
		
		if (conectado){
			// Criado o objeto pc do tipo ProfessorConsulta.
			ProfessorConsulta pc = 
				new ProfessorConsulta(cnx);
			// Exibindo a janela
			pc.setVisible(true);
		}else{
			System.out.print("Você excedeu o número de tentativas para conectar-se.\n");			
		}
   } //Fim do método main
}//Fim da classe ProfessorApp


