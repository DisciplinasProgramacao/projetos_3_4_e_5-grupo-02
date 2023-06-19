package business.interfaces;

import business.entidades.Cliente;
import business.entidades.Midia;
import business.exceptions.MidiaNaoEncontradaException;

public interface IClienteProfissional {
    public void assistirMidia(Midia midia, Cliente watcher) throws MidiaNaoEncontradaException;
}
