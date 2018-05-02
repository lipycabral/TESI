import javax.swing.table.*;
import java.util.*;

public class BolsistaTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Bolsista> bolsistas = new ArrayList<Bolsista>();
	private String[] cabecalho = {"Matrícula", "Nome", "Sexo", "Auxilio", "Iniciação", "Curso"};

	@Override
	public int getRowCount() {
		return bolsistas.size();	}

	@Override
	public int getColumnCount() {		
		return cabecalho.length;	
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
		return cabecalho[column]; 	
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		return getValueAt(0, columnIndex).getClass();
	
	}
	
	public void addBolsista(Bolsista bolsista) {
		bolsistas.add(bolsista);
		fireTableDataChanged();
	}

	public void delBolsista(int index) {
		bolsistas.remove(index);
		fireTableDataChanged();
	}
	
	public Bolsista getBolsista(int index) {
		return bolsistas.get(index);
	}
	
	public void updBolsista(Bolsista bolsista, int index) {
		bolsistas.set(index, bolsista);
		fireTableDataChanged();
	}
}







