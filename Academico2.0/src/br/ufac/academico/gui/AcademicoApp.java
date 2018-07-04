package br.ufac.academico.gui;

import javax.swing.JOptionPane;
import br.ufac.academico.db.Conexao;

class AcademicoApp {

	static final String DB_URL = "jdbc:mysql://localhost/academico";

	// Método main inicia execução do aplicativo
	public static void main(String args[]){
	
		String dbUsuario, dbSenha;
		int qtdTentativas=0;
		boolean conectado;
		
		Conexao cnx =  new Conexao();
		
		do {
			dbUsuario = JOptionPane.showInputDialog("Usuário:");
			dbSenha = JOptionPane.showInputDialog("Senha:");
			conectado = cnx.conecte(DB_URL, dbUsuario, dbSenha);	
			qtdTentativas ++;
		} while (!conectado && qtdTentativas < 3);
		
		if (conectado){
			// Criado o objeto pc do tipo Academico.
			Academico a = new Academico(cnx);
			// Exibindo a janela
			a.setVisible(true);
		}else{
			System.out.print("Você excedeu o número de tentativas para conectar-se.\n");			
		}
   } //Fim do método main
}//Fim da classe ProfessorApp


