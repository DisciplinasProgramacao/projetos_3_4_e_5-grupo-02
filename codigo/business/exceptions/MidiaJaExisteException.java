package business.exceptions;

import business.entidades.Midia;

public class MidiaJaExisteException extends Exception {
	
	public MidiaJaExisteException(String id) {
		super("A midia " + id + " já existe na plataforma de streamings");
	}

}
