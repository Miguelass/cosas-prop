
package Drivers;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.ListIterator;


public class Asignatura {

    private String nomass="Asignatura"; 
    private String nivell="N1";
    boolean isAssigOB=false; 
    private List<Grup> Grups= new ArrayList<Grup>();

    
    public Asignatura () {
         
    }
    
    public Asignatura (Scanner sc, String nomAssig, String nivell, boolean isAssigOB) {
        this.nomass=nomAssig;
        this.nivell=nivell;
        this.isAssigOB=isAssigOB;
        
    }
    
    public void assignar_grups(Scanner sc) {
        System.out.println("Introdueix el nom del grup/'exit' si no vol introduir-ne més:");
        String nom_grup=sc.next();
        while(!nom_grup.equals("exit")) {
                System.out.println("Introdueix el tipus de grup (Teoria/Laboratori/Problemes) :");
                String tipus=sc.next();
                while(!tipus.equals("Laboratori")  && !tipus.equals("Teoria")  && !tipus.equals("Problemes") ) {
                    System.out.println("Has introduit el tipus de manera incorrecte, torna a introduir-ho (Teoria/Laboratori/Problemes): " );
                    tipus =sc.next();
                }
                String pare="";
                if(tipus.equals("Laboratori") || tipus.equals("Problemes")) {
                     System.out.println("Introdueix el grup pare del subgrup :");
                     pare = sc.next();
                }
                System.out.println("Introdueix la capacitat del grup :");
                int capacitatg=sc.nextInt();
                while(capacitatg<10 || capacitatg>500){
                    System.out.println("Capacitat incorrecte, introdueix-ne una altre entre 10-500");
                    capacitatg=sc.nextInt();
                }
        
            Grup gp = new Grup(sc, nom_grup, tipus, pare, capacitatg);
            gp.assignar_sessions(sc, nomass, nivell);
            Grups.add(gp);
            System.out.println("Introdueix el nom del grup/'exit' si no vol introduir-ne més:");
            nom_grup=sc.next();
            Iterator it=Grups.iterator();
            while(it.hasNext()) { //mirem si ja existeix una assignatura amb aquest nom
                Grup gpp = (Grup)it.next();
                if(gpp.get_nomg().equals(nom_grup)) {
                    System.out.println("Ja existeix un grup amb aquest nom, introdueix-ne un altre: ");
                    nom_grup=sc.next();
                    it=Grups.iterator();
                }
            }
        }
    }
    public void modificar_grups(Scanner sc) {
        //quin grup vols
        //accedir al grup
        //canviarli al nom/fetset
        //recorre 
    }
    public void actualitzar_nom (String nomAssig) {
        Grups.forEach((gu) -> {
            gu.actualitzar_noma(nomAssig);
        });
    }
    
    public void actualitzar_nivell(String nivell) {
        Grups.forEach((gu) -> {
            gu.actualitzar_nivell(nivell);
        });
    }
    
    public Grup buscar_grup(String nomgr) {
        for(Grup gu:Grups) {
            if(gu.get_nomg().equals(nomgr)) return gu;
        }
        return null;
    }
    
    public void borrar_grup(Grup g) {
        Grups.remove(g);
        
    }
    
  
    public void borrar_sessio(String grup, int ids) {
        Iterator it1=Grups.iterator();
        boolean trobat=false;
        Grup g =(Grup)it1.next();
        while(!trobat && it1.hasNext()) {
            if(grup.equals(g.get_nomg())) trobat=true;
            else if(it1.hasNext()) g =(Grup)it1.next();
        }
        //g.borrar_sessio(ids);
    }
    /*
    public void afegir_sessio(Scanner sc, String nomg) {
        Iterator it=Grups.iterator();
        boolean trobat=false;
            while(it.hasNext() && !trobat) { //mirem si ja existeix una assignatura amb aquest nom
                Grup gpp = (Grup)it.next();
                if(gpp.get_nomg().equals(nomg)) {
                    trobat=true;
                    gpp.afegir_sessio(sc, nomass, nivell);
                }
            }        
    }*/
    
    public List<Grup> get_grups(){
        return this.Grups;
    }
     public String get_nomass() {
        return this.nomass;
    }
    public String get_nivell() {
        return this.nivell;
    }
    public boolean get_isAssigOB() {
        return this.isAssigOB;
    }
    public void set_nomass(String nomAssig){
        this.nomass = nomAssig; 
    }
    public void set_isAssigOB(boolean isAssigOB){
        this.isAssigOB = isAssigOB; 
    }
    public void set_nomAssig(List<Grup> Grups){
        this.Grups = Grups; 
    }
    public void set_nivell(String nivell){
        this.nivell = nivell; 
    }
    
}
