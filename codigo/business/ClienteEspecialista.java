package business;

import business.interfaces.ICliente;

public class ClienteEspecialista implements ICliente {

    @Override
    public ICliente verificarCategoria(Lista<Serie> assistidas) {
        // verifica e return new ClienteEspecialista(); ou return new ClienteRegular();
        return null;
    }

    @Override
    public void avaliarMidia(Midia midia, Cliente avaliador, String comentario) throws IllegalStateException {
        midia.avaliar(avaliador, comentario);
    }
}
