Cliente {
    private String nomeDeUsuario;
    private String senha;
    private Lista<Serie> listaParaVer;
    private Lista<Serie> listaJaVistas;

    public Cliente (nomeDeUsuario, senha, listaParaVer, listaJaVistas){
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.listaParaVer = listaParaVer
        this.listaJaVistas = listaJaVistas;
    }

    public void adicionarNaLista(Serie serie){
        if (PlataformaStreamimg.getSeries().contains(serie))
            this.listaParaVer.add(serie);
    };

    public void retirarDaLista(String nomeSerie){
        Series[] series = new Series[listaParaVer.size()];
        series = listaParaVer.allElements(series)

        for (Serie serie : series){
            if (serie.getNome().equals(nomeSerie))
                listaParaVer.remove(nomeSerie)
        }
    };


    public Lista<Serie> void filtrarPorGenero(String genero);
    public Lista<Serie> void filtrarPorIdioma(String idioma);
    public Lista<Serie> filtrarPorQtdEpisodios(int quantEpisodios);
    public void registrarAudiencia(Serie serie);

}