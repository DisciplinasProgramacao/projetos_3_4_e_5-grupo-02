package business.exceptions;

public class SerieNullException extends Exception {
	
	public SerieNullException() {
		super("O objeto serie recebido foi nulo");
	}
}
