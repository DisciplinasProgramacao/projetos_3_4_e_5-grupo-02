package business;

import business.interfaces.ICliente;

public class ClienteEspecialista implements ICliente {

    @Override
    public ICliente verificarCategoria(Lista<Serie> assistidas) {
        // verifica e return new ClienteEspecialista(); ou return new ClienteRegular();
        return null;
    }

    public void avaliarMidia(Midia midia, String comentario) throws IllegalStateException {
        midia.avaliar(this, comentario);
    }
}
