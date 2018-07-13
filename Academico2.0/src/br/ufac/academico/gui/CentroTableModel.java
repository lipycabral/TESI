package br.ufac.academico.gui;
import javax.swing.table.*;

import br.ufac.academico.entity.*;
import java.sql.*;
import java.util.*;

public class CentroTableModel extends AbstractTableModel{

	List<Centro> centros;

	public CentroTableModel(List<Centro> centros){

		this.centros = centros;
		
	}
	
	@Override
	public int getRowCount() {
		return centros.size();
	}

	@Override
	public int getColumnCount() {		
		return 2;	
	}
	@Override	
	public Object getValueAt(int rowIndex, int columnIndex) {		

		Centro c;
		Object dado = null;
		
		c = centros.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = c.getSigla(); break;
		case 1: dado = c.getNome(); break;		
		}
		
		return dado;		
	}

	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "Sigla"; break;
		case 1: nome = "Nome"; break;		
		}		
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		Object obj;
		
		switch (columnIndex) {
		case 0: obj = String.class; break;
		case 1: obj = String.class; break;
		default: obj = null; break;
		}		

		return obj.getClass();
	}
}







