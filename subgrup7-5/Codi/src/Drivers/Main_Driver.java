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
public class Main_Driver {
    public static void main(String[] args){
        FranjaHoraria_Driver Fh = new FranjaHoraria_Driver();       
        Aula_Driver Au = new Aula_Driver();       
        Sessio_Driver Se = new Sessio_Driver();        
        Grup_Driver Gr = new Grup_Driver();        
        Asignatura_Driver As = new Asignatura_Driver();        
        PlaEstudis_Driver Pa = new PlaEstudis_Driver();
        Restriccio_Driver Re = new Restriccio_Driver();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Prem 0 Driver FranjaHoraria \n"
            + "Prem 1 Driver Aula \n"
            + "Prem 2 Driver Sessio \n"
            + "Prem 3 Driver Grup \n"
            + "Prem 4 Driver Asignatura \n"
            + "Prem 5 Driver Pla Estudi \n" 
            + "Prem 6 Driver Restriccio \n"          
            + "Prem 7 Sortir ");
        int input = sc.nextInt();
        while(input != 7){
            switch (input){
                case 0:{
                    Fh.main();
                    break;
                }
                case 1:{
                    Au.main();
                    break;
                }
                case 2:{
                    Se.main();
                    break;
                }
                case 3:{
                    Gr.main();
                    break;
                }
                case 4:{
                    As.main();
                    break;
                }
                case 5:{
                    Pa.main();
                    break;
                }
                case 6:{
                    Re.main();
                    break;
                }
            } 
             System.out.println("Prem 0 Driver FranjaHoraria \n"
            + "Prem 1 Driver Aula \n"
            + "Prem 2 Driver Sessio \n"
            + "Prem 3 Driver Grup \n"
            + "Prem 4 Driver Asignatura \n"
            + "Prem 5 Driver Pla Estudi \n" 
            + "Prem 6 Driver Restriccio \n"          
            + "Prem 7 Sortir ");
            input = sc.nextInt();
        }
    }
}
