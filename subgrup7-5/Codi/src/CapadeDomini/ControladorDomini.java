
package CapadeDomini;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import CapaPersistencia.*;
import java.io.IOException;

public class ControladorDomini {
    private PlaEstudis pa =new PlaEstudis();
    private LinkedHashMap<String,Aula> Aules = new LinkedHashMap<String,Aula>();
    private Horari h=new Horari();
    private CapaPersistencia.ControladorPersistencia CP = new CapaPersistencia.ControladorPersistencia();
    public void ControladorDomini(){}
    
    public void crear_plans_destudis(Scanner sc) {
        pa=new PlaEstudis();
       
    }
    
    
    public void crear_horari(int numhores, int numdies) {
        h = pa.crear_horari(Aules, numhores, numdies);
        System.out.println("cp" +numhores+ " " +numdies);
    }
    
    public List<String> horariperaula(String nomaula, int numhores, int numdies, int horauxiliar, int diaini, int diafi) {
        int cd = trobar_posicio_a(nomaula);
        FranjaHoraria[][][] retorn = h.get_horari();
        List<String> aux = new ArrayList<>();
        String auxiliar = "";
        if(diaini<diafi) {
            int dia=0;
            for(int d = 0 ; d<diaini; ++d) aux.add("buit");
            for (int d = diaini; d < diafi; ++d) { //printem cada franja horari                    
                        if(retorn[horauxiliar][dia][cd]==null) auxiliar="buit";
                        else {
                            String aux1 = retorn[horauxiliar][dia][cd].get_nomAss();
                            String aux2 = retorn[horauxiliar][dia][cd].get_grup();
                            String aux3 = retorn[horauxiliar][dia][cd].get_tip();
                            auxiliar = aux1 + " " + aux2 + " " + aux3;
                        }
                        aux.add(auxiliar);
                        ++dia;
                    }
            for(int d = diafi; d<7; ++d) aux.add("buit");
        }
        else {
            int dia=0;
            for (int d = 0; d < diafi; ++d) { //printem cada franja horari                    
                if(retorn[horauxiliar][dia][cd]==null) auxiliar="buit";
                else {
                    String aux1 = retorn[horauxiliar][dia][cd].get_nomAss();
                    String aux2 = retorn[horauxiliar][dia][cd].get_grup();
                    String aux3 = retorn[horauxiliar][dia][cd].get_tip();
                    auxiliar = aux1 + " " + aux2 + " " + aux3;
                }
                aux.add(auxiliar);
                ++dia;
             }
            for(int d = diafi; d<diaini; ++d) aux.add("buit");
            for (int d = diaini; d < 7; ++d) { //printem cada franja horari                    
              if(retorn[horauxiliar][dia][cd]==null) auxiliar="buit";
              else {
                  String aux1 = retorn[horauxiliar][dia][cd].get_nomAss();
                  String aux2 = retorn[horauxiliar][dia][cd].get_grup();
                  String aux3 = retorn[horauxiliar][dia][cd].get_tip();
                  auxiliar = aux1 + " " + aux2 + " " + aux3;
              }
              aux.add(auxiliar);
              ++dia;
           }            
        }
        
        return aux;
    }
  
    public void afegir_restriccions(Scanner sc) {
        pa.afegir_restriccions(sc);
    }
    public void modificar_horari(Scanner sc) {
        System.out.println("Intrdueix la aula, el dia i la hora inici de la sessio a moure");
        String dia = "", aula = "";
        int horai = 0;
        int cd = 0, pd = 0, ph = 0;
        aula = sc.next();
        dia = sc.next();
        horai = sc.nextInt();
        cd = trobar_posicio_a(aula);
        pd = trobar_posicio_d(dia);
        ph = trobar_posicio_h(horai);
        if (cd > -1 && pd > -1 && ph > -1) {
            System.out.println("Intrdueix la aula, el dia i la hora inici on vols voure la sessio");
            String dian = "", aulan = "";
            int horain = 0;
            aulan = sc.next();
            dian = sc.next();
            horain = sc.nextInt();
            int cdn = 0, pdn = 0, phn = 0;
            cdn = trobar_posicio_a(aulan);
            pdn = trobar_posicio_d(dian);
            phn = trobar_posicio_h(horain);
            if(cdn > -1 && pdn > -1 && phn > -1) {
               // pa.modificar_horari(cd,pd,ph,cdn,pdn,phn,Aules);
               
            }
            else System.out.println("No existeix la posicio");
        }
        else System.out.println("No existeix aquesta posicio"); 
    }
    
