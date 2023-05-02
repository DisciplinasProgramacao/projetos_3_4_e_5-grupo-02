package business.exceptions;

public class ElementoJaExisteException extends Exception {
	
	public ElementoJaExisteException(String obj, String list) {
		super("O objeto '" + obj + "' recebido ja existe na lista '" + list + "'");
	}

}
