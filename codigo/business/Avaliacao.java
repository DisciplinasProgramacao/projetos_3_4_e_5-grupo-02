package business;

public class Avaliacao {

	private int nota;
	private Cliente cliente;  
	private String texto;
	
	public Avaliacao(Cliente cliente, int nota) {
		this.setCliente(cliente);
		this.setNota(nota);
	}
	
	public Avaliacao(Cliente cliente, int nota, String texto) {
		this.setCliente(cliente);
		this.setNota(nota);
		this.setTexto(texto);
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		if (nota >= 1 && nota <= 5) {
			this.nota = nota;
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
