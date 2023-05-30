package business.interfaces;

import business.Cliente;
import business.Lista;
import business.Midia;
import business.Serie;
import business.exceptions.ElementoJaExisteException;

public interface ICliente {

	// Atributo com a qtd de filmes assistidos
	// Atributo com a qtd de listas

	public ICliente verificarCategoria(Lista<Serie> assistidas);	// deve ser static
	public void avaliarMidia(Midia midia, Cliente avaliador, int nota, String comentario) throws IllegalStateException;

}
