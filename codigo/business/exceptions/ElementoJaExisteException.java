package business.exceptions;

import business.Cliente;
import java.util.HashMap;

public class ElementoJaExisteException extends Exception {
	
	public ElementoJaExisteException(String obj, String list) {
		super("Erro: O objeto '" + obj + "' recebido já existe na lista '" + list + "'.\n");
	}

	public ElementoJaExisteException(String obj) {
		super("Erro: O objeto '" + obj + "' recebido já está cadastrado.\n");
	}
}
