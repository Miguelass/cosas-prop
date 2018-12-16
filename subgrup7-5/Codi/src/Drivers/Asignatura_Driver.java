/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Miguel
 */
public class Asignatura_Driver {
    private Asignatura as = new Asignatura();
    public void main(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Prem 0 Testejar la creadora \n"
            + "Prem 1 Testejar els get() \n"
            + "Prem 2 Testejar els set() \n"
            + "Prem 3 Testejar AfegirGrups \n"
            + "Prem 4 Sortir ");
        int input = sc.nextInt();
        while(input != 4){
            switch (input){
                case 0:{
                    TestCreadora();
                    break;
                }
                case 1:{
                    TestGet();
                    break;
                }
                case 2:{
                    TestSet();
                    break;
                }
                case 3:{
                    TestAfegirGrups();
                }
            } 
            System.out.println("Prem 0 Testejar la creadora \n"
            + "Prem 1 Testejar els get() \n"
            + "Prem 2 Testejar els set() \n"
            + "Prem 3 Testejar AfegirGrups \n"
            + "Prem 4 Sortir ");
            input = sc.nextInt();
        }
    }
    private void TestCreadora(){
       as = new Asignatura();
       System.out.println("OK");
    }
    private void TestGet(){
        System.out.println("Valors : nomAsignatura " +as.get_nomass()+ " Nivell " + as.get_nivell());   
    }
    private void TestSet(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nom de l'assignatura");
        as.set_nomass(sc.next());
        System.out.println("Introdueix el nom del nivell");
        as.set_nivell(sc.next());
    }
    private void TestAfegirGrups(){
        Scanner sc = new Scanner(System.in);
        as.assignar_grups(sc);
        List<Grup> gr = as.get_grups();
        System.out.println("La asignatura te "+gr.size()+" grups");
    }
}
