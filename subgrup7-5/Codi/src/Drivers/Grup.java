
package Drivers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;

/**
 *
 * @author Miguel
 */
public class Grup {
    private String nomgrup="20";
    private String tipus="Teoria";
    private int capacitatg = 20;
    private List<Sessio> sessions = new ArrayList<Sessio>();
    private String pare="21";
    
    public Grup() {
        
    }
    
    public Grup (Scanner sc, String nomgrup, String tipus, String pare, int capacitat) {
        this.nomgrup=nomgrup;
        this.tipus=tipus;
        this.pare=pare;
        this.capacitatg=capacitat;
        
    }
    public void assignar_sessions(Scanner sc, String nomass, String nivell) {
        System.out.println("Introdueix cuantes sessions te aquest grup: (no hi pot haver-hi mes sessions que dies)");
        int nsessions=sc.nextInt();
        int ids=0;
        while(nsessions>0) {
            System.out.println("Quantes hores te la Sessio(max=4): ");
            int hores=sc.nextInt();
            while(hores>4 || hores <1) {
                System.out.println("Hora no valida, torna-la a introduir(max=4): ");
                hores=sc.nextInt();
            }
            Sessio S = new Sessio(sc, hores, ids);
            FranjaHoraria Fh = new FranjaHoraria(nomass, nomgrup, tipus, ids, pare, nivell,capacitatg);
            for(int aux=0; aux<hores; ++aux) S.afegir_franja(Fh);
            this.sessions.add(S);    
            --nsessions;
            ++ids;
        }
    }
    /*
    public void afegir_sessio(Scanner sc , String nomass, String nivell) {
        Sessio S = new Sessio();
        S.crear_sessio(sc, nomass, nomgrup, tipus, pare, nivell, capacitatg);
        S.set_ids(sessions.size());
        this.sessions.add(S);   
    }
    
    public Sessio borrar_sessio(int ids) {
        Iterator it1=sessions.iterator();
        boolean trobat=false;
        Sessio s =(Sessio)it1.next();
        while(!trobat && it1.hasNext()) {
            if(sessions.equals(s.get_ids())) { 
                sessions.remove(s);
                trobat=true;
            }
            if(it1.hasNext()) s =(Sessio)it1.next();
        }
    }*/
    public void actualitzar_noma(String nomAssig) {
        sessions.forEach((ss) -> {
            ss.actualitzar_noma(nomAssig);
        });
    }
    
    public void actualitzar_nivell(String nivell) {
        sessions.forEach((ss) -> {
            ss.actualitzar_nivell(nivell);
        });
    }
    
    public String get_nomg() {
        return this.nomgrup;
    }
    public String get_tipusg() {
        return this.tipus;
    }
    public int get_capg() {
        return this.capacitatg;
    }
    public List<Sessio> get_sessions(){
        return this.sessions;
    }   
    public String get_pare() {
        return this.pare;
    }
    public void set_nomgrup(String nomgrup ) {
        this.nomgrup=nomgrup;
    }
    public void set_tipus(String tipus) {
        this.tipus=tipus;
    }
    public void set_capacitat(int capacitatg ) {
        this.capacitatg=capacitatg;
    }
    public void set_sessions(List<Sessio> sessions ) {
        this.sessions=sessions;
    }
    public void set_pare(String pare) {
        this.pare=pare;
    }
            
}
