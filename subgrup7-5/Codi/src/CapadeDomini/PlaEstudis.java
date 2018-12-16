package CapadeDomini;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class PlaEstudis {

    public String diainici = "";
    private int horainici, horafi, numhores;
    private int numdies;
    private List<Sessio> Sessions = new ArrayList<Sessio>();
    private static String[] Dies = {"Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres", "Dissabte", "Diumenje"};
    private Boolean[] Diess = {false, false, false, false, false, false, false};
    private LinkedHashMap<String,Asignatura> Asignatures = new LinkedHashMap<>();
    Horari h=new Horari();
    private Restriccio res;

    
    public PlaEstudis() {}
    
        
   
    
    public void actualitzar_ass(Asignatura as) {
        Asignatures.replace(as.get_nomass(), as);
    }  
    public void afegir_asignatura(String nomass,Asignatura a) {
        Asignatures.put(nomass, a);
    }
    public void modificar_assignatura(Scanner sc, String nomass) {
        //si no esta introdueix una nova
        System.out.println("Tria que vols modificar: \n"
                            + "Prem 0 per modificar el nom \n"
                            + "Prem 1 per modificar el nivell \n"
                          );
         int opcio=sc.nextInt();
         Asignatura a = Asignatures.get(nomass);
         switch(opcio) {
             case 0:
                 System.out.println("Introdueix el nom nou");
                 String nom_asignatura=sc.next();
                 if(Asignatures.containsKey(nom_asignatura) ) System.out.println("Ja existeix una aula amb aquest nom"); 
                 else {
                    a.set_nomass(nom_asignatura);
                    //Asignatures.replace(nomass, a);
                    Asignatures.remove(nomass);
                    Asignatures.put(nom_asignatura, a);    
                 }
                 
                 break;
             case 1:
                 System.out.println("Introdueix el nivell");
                 String nivell=sc.next();
                 a.set_nivell(nivell);
                 Asignatures.replace(nomass,a);
                 break;
             default:
                 break;
                
         }
        
    }    
     public void borrar_assignatura(String nomass) {  
        Asignatures.remove(nomass);         
    }
    
    public Set consultar_grups(String nomass) {
        Asignatura a = Asignatures.get(nomass);
        return a.consultar_grups();
    }
    public List consultar_sessions(String nomass, String nomg) {
        Asignatura a = Asignatures.get(nomass);
        return a.consultar_sessions(nomg);
    }     
    public boolean existeixgrup(String nomass, String nomgrup) {
        Asignatura au = Asignatures.get(nomass);
        return au.existeixgrup(nomgrup);
    }
     public void borrar_grup(String nomassig, String nomgrup) {
            Asignatura as = Asignatures.get(nomassig); 
            as.borrar_grup(nomgrup);
            actualitzar_ass(as);
     }
    
    public boolean crear_sessio(String nomass, String nomgrup, String hores) {
        Asignatura au = Asignatures.get(nomass); 
        boolean aux=au.crear_sessio(nomass, nomgrup,hores);
        actualitzar_ass(au);
        return aux;
    }
    public void borrar_sessio(String nomas, String nomgrup, int hores) {
        Asignatura au = Asignatures.get(nomas); 
        au.borrar_sessio(nomgrup,hores);
        actualitzar_ass(au);
    }
   /*
    public boolean modificar_grup(Scanner sc, String nomass) {
        boolean trobat = false;
        ListIterator it = Asignatures.listIterator();
        while (it.hasNext() && !trobat) {
            Asignatura a = (Asignatura) it.next();
            if (nomass.equals(a.get_nomass())) {
                trobat = true;
                a.modificar_grups(sc);
            }
        }
        return trobat; 
    }
    public boolean borrar_grup(Scanner sc, String nomass) { 
            boolean tret = false;
            ListIterator it = Asignatures.listIterator();
            while (it.hasNext() && !tret) {
                Asignatura a = (Asignatura) it.next();
                if (nomass.equals(a.get_nomass())) {
                    tret = true;
                    System.out.println("Intrdueix el grup a borrar");
                    String nomg = sc.next();
                    Grup g=a.buscar_grup(nomg);
                    if (g!=null){
                        a.borrar_grup(g);
                        it.set(a);

                    } else {
                        System.out.println("El grup no existeix");
                    }
                }
            }
            return tret;
     }
    
    
   */
    public Horari crear_horari(LinkedHashMap<String,Aula> Aules, int numhores, int numdies ) {
        Sessions = new ArrayList<>();
        this.numhores=numhores;
        this.numdies=numdies;
        System.out.println("pla " + numhores+ " " +numdies);
        System.out.println("gets " + this.get_numhores()+ " " +this.get_numdies());
        for (Map.Entry<String, Asignatura> entry : Asignatures.entrySet()) {
            Asignatura aa = entry.getValue();
            Map<String,Grup> grup = aa.get_grups();
            for (Map.Entry<String, Grup> entryg : grup.entrySet()) {
                Grup gg = entryg.getValue();
                List<Sessio> sessions = gg.get_sessions();
                ListIterator it_s = sessions.listIterator();
                while(it_s.hasNext()) {
                    Sessio ss=(Sessio) it_s.next();
                    this.Sessions.add(ss);
                }
            }
        }
        h.crear_horari(Sessions, Aules, numdies, numhores, res);
        pintar_horari(h.get_horari(),Aules);
        return h;
     }
    
    public void pintar_horari(FranjaHoraria[][][] horari, LinkedHashMap<String,Aula> Aules) {
        int hi;
        int k=0;
        for (Map.Entry<String, Aula> entry : Aules.entrySet()) {
            hi = horainici;
            Aula a1 = entry.getValue(); // PER A CADA AULA ESCRIVIM EL NOM I PINTEM EL HORARI
            System.out.println(a1.get_nomA());
            System.out.print("          ");
            for (int die = 0; die < 7; ++die) { //PINTEM ELS DIES DESITJATS
                if (Diess[die]) {
                    System.out.print("  " + Dies[die] + "    ");
                }
            }
            System.out.println();
            for (int j = 0; j < numhores; ++j) { //for per cada fila del horari
                System.out.print(hi % 24 + "-" + (hi + 1) % 24); //PINTEM LES HORES QUE TOQUEN
                ++hi;
                System.out.print("  ");
                for (int d = 0; d < numdies; ++d) { //printem cada franja horari
                    if (horari[j][d][k] != null) {
                        System.out.print(horari[j][d][k].get_nomAss() + " " + horari[j][d][k].get_grup() + "(" + horari[j][d][k].get_tip() + ") ");
                    } else {
                        System.out.print("     buit      ");
                    }
                }
                System.out.println("\n");
            }
            ++k;
        }
    }
    
    public void modificar_horari(int cd, int pd, int ph, int cdn, int pdn, int phn, LinkedHashMap<String,Aula> Aules){
        h.modificar_horari(cd,pd,ph,cdn,pdn,phn, numhores,Aules, res);        
    }

    public boolean existeix_asignatura(String nomass) {
        return Asignatures.containsKey(nomass);
    }
    
    public void afegir_restriccions(Scanner sc) {
        res.afegir_restriccio(sc);
    }
    
    public String get_diainici() {
        return this.diainici;
    }

    public int get_horainici() {
        return this.horainici;
    }

    public int get_horafi() {
        return this.horafi;
    }

    public int get_numhores() {
        return this.numhores;
    }

    public int get_numdies() {
        return this.numdies;
    }

    public String get_dia(int i){
        return Dies[i];
    }
    
    public boolean get_diaa(int i) {
        return Diess[i];
    }
    
    public Horari get_horari() {
        return h;
    }
    
    public LinkedHashMap<String,Asignatura> get_asignatures() {
        return Asignatures;
    }
    public Asignatura get_asignatura(String nom){
        return Asignatures.get(nom);
    }
    
    public void set_diainici(String diainici) {
        this.diainici = diainici;
    }

    public void set_horainici(int horainici) {
        this.horainici = horainici;
    }

    public void set_horafi(int horafi) {
        this.horafi = horafi;
    }

    public void set_numhores(int numhores) {
        this.numhores = numhores;
    }

    public void set_numdies(int numdies) {
        this.numdies = numdies;
    }
    
    public void set_horari(Horari h){
        this.h = h;
    }

}
