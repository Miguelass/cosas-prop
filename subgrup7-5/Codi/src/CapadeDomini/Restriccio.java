
package CapadeDomini;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedHashMap;


public class Restriccio extends StructRestriccio{
    private int horaini;
    private int numhora;
    
    public  ArrayList<ArrayList<StructRestriccio>> un = new ArrayList<ArrayList<StructRestriccio>>(5);
    ArrayList<StructRestriccio> unaux = new ArrayList();
    private Boolean[] Resact = {false, false, false, false, false};
    //Binaria bi[2];
    
    public Restriccio(int horaini, int numhora){
        this.horaini = horaini;
        this.numhora = numhora;
        
        for(int i = 0; i<5;i++)un.add(unaux);
        
    }
    
    public void afegir_restriccio(Scanner sc){
        System.out.println("Quin tipus de restriccio vols crear?");
        System.out.println("    0 - X asignatura no pot anar abans de Y hora");
        System.out.println("    1 - X asignatura no pot anar en Y hora");
        System.out.println("    2 - X asignatura no pot anar en Y aula");
        System.out.println("    3 - X asignatura ha d'anar en Y aula ??");
        System.out.println("    4 - No hi ha clase a X hora");
        int num = sc.nextInt();
        Resact[num]=true;
        StructRestriccio aux = new StructRestriccio();
        if(num == 0 || num == 1){
            System.out.println("Introdueix el nom de l'asignatura");
            aux.set_nom1(sc.next());
            System.out.println("Introdueix l'hora");
            aux.set_num(sc.nextInt());
        }
        else if(num == 2 || num == 3){
            System.out.println("Introdueix el nom de l'asignatura");
            aux.set_nom1(sc.next());
            System.out.println("Introdueix el nom de l'aula");
            aux.set_nom2(sc.next());
        }
        if(num == 4){
            System.out.println("Introdueix l'hora");
            aux.set_num(sc.nextInt());
        }
        unaux = un.get(num);
        unaux.add(aux);
        un.set(num,unaux);
    }
     public boolean comprovar(String nomass, LinkedHashMap<String,Aula> aules, int hora,int dia,int aula){
         for(int i = 0; i<un.size(); i++){            
             ArrayList<StructRestriccio> arraux = un.get(i);
             for(int j = 0; j<arraux.size(); j++){
                 StructRestriccio aux = arraux.get(j);
                 if(i == 0 && aux != null && Resact[i]){
                    if(nomass.equals(aux.get_nom1()) && hora < aux.get_num()-horaini) return false;
                 }
                 if(i == 1 && aux != null && Resact[i]){
                    if(nomass.equals(aux.get_nom1()) && hora == aux.get_num()-horaini) return false;
                 }
                 if(i == 2 && aux != null && Resact[i]){;
                     int cont = 0;
                     
                     /*
                     List<String> indexes = new ArrayList<>(aules.keySet());
                     cont=indexes.indexOf(aux.get_nom2());
                     for (Aula au : aules) {
                         if(au.get_nomA().equals(aux.get_nom2()) && nomass.equals(aux.get_nom1()))break;
                         cont++;
                     }
                     if(aula == cont) return false;*/
                 }
                if(i == 3 && aux != null && Resact[i]){
                     int cont = 0;
                     /*
                     for (Aula au : aules) {
                         if(au.get_nomA().equals(aux.get_nom2()) && nomass.equals(aux.get_nom1()))break;
                         cont++;
                     }
                     if(aula != cont) return false;
                        */
                }
                if(i == 4 && aux != null && Resact[i]){
                    if(hora == aux.get_num()-horaini) return false;
                }
             }
         
         }
         return true;
     }
    
    
}
