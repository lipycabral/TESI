package br.ufac.academico.exception;

public class EntityAlreadyExistException extends Exception {

	public EntityAlreadyExistException(String entidade){
		super("Entidade já existe: " + entidade);
	}
}
