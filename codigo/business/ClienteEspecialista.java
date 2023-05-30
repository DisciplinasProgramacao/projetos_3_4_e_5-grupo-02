package business;

import business.interfaces.ICliente;

public class ClienteEspecialista implements ICliente {

	/*
	 *
	 *
	 *
	 *Avaliacao a = new Avaliacao(int nota);
	 *
	 *a.addComentario(String comentario);
	 *
	 *
	 */
	
    @Override
    public ICliente verificarCategoria(Lista<Serie> assistidas) {
        // verifica e return new ClienteEspecialista(); ou return new ClienteRegular();
        return null;
    }

	@Override
	public void avaliarMidia(Midia midia, Cliente avaliador, int nota, String comentario) throws IllegalStateException {
		midia.criarAvaliacao(avaliador, nota, comentario);
	}
}
