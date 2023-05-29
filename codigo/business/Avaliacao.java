package business;

import business.interfaces.ICliente;

public class Avaliacao {
	// ATRIBUTOS
	private int nota;
	private Cliente cliente;
	private String comentario;

	// CONSTRUTORES
	public Avaliacao(Cliente cliente, int nota) {
		this.setCliente(cliente);
		this.setNota(nota);
	}
	
	public Avaliacao(Cliente cliente, String comentario) {
		this.setCliente((Cliente) cliente);
		this.setTexto(comentario);
	}

	// GETTERS E SETTERS
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
		return comentario;
	}

	public void setTexto(String comentario) {
		this.comentario = comentario;
	}

	// MÉTODOS

	/**
	 * Sobrepõe o método equals() da classe Java Object a fim de modificar o resultado da comparação entre
	 * dois objetos. Para isso, realiza downcast para Avaliação, possibilitando comparar o cliente avaliador
	 * de ambos objetos. Caso possuam o mesmo cliente avaliador, o método assegura que os dois objetos se
	 * tratam de uma mesma avaliação.
	 * @param o Objeto a ser comparado com this.
	 * @return Se os clientes avaliadores são iguais.
	 */
	@Override
	public boolean equals(Object o){
		Avaliacao outra = (Avaliacao) o;
		return this.cliente.equals(outra.cliente);
	}
}
