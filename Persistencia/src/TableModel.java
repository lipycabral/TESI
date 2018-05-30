import javax.swing.table.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class TableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private int qtdLinhas, qtdColunas;

	public TableModel(ResultSet rs) {
		this.rs = rs;
		try {
			this.rsmd = this.rs.getMetaData();
			qtdColunas = rsmd.getColumnCount();
			this.rs.last();
			qtdLinhas = rs.getRow();
			this.rs.beforeFirst();
		}catch(SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", sqle.getErrorCode(),sqle.getMessage());
		}
	}
	@Override
	public int getRowCount() {
		return qtdLinhas;	}

	@Override
	public int getColumnCount() {		
		return qtdColunas;	
	}
	@Override	
	public Object getValueAt(int rowIndex, int columnIndex) {		
		Bolsista bolsista = bolsistas.get(rowIndex);
		switch(columnIndex) {
		case 0 : return bolsista.getMatricula();
		case 1 : return bolsista.getNome();
		case 2 : return bolsista.getSexo();
		case 3 : return bolsista.getAuxilio();
		case 4 : return bolsista.getIniciacao();
		case 5 : return bolsista.getCurso();
		default : return null;
		}

	}

	@Override
	public String getColumnName(int column) {
		String nome = null;
		try {
			nome = rsmd.getColumnLabel(column+1);
		}catch(SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", sqle.getErrorCode(),sqle.getMessage());
		}
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		return getValueAt(0, columnIndex).getClass();
	
	}
}







