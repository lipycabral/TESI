package br.ufac.academico.logic;

import java.util.*;
import br.ufac.academico.db.*;
import br.ufac.academico.entity.*;

public class ProfessorLogic {

	private ProfessorDB pdb;
	private CentroLogic cl;
	
	public ProfessorLogic(Conexao cnx) {
		pdb = new ProfessorDB(cnx);
		cl = new CentroLogic(cnx);
	}
	
	public boolean addProfessor(int matricula, String nome, 
			int rg, int cpf, String endereco, String fone, String sigla) {

		Centro centro = cl.getCentro(sigla);		
		
		Professor professor = new Professor(matricula, nome, rg, cpf, 
				endereco, fone, centro);

		return pdb.addProfessor(professor);
		
	}
	
	public Professor getProfessor(int matricula) {

		return pdb.getProfessor(matricula);
		
	}
	
	public boolean updProfessor(int matricula, String nome, 
			int rg, int cpf, String endereco, String fone, String sigla) {

		Centro centro = cl.getCentro(sigla);		
		
		Professor professor = new Professor(matricula, nome, rg, cpf, 
				endereco, fone, centro);

		return pdb.updProfessor(professor);
		
	}
	
	public boolean delProfessor(int matricula, String nome, 
			int rg, int cpf, String endereco, String fone, String sigla) {

		Centro centro = cl.getCentro(sigla);		
		
		Professor professor = new Professor(matricula, nome, rg, cpf, 
				endereco, fone, centro);
		
		return pdb.delProfessor(professor);
		
	}
	
	public List<Professor> getProfessores(){
		return pdb.getProfessores();
	}
	
	public List<Professor> getProfessoresPorNome(String nome){
		return pdb.getProfessoresPorNome(nome);
	}

}










