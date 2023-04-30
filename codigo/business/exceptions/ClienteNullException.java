package business.exceptions;

public class ClienteNullException extends Exception {
	
	public ClienteNullException() {
		super("O objeto cliente recebido foi nulo");
	}

}
