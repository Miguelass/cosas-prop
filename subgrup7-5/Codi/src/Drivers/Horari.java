/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author gerar
 */
public class Horari {
    private FranjaHoraria[][][] horari;
    boolean ple=false;
    
    public Horari(){}
    
    public FranjaHoraria[][][] crear_horari(List<Sessio> Sessions, List<Aula> Aules, int numdies, int numhores, Restriccio res) {
        horari = new FranjaHoraria[numhores][numdies][Aules.size()];
        
        ListIterator it_ss = Sessions.listIterator();
        ple=omplir_horari(it_ss, Aules, numdies, numhores,res);
        return horari;
    }
    
    public boolean omplir_horari(ListIterator it_ss, List<Aula> Aules, int numdies, int numhores, Restriccio res) { //AIXO ES UNA PROVA PER FER BE EL /*OUTPUT*/
        boolean ficat = false;
        if(!it_ss.hasNext()){
            //System.out.println("Falsa salida");
            return true;
            
        } 
        Sessio ss = (Sessio) it_ss.next();
        ListIterator it = Aules.listIterator();
        for (int i = 0; i < Aules.size() && !ficat; ++i) {
            Aula au = (Aula) it.next();
            for (int j = 0; j < numhores && !ficat; ++j) {
                for (int k = 0; k < numdies && !ficat; ++k) {
                    
                    int hores = ss.get_horesS();
                    List<FranjaHoraria> franjess=ss.get_franjes();
                    ListIterator it_ff=franjess.listIterator();
                    FranjaHoraria Fhh =(FranjaHoraria) it_ff.next();
                    //System.out.println("Intento ficar " + Fhh.get_nomAss());
                    //System.out.println(i+" "+ j + " "+ k + "  ");
                    
                    if (horari[j][k][i] == null && cabe(i, k, j, hores, numhores) 
                        && compatible(au, Fhh.get_nomAss(), Fhh.get_capacitat(), Fhh.get_tip()) 
                        && mateixnivell(j, k, Fhh.get_grup(), Fhh.get_gpare() , Fhh.get_nomAss(), Fhh.get_nivell(), Aules, numhores) 
                        && res.comprovar(Fhh.get_nomAss(), Aules, j, k, i)) 
                    {
                        //System.out.println("entra");
                        List<FranjaHoraria> franjes=ss.get_franjes();
                        ListIterator it_f=franjes.listIterator();
                        for (int h = 0; h < franjes.size();++h){
                            FranjaHoraria Fh =(FranjaHoraria) it_f.next();
                            horari[j + h][k][i] = Fh;
                        }
                        ficat = true;
                        if (omplir_horari(it_ss, Aules, numdies, numhores, res)) {
                            return true;
                        }
                        else {
                            for (int h = 0; h < franjes.size(); ++h){
                                horari[j + h][k][i] = null;
                            }
                            //System.out.println("Nope");
                            ficat=false;
                            it_ss.previous();
                        }
                    }
                }
            }
        }
        return false;

    }

   

    private boolean mateixnivell(int h, int d, String nomg, String pare, String nomass, String nivell,  List<Aula> Aules, int numhores) {
        boolean incorrecte = false;
        for (int i = 0; i < Aules.size() && !incorrecte; ++i) {
            if (horari[h][d][i] != null) {
                if (nivell.equals(horari[h][d][i].get_nivell())) {
                    if (horari[h][d][i].get_grup().equals(nomg)) {
                        return false;
                    }
                    String mipadre = pare;
                    String grup = horari[h][d][i].get_grup();
                    //soy el padre
                    if (mipadre.equals("")) {
                        String socpare = horari[h][d][i].get_gpare();
                        if (nomg.equals(socpare)) {
                            return false;
                        }
                    } //soy el hijo
                    else if (mipadre.equals(grup)) {
                        return false;
                    }
                }
            }
            for (int j = 0; j < numhores; ++j) {
                if (horari[j][d][i] != null) {
                    if (horari[j][d][i].get_nomAss().equals(nomass)) {
                        if (horari[j][d][i].get_grup().equals(nomg)) {
                            return false;
                        }
                        //System.out.println(gg.get_nomg() + "    " + horari[j][d][i].get_grup() + "    " + j + "   " + d + "    " + i);
                    }
                }
            }
        }
        return true;
    }

    public boolean compatible(Aula au, String aa, int capacitat, String tipusg) {
        //si el grup es mes gran que l'aula retorna false
        if (au.get_capA() < capacitat) {
            return false;
        }
        //si es de tipus laboratori i l'aula no te pc's retorna false i viceversa
        if ("Laboratori".equals(tipusg) && !au.get_pcAU()) {
            return false;
        } else if (!"Laboratori".equals(tipusg) && au.get_pcAU()) {
            return false;
        }
        return true;
    }

    private boolean cabe(int aula, int dia, int hora, int Hses, int numhores ) {
        for (int hs = 0; hs < Hses; hs++) {
            if (hora + Hses > numhores) {
                return false;
            } else if (horari[hora + hs][dia][aula] != null) {
                return false;
            }
        }
        return true;
    }
    
    public boolean get_ple() {
        return ple;
    }
    
    public FranjaHoraria[][][] get_horari() {
        return horari;
    }
    
}

