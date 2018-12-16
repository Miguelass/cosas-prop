
package CapadeDomini;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;


public class Horari {
    private FranjaHoraria[][][] horari;
    boolean ple=false;
    
    public Horari(){}
    
    public FranjaHoraria[][][] crear_horari(List<Sessio> Sessions, LinkedHashMap<String,Aula> Aules, int numdies, int numhores, Restriccio res) {
        horari = new FranjaHoraria[numhores][numdies][Aules.size()];
        
        ListIterator it_ss = Sessions.listIterator();
        ple=omplir_horari(it_ss, Aules, numdies, numhores,res);
        return horari;
    }
    
    public boolean omplir_horari(ListIterator it_ss, LinkedHashMap<String,Aula> Aules, int numdies, int numhores, Restriccio res) { //AIXO ES UNA PROVA PER FER BE EL /*OUTPUT*/
        boolean ficat = false;
        if(!it_ss.hasNext()){
            return true;
        } 
        Sessio ss = (Sessio) it_ss.next();
        Set<Map.Entry<String, Aula>> entrySet = Aules.entrySet();
        Iterator it = entrySet.iterator();
        int i=0;
        for (Map.Entry<String, Aula> entry : Aules.entrySet()) {
            Aula au = entry.getValue();
            for (int j = 0; j < numhores && !ficat; ++j) {
                for (int k = 0; k < numdies && !ficat; ++k) {
                    
                    int hores = ss.get_horesS();
                    List<FranjaHoraria> franjess=ss.get_franjes();
                    ListIterator it_ff=franjess.listIterator();
                    //System.out.print(au.get_nomA());
                    FranjaHoraria Fhh =(FranjaHoraria) it_ff.next();
                    //System.out.print(au.get_nomA() + " " + Fhh.get_nomAss() + " " + Fhh.get_grup());
                    //AQUI FALTA FICAR LES COMPROVACIONS DE RESTRICCIO
                    if (horari[j][k][i] == null 
                        && cabe(i, k, j, hores, numhores)
                        && mateixnivell(j,k,Fhh.get_grup(),Fhh.get_gpare(), Fhh.get_nomAss(), Fhh.get_nivell(), Aules, numhores)
                        && compatible(au, Fhh.get_capacitat(), Fhh.get_tip())
                        //restriccions
                        )
                    {
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
                            ficat=false;
                            it_ss.previous();
                        }
                    }
                }
            }
            ++i;
        }
        return false;

    }

    private boolean mateixnivell(int h, int d, String nomg, String pare, String nomass, String nivell,  LinkedHashMap<String,Aula> Aules, int numhores) {
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
                    }
                }
            }
        }
        return true;
    }

    public boolean compatible(Aula au, int capacitat, String tipusg) {
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
    
    
    public void modificar_horari(int cd, int pd, int ph, int cdn, int pdn, int phn, int numhores, LinkedHashMap<String,Aula> Aules, Restriccio res) {
        if(horari[ph][pd][cd]==null) System.out.println("No hi ha cap sessio en aquesta posicio");
        else  {
            FranjaHoraria Fhh=horari[ph][pd][cd];
            int horessessio=0;
            int pha=ph;
            String grup = Fhh.get_grup();
            String assignatura = Fhh.get_nomAss();
            int id=Fhh.get_ids();
            while (pha < numhores && horari[pha][pd][cd] != null && horari[pha][pd][cd].get_grup().equals(grup) && horari[pha][pd][cd].get_nomAss().equals(assignatura) && horari[pha][pd][cd].get_ids() == id) {
                    horessessio++;
                    pha++;
                }
            int pha1=ph-1;
            while (pha1 >-1  && horari[pha1][pd][cd] != null && horari[pha1][pd][cd].get_grup().equals(grup) && horari[pha1][pd][cd].get_nomAss().equals(assignatura) && horari[pha1][pd][cd].get_ids() == id) {
                    horessessio++;
                    pha1--;
            }

            if(horari[phn][pdn][cdn]==null) {
                List<String> indexes = new ArrayList<>(Aules.keySet());
                String nomaula = indexes.get(cdn);
                Aula au = Aules.get(nomaula);
                        
                
             
                FranjaHoraria faux=horari[ph][pd][cd];
                horari[ph][pd][cd]=null;
                if(cabe(cdn, pdn, phn, horessessio, numhores) 
                        && compatible(au, Fhh.get_capacitat(), Fhh.get_tip()) 
                        && mateixnivell(phn, pdn, Fhh.get_grup(), Fhh.get_gpare() , Fhh.get_nomAss(), Fhh.get_nivell(), Aules, numhores) 
                        && res.comprovar(Fhh.get_nomAss(), Aules, phn, pdn, cdn)) {
                        horari[ph][pd][cd]=faux;
                        System.out.println("ENTEIYAFGHDLKJBAKJB");
                        pha=ph;
                        int phan=phn;
                        while (pha < numhores && horari[pha][pd][cd] != null && horari[pha][pd][cd].get_grup().equals(grup) && horari[pha][pd][cd].get_nomAss().equals(assignatura) && horari[pha][pd][cd].get_ids() == id) {
                            horari[phan][pdn][cdn]=horari[pha][pd][cd];
                            horari[pha][pd][cd]=null;
                            pha++;
                            phan++;
                        }
                        pha1=ph-1;
                        int phan1=phn-1;
                        while (pha1 >-1  && horari[pha1][pd][cd] != null && horari[pha1][pd][cd].get_grup().equals(grup) && horari[pha1][pd][cd].get_nomAss().equals(assignatura) && horari[pha1][pd][cd].get_ids() == id) {
                            horari[phan1][pdn][cdn]=horari[pha1][pd][cd];
                            horari[pha1][pd][cd]=null;
                            pha1--;
                            phan1--;
                        }                       
                }
                else System.out.println("No es pot moure la sessio");
            }
        }
    }

    
    public boolean get_ple() {
        return ple;
    }
    
    
    public FranjaHoraria[][][] get_horari() {
        return horari;
    }
    public void set_horari(FranjaHoraria[][][] horari){
        this.horari = horari;
    }
    
    
}
