package business.exceptions;

public class FilmeJaExisteException extends Exception {
	
	public FilmeJaExisteException() {
		super("O objeto filme recebido ja existe na lista de filmes");
	}

}
