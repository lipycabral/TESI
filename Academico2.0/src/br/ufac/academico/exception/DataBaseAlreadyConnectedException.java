package br.ufac.academico.exception;

public class DataBaseAlreadyConnectedException extends Exception {

	public DataBaseAlreadyConnectedException(String db){
		super("Banco de dados '"+ db + "' já está conectado!");
	}
}
