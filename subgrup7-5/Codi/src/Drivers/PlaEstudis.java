package Drivers;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.ListIterator;

public class PlaEstudis {

    public String Titulacio = "";
    public String diainici = "";
    private int horainici, horafi, numhores;
    private int numdies;
    private List<Sessio> Sessions = new ArrayList<Sessio>();
    private static String[] Dies = {"Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres", "Dissabte", "Diumenje"};
    private Boolean[] Diess = {false, false, false, false, false, false, false};
    private List<Asignatura> Asignatures = new ArrayList<Asignatura>();
    Horari h=new Horari();
    private Restriccio res;

    /*
    *
     */
    
    public PlaEstudis() {}
    
        
    public void crear_pla_destudi(Scanner sc) {
        System.out.println("Introdueïx la Titulació: ");
        Titulacio = sc.next();
        System.out.println(Titulacio);
  
        System.out.println("Introdueix numero de dies de l'horari (1-7) ");
        numdies = sc.nextInt();
        while (numdies < 1 || numdies > 7) {
            System.out.println("Numero no valid, Introdueix el numero de dies de l'horari (1-7) ");
            numdies = sc.nextInt();
        }

        System.out.println("Introdueix els dies que te l'horari Dilluns0/Dimarts1/Dimecres2/Dijous3/Divendres4/Dissabte5/Diumenje6 ");
        int aux = numdies;
        int dia;
        while (aux > 0) {
            dia = sc.nextInt();
            if (!Diess[dia]) {
                Diess[dia] = true;
                --aux;
            } else {
                System.out.println("Tria un dia diferent");
            }
        }

        System.out.println("Introdueix l'hora en la qual comensa l'horari(0h-24h): ");
        horainici = sc.nextInt();
        while (horainici < 0 || horainici > 24) {
            System.out.println("Hora no valida, tornala a introduir: ");
            horainici = sc.nextInt();
        }
        System.out.println("Introdueix l'hora en la qual acaba l'horari(0h-24h): ");
        horafi = sc.nextInt();
        while (horafi < 0 || horafi > 24) {
            System.out.println("Hora no valida, tornala a introduir: ");
            horafi = sc.nextInt();
        }
        if (horafi > horainici) {
            numhores = (horafi - horainici);
        } else {
            numhores = horafi + 24 - horainici;
        }
       // crear_asignatures(sc);
       res=new Restriccio(horainici, numhores);
    }
    
    public void crear_asignatures(Scanner sc) {
        System.out.println("Introdueix el nom de l'assignatura/'exit' si no vol introduir-ne més:");
        String nom_assignatura = sc.next();
        while (!nom_assignatura.equals("exit")) {
            System.out.println("Es obligatoria?: S/N" );
            String aux =sc.next();
            //guardar S/N
            System.out.println("Introdueix el nivell de l'assignatura: ");
            String nivell=sc.next();
            System.out.println(aux);
            Asignatura as = new Asignatura(sc, nom_assignatura, nivell, true);
            as.assignar_grups(sc);
            Asignatures.add(as);
            System.out.println("Introdueix el nom de l'assignatura/'exit' si no vol introduir-ne més:");
            nom_assignatura = sc.next();
            ListIterator it = Asignatures.listIterator();
            while (it.hasNext()) { //mirem si ja existeix una assignatura amb aquest nom
                Asignatura ass = (Asignatura) it.next();
                if (ass.get_nomass().equals(nom_assignatura)) {
                    System.out.println("l'assignatura introduida ja existeix");
                    nom_assignatura = sc.next();
                    it = Asignatures.listIterator();
                }
            }
        }

    }
    public void modificar_assignatura(Scanner sc) {
        System.out.println("Introdueix l'assignatura que vols modificar");
        String nomass=sc.next();
        ListIterator a=Asignatures.listIterator();
        Asignatura assignatura=new Asignatura();
        boolean trobat=false;
        while(!trobat) {
            while(a.hasNext() && !trobat) {
                assignatura=(Asignatura) a.next();
                if(assignatura.get_nomass().equals(nomass)) trobat=true;
            }
            if(!trobat) {
                System.out.println("L'assignatura no existeix, introdueix-ne una altre");
                nomass=sc.next();
                a=Asignatures.listIterator();
                trobat=false;
            }
        }
        //si no esta introdueix una nova
        System.out.println("Tria que vols modificar: \n"
                            + "Prem 0 per modificar el nom \n"
                            + "Prem 1 per modificar el nivell \n"
                            + "Prem 2 per modificar obligatoria/no obligatoria \n");
         int opcio=sc.nextInt();
         switch(opcio) {
             case 0:
                 System.out.println("Introdueix el nom nou");
                 String nom_assignatura=sc.next();
                 ListIterator aa = Asignatures.listIterator();
                 trobat=false;
                 while(aa.hasNext() && !trobat) {
                    Asignatura assignaturanew=(Asignatura) aa.next();
                    if(assignaturanew.get_nomass().equals(nom_assignatura)) trobat=true;
                 }
                 if(trobat) System.out.println("Ja existeix una aula amb aquest nom");
                 else {
                     System.out.println("sdgkhsdjgksasa " + nom_assignatura);
                     assignatura.set_nomass(nom_assignatura);
                     assignatura.actualitzar_nom(nom_assignatura);
                     a.set(assignatura);
                 }
                 
                 break;
             case 1:
                 System.out.println("Introdueix el nivell");
                 String nivell=sc.next();
                 assignatura.set_nivell(nivell);
                 System.out.println(nivell + "  " + assignatura.get_nivell());
                 assignatura.actualitzar_nivell(nivell);
                 a.set(assignatura);
                 break;
             case 2:
                 System.out.println("Prem 0 si vols que sigui assignatura obligatora/1 en cas contrari");
                 int aux=sc.nextInt();
                 if(aux==0) assignatura.set_isAssigOB(true);
                 else if(aux==1)  assignatura.set_isAssigOB(false);
                 a.set(assignatura);
                 break;
             default:
                 break;
                
         }
        
    }
    public boolean borrar_assignatura(Scanner sc) {
        System.out.println("Intrdueix el nom de l'assignatura a borrar");
        String nomass = sc.next();
        boolean tret = false;
        ListIterator it = Asignatures.listIterator();
        for(Asignatura a:Asignatures) {
            if (nomass.equals(a.get_nomass())) {
                Asignatures.remove(a);
                tret=true;
            }
        }
        return tret;
         
    }
     
