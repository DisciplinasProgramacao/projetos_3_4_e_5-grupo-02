package business;

import business.interfaces.ICliente;

public class ClienteEspecialista implements ICliente {

	@Override
	public ICliente verificarCategoria(Lista<Serie> assistidas) {
		// verifica e return new ClienteEspecialista(); ou return new ClienteRegular();
		return null;
	}

	public boolean avaliarMidia(Midia midia, int nota, String comentario) {
		avaliarMidia(midia, nota);
		return false;
	}

	@Override
	public boolean avaliarMidia(Midia midia, int nota) {
		
		return false;
	}

}
