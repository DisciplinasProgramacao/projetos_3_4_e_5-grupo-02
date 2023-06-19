package business.exceptions;

import business.entidades.Midia;

public class MidiaNaoEncontradaException extends Exception {

	public MidiaNaoEncontradaException() {
		super("Midia não encontrada");
	}
	
}
