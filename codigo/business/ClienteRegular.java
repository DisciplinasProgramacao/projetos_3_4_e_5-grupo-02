package business;

import business.interfaces.ICliente;

public class ClienteRegular implements ICliente {

    @Override
    public ICliente verificarCategoria(Lista<Serie> assistidas) {
        return null;
    }

    // TODO Em vez de Exception genérica, capturar a exceção customizada lançada em midia.avaliar()
    @Override
    public boolean avaliarMidia(Midia midia, int nota) {
        try {
            midia.avaliar(this, nota);
        } catch (Exception e) {

        }

        return false;
    }

    public boolean avaliarMidia(Midia midia, String comentario) {
        // OBS: Para cliente regular, este método não deve ser implementado,
        // pois cliente regular não pode deixar comentário, apenas nota.
        return false;
    }

}
