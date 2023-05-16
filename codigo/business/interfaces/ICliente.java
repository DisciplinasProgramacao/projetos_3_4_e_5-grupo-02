package business.interfaces;

import business.Lista;
import business.Midia;
import business.Serie;

public interface ICliente {
	
	public ICliente verificarCategoria(Lista<Serie> assistidas);
	public boolean avaliarMidia(Midia midia, int nota);

}
