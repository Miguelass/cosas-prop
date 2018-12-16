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
public class Aula_Driver {
    Aula au = new Aula();
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
        au = new Aula();
        System.out.println("OK");
    }
    private void TestGet(){
        System.out.print("Valors: " + au.get_nomA() + " " + au.get_capA() + " ");
        if(au.get_pcAU()) System.out.print("S\n");
        else System.out.print("N\n");
    }
    private void TestSet(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nom de l'aula");
        au.set_nomA(sc.next());

        System.out.println("Introdueix la capacitat de l'aula");
        au.set_capacitatA(sc.nextInt());

        System.out.println("Es de tipus laboratori? S/N");
        String aux =sc.next();  
        while(!aux.equals("S")  && !aux.equals("s")  && !aux.equals("N") && !aux.equals("n") ) {
            System.out.println("Lletra no valida torna a introduir-ho, es aula de laboratori? : S/N" );
            aux =sc.next();
        }
        boolean Aulalaboratori=false;
        if(aux.equals("S") || aux.equals("s")) Aulalaboratori = true;
        au.set_Aulalaboratori(Aulalaboratori);

    }
}
