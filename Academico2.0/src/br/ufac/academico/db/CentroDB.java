package br.ufac.academico.db;

import java.sql.*;
import java.util.*;

import br.ufac.academico.entity.Centro;

public class CentroDB {
	
	private Conexao cnx;
	private ResultSet rs;
	
	public CentroDB(Conexao cnx) {
		this.cnx = cnx;
	}

	public boolean addCentro(Centro c) {
		
		String strInsercao = 
				"INSERT INTO centros (sigla, nome) "
				+ "VALUES ('" + c.getSigla() + "', "
				+ "'" + c.getNome() + "');";
		
		return cnx.atualize(strInsercao) > 0;
		
	}
	
	public Centro getCentro (String sigla) {

		Centro c = null;
		
		String strBusca = "SELECT sigla, nome "
				+ "FROM centros "
				+ "WHERE sigla = '" + sigla + "';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				c = new Centro(rs.getString(1), rs.getString(2)); 
			}
		} catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return c;
	}
	
	public boolean updCentro(Centro c) {
		
		String strAtualizao = 
				"UPDATE centros "
				+ "SET nome = '" + c.getNome() + "' "
				+ "WHERE sigla = '" + c.getSigla() + "';";
		
		return cnx.atualize(strAtualizao) > 0;
		
	}
	
	public boolean delCentro(Centro c) {
		
		String strDelecao = 
				"DELETE FROM centros "
				+ "WHERE sigla = '" + c.getSigla() + "';";
		
		return cnx.atualize(strDelecao) > 0;
		
	}		
	
	public List<Centro> getCentros () {

		Centro c = null;
		List<Centro> centros = new ArrayList<Centro>();
		
		String strBusca = "SELECT sigla, nome "
				+ "FROM centros;";
		
		rs = cnx.consulte(strBusca);
		
		try {
			while (rs.next()) {
				c = new Centro(rs.getString(1), rs.getString(2));
				centros.add(c);
			}
		} catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return centros;
	}	
	
	public List<Centro> getCentrosPorNome (String nome) {

		Centro c = null;
		List<Centro> centros = new ArrayList<Centro>();
		
		String strBusca = "SELECT sigla, nome "
				+ "FROM centros "
				+ "WHERE nome LIKE '%" + nome + "%';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			while (rs.next()) {
				c = new Centro(rs.getString(1), rs.getString(2));
				centros.add(c);
			}
		} catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return centros;
	}	

}
















