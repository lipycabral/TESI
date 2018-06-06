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
		Object dado = null;
		try {
			rs.absolute(rowIndex+1);
			dado = rs.getObject(columnIndex+1);
		}catch(SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", sqle.getErrorCode(),sqle.getMessage());
		}
		return dado;
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
		String nomeClasse;
		try {
			nomeClasse = rsmd.getColumnClassName(columnIndex+1);
			return Class.forName(nomeClasse);
		}catch(SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", sqle.getErrorCode(),sqle.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.printf("Classe não encontrada: %s", e.getMessage());
		}
		return null;
	
	}
}







