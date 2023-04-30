package business.exceptions;

public class LoginInvalidoException extends Exception {

	public LoginInvalidoException(String user, String password) {
		super("Nao foi encontrado nenhum usuario com as credenciais: " + user + " | " + password);
	}
	
}
