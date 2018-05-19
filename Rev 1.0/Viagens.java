package Syst;

/**
*@author Dymsi
*@version 1.00
**/

public class Viagens extends Thread {

    // <editor-fold defaultstate="collapsed" desc="Variaveis.">
    private int tipoAutocarro, cidadeOrigem, cidadeDestino, cidadeInt, percurso, proxCidade;
    private int posCoords = 0;
    private Boolean sentidoSul;
    private Boolean emViagem;
    private String cidade;
    Viaturas autocarro;
    Logger log = new Logger("Viagens.java");
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructores.">
    /**
     *
     * Construtor da class Viagens.
     * <br />
     * Construtor utilizado para criar um objecto Viagem. Pode ser tanto
     * utilizado com variaveis á parte, ou com um array;
     *
     * @param tipoAutocarro - tipo do autocarro a fazer a viagem
     * @param cidadeOrigem - cidade origem onde irá começar a viagem
     * @param cidadeDestino - cidade destino onde irá acabar a viagem
     * @param isSul - boolean que representa o sentido da viagem
     * @param ID - id numérico utilizado para numerar a viagem. ID deverá ser
     * igual ao index correspondente na lista de viagens
     * @return Viagem - objecto Viagem;
     * @since version 1.00
     */
    public Viagens(int tipoAutocarro, String cidadeOrigem, String cidadeDestino, boolean isSul, int ID) {
        this.tipoAutocarro = tipoAutocarro;
        this.sentidoSul = isSul;
        this.cidadeOrigem = getDistancia(cidadeOrigem, isSul);
        this.cidadeDestino = getDistancia(cidadeDestino, isSul);
        this.autocarro = new Viaturas(ID);
        this.emViagem = true;
        percurso = Math.abs(this.cidadeDestino - this.cidadeOrigem);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get Avaria.">
    /**
     *
     * Get Avaria.
     * <br />
     * Utilizado para dar return do estado do autocarro (se está avariado ou
     * não)
     *
     * @return Boolean true ou false, consoante o estado da variável
     * @since version 1.00
     */
    public Boolean getAvaria() {

        return this.autocarro.getAvaria();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get Origem.">
    /**
     *
     * Get Origem.
     * <br />
     * Utilizado para dar return da cidade origem de uma viagem;
     *
     * @return String - nome da cidade origem
     * @since version 1.00
     */
    public String getOrigem() {
        return getCidade(this.cidadeOrigem, this.sentidoSul);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get Destino.">
    /**
     *
     * Get Destino.
     * <br />
     * Utilizado para dar return da cidade destino de uma viagem;
     *
     * @return String - nome da cidade destino
     * @since version 1.00
     */
    public String getDestino() {
        return getCidade(this.cidadeDestino, this.sentidoSul);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get Sentido.">    
    /**
     *
     * Get Sentido.
     * <br />
     * Utilizado para dar return do sentido da viagem, em forma de Boolean; True
     * - Sentido Sul False - Sentido Norte
     *
     * @return Boolean - sentido da viagem.;
     * @since version 1.00
     */
    public Boolean getSentido() {
        return this.sentidoSul;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get emViagem.">    
    /**
     *
     * Get emViagem.
     * <br />
     * Utilizado para dar return do estado da viagem; True - Em viagem; False -
     * Parado;
     *
     * @return Booleam - estado da viagem;
     * @since version 1.00
     */
    public Boolean getEmViagem() {
        return this.emViagem;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get Cidade.">    
    /**
     *
     * Get Cidade.
     * <br />
     * Utilizado para dar return da associação entre o numero identificador da
     * cidade, e o nome da cidade;
     *
     * @param distPercorrida - Int identificador da cidade
     * @param sentido - Sentido da viagem
     * @return String - nome da cidade
     * @since version 1.00
     */
    public String getCidade(int distPercorrida, boolean sentido) {
        String[] cidades = {"Cascais", "Lisboa", "Coimbra", "Porto", "Braga"};
        int[] distancias = {0, 20, 50, 75, 100};
        if (sentido == true) {
            distancias = invertArray(distancias);
        }
        for (int i = 0; i < cidades.length; i++) {
            if (distancias[i] == distPercorrida) {
                return cidades[i];
            }
        }
        log.print("Nao foi encontrada uma cidade correspondente. > Viagens.getCidade", 1);
        return "Cidade desconhecida";
    }
    // </editor-fold>
     
    // <editor-fold defaultstate="collapsed" desc="Get Distancia.">    
    /**
     *
     * Get Distancia.
     * <br />
     * Utilizado para dar return da associação entre o numero identificador da
     * cidade, e o nome da cidade;
     *
     * @param distPercorrida - nome da cidade
     * @param sentido - Sentido da viagem
     * @return int - Int identificador da cidade
     * @since version 1.00
     */
    private int getDistancia(String cidade, boolean sentido) {
        String[] cidades = {"Cascais", "Lisboa", "Coimbra", "Porto", "Braga"};
        int[] distancias = {0, 20, 50, 75, 100};
        if (sentido == true) {
            distancias = invertArray(distancias);
        }
        for (int i = 0; i < cidades.length; i++) {
            if (cidades[i] == cidade) {
                return distancias[i];
            }
        }
        log.print("Nao foi encontrada uma cidade correspondente. > Viagens.getDistancia", 1);
        return 999999;
    }
    // </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="Set Avaria.">    
    /**
     *
     * Set Avaria.
     * <br />
     * Utilizado para definir o estado da varialvel Avariado;
     *
     * @param Boolean - estado a definir
     * @since version 1.00
     */
    public void setAvaria(Boolean estado) {

        autocarro.setAvaria(estado);
    }
    // </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="Set EmViagem.">    
    /**
     *
     * Set EmViagem.
     * <br />
     * Utilizado para definir o estado da varialvel emViagem;
     *
     * @param Boolean - estado da variavel
     * @since version 1.00
     */
    public void setEmViagem(Boolean estado) {

        this.emViagem = estado;
    }
    // </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="invertArray.">    
    /**
     *
     * invertArray.
     * <br />
     * Utilizado para dar inverter arrays;
     *
     * @param arr - array a inverter
     * @return int[] - array invertido
     * @since version 1.00
     */
    private int[] invertArray(int[] arr) {
        int arrSize = arr.length;
        int[] returnArr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            returnArr[i] = arr[arrSize - 1 - i];
        }
        return returnArr;
    }
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="isDist.">    
    /**
     *
     * isDist.
     * <br />
     * Utilizado para verificar se valor se encontra no array;
     *
     * @param dist - valor a verificar
     * @return Boolean - verificaçao se o valor se encontra no array
     * @since version 1.00
     */
    public boolean isDist(int dist) {
        int[] distancias = {0, 20, 50, 75, 100};
        for (int i = 0; i < distancias.length; i++) {
            if (dist == distancias[i]) {

                return true;
            }
        }
        return false;
    }
    // </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="getProxCidade.">    
    /**
     *
     * getProxCidade.
     * <br />
     * Utilizado para dar return da proxima cidade na cidade;
     *
     * @return int - numero identificador da cidade
     * @since version 1.00
     */
    public int getProxCidade() {
        int currCoords = getCoords();
        int[] distancias = {0, 20, 50, 75, 100};
        for (int i = 0; i < distancias.length; i++) {
            if (currCoords <= distancias[i]) {
                return distancias[i];
            }
        }
        return currCoords;
    }
    // </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="getCoords.">    
    /**
     *
     * getCoords.
     * <br />
     * Utilizado para dar return das coordenadas actuais do veiculo;
     *
     * @return int - coordenadas actuais
     * @since version 1.00
     */
    public int getCoords() {
        return this.posCoords;
    }
    // </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="getAutocarro.">    
    /**
     *
     * getAutocarro.
     * <br />
     * Utilizado para se obter o ID do autocarro associado ao objecto;
     *
     * @return String - id do autocarro
     * @since version 1.00
     */
    public String getAutocarro() {
        return this.autocarro.getID();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get Coordenadas para desenhar na imagem.">    
    /**
     *
     * Get Coordenadas para desenhar na imagem.
     * <br />
     * É utilizado o int com a posição atual (entre 0 - 100) e comparado com
     * um array de valores para se obter as coordenadas apropriadas para fazer
     * o display da posição no ecra
     *
     * @return Int[] - coordenadas para display
     * @since version 1.00
     */
    public int[] getPosicao() {
        int nossaPos = getCoords();
        nossaPos = nossaPos % 25;
        int proxCidade = getProxCidade();
        int[] x = new int[5];
        int k = 1;
        int valorCidade = 0;
        
        //Coordenadas da imagem
        String[][] coordsCidades = {{"110,280", "155,280", "190,280", "190,280", "190,280"}, {"110,280", "120,250", "130,210", "190,280", "190,280"}, {"130,160", "130,140", "130,120", "190,280", "190,280"}, {"130,160", "130,140", "130,120", "190,280", "190,280"}};
        int[] local = {0, 25, 50, 75, 100};
               
        for (int i = 5; i <= 20; i += 5) {
            k++;
        }

        x[0] = 25;
        x = invertArray(x);
        
        //Criar IF code block para verificar. Todo o codigo a seguir so executa
        //se <emViagem> se encontrar cm o valor true;
        //else dar return de coordenadas que nao se encontrem a vista no ecra;
        for (int i = 1; i < x.length; i++) {
            if (nossaPos <= x[i]) {
                for (int f = 0; f < local.length; f++) {
                    if (local[f] == proxCidade) {
                        valorCidade = f;
                    }
                }
                
                String[] coords = coordsCidades[valorCidade][i].split(",");
                int[] intCoords = new int[2];
                intCoords[0] = Integer.parseInt(coords[0]);
                intCoords[1] = Integer.parseInt(coords[1]);
                return intCoords;
            }
        }
        
        log.print("Nao foi encontrada uma combinação de coordenadas possiveis. > Viagens.getDistancia", 1);
        int[] shitFailed = new int[]{999, 999};
        return shitFailed;
    }
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Thread Run.">    
    /**
     *
     * Thread Run.
     * <br />
     * Loop principal do objecto, onde é feita a iteração por todos os valores
     * entre cada cidade;
     *
     * @since version 1.00
     */
    public void run() {
        //Verificar Sentido e Calcular Percurso 
        int i = cidadeOrigem;
        for (int x = i; x <= (i + this.percurso); x++) {
            this.posCoords = x;
            if (this.getAvaria() == true) {
                try {
                    log.print("Autocarro " + getAutocarro() + " parado por avaria.");
                    Thread.sleep(12000);
                } catch (InterruptedException iE) {
                    iE.printStackTrace();
                }
                if (x != 0) {
                    x--;
                }
                this.setAvaria(false);
                this.setEmViagem(true);
            }

            try {
                Thread.sleep(500);
            } catch (Exception e){
                e.printStackTrace();
            }

            if (isDist(x)) {
                log.print("Autocarro " + getAutocarro() + " chegou a " + getCidade(x, sentidoSul) + ".");
                this.emViagem = false;
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.emViagem = true;
            }
        }
        this.emViagem = false;
    }
}
// </editor-fold>
