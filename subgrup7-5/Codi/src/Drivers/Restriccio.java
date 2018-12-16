/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Iterator;



public class Restriccio extends Unaria{
    private int horaini;
    private int numhora;
    
    public  ArrayList<ArrayList<Unaria>> un = new ArrayList<ArrayList<Unaria>>(5);
    ArrayList<Unaria> unaux = new ArrayList();
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
        Unaria aux = new Unaria();
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
        System.out.println(un.size());
        unaux = un.get(num);
        System.out.println();
        unaux.add(aux);
        un.set(num,unaux);
    }
     public boolean comprovar(String nomass, List<Aula> aules, int hora,int dia,int aula){
         for(int i = 0; i<un.size(); i++){
             //System.out.println(i+" de "+ (un.size()-1));
             ArrayList<Unaria> arraux = un.get(i);
             for(int j = 0; j<arraux.size(); j++){
                 Unaria aux = arraux.get(j);
                 System.out.println(aux.get_nom1() + " " + aux.get_num());
                 //horario[horas][dias][aula]
                 if(i == 0 && aux != null && Resact[i]){
                   // System.out.println("Entra 0");
                    //System.out.println(hora + "  " + aux.get_num()+ "   " + horaini);
                    if(nomass.equals(aux.get_nom1()) && hora < aux.get_num()-horaini) return false;
                 }
                 if(i == 1 && aux != null && Resact[i]){
                    //System.out.println("Entra 1");
                    if(nomass.equals(aux.get_nom1()) && hora == aux.get_num()-horaini) return false;
                 }
                 if(i == 2 && aux != null && Resact[i]){
                     //System.out.println("Entra 2");
                     int cont = 0;
                     for (Aula au : aules) {
                         if(au.get_nomA().equals(aux.get_nom2()) && nomass.equals(aux.get_nom1()))break;
                         cont++;
                     }
                     if(aula == cont) return false;
                 }
                if(i == 3 && aux != null && Resact[i]){
                  //System.out.println("Entra 3");
                     int cont = 0;
                     for (Aula au : aules) {
                         if(au.get_nomA().equals(aux.get_nom2()) && nomass.equals(aux.get_nom1()))break;
                         cont++;
                     }
                     if(aula != cont) return false;
                }
                if(i == 4 && aux != null && Resact[i]){
                    //System.out.println("Entra 4");
                    //System.out.println(hora+" "+dia+" "+aula);
                    if(hora == aux.get_num()-horaini) return false;
                }
             }
         
         }
         return true;
     }
    
    
}
