
package CapadeDomini;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.List;
import java.util.Set;


public class Asignatura {

    private String nomass=""; 
    private String nivell="";
    private LinkedHashMap<String,Grup> Grups= new LinkedHashMap<String,Grup>();
    
    
    public Asignatura () {
         
    }
    
    public Asignatura (String nomAssig, String nivell) {
        this.nomass=nomAssig;
        this.nivell=nivell;
    }
    
    public boolean crear_grup(String nom, int capacitat, String tipus, String pare){
        if(!Grups.containsKey(nom)) {
            Grup gr = new Grup(nom, tipus, pare, capacitat);
            Grups.put(nom,gr);
            return true;
        }
        else return false;
    }
    
    public boolean existeixgrup(String nomgrup) {
        return Grups.containsKey(nomgrup);
    }
     public Set consultar_grups() {
         return Grups.keySet();
    }
    public List consultar_sessions(String nomass) {
        Grup g = Grups.get(nomass);
        return g.consultar_sessions();
    }
    public boolean crear_sessio(String nomass, String nomgrup, String hores) {
        Grup g = Grups.get(nomgrup);
        boolean aux = g.crear_sessio(nomass, nivell, hores);
        actualitzar_g(g);
        return aux;
    }
     public void actualitzar_g(Grup g) {
        Grups.replace(g.get_nomg(), g);
    }
    public void borrar_grup(String nomgrup) {
        Grups.remove(nomgrup);
    }
    
    public void borrar_sessio(String nomgrup, int hores) {
        Grup g = Grups.get(nomgrup);
        g.borrar_sessio(hores);
        actualitzar_g(g);
    }
    
   
    /*
    public void modificar_grups(Scanner sc) {
        System.out.println("Introdueix el nom del grup/'exit' si no vol introduir-ne més:");
        String nom_grup=sc.next();
        Iterator it=Grups.iterator();
        boolean trobat=false;
        while(it.hasNext()) { //mirem si ja existeix una assignatura amb aquest nom
                Grup gpp = (Grup)it.next();
                if(gpp.get_nomg().equals(nom_grup)) {
                       System.out.println("Tria que vols modificar: \n"
                            + "Prem 0 per modificar el nom \n"
                            + "Prem 1 per modificar la capacitat \n"
                            + "Prem 2 per modificar el tipus de grup \n");
                            int opcio=sc.nextInt();
                            while(opcio!=0 && opcio!=1 && opcio !=2) {
                                System.out.println("Numero incorrecte, introdueix:\n"
                                                + "Prem 0 per modificar el nom \n"
                                                + "Prem 1 per modificar la capacitat \n"
                                                + "Prem 2 per modificar el tipus de grup \n"
                                                + "Prem 3 per modificar el grup pare \n" );
                            }
                            switch(opcio) {
                                case 0:
                                     System.out.println("Introdueix el nou nom del grup:");
                                    nom_grup=sc.next();
                                    Iterator itt=Grups.iterator();
                                    while(it.hasNext()) { //mirem si ja existeix una assignatura amb aquest nom
                                        Grup gppp = (Grup)it.next();
                                        if(gpp.get_nomg().equals(nom_grup)) {
                                            System.out.println("Ja existeix un grup amb aquest nom, introdueix-ne un altre: ");
                                            nom_grup=sc.next();
                                            it=Grups.iterator();                                                                                                                     
                                        }
                                    }
                                    gpp.set_nomgrup(nom_grup);
                                    break;
                                case 1:
                                    System.out.println("Introdueix la capacitat del grup(10-500) :");
                                    int capacitatg=sc.nextInt();
                                    while(capacitatg<10 || capacitatg>500){
                                        System.out.println("Capacitat incorrecte, introdueix-ne una altre entre 10-500");
                                        capacitatg=sc.nextInt();
                                    }
                                    gpp.set_capacitat(capacitatg);
                                    break;
                                case 2:
                                   System.out.println("Introdueix el tipus de grup (Teoria/Laboratori/Problemes) :");
                                    String tipus=sc.next();
                                    while(!tipus.equals("Laboratori")  && !tipus.equals("Teoria")  && !tipus.equals("Problemes") ) {
                                        System.out.println("Has introduit el tipus de manera incorrecte, torna a introduir-ho (Teoria/Laboratori/Problemes): " );
                                        tipus =sc.next();
                                    }
                                    gpp.set_tipus(tipus);
                                    break;
                                case 3:
                                    String pare="";
                                    if(gpp.get_tipusg().equals("Laboratori") || gpp.get_tipusg().equals("Problemes")) {
                                         System.out.println("Introdueix el grup pare del subgrup :");
                                         pare = sc.next();
                                         gpp.set_pare(pare);
                                    }
                                    else System.out.println("El grup introduït ja es un pare");
                                    break;
                                default:
                                    break;
                            }

                }
        }
        if(!trobat) System.out.println("No existei un grup amb aquest nom");
    }
   */
    /*
    public void actualitzar_nom (String nomAssig) {
        Grups.forEach((gu) -> {
            gu.actualitzar_noma(nomAssig);
        });
    }*/
    /*
    public void actualitzar_nivell(String nivell) {
        Grups.forEach((gu) -> {
            gu.actualitzar_nivell(nivell);
        });
    }*/
    /*
    public Grup buscar_grup(String nomgr) {
        for(Grup gu:Grups) {
            if(gu.get_nomg().equals(nomgr)) return gu;
        }
        return null;
    }*/
    
    public void borrar_grup(Grup g) {
        Grups.remove(g);
        
    }
   
    public LinkedHashMap<String,Grup> get_grups(){
        return this.Grups;
    }
     public String get_nomass() {
        return this.nomass;
    }
    public String get_nivell() {
        return this.nivell;
    }
  
    public void set_nomass(String nomAssig){
        this.nomass = nomAssig; 
    }
   
    public void set_Grup(LinkedHashMap<String,Grup> Grups ){
        this.Grups = Grups; 
    }
    public void set_nivell(String nivell){
        this.nivell = nivell; 
    }
    
}
