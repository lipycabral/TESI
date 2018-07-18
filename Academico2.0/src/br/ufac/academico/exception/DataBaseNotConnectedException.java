package br.ufac.academico.exception;

public class DataBaseNotConnectedException extends Exception {

	public DataBaseNotConnectedException(String db){
		super("Banco de dados '"+ db + "' não está conectado!");
	}
}
