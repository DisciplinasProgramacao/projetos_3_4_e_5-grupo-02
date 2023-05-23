package business;

import business.interfaces.ICliente;

public class Avaliacao {

	private int nota;
	private ICliente cliente;
	private String comentario;
	
	public Avaliacao(ICliente cliente, int nota) {
		this.setCliente(cliente);
		this.setNota(nota);
		// ? Aqui deveria ser inicializado String comentário?
	}
	
	public Avaliacao(ICliente cliente, String comentario) {
		this.setCliente(cliente);
		this.setTexto(comentario);
		// ? Aqui deveria ser inicializado int nota?
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		if (nota >= 1 && nota <= 5) {
			this.nota = nota;
		}
	}

	public ICliente getCliente() {
		return cliente;
	}

	public void setCliente(ICliente cliente) {
		this.cliente = cliente;
	}

	public String getTexto() {
		return comentario;
	}

	public void setTexto(String comentario) {
		this.comentario = comentario;
	}
}