    public boolean crear_aula(String nomaula, String capacitat, boolean laboratori) {
        int cap= Integer.parseInt(capacitat);
        if(!Aules.containsKey(nomaula)) {
            Aula au = new Aula(nomaula, cap, laboratori);
            Aules.put(nomaula, au);
            return true;
        }
        else return false;
    }  
    public boolean borrar_aula(String nomaula) {     
        if(Aules.containsKey(nomaula)) Aules.remove(nomaula);
        return true;
    }
    public void modificar_aula(Scanner sc) {
        System.out.println("Introdueix l'aula que vols modificar");
        String nomaula=sc.next();
        while (!Aules.containsKey(nomaula)) {
            System.out.println("L'aula no existeix, introdueix-ne una altre");
            nomaula=sc.next();
        }
        
        
        //si no esta introdueix una nova
        System.out.println("Tria que vols modificar: \n"
                            + "Prem 0 per modificar el nom \n"
                            + "Prem 1 per modificar la capacitat \n"
                            + "Prem 2 per modificar el tipus d'aula \n");
         int opcio=sc.nextInt();
         Aula a = Aules.get(nomaula);
         switch(opcio) {
             case 0:
                 System.out.println("Introdueix el nom nou");
                 String nom_aula=sc.next();
                 if(Aules.containsKey(nom_aula)) {
                      System.out.println("Ja existeix una aula amb aquest nom");
                 }
                 else {
                    Aula au = Aules.get(nomaula);
                    au.set_nomA(nom_aula);
                    Aules.remove(nomaula,a);
                    
                    Aules.put(nom_aula, au);
                 }
                 
                 break;
             case 1:
                 System.out.println("Introdueix la nova capacitat");
                 int capacitat=sc.nextInt();
                 a.set_capacitatA(capacitat);
                 Aules.replace(nomaula,a);
                 break;
             case 2:
                 System.out.println("Prem 0 si vols que sigui aula de laboratori 1 en cas contrari");
                 int aux=sc.nextInt();
                 if(aux==0) a.set_Aulalaboratori(true);
                 else if(aux==1)  a.set_Aulalaboratori(false);
                 Aules.replace(nomaula,a);
                 break;
             default:
                 break;
                
         }
    }
    public Set consultar_aula() {
        return Aules.keySet();
    }
    
    public boolean crear_asignatura(String nomasig, String nivell){
        if(!pa.get_asignatures().containsKey(nomasig)) {
            Asignatura as = new Asignatura(nomasig, nivell);
            pa.afegir_asignatura(nomasig, as);
            return true;
        }
        else return false;
    }
    public void borrar_assig(String nomassig) {
        pa.borrar_assignatura(nomassig);
    }
    public void modificar_assignatura(Scanner sc) {
        System.out.println("Introdueix l'assignatura que vols modificar");
        String nomass=sc.next();
        while(!pa.existeix_asignatura(nomass)) {
            System.out.println("L'assignatura no existeix, introdueix-ne una altre");
            nomass=sc.next();
        }
        pa.modificar_assignatura(sc, nomass);
    }
    public Set consultar_assignatura() {
        LinkedHashMap<String,Asignatura> Asignatures=pa.get_asignatures();
        /*System.out.println("Les assignatures existents son:");
        Asignatures.keySet().forEach((k) -> {
            System.out.print(k + " ");
        }); 
        System.out.println();*/
        return Asignatures.keySet();
    }
    
    public boolean crear_grup(String asig, String nom, int capacitat, String tipus, String pare){
        Asignatura as = pa.get_asignatura(asig);
        if(as.crear_grup(nom,capacitat,tipus,pare)) {
            pa.actualitzar_ass(as);
            return true;
        }
        else return false;
    }
    public void afegir_grup(Scanner sc) {
        System.out.println("Introdueix l'assignatura del grup a afegir");
        String nombass = sc.next();
        //if(!pa.afegir_grup(sc, nombass)) System.out.println("L'assignatura no existeix");  
    }
   public void borrar_grup(String nomassig, String nomgrup) {
        pa.borrar_grup(nomassig, nomgrup);
    }
    public void modificar_grup(Scanner sc) {
        System.out.println("Introdueix l'assignatura del grup a borrar");
        String nomass = sc.next();
        //pa.modificar_grup(sc,nomass);
    }    
    public boolean existeixgrup(String nomass, String nompare) {
        return pa.existeixgrup(nomass,nompare);
    }
    public Set consultar_grups(String nomass) {
        return pa.consultar_grups(nomass);
        /*grups.forEach((g) -> {
            
            System.out.println(g + "  ");
        });  */  
    }
    
