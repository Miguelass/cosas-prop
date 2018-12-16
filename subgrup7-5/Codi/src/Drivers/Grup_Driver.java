/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

/**
 *
 * @author Miguel
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

public class Grup_Driver {
    private Grup gr = new Grup();
    public void main(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Prem 0 Testejar la creadora \n"
            + "Prem 1 Testejar els get() \n"
            + "Prem 2 Testejar els set() \n"
            + "Prem 3 Testejar AfegirSessions \n"
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
                    TestAsignarSessions();
                }
            } 
            System.out.println("Prem 0 Testejar la creadora \n"
            + "Prem 1 Testejar els get() \n"
            + "Prem 2 Testejar els set() \n"
            + "Prem 3 Testejar Sessions \n"
            + "Prem 4 Sortir ");
            input = sc.nextInt();
        }
    }
    private void TestCreadora(){
        gr = new Grup();
        System.out.println("OK");
    }
    private void TestGet(){
        System.out.println("Valors : nomGrup " +gr.get_nomg() + " Tipus " + gr.get_tipusg()+ " GrupPare " + gr.get_pare() +" Capacitat " + gr.get_capg());   
    }
    private void TestSet(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nom del Grup");
        gr.set_nomgrup(sc.next());
        System.out.println("Introdueix el tipus de grup (Teoria/Laboratori/Problemes) :");
        String tipus=sc.next();
        while(!tipus.equals("Laboratori")  && !tipus.equals("Teoria")  && !tipus.equals("Problemes") ) {
            System.out.println("Has introduit el tipus de manera incorrecte, torna a introduir-ho (Teoria/Laboratori/Problemes): " );
            tipus =sc.next();
        }
        gr.set_tipus(tipus);
        String pare="";
        if(tipus.equals("Laboratori") || tipus.equals("Problemes")) {
             System.out.println("Introdueix el grup pare del subgrup :");
             pare = sc.next();
        }
        gr.set_pare(pare);
        System.out.println("Introdueix la capacitat del grup");
        gr.set_capacitat(sc.nextInt());  
    }
    private void TestAsignarSessions(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nom de l'asignatura");
        String nomass = sc.next();
        System.out.println("Introdueix el nivell de l'asignatura");
        String nivell = sc.next();
        gr.assignar_sessions(sc,nomass,nivell);
        List <Sessio> ListS = gr.get_sessions();
        System.out.println(ListS.size() + " Sessions afegides ");
    } 
}
