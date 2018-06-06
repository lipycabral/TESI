package br.ufac.bsi.tesi;
import javax.swing.table.*;

import java.sql.*;
import java.util.*;

public class SQLTableModel extends AbstractTableModel{

	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private int qtdLinhas, qtdColunas;

	public SQLTableModel(ResultSet rs){

		this.rs = rs;
		try {
			rsmd  = this.rs.getMetaData();
			qtdColunas = rsmd.getColumnCount();
			this.rs.last();
			qtdLinhas = rs.getRow();
			rs.beforeFirst();
		}catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
	}
	
	@Override
	public int getRowCount() {
		return qtdLinhas;
	}

	@Override
	public int getColumnCount() {		
		return qtdColunas;	
	}
	@Override	
	public Object getValueAt(int rowIndex, int columnIndex) {		

		Object dado = null; 

		try {
			rs.absolute(rowIndex+1);
			dado = rs.getObject(columnIndex+1);
		}catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}		
		return dado;		
	}

	@Override
	public String getColumnName(int column) {

		String nome="";
		
		try {
			nome = rsmd.getColumnLabel(column+1);
		}catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}		
		
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		String nomeClasse;
		
		try {
			nomeClasse = rsmd.getColumnClassName(columnIndex+1);
			return Class.forName(nomeClasse);
		}catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.printf("Classe não encontrada: %s\n", 
					cnfe.getMessage());
		}		

		return null;
	}
}







