
package CapadeDomini;

import java.util.Map;
import java.util.Scanner;



public class Aula {

    static Iterable<Map.Entry<String, Aula>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private String nomAula=" ";
    private int capacitatA=0;
    private boolean Aulalaboratori = false;
    
     public Aula() { }
   
    public Aula(String nomaula, int capacitatA, boolean Aulalaboratori ) {
        this.nomAula=nomaula;
        this.capacitatA=capacitatA;
        this.Aulalaboratori=Aulalaboratori;   
    }
    
    public String get_nomA  () {
        return this.nomAula;
    }
    
    public int get_capA() {
        return this.capacitatA;
    }
    
    public boolean get_pcAU () {
        return this.Aulalaboratori;
    }
    
    public void set_nomA(String nomAula){
        this.nomAula = nomAula; 
    }
    
    public void set_capacitatA(int capacitatA){
        this.capacitatA = capacitatA; 
    }
    public void set_Aulalaboratori(boolean Aulalaboratori){
        this.Aulalaboratori = Aulalaboratori; 
    }
    public boolean esIgual(Aula au){
        return this.nomAula.equals(au.nomAula) 
                && this.capacitatA == au.capacitatA
                && this.Aulalaboratori == au.Aulalaboratori;
    }
    

}
    

    
