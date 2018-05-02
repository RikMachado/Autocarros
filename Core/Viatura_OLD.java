
package projeto.pp;
import java.util.*;
import java.lang.*;

class Viatura {

//Atributos
    int tipoAutocarro,num_max_pessoas,num_atual_pessoas,ID;
    //int cidadeOrigem, cidadeDestino, cidadeInt;
    Boolean Avariado = false, //SentidoSul;
    //int percurso;
    //String cidade;
    
    
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
