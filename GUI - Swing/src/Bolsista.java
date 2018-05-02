
public class Bolsista {
	private int matricula;
	private String nome;
	private String sexo;
	private String auxilio;
	private String iniciacao;
	private String curso;
	
	public Bolsista(int matricula, String nome, String sexo, String auxilio, String iniciacao, String curso) {
		this.matricula = matricula;
		this.nome = nome;
		this.sexo = sexo;
		this.auxilio = auxilio;
		this.iniciacao = iniciacao;
		this.curso = curso;
	}
	
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getAuxilio() {
		return auxilio;
	}
	public void setAuxilio(String auxilio) {
		this.auxilio = auxilio;
	}
	public String getIniciacao() {
		return iniciacao;
	}
	public void setIniciacao(String iniciacao) {
		this.iniciacao = iniciacao;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
}
