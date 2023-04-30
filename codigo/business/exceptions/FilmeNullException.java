package business.exceptions;

public class FilmeNullException extends Exception {
	
	public FilmeNullException() {
		super("O objeto filme recebido foi nulo");
	}

}
