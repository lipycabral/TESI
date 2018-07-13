package br.ufac.academico.gui;
import javax.swing.table.*;

import br.ufac.academico.entity.*;
import java.sql.*;
import java.util.*;

public class ProfessorTableModel extends AbstractTableModel{

	List<Professor> professores;

	public ProfessorTableModel(List<Professor> professores){

		this.professores = professores;
		
	}
	
	@Override
	public int getRowCount() {
		return professores.size();
	}

	@Override
	public int getColumnCount() {		
		return 7;	
	}
	@Override	
	public Object getValueAt(int rowIndex, int columnIndex) {		

		Professor p;
		Object dado = null;
		
		p = professores.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = p.getMatricula(); break;
		case 1: dado = p.getNome(); break;		
		case 2: dado = p.getRg(); break;
		case 3: dado = p.getCpf(); break;
		case 4: dado = p.getEndereco(); break;
		case 5: dado = p.getFone(); break;
		case 6: dado = p.getCentro().getSigla(); break;
		}
		
		return dado;		
	}

	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "Matrícula"; break;
		case 1: nome = "Nome"; break;		
		case 2: nome = "RG"; break;
		case 3: nome = "CPF"; break;		
		case 4: nome = "Endereço"; break;
		case 5: nome = "Fone"; break;
		case 6: nome = "Centro"; break;		
		}		
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		Object obj;
		
		switch (columnIndex) {
		case 0: obj = Integer.class; break;
		case 1: obj = String.class; break;
		case 2: obj = Integer.class; break;		
		case 3: obj = Integer.class; break;
		case 4: obj = String.class; break;
		case 5: obj = String.class; break;
		case 6: obj = String.class; break;		
		default: obj = null; break;
		}		

		return obj.getClass();
	}
}







