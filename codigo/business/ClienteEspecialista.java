package business;

import business.interfaces.ICliente;

public class ClienteEspecialista implements ICliente {

    @Override
    public ICliente verificarCategoria(Lista<Serie> assistidas) {
        // verifica e return new ClienteEspecialista(); ou return new ClienteRegular();
        return null;
    }

    // TODO Em vez de Exception genérica, capturar a exceção customizada lançada em midia.avaliar()
    public boolean avaliarMidia(Midia midia, int nota) {
        try {
            midia.avaliar(this, nota);
        } catch (Exception e) {

        }

        return false;
    }

    public boolean avaliarMidia(Midia midia, String comentario) {
        try {
            midia.avaliar(this, comentario);
        } catch (Exception e) {

        }

        return false;
    }
}
