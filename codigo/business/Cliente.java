package business;


public class Cliente {
    // ATRIBUTOS
    private String nomeDeUsuario;
    private String senha;
    private Lista<Serie> listaParaVer;
    private Lista<Serie> listaJaVistas;

    // CONSTRUTORES
    public Cliente (String nomeDeUsuario, String senha){
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.listaParaVer = new Lista<Serie>();
        this.listaJaVistas = new Lista<Serie>();
    }

    // MÉTODOS

    /**
     * Adiciona uma série à lista de séries para ver. Caso a serie a ser adicionada já exista na lista,
     * a operação não é realizada.
     * @param nomeSerie Nome da série a ser adicionada
     */
    public void adicionarNaLista(Serie nomeSerie){
        Serie[] series = new Serie[listaParaVer.size()];
        series = listaParaVer.allElements(series);

        for (int i = 0; i < series.length; i++){
            if (series[i].getNome().equals(nomeSerie)) {
                break;
            } else {
                this.listaParaVer.add(nomeSerie);
            }
        }
    };

    /**
     * Retira uma série da lista de séries para ver, contanto que a série selecionada esteja presente na lista.
     * @param nomeSerie Nome da série a ser removida
     */
    public void retirarDaLista(String nomeSerie){
        Serie[] series = new Serie[listaParaVer.size()];
        series = listaParaVer.allElements(series);

        for (int i = 0; i < series.length; i++){
            if (series[i].getNome().equals(nomeSerie))
                listaParaVer.remove(i);
        }
    };

    /**
     * Filtra, entre as listas de séries para ver e de séries já vistas, aquelas que correspondem ao gênero selecionado.
     * @param genero gênero selecionado.
     * @return lista filtrada por gênero das séries encontradas
     */
    public Lista<Serie> filtrarPorGenero(String genero){
        Lista<Serie> seriesFiltradas = new Lista<Serie>();

        Serie[] series = new Serie[listaParaVer.size()];
        series = listaParaVer.allElements(series);

        for (Serie serie : series) {
            if (serie.getGenero().equals(genero)) {
                seriesFiltradas.add(serie);
            }
        }

        series = listaJaVistas.allElements(series);

        for (Serie serie : series) {
            if (serie.getGenero().equals(genero)) {
                seriesFiltradas.add(serie);
            }
        }

        return seriesFiltradas;
    };

    /**
     * Filtra, entre as listas de séries para ver e de séries já vistas, aquelas que correspondem ao idioma selecionado
     * @param idioma idioma selecionado.
     * @return lista filtrada por idioma das séries encontradas
     */
     public Lista<Serie> filtrarPorIdioma(String idioma){
         Lista<Serie> seriesFiltradas = new Lista<Serie>();

         Serie[] series = new Serie[listaParaVer.size()];
         series = listaParaVer.allElements(series);

         for (Serie serie : series) {
             if (serie.getGenero().equals(idioma)) {
                 seriesFiltradas.add(serie);
             }
         }

         series = listaJaVistas.allElements(series);

         for (Serie serie : series) {
             if (serie.getGenero().equals(idioma)) {
                 seriesFiltradas.add(serie);
             }
         }

         return seriesFiltradas;
     };

    /**
     * Filtra, entre as listas de séries para ver e de séries já vistas, aquelas que possuem quantidade de
     * episódios igual à selecionada.
     * @param quantEpisodios quantidade de episódios selecionada
     * @return lista filtrada por qtd. de episódios das séries encontradas
     */
     public Lista<Serie> filtrarPorQtdEpisodios(int quantEpisodios){
         Lista<Serie> filtrada = new Lista<Serie>();

         Serie[] series = new Serie[listaParaVer.size()];
         series = listaParaVer.allElements(series);

         for (Serie serie : series) {
             if (serie.getQuantidadeEpisodios() == quantEpisodios) {
                 filtrada.add(serie);
             }
         }

         series = listaJaVistas.allElements(series);

         for (Serie serie : series) {
             if (serie.getQuantidadeEpisodios() == quantEpisodios) {
                 filtrada.add(serie);
             }
         }
         return filtrada;
     };
     
    /**
     * Contabiliza audiência de uma série
     * @param serie série selecionada
     */
     public void registrarAudiencia(Serie serie){
         serie.registrarAudiencia();
     };
     
     public String getNomeUsuario() {
    	 return this.nomeDeUsuario;
     }
     
     public String getSenha() {
    	 return this.senha;
     }
}