public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private Lista<Serie> listaParaVer;
    private Lista<Serie> listaJaVistas;

    public Cliente (String nomeDeUsuario, String senha, Lista listaParaVer, Lista listaJaVistas){
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.listaParaVer = new Lista<Serie>();
        this.listaJaVistas = new Lista<Serie>();
    }

    // Verifica se já não existe na lista e adiciona a série
    public void adicionarNaLista(Serie nomeSerie){
        Serie[] series = new Serie[listaParaVer.size()];
        series = listaParaVer.allElements(series)

        for (int i = 0; i < series.length; i++){
            if (series[i].getNome().equals(nomeSerie)) {
                break;
            } else {
                this.listaParaVer.add(nomeSerie);
            }
        }
    };

    public void retirarDaLista(String nomeSerie){
        Serie[] series = new Serie[listaParaVer.size()];
        series = listaParaVer.allElements(series)

        for (int i = 0; i < series.length; i++){
            if (series[i].getNome().equals(nomeSerie))
                listaParaVer.remove(i);
        }
    };

    // public Lista<Serie> filtrarPorGenero(String genero);
    // public Lista<Serie> filtrarPorIdioma(String idioma);
    // public Lista<Serie> filtrarPorQtdEpisodios(int quantEpisodios);
    // public void registrarAudiencia(Serie serie);

}