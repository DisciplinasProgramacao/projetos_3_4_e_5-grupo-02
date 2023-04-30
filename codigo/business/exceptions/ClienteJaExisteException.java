package business.exceptions;

public class ClienteJaExisteException extends Exception {
	
	public ClienteJaExisteException() {
		super("O objeto cliente recebido ja existe na lista de clientes");
	}

}
