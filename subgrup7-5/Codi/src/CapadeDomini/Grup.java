
package CapadeDomini;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.Set;


public class Grup {
    private String nomgrup="";
    private String tipus="";
    private int capacitatg;
    private List<Sessio> sessions = new ArrayList<>();
    private String pare="";
    
    public Grup() {
        
    }
    public Grup (String nomgrup, String tipus, String pare, int capacitat) {
        this.nomgrup=nomgrup;
        this.tipus=tipus;
        this.pare=pare;
        this.capacitatg=capacitat;
        
    }
    
    public boolean crear_sessio(String nomass, String nivell, String hores) {
        int h=Integer.parseInt(hores);
        int ids = (int) (Math.random() * 9999999) + 1;
        Sessio s = new Sessio(h,ids);
        FranjaHoraria Fh = new FranjaHoraria (nomass, nivell, nomgrup, capacitatg, tipus, ids, pare);
        for(int aux=0; aux<h; ++aux) s.afegir_franja(Fh);
        this.sessions.add(s);       
        return true;
    }
    
    public void borrar_sessio(int hores) {
        boolean primer=false;
        for (Iterator<Sessio> it = sessions.iterator(); it.hasNext() && !primer;) {
            Sessio s = it.next();
            if(s.get_horesS()==hores) {
                primer=true;
                sessions.remove(s);
            }
        }
    }
    
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
     public List consultar_sessions() {
         return sessions;
    }
    /*
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
    }*/
    
            
}
