/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPersistencia;
import CapadeDomini.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Miguel
 */
public class ControladorPersistencia {
    @SuppressWarnings("empty-statement")
    public void Guardar(PlaEstudis pa, LinkedHashMap<String,Aula> aules, Horari h) throws IOException{
        //Dades
        Path path =Paths.get("C:\\Prop\\dades.txt");
        Files.deleteIfExists(path);
        try(FileWriter fw = new FileWriter("C:\\Prop\\dades.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
            {
                Scanner tap = new Scanner(System.in);
                out.println( pa.get_numdies() + " " + pa.get_numhores());
                out.close();
            } 
        
        //Aules
        path =Paths.get("C:\\Prop\\Aules.txt");
        Files.deleteIfExists(path);
        try(FileWriter fw = new FileWriter("C:\\Prop\\Aules.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
            {
                Scanner tap = new Scanner(System.in);
                for(String key : aules.keySet()){
                    Aula au = aules.get(key);
                    out.print(key + " " + au.get_capA());    
                    if(au.get_pcAU()) out.println(" S" );
                    else out.println(" N" );
                }
                out.close();
            }
        //Asignatures
        path =Paths.get("C:\\Prop\\Asignatures.txt");
        Files.deleteIfExists(path);
        LinkedHashMap<String,Asignatura> asig = pa.get_asignatures();
        try(FileWriter fw = new FileWriter("C:\\Prop\\Asignatures.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
            {
                Scanner tap = new Scanner(System.in);
                for(String keyasig : asig.keySet()){
                    Asignatura au = asig.get(keyasig);
                    out.println(keyasig + " " + au.get_nivell());
                    LinkedHashMap<String,Grup> grup = au.get_grups();
                    for(String keygrup : grup.keySet()){
                        Grup gr = grup.get(keygrup);
                        out.print("   " + keygrup + " " + gr.get_capg() + " " + gr.get_tipusg()+ " ");
                        if(gr.get_pare().equals("")) out.print("null");
                        else out.print(gr.get_pare());
                        List<Sessio> sessio = gr.get_sessions();
                        for (Sessio s : sessio) out.print( " " + s.get_horesS() + " " + s.get_ids());
                        out.println( " /" );
                    }
                out.println( "^" );    
                }
                out.println( "*" );
                out.close();
            }
        
        //Horari
        path =Paths.get("C:\\Prop\\Horari.txt");
        Files.deleteIfExists(path);
        FranjaHoraria[][][] ho = h.get_horari();
        try(FileWriter fw = new FileWriter("C:\\Prop\\Horari.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
            {
                //Continua aquiq
                for(int x = 0; x<aules.size(); x++){
                    for(int y = 0;y<pa.get_numhores();y++){
                        for(int z = 0;z<pa.get_numdies();z++){
                            if(ho[y][z][x] != null){
                                out.print(ho[y][z][x].get_nomAss()+ "   " + ho[y][z][x].get_grup()+ "   " + ho[y][z][x].get_tip() + "   " + ho[y][z][x].get_ids()+ "   "); 
                                if(ho[y][z][x].get_gpare().equals("")) out.print("null");
                                else out.print(ho[y][z][x].get_gpare());
                                out.print("   " + ho[y][z][x].get_nivell()+ "   " + ho[y][z][x].get_capacitat() + "           ");
                                //out.print(y + " " + z + " " + x);
                            }
                            else out.print("null    ");
                        }
                    out.println();    
                    }
                    out.println();
                    out.println();
                    out.println();
                }                       
            }
    }
    
    public void Cargar(PlaEstudis pa, LinkedHashMap<String,Aula> aules) throws IOException{
        //Dades
        System.out.println("entra dades");
        File dad = new File("C:\\Prop\\dades.txt");
        Scanner scd = new Scanner(new FileInputStream(dad));
        if(scd.hasNext()){
            pa.set_numdies(scd.nextInt());
            pa.set_numhores(scd.nextInt());
            
        }
        //Aules
        System.out.println("entra");
        File aul = new File("C:\\Prop\\Aules.txt");
        Scanner sc = new Scanner(new FileInputStream(aul));
        while (sc.hasNext()){
            String nom = sc.next();
            int capacitat = sc.nextInt();
            boolean aula;
            if(sc.next().equals("S")) aula = true;
            else aula = false;
            Aula au = new Aula(nom,capacitat,aula);
            aules.put(nom, au);
        } 
        sc.close();
        
        //Asignatures
        //LinkedHashMap<String,Asignatura> auxiliar = new LinkedHashMap<String,Asignatura>();
        File asig = new File("C:\\Prop\\Asignatures.txt");
        Scanner scc = new Scanner(new FileInputStream(asig));
        
        System.out.println("antes de cargar aula");
        if(scc.hasNext()){
            String input = scc.next();
            System.out.println("entra carga");
            while(!input.equals("*")){
                
                LinkedHashMap<String,Asignatura> asigs = new LinkedHashMap<String,Asignatura>();
                String nomA = input;
                String nivellA = scc.next();
                Asignatura as = new Asignatura(nomA,nivellA);
                //System.out.println(nomA+ " " + nivellA);
                input = new String();
                input = scc.next();

                while(!input.equals("^")){
                    
                    LinkedHashMap<String,Grup> Grups = new LinkedHashMap<String,Grup>();
                    String nomG = input; 
                    int capacitat = scc.nextInt();
                    String tipus = scc.next();
                    String pare = scc.next();
                    //System.out.println(nomG+ " " + capacitat + " " + tipus + " " + pare);
                    if(pare.equals("null")) pare = "";
                    Grup gr = new Grup(nomG,tipus,pare,capacitat);
                    input = scc.next();
                    List<Sessio> ses = new ArrayList<Sessio>();

                    while(!input.equals("/")){
                        //Acabar
                        int horess = Integer.parseInt(input);
                        int id = scc.nextInt();
                        //System.out.println(horess + " " + id);
                        Sessio se = new Sessio(horess,id);
                        
                        System.out.println(nomA + " " + nivellA+ " " + nomG+ " " + capacitat+ " " + tipus+ " " + id+ " " + pare +"  "+ horess);
                        FranjaHoraria Fh = new FranjaHoraria (nomA, nivellA, nomG, capacitat, tipus, id, pare);
                        for(int aux=0; aux<horess; ++aux) se.afegir_franja(Fh);
                        
                        ses.add(se);
                        input = scc.next();
                    }

                   gr.set_sessions(ses);
                   Grups.put(nomG, gr);
                   as.set_Grup(Grups);
                   input = scc.next();
                }
                pa.afegir_asignatura(nomA, as);
                input = scc.next();
            } 
            scc.close();
            System.out.println("acaba");
        }
        //Cargar Horario
        File hora = new File("C:\\Prop\\Horari.txt");
        Scanner sch = new Scanner(new FileInputStream(hora));
        System.out.println("antes de cargar Horari");
        Horari horari = pa.get_horari();
        FranjaHoraria[][][] ho = new FranjaHoraria[pa.get_numhores()][pa.get_numdies()][aules.size()];
        
                
        if(sch.hasNext()){
            String input = sch.next();
            for(int x = 0; x<aules.size() ;x++){
                //System.out.println("entra aules");
                //System.out.println(pa.get_numhores());
                for(int y = 0; y<pa.get_numhores() ;y++){
                    //System.out.println("entra hores");
                    for(int z = 0; z<pa.get_numdies() ;z++){
                        //System.out.println("entra dies");
                        if(input.equals("null")){
                            FranjaHoraria fj = new FranjaHoraria();
                            ho[y][z][x] = fj;
                            System.out.println("null");
                            System.out.println(y + " " +z +" "+x);
                        } 
                        else{
                            String nom = input;
                            String grup = sch.next();
                            String tipus = sch.next();
                            int id = sch.nextInt();
                            String pare = sch.next();
                            if(pare.equals("null")) pare = "";
                            String nivell = sch.next();
                            int capacitat = sch.nextInt();
                            FranjaHoraria fj = new FranjaHoraria(nom,nivell,grup,capacitat,tipus,id,pare);
                            System.out.println(nom+" "+nivell+" "+grup+" "+capacitat+" "+tipus+" "+id+" "+pare);
                            System.out.println(y + " " +z +" "+x);
                            ho[y][z][x] = fj;
                        }    
                        if(sch.hasNext())input = sch.next();
                    }
                }
            }
        }
        horari.set_horari(ho);
        pa.set_horari(horari);
    }
}