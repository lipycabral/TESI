import javax.swing.table.*;
import java.util.*;

public class ProfessorTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Professor> professores = new ArrayList<Professor>();
	private String[] cabecalho = {"Matrícula", "Nome"};

	@Override
	public int getRowCount() {
		return professores.size();	}

	@Override
	public int getColumnCount() {		
		return cabecalho.length;	
	}
	@Override	
	public Object getValueAt(int rowIndex, int columnIndex) {		
		Professor professor = professores.get(rowIndex);
		switch(columnIndex) {
		case 0 : return professor.getMatricula();
		case 1 : return professor.getNome();
		default : return null;
		}

	}

	@Override
	public String getColumnName(int column) {
		return cabecalho[column]; 	
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		return getValueAt(0, columnIndex).getClass();
	
	}
	
	public void addProfessor(Professor professor) {
		professores.add(professor);
		fireTableDataChanged();
	}

	public void removeProfessor(int index) {
		professores.remove(index);
		fireTableDataChanged();
	}
}







