package br.ufac.academico.db;

import java.sql.*;
import java.util.*;

import br.ufac.academico.entity.*;

public class ProfessorDB {

	private Conexao cnx;
	private ResultSet rs;
	private CentroDB cdb;

	public ProfessorDB(Conexao cnx){
		this.cnx = cnx;
		cdb = new CentroDB(this.cnx);
	}

	public boolean addProfessor(Professor p){

		String strIncluir = "INSERT INTO professores ("
				+ "matricula, nome, rg, cpf, endereco, fone, centro) "
				+ "VALUES (" + p.getMatricula() 	+ ","
				+ "'" + p.getNome() 				+ "',"
				+ p.getRg() 						+ ","
				+ p.getCpf() 						+ ","
				+ "'" + p.getEndereco()				+ "', "
				+ "'" + p.getFone() 				+ "',"
				+ "'" + p.getCentro().getSigla()	+ "');";

		return cnx.atualize(strIncluir) > 0;

	}

	public Professor getProfessor(int matricula){

		String strBusca = "SELECT matricula, nome, rg, cpf, endereco, fone, centro "
				+ " FROM professores "
				+ " WHERE matricula = " + matricula + ";";

		Professor professor = null;
		Centro centro = null;

		rs = cnx.consulte(strBusca);
		try{

			if (rs.next()){

				centro = cdb.getCentro(rs.getString(7));				

				professor = new Professor(rs.getInt(1), 
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						centro);

			}
		}catch(SQLException sqle){
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}
		return professor;
	}

	public boolean updProfessor(Professor p) {

		String strAtualizar = "UPDATE professores "
				+ " SET nome = '" + p.getNome() 			+ "',"
				+ " rg = " + p.getRg()						+ ", "
				+ " cpf = " + p.getCpf()					+ ", "
				+ " endereco = '" + p.getEndereco() 		+ "', "
				+ " fone = '" + p.getFone()					+ "', "
				+ " centro = '" + p.getCentro().getSigla()	+ "' "
				+ " WHERE matricula = " + p.getMatricula() 	+ ";";

		return cnx.atualize(strAtualizar) > 0;
	}

	public boolean delProfessor(Professor p){

		String strDeletar = "DELETE FROM professores "
				+ " WHERE matricula = " + p.getMatricula() + ";";

		return cnx.atualize(strDeletar) > 0;
	}

	public List<Professor> getProfessores(){

		List<Professor> listaDeProfessores = new ArrayList<Professor>();

		String strBusca = "SELECT matricula, nome, rg, cpf, endereco, fone, centro "
				+ " FROM professores;";

		Professor professor;
		Centro centro;

		rs = cnx.consulte(strBusca);
		try{
			while(rs.next()){

				centro = cdb.getCentro(rs.getString(7));				

				professor = new Professor(rs.getInt(1), 
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						centro);

				listaDeProfessores.add(professor);
			}

		}catch(SQLException sqle){
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}
		return listaDeProfessores;
	}

	public List<Professor> getProfessoresPorNome(String nome){

		List<Professor> listaDeProfessores = new ArrayList<Professor>();

		String strBusca = "SELECT matricula, nome, rg, cpf, endereco, fone, centro "
				+ " FROM professores "
				+ " WHERE nome LIKE '%" + nome + "%';";

		Professor professor;
		Centro centro;

		rs = cnx.consulte(strBusca);

		try{
			while(rs.next()){
				centro = cdb.getCentro(rs.getString(7));				

				professor = new Professor(rs.getInt(1), 
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						centro);

				listaDeProfessores.add(professor);
			}
		}catch(SQLException sqle){
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}
		return listaDeProfessores;
	}

}