
package Drivers;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;



public class Sessio {
    //crear franjes horaries i relacionarho amb horari
    private int hores = 2;
    private int ids = 0;
    private List<FranjaHoraria> franjes = new ArrayList<FranjaHoraria>();
    
    public Sessio(){}
    
    public Sessio (Scanner sc, int hores, int ids) {
        //super(nomAss, grupg, )
        this.hores=hores;
        this.ids=ids;
        
    }
    
    public void afegir_franja(FranjaHoraria fh) {
        franjes.add(fh);
    }
    
    public void actualitzar_noma(String nomAssig) {
        for(FranjaHoraria fh:franjes) {
            fh.set_nomAss(nomAssig);
        }
            
    }
    
    public void actualitzar_nivell(String nivell) {
        franjes.forEach((fh) -> {
            fh.set_nivell(nivell);
        });
    }
    
    public List<FranjaHoraria> get_franjes() {
        return franjes;
    }
    public int get_horesS() {
        return hores;
    }
    public int get_ids(){
       return ids;
    }
    public void set_ids(int ids) {
        this.ids=ids;
    }
    
    public void set_hores(int hores) {
        this.hores=hores;
    }
    
}
