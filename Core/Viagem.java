package pp;

class viagem extends Thread{
    
    int tipoAutocarro, cidadeOrigem, cidadeDestino, cidadeInt, percurso;
    Boolean sentidoSul;
    String cidade;
    
    public viagem(int tipoAutocarro, String cidadeOrigem, String cidadeDestino, boolean isSul){
        
        this.tipoAutocarro = tipoAutocarro;
        this.sentidoSul = isSul; 
        this.cidadeOrigem = getDistancia(cidadeOrigem, isSul);
        this.cidadeDestino = getDistancia(cidadeDestino, isSul);
       
        percurso =  Math.abs(this.cidadeDestino - this.cidadeOrigem);
        
        /*
        if (this.sentidoSul == true) { 
            switch (cidadeOrigem){
                case "Cascais": this.cidadeOrigem = 100;
                    break;
                case "Lisboa": this.cidadeOrigem = 75;
                    break;
                case "Coimbra": this.cidadeOrigem = 50;
                    break;
                case "Porto": this.cidadeOrigem = 20;
                    break;
                case "Braga": this.cidadeOrigem = 0;
                    break;
            } 
        }
        */
    }
    
    /*
    public int DefinirCidade(String cidade){
       
        switch (cidade){
            case "Cascais": cidadeInt = 0;
                break;
            case "Lisboa": cidadeInt = 20;
                break;
            case "Coimbra": cidadeInt = 50;
                break;
            case "Porto": cidadeInt = 75;
                break;
            case "Braga": cidadeInt = 100;
                break;
        } 
        return cidadeInt;
    }
    */
     
    private String getCidade(int distPercorrida, boolean sentido){
        String[] cidades = {"Cascais", "Lisboa", "Coimbra", "Porto", "Braga"};
        int[] distancias = {0, 20, 50, 75, 100};
        if(sentido == true){
            distancias = invertArray(distancias);
        }
        for(int i = 0; i < cidades.length; i++){
            if(distancias[i] == distPercorrida){
                return cidades[i];
            }
        }
        return "AYYYYY SHIET";
    }
    
    
    private int getDistancia(String cidade, boolean sentido){
        String[] cidades = {"Cascais", "Lisboa", "Coimbra", "Porto", "Braga"};
        int[] distancias = {0, 20, 50, 75, 100}; 
        if(sentido == true){
            distancias = invertArray(distancias);
        }
        for(int i = 0; i < cidades.length; i++){
            if(cidades[i] == cidade){
                return distancias[i];
            }
        }
        return 999999;
    }
    
    private int[] invertArray(int[] arr){
        int arrSize = arr.length;
        int[] returnArr = new int[arrSize];
        
        /*
        for(int i = 0; i < arrSize/2; i++){
            int tempValue = arr[i];
            arr[i] = arr[arrSize - 1 - i];
            arr[arrSize - 1 - i] = tempValue;
        }
        */
        
        for(int i = 0; i < arrSize; i++){
            returnArr[i] = arr[arrSize - 1 - i];
        }
        
        return returnArr;
        
    }
    
    private boolean isDist(int dist){
        int[] distancias = {0, 20, 50, 75, 100};
        for(int i = 0; i < distancias.length; i++){
            if(dist == distancias[i]){
                return true;
            }     
        }
        
        return false;
    }
     
     
     
     
     

    
    public void run(){
      
    //Verificar Sentido e Calcular Percurso 
    int i = cidadeOrigem;
    for (int x=i; x<=(i + this.percurso) ;x++){
        if(isDist(x)){
            System.out.println(getCidade(x, sentidoSul));
        }
          
      
        
        /*
        if (sentidoSul == false){
             
            switch (x){
                case 0: System.out.println("Cascais");
                    break;
                case 20: System.out.println("Lisboa");
                    break; 
                case 50: System.out.println("Coimbra");
                    break;
                case 75: System.out.println("Porto");
                    break;
                case 100: System.out.println("Braga");
                    break;
            }
        }
        else{
            switch (x){
            case 0: System.out.println("Braga");
                break;
            case 20: System.out.println("Porto");
                break;
            case 50: System.out.println("Coimbra");
                break;
            case 75: System.out.println("Lisboa");
                break;
            case 100: System.out.println("Cascais");
                break;
            }
            */
        
        }   
        try{
            Thread.sleep(20);
        }
        catch(Exception e){}
      
    }
      //Avaria
}