    public List consultar_sessions(String nomass,String nomg) {
        return pa.consultar_sessions(nomass,nomg);
        /*grups.forEach((g) -> {
            
            System.out.println(g + "  ");
        });  */  
    }
 
    public boolean crear_sessio(String nomass, String nomgrup, String hores) {
        return pa.crear_sessio(nomass,nomgrup,hores);
    }
    public void borrar_sessio(String nomass, String nomgrup, int hores) {
        pa.borrar_sessio(nomass,nomgrup,hores);
    }
    
     
    public void Guardar() throws IOException{
        CP.Guardar(pa,Aules,h);
    }
    public void Cargar() throws IOException{
        CP.Cargar(pa,Aules);
    }
    
    /*
    public void consultar_sessio(Scanner sc) {
        List<Asignatura> Asignatures=pa.get_asignatures();
        ListIterator it_cas = Asignatures.listIterator();
        Asignatura cas = (Asignatura) it_cas.next();
        System.out.println("Tria quina de les assignatures seguents vols saber les seves sessions:");
        for (int i = 0; i < Asignatures.size(); ++i) {
            System.out.print("  " + cas.get_nomass());
            if (it_cas.hasNext()) {
                cas = (Asignatura) it_cas.next();
            }
        }
        System.out.println();
        ListIterator it_cass = Asignatures.listIterator();
        String nomassc = sc.next();
        boolean trobat = false;
        boolean trobat2 = false; 
        while(it_cass.hasNext() && !trobat){
            Asignatura assc = (Asignatura) it_cass.next(); 
            if(nomassc.equals(assc.get_nomass())){
                trobat = true; 
                ListIterator it_cg = assc.get_grups().listIterator();
                Grup cg = (Grup) it_cg.next(); 
                System.out.println("Tria per quins d'aquests grups vols saber les hores de la sessio:");
                for(int i = 0; i < assc.get_grups().size(); ++i){
                    System.out.print(" " + cg.get_nomg());
                }
                System.out.println(); 
                ListIterator it_cgs = assc.get_grups().listIterator();
                String nomg = sc.next(); 
                while(it_cgs.hasNext() && !trobat2){
                    Grup gss = (Grup) it_cgs.next();
                    if(nomg.equals(cg.get_nomg())){
                        trobat2 = true; 
                        ListIterator it_csg = cg.get_sessions().listIterator();
                        Sessio css = (Sessio) it_csg.next();
                        System.out.println("Les hores de la sessio d'aquest grup son:");
                        for(int i = 0; i < gss.get_sessions().size(); ++i){
                        System.out.print(" " + css.get_horesS());
                        }    
                    }
                }
                
            }
        }
        if(!trobat) System.out.println("Assignatura no trobada, tornarla a introduir");
        if(!trobat2) System.out.println("Grup no trobat, tornal a introduir");
        
    }
    */
    
    public int trobar_posicio_a(String aula) {
        if(Aules.containsKey(aula)) {
            List<String> indexes = new ArrayList<>(Aules.keySet());
            return indexes.indexOf(aula);
        }
        else return -1;
    }
    public int trobar_posicio_d(String dia) {
        boolean trobat = false;
        int pd = 0;
        for (int i = 0; i < 7 && !trobat; ++i) {
            if (pa.get_dia(i).equals(dia)) {
                trobat = true;
            } else if (pa.get_diaa(i)) {
                ++pd;
            }
        }
        if (trobat) {
            return pd;
        } else {
            return -1;
        }
    }
    public int trobar_posicio_h(int horai) {
        boolean trobat = false;
        int ph = 0;
        int auxi = pa.get_horainici();
        for (int i = 0; i < pa.get_numhores() && !trobat; ++i) {
            if (auxi == horai) {
                ph = i;
                trobat = true;
            }
            ++auxi;
        }
        if (trobat) {
            return ph;
        } else {
            return -1;
        }
    }
    
}
