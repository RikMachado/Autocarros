
package projeto.pp;
import java.util.Random;
import projeto.pp.Viaturas.*;
import java.util.concurrent.Semaphore;

public class Viagens extends Thread {

    // <editor-fold defaultstate="collapsed" desc="Variaveis.">
    private int tipoAutocarro, cidadeOrigem, cidadeDestino, cidadeInt, percurso, proxCidade;
    private int posCoords = 0;
    private Boolean sentidoSul;
    private Boolean emViagem;
    private String cidade;
    private int maxPessoas, atualPessoas = 1, totalPessoas = 0;
    private static int totalPessoasStatic = 0;
    private int tempoEstimado;
    private int tempoEstInt;
    private int numRep = 2;
    private int numRepTotal = this.numRep * 2;
    private int repFeito = 0;
    private static int lucro;
    private int precoViagem = 12;
    private String origemBackup;
    private String destinoBackup;
    private String tipoAvaria;
    private static int totalAvaria;
    private String nomeMotorista;
    private boolean paragemForcada = false;
    private static int kmPercorridos;
    static Semaphore semaphore = new Semaphore(1);
    Motorista motoristaDesignado;
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
    public Viagens(int tipoAutocarro, String cidadeOrigem, String cidadeDestino, boolean isSul, int ID, Viaturas autocarro, Motorista motoristaDesignado) {
        this.tipoAutocarro = tipoAutocarro;
        this.sentidoSul = isSul;
        this.cidadeOrigem = getDistancia(cidadeOrigem, isSul);
        this.cidadeDestino = getDistancia(cidadeDestino, isSul);
        this.origemBackup = cidadeOrigem;
        this.destinoBackup = cidadeDestino;
        this.autocarro = autocarro;
        this.emViagem = true;
        this.tipoAvaria = "Avaria";
        this.motoristaDesignado = motoristaDesignado;
        this.motoristaDesignado.setEstado(true);
        this.nomeMotorista = motoristaDesignado.getNome();
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
        int[] distancias = {0, 25, 50, 75, 100};
        if (sentido == true) {
            distancias = invertArray(distancias);
        }
        for (int i = 0; i < cidades.length; i++) {
            if (distancias[i] == distPercorrida) {
                if (this.autocarro.getTipoAutocarro() == "Expresso" && distancias[i]==50) {
                    if (sentido == true) {
                        return cidades[i - 1];
                    } else {
                        return cidades[i + 1];
                    }
                } else
                return cidades[i];
            }
        }
        log.print("Nao foi encontrada uma cidade correspondente para o valor: " + distPercorrida + ". > Viagens.getCidade", 1);
        return "Cidade desconhecida";
    }
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Get ObjAutocarro">
    public Viaturas getObjAutocarro(){
        
        return this.autocarro;
    }
//</editor-fold>
     
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
        int[] distancias = {0, 25, 50, 75, 100};
        if (sentido == true) {
            distancias = invertArray(distancias);
        }
        for (int i = 0; i < cidades.length; i++) {
            if (cidades[i].equals(cidade)) {
                return distancias[i];
            }
        }
        log.print("Nao foi encontrada uma cidade correspondente para a String " + cidade + ". > Viagens.getDistancia", 1);
        return 999999;
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
        int[] distancias = {0, 25, 50, 75, 100};
        for (int i = 0; i < distancias.length; i++) {
            if (currCoords <= distancias[i]) {
                if (this.autocarro.getTipoAutocarro()!="Expresso")
                    return distancias[i];
                else if(currCoords!=50 || currCoords!=25)
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
    
    // <editor-fold defaultstate="collapsed" desc="getAtualPessoas.">    
    /**
     *
     * getAtualPessoas.
     * <br />
     * Utilizado para dar return do número atual de pessoas no Autocarro;
     *
     * @return int - número atual de pessoas
     * @since version 1.00
     */
    public int getAtualPessoas(){
        return this.atualPessoas;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getTotalPessoas.">    
    /**
     *
     * getTotalPessoas.
     * <br />
     * Utilizado para dar return do número total de pessoas na totalidade da viagem no Autocarro;
     *
     * @return int - número total de pessoas
     * @since version 1.00
     */
    public int getTotalPessoas(){
        return this.totalPessoas;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getTempoEstimado.">    
    /**
     *
     * getTempoEstimado.
     * <br />
     * Utilizado para calcular o tempo estimado incial de viagem do Autocarro;
     *
     * @return String - tempo estimado
     * @since version 1.00
     */    
    public String getTempoEstimado(){
    
        String tempo;
        int horas = 0;
        //double minutosRaw = tempoEstimado;
        int minutos = this.tempoEstimado; 
        
        while(minutos >= 60){
            
            minutos = minutos - 60;
            horas+=1;
        }
        //minutos = (int) minutos;
        tempo = horas + "h " + minutos + "m";
        
        return tempo;
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
        int nossaPosOriginal = getCoords();
        int nossaPos = nossaPosOriginal % 25;
        int proxCidade = getProxCidade();
        int[] x = new int[5];
        int k = 0;
        int valorCidade = 0;
        
        //Coordenadas da imagem
        String[][] coordsCidades = {{"73,270", "110,280", "155,280", "190,280", "236,260"}, //Cascais Lisboa//
                                    {"73,270", "110,280", "155,280", "190,280", "236,260"}, //Cascais Lisboa//
                                    {"90,290", "110,280", "120,250", "130,210", "132,172"}, 
                                    {"132,172", "130,160", "130,140", "130,120", "120,90"}, 
                                    {"83,260", "120,256", "140,230", "170,270", "160,160"}};
        
        int[] local = {0, 25, 50, 75, 100};
               
        for (int i = 5; i <= 20; i += 5) {
            x[k] = i;
            k++;
        }

        x[4] = 25;
        //x = invertArray(x);
        
        //Criar IF code block para verificar. Todo o codigo a seguir so executa
        //se <emViagem> se encontrar cm o valor true;
        //else dar return de coordenadas que nao se encontrem a vista no ecra;
        
        log.print("Posicao atual: " + Integer.toString(nossaPos),4);
        log.print("ProxCidade: " + proxCidade,4);
        if (this.emViagem == true){

            for (int i = 0; i < x.length; i++)
            {
                if (nossaPos <= x[i])
                {
                    for (int f = 0; f < local.length; f++)
                    {
                        if (local[f] == proxCidade)
                        {
                            valorCidade = f;
                            log.print("Coluna: " + (i+1) + " Linha:" + (f+1),4);
                        }
                    }

                    
                    String[] coords = coordsCidades[valorCidade][i].split(",");
                    int[] intCoords = new int[2];
                    intCoords[0] = Integer.parseInt(coords[0]);
                    intCoords[1] = Integer.parseInt(coords[1]);

                    log.print(intCoords[0] + " " + intCoords[1]);
                    return intCoords;
                }
            }
        }
        else if(this.emViagem == false && isDist(nossaPosOriginal) == true){
            
            String[] coords = {"73,270","236,260","132,172","120,90","160,160"};
            
            int[] intCoords = new int[2];
            for(int i = 0; i < local.length; i++){
                if(local[i] == nossaPosOriginal){
                    String[] coordsSplit = coords[i].split(",");
                    intCoords[0] = Integer.parseInt(coordsSplit[0]);
                    intCoords[1] = Integer.parseInt(coordsSplit[1]);
                    
                    return intCoords;
                }
            }
        }
        
        log.print("Nao foi encontrada uma combinação de coordenadas possiveis. > Viagens.getDistancia", 1);
        int[] shitFailed = new int[]{999, 999};
        return shitFailed;
    }
        
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getTotalPessoasStatic">    
    /**
     *
     * getTotalPessoasStatic
     * <br />
     * Utilizado para dar return do total de pessoas em viagem;
     *
     * @return Int - numero de pessoas;
     * @since version 1.00
     */
    public int getTotalPessoasStatic(){
        return totalPessoasStatic;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getTotalAvaria">    
    /**
     *
     * getTotalAvaria
     * <br />
     * Utilizado para dar return do total de avarias;
     *
     * @return Int - total de avarias;
     * @since version 1.00
     */
    public int getTotalAvaria(){
    
        return this.totalAvaria;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getEstadoAtual">    
    /**
     *
     * getEstadoAtual
     * <br />
     * Utilizado para dar return do estado actual do autocarro;
     *
     * @return String - estado actual;
     * @since version 1.00
     */
    public String getEstadoAtual(){
        
        if(getAvaria() == true){
        return this.tipoAvaria; 
        }
        
        else if (this.emViagem == true && getAvaria() == false){
            
            return "Em Viagem";
        }
        
        else if (this.emViagem == false && getAvaria() == false)
        {
            return "Paragem";
        }
        
        return "Situação desconhecida";
    }
     // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getTipoAvaria">    
    /**
     *
     * getTipoAvaria
     * <br />
     * Utilizado para dar return do tipo de avaria a afetar o autocarro;
     *
     * @return String - tipo de avaria;
     * @since version 1.00
     */
    private String getTipoAvaria(){
        
        return this.tipoAvaria;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getNomeMotorista">    
    /**
     *
     * getNomeMotorista
     * <br />
     * Utilizado para dar return do nome do motorista a utilizar o autocarro;
     *
     * @return String - nome do motorista;
     * @since version 1.00
     */
    public String getNomeMotorista(){
    
        return this.nomeMotorista;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getParagemForcada">    
    /**
     *
     * setParagemForcada
     * <br />
     * Utilizado para dar return do valor da variavel paragemForcada;
     *
     * 
     * @return estado - boolean com valor do estado
     * 
     * @since version 1.00
     */
    public boolean getParagemForcada(){
    
        return this.paragemForcada;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getLucro">    
    /**
     *
     * getLucro
     * <br />
     * Utilizado para dar return do lucro total;
     *
     * 
     * @return int - lucro total
     * 
     * @since version 1.00
     */
    public int getLucro(){
        return this.lucro;
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
    
    // <editor-fold defaultstate="collapsed" desc="setTotalPessoas.">    
    /**
     *
     * setTotalPessoas.
     * <br />
     * Utilizado para definir o número total de pessoas na totalidade da viagem no Autocarro;
     *
     * @param int - número total de pessoas
     * @since version 1.00
     */
    public void setTotalPessoas(int pessoas){
        this.totalPessoas = this.totalPessoas + pessoas;
        totalPessoasStatic = totalPessoasStatic + pessoas;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="setNomeMotorista">    
    /**
     *
     * setNomeMotorista
     * <br />
     * Utilizado para dar set do nome do motorista a utilizar o autocarro;
     *
     * @since version 1.00
     */
    public void setNomeMotorista(String nome){
    
        this.nomeMotorista = nome;
    }
     // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="setParagemForcada">    
    /**
     *
     * setParagemForcada
     * <br />
     * Utilizado para definir o valor da variavel paragemForcada;
     *
     * 
     * @param estado - boolean com valor do estado
     * 
     * @since version 1.00
     */
    public void setParagemForcada(boolean estado){
    
        this.paragemForcada = estado;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="setTipoAvaria">    
    /**
     *
     * setTipoAvaria
     * <br />
     * Utilizado para definir o valor da variavel tipoAvaria;
     *
     * 
     * @param randAvaria - valor Int utilizado para definir o tipo de avaria: <br />
     *    0 - Pneu Furado  <br />
     *    1 - Problema no motor  <br />
     *    2 - Falta de combustível  <br />
     *    3 - Acidente rodoviário  <br />
     * 
     * 
     * @since version 1.00
     */
    private void setTipoAvaria(int randAvaria){
        
        switch(randAvaria) {
            case 0: this.tipoAvaria = "Pneu Furado";
            break;
            
            case 1: this.tipoAvaria = "Problema no motor";
            break;
            
            case 2: this.tipoAvaria = "Falta de combustível";
            break;
            
            case 3: this.tipoAvaria = "Acidente rodoviário";
            break;
            
            case 4: this.tipoAvaria = "Sem problemas";
            break;
        }
        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="addKm.">    
    /**
     *
     * addKm.
     * <br />
     * Utilizado para adicionar km's totais;
     *
     * @param Boolean - estado da variavel
     * @since version 1.00
     */
    private void addKm() throws InterruptedException{
        semaphore.acquire();
        kmPercorridos += 25;
        semaphore.release();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="addLucro.">    
    /**
     *
     * addLucro.
     * <br />
     * Utilizado para adicionar ao lucro total;
     *
     * @param Boolean - estado da variavel
     * @since version 1.00
     */
    private synchronized void addLucro(){
        int novoLucro = this.atualPessoas * this.precoViagem;
        lucro += novoLucro;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="addAvariaTempo.">    
    /**
     *
     * addAvariaTempo.
     * <br />
     * Utilizado para adicionar o tempo das avarias duranta a viagem do Autocarro;
     *
     * @return String - tempo estimado
     * @since version 1.00
     */    
    public void addAvariaTempo(){
    
        tempoEstimado = tempoEstimado + 12 * 2;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="calcTempoEstInicial.">    
    /**
     *
     * calcTempoEstInicial.
     * <br />
     * Utilizado para calcular o tempo estimado incial de viagem do Autocarro;
     *
     * @param int - número atual de pessoas
     * @since version 1.00
     */
    public void calcTempoEstInicial(int perViagem){
    
        this.tempoEstimado = perViagem;
        this.tempoEstInt = perViagem;
        
        if(this.autocarro.getTipoAutocarro()!="Expresso"){
        switch (this.tempoEstInt){
            case (50) : this.tempoEstimado += 10;
                break;
            case 75: this.tempoEstimado += 20;
                break;
            case 100: this.tempoEstimado += 30;
                break;
        }
        }else{
            if (this.tempoEstInt == 75)
                this.tempoEstimado += 10;
        }
        
        
        
        
        //log.print("AUTOCARRO "+this.autocarro.getID()+" - VELOCIDADE: "+ this.autocarro.multTempo(),4);
        this.tempoEstimado *= this.autocarro.multTempo();
        //log.print("AUTOCARRO "+this.autocarro.getID()+" - PERCURSO: "+ perViagem,4);
        //log.print("AUTOCARRO "+this.autocarro.getID()+" - TEMPO ESTIMADO: "+ this.tempoEstimado,4);
        
        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="calcAtualPessoas.">    
    /**
     *
     * calcAtualPessoas.
     * <br />
     * Utilizado para definir o número de pessoas a entrar e sair do Autocarro;
     *
     * @param int - número atual de pessoas
     * @since version 1.00
     */
    public int calcAtualPessoas(){
    Random rand = new Random();
        
        // Pessoas que saem
        int atualPessoasTemp = this.atualPessoas - 1;
        this.atualPessoas = rand.nextInt(atualPessoasTemp+1);
            
        //log.print("Autocarro " + getAutocarro() + " - Pessoas que sairam:" + this.atualPessoas);
        // Pessoas que entram
        int diferenca = this.maxPessoas - this.atualPessoas;
        
        this.atualPessoas = this.atualPessoas + (rand.nextInt(diferenca - 1 ) + 1);
        //log.print("Autocarro " + getAutocarro() + " - Pessoas atuais:" + this.atualPessoas);
        this.setTotalPessoas(this.atualPessoas);
        
        
        return this.atualPessoas;
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
        int[] distancias = {0, 25, 50, 75, 100};
        for (int i = 0; i < distancias.length; i++) {
            if (dist == distancias[i]) {
                if (this.autocarro.getTipoAutocarro()!="Expresso")
                    return true;
                else if(dist!=50)
                    return true;
            }
        }
        return false;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="avariaEvento">    
    /**
     *
     * avariaEvento
     * <br />
     * Método evento utilizado para definir o Sleep time de uma avaria e o tipo
     * de avaria;
     *
     * 
     *@return Int - duração da avaria;
     * 
     * @since version 1.00
     */
    private int avariaEvento(){
        
        Random rand = new Random();
        int durationProblema = rand.nextInt(15) + 5;
        int durationAjuda = rand.nextInt(20) + 5;
        int avaria = rand.nextInt(4);
        int durationAvaria = (durationProblema + durationAjuda) * 1000;
        
        setTipoAvaria(avaria);
        totalAvaria+=1;
        
        return durationAvaria;
        
        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="prepararViagem">    
    /**
     *
     * prepararViagem
     * <br />
     * Método utilizado para preparar a viagem de regresso, contando o numero de vezes
     * que já o fez;
     *
     * 
     * @since version 1.00
     */
    private void prepararViagem() {
        if (sentidoSul == true) {
            sentidoSul = false;
        } else {
            sentidoSul = true;
        }

        String tempDestino = this.destinoBackup;
        String tempOrigem = this.origemBackup;

        this.cidadeOrigem = getDistancia(this.destinoBackup, this.sentidoSul);
        this.cidadeDestino = getDistancia(this.origemBackup, this.sentidoSul);

        this.destinoBackup = tempOrigem;
        this.origemBackup = tempDestino;

        this.percurso = Math.abs(this.cidadeDestino - this.cidadeOrigem);
        this.posCoords = this.cidadeOrigem;

        this.atualPessoas = 0;

        //Sleep para dar ideia que esta parado na paragem;
        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        };

        this.repFeito++;
        this.emViagem = true;
        log.print("Autocarro " + getAutocarro() + " fez " + this.repFeito + " viagens de " + this.numRepTotal);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="pessoasIniciais.">    
    /**
     *
     * pessoasIniciais.
     * <br />
     * Utilizado para definir o número inicial de pessoas no Autocarro;
     *
     * @param int - número atual de pessoas
     * @since version 1.00
     */
    public int pessoasIniciais(){
    Random rand = new Random();
        
        this.maxPessoas = this.autocarro.getMaxPessoas();
        int maxPessoasTemp = this.maxPessoas - 1;
        this.atualPessoas = rand.nextInt(maxPessoasTemp+1);
        this.setTotalPessoas(this.atualPessoas);
        //log.print("Autocarro " + getAutocarro() + " - Pessoas iniciais:" + this.atualPessoas);
        
        return this.atualPessoas;
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
        
        while (this.repFeito < this.numRepTotal) {
         
            int i = this.cidadeOrigem;
            this.pessoasIniciais();
            this.calcTempoEstInicial(this.percurso);

            for (int x = i; x <= (i + this.percurso); x++) {
                this.posCoords = x;
                if (this.paragemForcada == false) {
                    if (this.getAvaria() == true) {
                        try {
                            log.print("Autocarro " + getAutocarro() + " parado por avaria.");
                            this.addAvariaTempo();
                            Thread.sleep(avariaEvento());
                            setTipoAvaria(4);
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
                        //log.print(Integer.toString(this.autocarro.getVelocidade()),4);
                        Thread.sleep(this.autocarro.getVelocidade());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (isDist(x)) {
                        if (x != i) {
                            log.print("Autocarro " + getAutocarro() + " chegou a " + getCidade(x, sentidoSul) + ".");
                            this.calcAtualPessoas();
                            try{
                                this.addKm();
                                this.addLucro();
                            }
                            catch(InterruptedException e){
                                e.printStackTrace();
                            }
                            
                        }

                        this.emViagem = false;
                        try {
                            Thread.sleep(10000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.emViagem = true;
                    }
                } else {
                    try{
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e){e.printStackTrace();}
                    
                    x = x - 1;
                }
            }

            this.emViagem = false;
            this.prepararViagem();

        }
        this.posCoords = this.cidadeDestino;
        this.emViagem = false;
        log.print("Autocarro " + getAutocarro() + " acabou o dia!");

    }

}
// </editor-fold>
    
    
    
    
    
    
    
    
        
    

    
    
    
    
    
    
    
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        
