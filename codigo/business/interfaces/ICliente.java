package business.interfaces;

import business.Lista;
import business.Midia;
import business.Serie;

public interface ICliente {

	// Atributo com a qtd de filmes assistidos
	// Atributo com a qtd de listas
	
	public ICliente verificarCategoria(Lista<Serie> assistidas);	// deve ser static
	public boolean avaliarMidia(Midia midia, int nota);
	public boolean avaliarMidia(Midia midia, String comentario);

}
