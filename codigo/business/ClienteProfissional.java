package business;

public class ClienteProfissional extends Cliente {
	
	private String profissao;
	
	public ClienteProfissional(String nomeDeUsuario, String id, String senha, String profissao) {
		super(nomeDeUsuario, id, senha);
		this.profissao = profissao;
	}

	

}
