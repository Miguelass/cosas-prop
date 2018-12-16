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
public class PlaEstudis_Driver {
    PlaEstudis pa = new PlaEstudis();
    public void main(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Prem 0 Testejar la creadora \n"
            + "Prem 1 Testejar els get() \n"
            + "Prem 2 Testejar signacio asignatures \n");
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
                    TestCrearAsignatura();
                    break;
                }
                case 3:{
                    //TestAfegirGrups();
                }
            } 
            System.out.println("Prem 0 Testejar la creadora \n"
            + "Prem 1 Testejar els get() \n"
            + "Prem 2 Testejar signacio asignatures \n");
            input = sc.nextInt();
        }
    }
    private void TestCreadora(){
        Scanner sc = new Scanner(System.in);
        pa.crear_pla_destudi(sc);
    }
    private void TestGet(){
        System.out.println("Valors: Titulacio " + pa.get_Titulacio() + " Dies de clase " + pa.get_numdies() + " De " + pa.get_horainici() +" A "+ pa.get_horafi());
           
    }
    private void TestCrearAsignatura(){
        Scanner sc = new Scanner(System.in);
        pa.crear_asignatures(sc);
        List<Asignatura> lista = pa.get_asignatures();
        System.out.println(lista.size()+ " Asignatures asignades al pla d'estudi");
    }
    
}
