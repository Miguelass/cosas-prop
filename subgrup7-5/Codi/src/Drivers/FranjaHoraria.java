
package Drivers;


public class FranjaHoraria {
    //juegen
    private String nomAss= "Asignatura";
    private String grup = "20";
    private String tipus="Teoria";
    private String gpare="21";
    private String nivell="N1";
    private int capg=20;
    private int ids=0;
    
    //creadora

    public FranjaHoraria (){};
    public FranjaHoraria (String nomAss, String grup, String tipus, int ids, String gpare, String nivell, int capg) {
        //super(nomAss, grup, tipus, ids, gpare, nivell, capg);
        this.nomAss=nomAss;
        this.grup=grup;
        this.tipus=tipus;
        this.ids=ids;
        this.gpare=gpare;
        this.nivell=nivell;
        this.capg=capg;
        
    }
    
    public String get_nomAss(){
        return this.nomAss;
    }
    
    public String get_tip() {
        return this.tipus;
    }
    
    public String get_grup() {
        return this.grup;
    }
    
    public String get_gpare() {
        return this.gpare;
    }
    
    public String get_nivell() {
        return this.nivell;
    }
    
    public int get_ids() {
        return this.ids;
    } 
     
    public int get_capacitat() {
        return capg;
    }
    
    public void set_nomAss(String nomAss) {
        this.nomAss=nomAss;
    }
    
    public void set_grup(String grup) {
        this.grup=grup;
    }
    
    public void set_tipus(String tipus ) {
        this.tipus=tipus;
    }
    
    public void set_gpare(String gpare) {
        this.gpare=gpare;
    }
    
    public void set_nivell(String nivell) {
        this.nivell=nivell;
    }
    
    public void set_ids(int ids) {
        this.ids=ids;
    }
}
