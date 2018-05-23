
public class ConexaoAPP {

	public static void main(String[] args) {
		Conexao cnx = new Conexao();
		cnx.conecte("jdbc:mysql://localhost/Academico", "aluno", "aluno");
		cnx.desconecte();
	}

}
