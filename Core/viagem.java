package autocarro;

class viagem extends Thread{
    
    int tipoAutocarro, cidadeOrigem, cidadeDestino, cidadeInt, percurso;
    Boolean sentidoSul;
    String cidade;
    
    public viagem(int tipoAutocarro, String cidadeOrigem, String cidadeDestino, boolean SentidoSul){
        
        this.tipoAutocarro = tipoAutocarro;
        this.sentidoSul = sentidoSul; 
        DefinirCidade(cidadeOrigem);
        this.cidadeOrigem = cidadeInt;
        DefinirCidade(cidadeDestino);  
        this.cidadeDestino = cidadeInt;
       
        percurso =  Math.abs(this.cidadeDestino - this.cidadeOrigem);
      
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
    }
    
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

    
    public void run(){
      
      //Verificar Sentido e Calcular Percurso 
      int i = cidadeOrigem;
      for (int x=i; x<=(i+percurso) ;x++)
      {
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
        
        }   
        try{
            Thread.sleep(20);}
        catch(Exception e){}
      
      }
      //Avaria
   }

            
    
}