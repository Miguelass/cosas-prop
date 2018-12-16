/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

/**
 *
 * @author Miguel
 */
public class Sessio_Driver {
    private Sessio se = new Sessio();
    private FranjaHoraria fj = new FranjaHoraria();
    public void main(){
        se.afegir_franja(fj);
        Scanner sc = new Scanner(System.in);
        System.out.println("Prem 0 Testejar la creadora \n"
            + "Prem 1 Testejar els get() \n"
            + "Prem 2 Testejar els set() \n"
            + "Prem 3 Testejar AfegirFranja \n"
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
                    TestAfegirFranja();
                }
            } 
            System.out.println("Prem 0 Testejar la creadora \n"
            + "Prem 1 Testejar els get() \n"
            + "Prem 2 Testejar els set() \n"
            + "Prem 3 Testejar AfegirFranja \n"
            + "Prem 4 Sortir ");
            input = sc.nextInt();
        }
    }
    private void TestCreadora(){
        se = new Sessio();
        System.out.println("OK");
    }
    private void TestGet(){
        List<FranjaHoraria> franj = new ArrayList<FranjaHoraria>();
        franj = se.get_franjes();
        Iterator it = franj.iterator();
        fj = (FranjaHoraria) it.next();
        System.out.println("Valors : numHores" +se.get_horesS() + " idSessio" + se.get_ids() + " ,Asignatura en Lista de Franjes " + fj.get_nomAss());   
    }
    private void TestSet(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el numero de hores de la sessio");
        se.set_hores(sc.nextInt());
        System.out.println("Introdueix l'id de la sessio");
        se.set_ids(sc.nextInt());
    }
    private void TestAfegirFranja(){
        se.afegir_franja(fj);
        List<FranjaHoraria> franj = se.get_franjes();
        System.out.println("Tamany de la llista FranjaHoraria: "+ franj.size());
    }
}
