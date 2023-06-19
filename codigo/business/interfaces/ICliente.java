package business.interfaces;
import business.entidades.Cliente;
import business.entidades.Midia;
import business.entidades.fracas.ClienteEspecialista;
import business.entidades.fracas.Serie;

import utils.Lista;

public interface ICliente {

	public static ICliente verificarCategoria(Lista<Serie> assistidas) {
		if (assistidas.size() >= 5)
			return new ClienteEspecialista();
		else
			return null;
	}

	public void avaliarMidia(Midia midia, Cliente avaliador, int nota, String comentario) throws IllegalStateException;

}
