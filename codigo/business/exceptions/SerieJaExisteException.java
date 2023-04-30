package business.exceptions;

public class SerieJaExisteException extends Exception {
	
	public SerieJaExisteException() {
		super("O objeto serie recebido ja existe na lista de series");
	}

}
