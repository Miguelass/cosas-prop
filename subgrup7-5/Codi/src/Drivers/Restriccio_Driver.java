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
public class Restriccio_Driver {
    Restriccio re = new Restriccio(10,4);
    public void main(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Prem 0 Testejar Afegir Restriccio \n"
            + "Prem 1 Sortir \n");
        int input = sc.nextInt();
        while(input != 1){
            switch (input){
                case 0:{
                    TestAfegirRestriccio();
                    break;
                }      
            } 
             System.out.println("Prem 0 Testejar Afegir Restriccio \n"
            + "Prem 1 Sortir \n");
        }
    }
    private void TestAfegirRestriccio(){
        Scanner sc = new Scanner(System.in);
        re.afegir_restriccio(sc);
    }
}
