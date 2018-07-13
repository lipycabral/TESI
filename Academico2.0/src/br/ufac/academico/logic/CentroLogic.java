package br.ufac.academico.logic;

import java.util.*;
import br.ufac.academico.db.*;
import br.ufac.academico.entity.*;

public class CentroLogic {

	private CentroDB cdb;
	
	public CentroLogic(Conexao cnx) {
		cdb = new CentroDB(cnx);
	}
	
	public boolean addCentro(String sigla, String nome) {

		Centro c = new Centro(sigla, nome);
		return cdb.addCentro(c);
		
	}
	
	public Centro getCentro(String sigla) {

		return cdb.getCentro(sigla);
		
	}
	
	public boolean updCentro(String sigla, String nome) {
		Centro c = new Centro(sigla, nome);
		return cdb.updCentro(c);	
	}
	
	public boolean delCentro(String sigla, String nome) {
		Centro c = new Centro(sigla, nome);
		return cdb.delCentro(c);
	}
	
	public List<Centro> getCentros(){
		return cdb.getCentros();
	}
	
	public List<Centro> getCentrosPorNome(String nome){
		return cdb.getCentrosPorNome(nome);
	}
	
}










