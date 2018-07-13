package br.ufac.academico.entity;

public class Professor {
	
	private int matricula;
	private String nome;
	private int rg;
	private int cpf;
	private String endereco;
	private String fone;
	private Centro centro;

	public Professor(int matricula, String nome, int rg, int cpf, String endereco, String fone, Centro centro) {
		
		this.matricula = matricula;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.endereco = endereco;
		this.fone = fone;
		this.centro = centro;
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
	public int getRg() {
		return rg;
	}
	public void setRg(int rg) {
		this.rg = rg;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	
}
