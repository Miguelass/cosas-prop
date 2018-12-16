
package Drivers;

import java.util.Scanner;



public class Aula {
    private String nomAula="Aula";
    private int capacitatA=30;
    private boolean Aulalaboratori = false;
    
     public Aula() {
    }
   
    public Aula(Scanner sc, String nomaula, int capacitatA, boolean Aulalaboratori ) {
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
    

}
    

    
