package br.ufac.academico.logic;

import br.ufac.academico.db.CentroDB;
import br.ufac.academico.db.Conexao;
import br.ufac.academico.entity.Centro;

public class CentroLogic {
	CentroDB cdb;
	public CentroLogic(Conexao cnx) {
		cdb = new CentroDB(cnx);
	}
	
	public boolean addCentro(String sigla, String nome) {
		Centro c = new Centro(sigla, nome);
		return cdb.addCentro(c);
	}
}
