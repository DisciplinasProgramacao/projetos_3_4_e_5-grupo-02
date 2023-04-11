Cliente {
    private String nomeDeUsuario;
    private String senha;
    private Lista<Serie> listaParaVer;
    private Lista<Serie> listaJaVistas;

    public void adicionarNaLista(Serie serie);
    public void retirarDaLista();
    public void filtrarPorGenero();

}