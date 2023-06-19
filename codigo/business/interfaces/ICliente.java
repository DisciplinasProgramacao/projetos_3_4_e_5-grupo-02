package business.interfaces;

import business.entidades.Cliente;
import business.entidades.Midia;
import business.entidades.fracas.Serie;
import utils.Lista;

public interface ICliente {

	// Atributo com a qtd de filmes assistidos
	// Atributo com a qtd de listas

	public ICliente verificarCategoria(Lista<Serie> assistidas);	// deve ser static
	public void avaliarMidia(Midia midia, Cliente avaliador, int nota, String comentario) throws IllegalStateException;

}
