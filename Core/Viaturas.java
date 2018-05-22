
package projeto.pp;

import java.util.*;
import java.lang.*;

class testarPessoas extends Exception {

    public testarPessoas() {
        super();
    }

    public testarPessoas(String msg) {
        super(msg);
    }
}

public class Viaturas {

    //Atributos           
    int tipoAutocarro, num_max_pessoas = 51, num_atual_pessoas, ID, pessoasInicial;
    int velocidade = 70;
    int velocidadeBase = 70;
    double velocidadeDouble = 70;
    double velocidadeBaseDouble = 70;

    String tipoViatura = "debug";

    boolean Avariado = false; //SentidoSul;

    public Viaturas(int ID, int pessoasInicial) {

        this.ID = ID;
        this.pessoasInicial = pessoasInicial;
    }

    public Viaturas() {
    }

    public String getID() {

        return Integer.toString(ID);
    }

    public Boolean getAvaria() {

        return this.Avariado;
    }

    public int getPessoasInicial() {

        return this.pessoasInicial;
    }

    public String getTipoAutocarro() {

        return this.tipoViatura;
    }

    public void setAvaria(Boolean estado) {

        this.Avariado = estado;
    }

    public int getMaxPessoas() {

        return this.num_max_pessoas;
    }

    public int getVelocidade() {
        
        double tempCalc = ((this.velocidadeBaseDouble / this.velocidadeDouble) * 1000) + 400 + 1000;
        int returnValue = (int) tempCalc;

        return returnValue;
    }

    public double multTempo() {

        int returnValue = (int) ((this.velocidadeBaseDouble / this.velocidadeDouble) + 2);
        return returnValue;
    }

    void excPessMax(int pessoas, int pessoasMax) throws testarPessoas {
        
        if (pessoas > pessoasMax) {
            throw new testarPessoas();
        }
    }

}

class Convencional extends Viaturas {

    public Convencional(int ID, int pessoasInicial) {
        
        this.ID = ID;
        this.num_max_pessoas = 51;
        this.tipoViatura = "Convencional";
        this.velocidade = 80;
        this.velocidadeDouble = this.velocidade;
        
        try {
            this.pessoasInicial = pessoasInicial;
            excPessMax(this.pessoasInicial,this.num_max_pessoas);
        } catch (testarPessoas tP) {
            tP.printStackTrace();
        }
    }

}

class MiniBus extends Viaturas {

    public MiniBus(int ID, int pessoasInicial) {
        
        this.ID = ID;
        this.num_max_pessoas = 24;
        this.tipoViatura = "MiniBus";
        this.velocidade = 120;
        this.velocidadeDouble = this.velocidade;
        
        try {
            this.pessoasInicial = pessoasInicial;
            excPessMax(this.pessoasInicial,this.num_max_pessoas);
        } catch (testarPessoas tP) {
            tP.printStackTrace();
        }
        
    }

}

class LongDrive extends Viaturas {

    public LongDrive(int ID, int pessoasInicial) {
        
        this.ID = ID;
        this.num_max_pessoas = 59;
        this.tipoViatura = "LongDrive";
        this.velocidade = 50;
        this.velocidadeDouble = this.velocidade;
        
        try {
            this.pessoasInicial = pessoasInicial;
            excPessMax(this.pessoasInicial,this.num_max_pessoas);
        } catch (testarPessoas tP) {
            tP.printStackTrace();
        }
    }
}

class Expresso extends Viaturas {

    public Expresso(int ID, int pessoasInicial) {
        
        this.ID = ID;
        this.num_max_pessoas = 69;
        this.tipoViatura = "Expresso";
        this.velocidade = 100;
        this.velocidadeDouble = this.velocidade;
        
        try {
            this.pessoasInicial = pessoasInicial;
            excPessMax(this.pessoasInicial,this.num_max_pessoas);
        } catch (testarPessoas tP) {
            tP.printStackTrace();
        }
    }

}
