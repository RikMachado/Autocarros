
package projeto.pp;
import java.util.*;
import java.lang.*;

class Viatura extends Thread{

//Atributos
    int tipoAutocarro,num_max_pessoas,num_atual_pessoas,ID;
    int cidadeOrigem, cidadeDestino, cidadeInt;
    Boolean Avariado = false, SentidoSul;
    int percurso;
    String cidade;
    
    public Viatura(String cidadeOrigem, String cidadeDestino, boolean SentidoSul){
       
        this.SentidoSul = SentidoSul; 
        DefinirCidade(cidadeOrigem);
        this.cidadeOrigem = cidadeInt;
        DefinirCidade(cidadeDestino);  
        this.cidadeDestino = cidadeInt;
       
        percurso =  Math.abs(this.cidadeDestino - this.cidadeOrigem);
      
        if (this.SentidoSul == true) { 
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
    
@Override
   public void run(){
      
      //Verificar Sentido e Calcular Percurso 
      int i = cidadeOrigem;
      for (int x=i; x<=(i+percurso) ;x++)
      {
        if (SentidoSul == false){
             
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
}

class Convencional extends Viatura{
    
    public Convencional(int ID, int num_atual_pessoas, String cidadeOrigem, String cidadeDestino, boolean SentidoSul){
        
        super(cidadeOrigem, cidadeDestino, SentidoSul);
        this.num_max_pessoas = 51;
        
        if (num_atual_pessoas <= num_max_pessoas){
            this.tipoAutocarro = 1;
            
            this.ID = ID;
            this.num_atual_pessoas = num_atual_pessoas;
            // show();
        }
        else {
            System.out.println("Número de passageiros excede o máximo possível para este Autocarro!");
            System.out.println("Este autocarro permite no máximo 51 passageiros!");
    }
}
    
    public void show(){
        
        System.out.println(this.tipoAutocarro);
        System.out.println(this.num_max_pessoas);
        System.out.println(this.ID);
        System.out.println(this.num_atual_pessoas);
        System.out.println(this.cidadeOrigem);
        System.out.println(this.cidadeDestino);
    
    }
}

class MiniBus extends Viatura{
    
    public MiniBus(int ID, int num_atual_pessoas, String cidadeOrigem, String cidadeDestino, boolean SentidoSul){
        
        super(cidadeOrigem, cidadeDestino, SentidoSul);
        
        if (num_atual_pessoas <= num_max_pessoas){
            this.tipoAutocarro = 2;
            this.num_max_pessoas = 24;
            this.ID = ID;
            this.num_atual_pessoas = num_atual_pessoas;
            
            //show();
        }
        else {
            System.out.println("Número de passageiros excede o máximo possível para este Autocarro!");
            System.out.println("Este autocarro permite no máximo 24 passageiros!");
        }
    }
}
    
class LongDrive extends Viatura{
   
    public LongDrive(int ID, int num_atual_pessoas, String cidadeOrigem, String cidadeDestino, boolean SentidoSul){
    
    super(cidadeOrigem, cidadeDestino, SentidoSul);    
        
        if (num_atual_pessoas <= num_max_pessoas){
            this.tipoAutocarro = 3;
            this.num_max_pessoas = 59;
            this.ID = ID;
            this.num_atual_pessoas = num_atual_pessoas;
            //show();
        }
        else {
            System.out.println("Número de passageiros excede o máximo possível para este Autocarro!");
            System.out.println("Este autocarro permite no máximo 59 passageiros!");
        }
    }
}

class Expresso extends Viatura{
    
    public Expresso(int ID, int num_atual_pessoas, String cidadeOrigem, String cidadeDestino, boolean SentidoSul){
    
    super(cidadeOrigem, cidadeDestino, SentidoSul);    
        
        if (num_atual_pessoas <= num_max_pessoas){
            this.tipoAutocarro = 4;
            this.num_max_pessoas = 69;
            this.ID = ID;
            this.num_atual_pessoas = num_atual_pessoas;
            
            //show();
        }
        else {
            System.out.println("Número de passageiros excede o máximo possível para este Autocarro!");
            System.out.println("Este autocarro permite no máximo 69 passageiros!");
        }
    }
}

public class ProjetoPP {

    public static void main(String[] args) {
      
        Convencional c1 = new Convencional(1,50,"Braga","Lisboa",true);
        Thread t1 = new Thread(c1);
        t1.start();
        
    }
    
}
