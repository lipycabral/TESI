package br.ufac.academico.test;

import br.ufac.academico.db.CentroDB;
import br.ufac.academico.db.Conexao;
import br.ufac.academico.entity.Centro;

public class CentroDBTeste {
	
	static final String DB_URL = "jdbc:mysql://localhost/academico";

	public static void main(String[] args) {
		Conexao cnx = new Conexao();
		cnx.conecte(DB_URL, "aluno", "aluno");
		CentroDB cdb = new CentroDB(cnx);
		Centro c1,c2,c3;
		c1 = new Centro("123", "tres dois 1");
		c2 = new Centro("456", "seis cinco quatro");
		c3 = new Centro("789", "nove oito sete");
		
		cdb.updCentro(c1);
		cdb.updCentro(c2);
		cdb.updCentro(c3);
		
		c1 = cdb.getCentro("123");
		c2 = cdb.getCentro("456");
		c3 = cdb.getCentro("789");
		
		System.out.printf("Dados de c1: %s, %s\n", c1.getSigla(),c1.getNome());
		System.out.printf("Dados de c2: %s, %s\n", c2.getSigla(),c2.getNome());
		System.out.printf("Dados de c3: %s, %s\n", c3.getSigla(),c3.getNome());
		cnx.desconecte();
	}

}