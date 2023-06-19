package business.entidades.fracas;
import business.entidades.Cliente;
import business.entidades.Midia;
import business.interfaces.ICliente;

public class ClienteProfissional implements ICliente {

    /**
     * Chama o método criarAvaliacao(Cliente, int, String) de mídia.
     *
     * @param midia Mídia a ser avaliada
     * @param avaliador Cliente que está avaliando
     * @param nota  Nota a ser atribuída à mídia avaliada
     * @param comentario Comentário a ser atribuído na mídia avaliada
     * @throws IllegalStateException Caso este cliente já tenha avaliado a mesma mídia anteriormente
     */
	@Override
	public void avaliarMidia(Midia midia, Cliente avaliador, int nota, String comentario) throws IllegalStateException {
		midia.criarAvaliacao(avaliador, nota, comentario);
	}
}