    public boolean afegir_grup(Scanner sc, String nomass) {
       boolean trobat = false;
        ListIterator it = Asignatures.listIterator();
        while (it.hasNext() && !trobat) {
            Asignatura a = (Asignatura) it.next();
            if (nomass.equals(a.get_nomass())) {
                trobat = true;
                a.assignar_grups(sc);
            }
        }
        return trobat;        
    } 
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
    
    
    public void afegir_sessio(Scanner sc, String nomass) { 
        boolean trobat = false;
        ListIterator it = Asignatures.listIterator();
        while (it.hasNext() && !trobat) {
            Asignatura a = (Asignatura) it.next();
            if (nomass.equals(a.get_nomass())) {
                trobat = true;
                System.out.println("Intrdueix el grup de la sessio a afegir");
                String nomg = sc.next();
                Grup g=a.buscar_grup(nomg);
                if(g!=null) {
                    g.assignar_sessions(sc, nomass, a.get_nivell());
                } else {
                    System.out.println("El grup no existeix");
                }
            }
        }
        if (!trobat) {
            System.out.println("L'assignatura no existeix");
        }
    }
    //public boolean modificar_sessio(Scanner sc) {return false} 
   // public boolean borrar_sessio(Scanner sc, String nomass) {return false}
    
    
    
    public Horari crear_horari(List<Aula> Aules ) {
        Sessions = new ArrayList<Sessio>();
        ListIterator it_a = Asignatures.listIterator();
        while (it_a.hasNext()) {
            Asignatura aa = (Asignatura) it_a.next();
            List<Grup> grup = aa.get_grups();
            ListIterator it_g = grup.listIterator();
            while (it_g.hasNext()) {
                Grup gg = (Grup) it_g.next();
                List<Sessio> sessions = gg.get_sessions();
                ListIterator it_s = sessions.listIterator();
                while(it_s.hasNext()) {
                    Sessio ss=(Sessio) it_s.next();
                    this.Sessions.add(ss);
                }
            }
        }
        h.crear_horari(Sessions, Aules, numdies, numhores, res);
        if(h.get_ple()) pintar_horari(h.get_horari(),Aules);
        else System.out.println("No es pot crear horari");
        return h;
     }
    //public void modificar_horari(Scanner sc) 
    
    public void pintar_horari(FranjaHoraria[][][] horari, List<Aula> Aules) {
        int hi;
        ListIterator a = Aules.listIterator();
        //System.out.println(Aules.size());
        for (int k = 0; k < Aules.size(); ++k) {
            hi = horainici;
            Aula a1 = (Aula) a.next(); // PER A CADA AULA ESCRIVIM EL NOM I PINTEM EL HORARI
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
        }
    }
   
    public void modificar_horari(String dia,String aula,int horai, ArrayList<Aula> Aules) {
        int cd = 0, pd = 0, ph = 0;
        
        
    }

    public void afegir_restriccions(Scanner sc) {
        res.afegir_restriccio(sc);
    }

    public String get_Titulacio() {
        return this.Titulacio;
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
    
    public List<Asignatura> get_asignatures() {
        return Asignatures;
    }

    public void set_Titulacio(String Titulacio) {
        this.Titulacio = Titulacio;
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

}
