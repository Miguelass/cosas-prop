/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;


import java.util.Scanner;

/**
 *
 * @author Miguel
 */
public class FranjaHoraria_Driver {
    private FranjaHoraria fj = new FranjaHoraria();
    public void main(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Prem 0 Testejar la creadora \n"
            + "Prem 1 Testejar els get() \n"
            + "Prem 2 Testejar els set() \n"
            + "Prem 3 Sortir ");
        int input = sc.nextInt();
        while(input != 3){
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
            } 
            System.out.println("Prem 0 Testejar la creadora \n"
            + "Prem 1 Testejar els get() \n"
            + "Prem 2 Testejar els set() ");
            input = sc.nextInt();
        }
    }
    private void TestCreadora(){
        fj = new FranjaHoraria();
        System.out.println("OK");
    }
    private void TestGet(){
        System.out.println("Valors: " + fj.get_nomAss() + " " + fj.get_grup() + " " + fj.get_tip() + " " + fj.get_gpare() + " " + fj.get_nivell() + " "+ fj.get_capacitat() + " "+ fj.get_ids());
    }
    private void TestSet(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nom de l'assignatura");
        fj.set_nomAss(sc.next());
        
        System.out.println("Introdueix el nivell al que pertany l'assignatura");
        fj.set_grup(sc.next());
        
        System.out.println("Introdueix el nom del grup");
        String nomgrup = sc.next();
        
        System.out.println("Introdueix el nom del grup del que es subgrup(exit si no es subgrup)");
        String aux = sc.next();
        String pare = null;
        if(!aux.equals("exit")) pare = aux;
        fj.set_gpare(pare);
        
        System.out.println("Introdueix la capacitat del grup");
        int capacitatg = sc.nextInt();
        
        System.out.println("Introdueix el tipus de grup");
        aux = sc.next();
        while(!aux.equals("Laboratori")  && !aux.equals("Teoria")  && !aux.equals("Problemes") ) {
            System.out.println("Has introduit el tipus de manera incorrecte, torna a introduir-ho (Teoria/Laboratori/Problemes): " );
            aux =sc.next();
        }
        
        System.out.println("Introdueix l'id de la sessio");
        fj.set_tipus(sc.next());

    }
}
