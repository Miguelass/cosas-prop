/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacio;
import CapadeDomini.ControladorDomini;
import CapadeDomini.Sessio;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Miguel
 */
public class ControladorPresentacio implements Initializable {
    ControladorDomini CD = new ControladorDomini();
    private static String[] nomdies = {"Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres", "Dissabte", "Diumenje"};
    private boolean[] diesok;
    Alert alertacreat = new Alert(AlertType.INFORMATION);
    Alert alertaerror =  new Alert(AlertType.ERROR);
    int numhores = 0;
    int numdies = 0;
    int horaini=0;
    int diaini=-1;
    int diafi=-1;
   
    @FXML TableColumn<Auxiliar, String> Hores = new TableColumn<>();
    @FXML TableColumn<Auxiliar, String> Dilluns = new TableColumn<>();
    @FXML TableColumn<Auxiliar, String> Dimarts = new TableColumn<>();
    @FXML TableColumn<Auxiliar, String> Dimecres = new TableColumn<>();
    @FXML TableColumn<Auxiliar, String> Dijous = new TableColumn<>();
    @FXML TableColumn<Auxiliar, String> Divendres = new TableColumn<>();
    @FXML TableColumn<Auxiliar, String> Dissabte = new TableColumn<>();
    @FXML TableColumn<Auxiliar, String> Diumenje = new TableColumn<>();
     
    @FXML private Label aulacreada;
    @FXML private Label asigcreada;
    @FXML private Label grupcreat;
    @FXML private Label numsessions;
    @FXML private Label sessiocreada;
    @FXML private Label asigborrada;
    @FXML private Label grupborrat;
    @FXML private Label aulaborrat;
    @FXML private Label sessioborrat;
    @FXML private ComboBox<String> Diainici = new ComboBox();
    @FXML private ComboBox<String> Diafinal = new ComboBox();
    @FXML private ComboBox<String> Horainici = new ComboBox();
    @FXML private ComboBox<String> Horafinal = new ComboBox();
    @FXML private ChoiceBox SelectorAsig = new ChoiceBox();
    @FXML private ComboBox<String> comboAula = new ComboBox();
    @FXML private ComboBox<String> comboAsig = new ComboBox();
    @FXML private ComboBox<String> comboGrup = new ComboBox();
    @FXML private ComboBox<String> comboTipusGrup = new ComboBox();
    @FXML private ComboBox<String> comboAsigSessio = new ComboBox();
    @FXML private ComboBox<String> comboGrupSessio = new ComboBox();
    
    @FXML private ComboBox<String> comboBorrarAula = new ComboBox();
    
    @FXML private ComboBox<String> comboBorrarAsig = new ComboBox();
    
    @FXML private ComboBox<String> comboBorrarAsigGrup = new ComboBox();
    @FXML private ComboBox<String> comboBorrarGrup = new ComboBox();
    
    @FXML private ComboBox<String> comboBorrarAsigSessio = new ComboBox();
    @FXML private ComboBox<String> comboBorrarGrupSessio = new ComboBox();
    @FXML private ComboBox<String> comboHoresBorrarSessio = new ComboBox();
    @FXML private ComboBox<String> moureaula1 = new ComboBox();
    @FXML private ComboBox<String> moureaula2 = new ComboBox();
    @FXML private ComboBox<String> mouredia1 = new ComboBox();
    @FXML private ComboBox<String> mouredia2 = new ComboBox();
    @FXML private ComboBox<String> mourehora1 = new ComboBox();
    @FXML private ComboBox<String> mourehora2 = new ComboBox();
    
    @FXML private ComboBox<String> horessessio = new ComboBox();
    @FXML private TextField nomaula = new TextField();
    @FXML private TextField capacitat = new TextField();
    @FXML private TextField nomasig = new TextField();
    @FXML private TextField nivell = new TextField();
    @FXML private TextField nomgrup = new TextField();
    @FXML private TextField nompare = new TextField();
    @FXML private TextField capacitatgrup = new TextField();
    @FXML private Button crearaula = new Button();
    @FXML private Button crearasignatura = new Button();
    @FXML private Button creargrup = new Button();
    @FXML private Button generarhorari = new Button();
    @FXML private Button crearsessio = new Button();
    @FXML private Button Carregar = new Button();
    
    @FXML private Button BorrarAula = new Button();
    @FXML private Button BorrarAsig = new Button();
    @FXML private Button BorrarGrup = new Button();  
    @FXML private Button BorrarSessio = new Button();
    @FXML private Button veuretotesaules = new Button();
    
    @FXML private RadioButton PCSi = new RadioButton();
    @FXML private RadioButton PCNo = new RadioButton();
    @FXML private RadioButton ObligatoriaSi = new RadioButton();
    @FXML private RadioButton ObligatoriaNo = new RadioButton();
  
    @FXML private TableView<Auxiliar> horari = new TableView();
    
    @FXML private void crear_aula(ActionEvent event) {
        alertaerror.setTitle("Error");
        alertaerror.setContentText(null);
        boolean pc = false;
        if("".equals(nomaula.getText()))  {
            alertaerror.setHeaderText("Has d'introduir el nom de l'aula");
            alertaerror.showAndWait();       
        }
        else if("".equals(capacitat.getText())) {
            alertaerror.setHeaderText("Has d'introduir una capacitat");
            alertaerror.showAndWait();
        }
        else if( !PCSi.isSelected() && !PCNo.isSelected()) {
            alertaerror.setHeaderText("Selecciona si es una aula amb PC's o no");
            alertaerror.showAndWait();
        }
        else {
            if(PCSi.isSelected()) pc = true;
            else if(PCNo.isSelected()) pc = false;
            if(CD.crear_aula(nomaula.getText(), capacitat.getText(), pc)) {
                //COMBOBOX DE LES AULES
                ObservableList<String> observableListAula = comboAula.getItems();
                observableListAula.add(nomaula.getText());
                comboAula.setItems(observableListAula);
                comboBorrarAula.setItems(observableListAula);
                moureaula1.setItems(observableListAula);
                moureaula2.setItems(observableListAula);
                aulacreada.setText("L'aula " + nomaula.getText()+ " s'ha creat correctament");
            }
            else {
                alertaerror.setHeaderText("Ja Existeix una Aula amb aquest nom");  
                alertaerror.showAndWait();
            }
        }
    }   
    @FXML private void crear_asignatura(ActionEvent event) {
        boolean obligatoria = false;
        alertaerror.setTitle("Error");
        alertaerror.setContentText(null);
        if("".equals(nomasig.getText()))  {
            alertaerror.setHeaderText("Has d'introduir el nom de l'asignatura");
            alertaerror.showAndWait();
        }
        else if("".equals(nivell.getText())) {
            alertaerror.setHeaderText("Has d'introduir un nivell");
            alertaerror.showAndWait();
        }
        else if( !ObligatoriaSi.isSelected() && !ObligatoriaNo.isSelected()) {
            alertaerror.setHeaderText("Selecciona si l'asignatura es obligatoria o no");
            alertaerror.showAndWait();
        }
        else {
            if(ObligatoriaSi.isSelected()) obligatoria = true;
            else if(ObligatoriaNo.isSelected()) obligatoria = false;
            if(CD.crear_asignatura(nomasig.getText(), nivell.getText())) {
                ObservableList<String> observableListAsig = comboAsig.getItems();
                observableListAsig.add(nomasig.getText());
                comboAsig.setItems(observableListAsig);
                comboAsigSessio.setItems(observableListAsig);
                comboBorrarAsig.setItems(observableListAsig);
                comboBorrarAsigGrup.setItems(observableListAsig);
                comboBorrarAsigSessio.setItems(observableListAsig);

                asigcreada.setText("L'assignatura " + nomasig.getText() + " s'ha creat correctament");
                //asigcreada.setText("L'asignatura " + nomasig.getText()+ " s'ha creat correctament");
            }
            else{
                alertaerror.setHeaderText("Ja existeix una asignatura amb el mateix nom");
                alertaerror.showAndWait();
            }
            
        }
    }    
    @FXML private void crear_grup(ActionEvent event) {
        //Alert dialogoAlerta = new Alert(AlertType.INFORMATION);
        //System.out.print(nomgrup.getText() + " " + capacitatgrup.getText() + " " + comboTipusGrup.getValue() + " " + nompare.getText());
        if( comboAsig.getSelectionModel().isEmpty()) {
            alertaerror.setHeaderText("Selecciona el de quina asignatura sera el grup");
            alertaerror.showAndWait();
        }
        else if("".equals(nomgrup.getText()))  {
            alertaerror.setHeaderText("Has d'introduir el nom del grup");
            alertaerror.showAndWait();
        }
        else if("".equals(capacitatgrup.getText())) {
            alertaerror.setHeaderText("Has d'introduir la capacitat del grup");
            alertaerror.showAndWait();
        }
        else if(comboTipusGrup.getSelectionModel().isEmpty()) {
            alertaerror.setHeaderText("Selecciona el tipus de grup");
            alertaerror.showAndWait();
        }
        else if((comboTipusGrup.getValue().equals("Laboratori") || comboTipusGrup.getValue().equals("Problemes")) && "".equals(nompare.getText())){
            alertaerror.setHeaderText("Has d'introduir el grup pare del nou subgrup");
            alertaerror.showAndWait();
        }
        else if((comboTipusGrup.getValue().equals("Laboratori") || comboTipusGrup.getValue().equals("Problemes")) && !CD.existeixgrup(comboAsig.getValue(),nompare.getText()) ){
            alertaerror.setHeaderText("No hi ha cap pare amb aquest nom");
            alertaerror.showAndWait();
        }
        else {
            if(CD.crear_grup(comboAsig.getValue(), nomgrup.getText(), Integer.parseInt(capacitatgrup.getText()), comboTipusGrup.getValue(), nompare.getText())) {
                /*ObservableList<String> observableListGrup = comboGrup.getItems();
                observableListGrup.add(nomgrup.getText());
                comboGrup.setItems(observableListGrup);*/
                //COMBO BOX DE SESSIo
                
                grupcreat.setText("El grup " + nomgrup.getText()+ " de l'assignatura: " + comboAsig.getValue() + " s'ha creat correctament");
               

            }
            else {
                alertaerror.setHeaderText("Ja existeix un grup amb aquest nom");
                alertaerror.show();
            }
        }
    }    
    @FXML private void crear_sessio(ActionEvent event) {
        if( comboAsigSessio.getSelectionModel().isEmpty()) {
            alertaerror.setHeaderText("Selecciona el de quina asignatura sera la sessio");
            alertaerror.showAndWait();
        }
        else if( comboGrupSessio.getSelectionModel().isEmpty()) {
            alertaerror.setHeaderText("Selecciona el de quin grup sera la sessio");
            alertaerror.showAndWait();
        }
        else if(horessessio.getSelectionModel().isEmpty()) {
            alertaerror.setHeaderText("Has de triar les hores de la sessio");
            alertaerror.showAndWait();
        }
        else {
            if(CD.crear_sessio(comboAsigSessio.getValue(), comboGrupSessio.getValue(), horessessio.getValue())) {
                sessiocreada.setText("S'ha creat una nova sessio de: " + horessessio.getValue()+ " hores");
            }            
        }                 
    }
    
    @FXML private void borrar_aula(ActionEvent event) {
        if(comboBorrarAula.getSelectionModel().isEmpty()) {
            alertaerror.setHeaderText("Selecciona una aula a borrar");
            alertaerror.showAndWait();
        }
        else{ 
            String nomaula = comboBorrarAula.getValue();
            ObservableList<String> observableListAula = comboAula.getItems();
            observableListAula.remove(nomaula);
            comboBorrarAula.setItems(observableListAula);
            comboAula.setItems(observableListAula);
            moureaula1.setItems(observableListAula);
            moureaula2.setItems(observableListAula);
            CD.borrar_aula(nomaula);            
            aulaborrat.setText("S'ha borrat l'aula: " + nomaula);
        }
    }    
    @FXML private void borrar_assig(ActionEvent event) {
        //agafem el valor del combobox
        String nomassig = comboBorrarAsig.getValue();
        //borrem l'assignatura
        CD.borrar_assig(nomassig);
        //actualitzem els combobox
        ObservableList<String> observableListAssig = comboBorrarAsig.getItems();
        observableListAssig.remove(nomassig);
        comboBorrarAula.setItems(observableListAssig);
        comboBorrarAsigGrup.setItems(observableListAssig);
        comboAsig.setItems(observableListAssig);
        comboAsigSessio.setItems(observableListAssig);
        comboBorrarAsigSessio.setItems(observableListAssig);

        asigborrada.setText("S'ha borrat l'assignatura: " + nomassig);
    }   
    @FXML private void borrar_grup(ActionEvent event) {
        //agafem el valor del combobox
        String nomassig = comboBorrarAsigGrup.getValue();
        String nomg = comboBorrarGrup.getValue();
        //borrem el grup
        CD.borrar_grup(nomassig, nomg);
        //actualitzem els combobox
        ObservableList<String> observableList = comboBorrarGrup.getItems();
        observableList.remove(nomg);
        comboBorrarGrup.setItems(observableList);
        comboGrupSessio.setItems(observableList);
        grupborrat.setText("S'ha borrat el grup: " + nomg + " de l'assignatura " + nomassig);
    }
    @FXML private void borrar_sessio(ActionEvent event) {
        if(comboHoresBorrarSessio.getValue()==null 
                || comboBorrarGrupSessio.getValue()==null
                || comboHoresBorrarSessio.getValue() == null) {}
        else {
            String nomassig = comboBorrarAsigSessio.getValue();
            String nomg = comboBorrarGrupSessio.getValue();
            String hores = comboHoresBorrarSessio.getValue();
            CD.borrar_sessio(nomassig,nomg,Integer.parseInt(hores));
        }
    }
 
    
    @FXML private void mostrargrups (MouseEvent event) {
        if(comboAsigSessio.getValue()!=null) {
            Set auxiliar = CD.consultar_grups(comboAsigSessio.getValue());
            ObservableList<String> observableListGrup = (FXCollections.observableArrayList(auxiliar));
            comboGrupSessio.setItems(observableListGrup);
        }
    }
    @FXML private void mostrargrupsb (MouseEvent event) {
        if(comboBorrarAsigGrup.getValue()!=null) {
            Set auxiliar = CD.consultar_grups(comboBorrarAsigGrup.getValue());
            ObservableList<String> observableListGrup = (FXCollections.observableArrayList(auxiliar));
            comboBorrarGrup.setItems(observableListGrup);
        }
    }
    @FXML private void mostrargrupsborrarsessio(MouseEvent event) {
        if(comboBorrarAsigSessio.getValue()!=null) {
            Set auxiliar = CD.consultar_grups(comboBorrarAsigSessio.getValue());
            ObservableList<String> observableListGrup = (FXCollections.observableArrayList(auxiliar));
            comboBorrarGrupSessio.setItems(observableListGrup);
        }
    }
    @FXML private void mostrarsessions(MouseEvent event){
        if(comboAsigSessio.getValue()!=null && comboGrupSessio.getValue()!=null) {
            List<Sessio> sessions = CD.consultar_sessions(comboAsigSessio.getValue(), comboGrupSessio.getValue());
            int aux = sessions.size();
            String text = String.valueOf(aux);
            for(int i=0; i<aux; ++i) {
               //int aux1 = sessions.get(aux).get_horesS();
               //text = text +  " " + String.valueOf(aux1);
               System.out.println(text + "jsipah`gpdl");
            }
            System.out.println(text + "jsipah`gpdl");
            numsessions.setText(text);
        }
    }
    @FXML private void mostrarsessionsborrar(MouseEvent event) {
        if(comboAsigSessio.getValue()!=null && comboGrupSessio.getValue()!=null) {
            List<Sessio> sessions = CD.consultar_sessions(comboAsigSessio.getValue(), comboGrupSessio.getValue());
            int aux=0;
            String a="";
            comboHoresBorrarSessio.getItems().clear();
            for(int i=0; i<sessions.size(); ++i) {
                aux=sessions.get(i).get_horesS();
                comboHoresBorrarSessio.getItems().add(String.valueOf(aux));
            }
        }
    }
    @FXML private void check_grup_tipus (ActionEvent event) {
        if(comboTipusGrup.getValue().equals("Laboratori") || comboTipusGrup.getValue().equals("Problemes")){
            nompare.setDisable(false);
            System.out.println("baina");
        } 
        else{
            nompare.setDisable(true);
            System.out.println("baina falsa");
        } 
    }
    
    @FXML private void generarhorari(ActionEvent event) {
        String nomdiaini, nomdiafi, hi,hf;
        int horafi=0, columnes=0;
        nomdiaini= Diainici.getValue();
        nomdiafi = Diafinal.getValue();
        hi = Horainici.getValue();
        hf = Horafinal.getValue();
        
        if(nomdiafi==null || nomdiaini==null || hi==null || hf==null) {
            Alert dialogoAlerta = new Alert(AlertType.INFORMATION);
            dialogoAlerta.setTitle("Error");
            dialogoAlerta.setHeaderText(null);
            dialogoAlerta.setContentText("Has de donar un valor als parametres diainici/diafinal/horainici/horafinal");
            dialogoAlerta.showAndWait();    
        }
        else {
            //calculem  numero de dies
            int i=0;
            for(String a : nomdies) {
                if(a.equals(nomdiaini)) diaini=i+1;
                if(a.equals(nomdiafi)) diafi=i+1;
                ++i;
            }
            System.out.println(diaini + "  " + diafi);
            if (diafi > diaini) {
                this.numdies = (diafi - diaini)+1;
                
            } else {
                this.numdies = diafi + 7 - diaini+1;
                
            }
          
            
            //calculem numero hores
            this.horaini = Integer.parseInt(hi);
            horafi = Integer.parseInt(hf); 
            if (horafi > horaini) {
                this.numhores = (horafi - horaini);
            } else {
                this.numhores = horafi + 24 - horaini;
            }
            //cridem a crear horari
            /*
            if(CD.crear_horari(numhores,numdies)) mostraraules(event);
            else {
                alertaerror.setContentText("No es pot generar l'horari");
                alertaerror.showAndWait();  
            }
            */
        }
        
    }
    @FXML private void mostrarhorariaula (ActionEvent event) {
        
        horari.getItems().clear();
        String nomaulaa=comboAula.getValue();
        int horauxiliar = horaini;
        List<String> pintar = CD.horariperaula(nomaulaa, numhores, numdies, 0, diaini-1, diafi-1);
        Auxiliar aux = new Auxiliar (horauxiliar,pintar.get(0), pintar.get(1), pintar.get(2), pintar.get(3), pintar.get(4), pintar.get(5), pintar.get(6));
        horari.getItems().add(aux);
        for(int j=1; j<numhores; ++j) {
           ++horauxiliar;
           pintar = CD.horariperaula(nomaulaa, numhores, numdies, j, diaini-1, diafi-1);
           aux =  new Auxiliar(horauxiliar,pintar.get(0), pintar.get(1), pintar.get(2), pintar.get(3), pintar.get(4), pintar.get(5), pintar.get(6));
           horari.getItems().add(aux);         
        }
        Hores.setResizable(true);
        Dilluns.setResizable(true);
        
    }
    
    @FXML private void mostraraules(ActionEvent evenet) {
        horari.getItems().clear();
        int horauxiliar;
        ObservableList<String> aules = (FXCollections.observableArrayList(comboAula.getItems()));
        for(int i=0; i<aules.size(); ++i) {
            horauxiliar=horaini;
            String nomaulaa=aules.get(i);
            List<String> pintar = CD.horariperaula(nomaulaa, numhores, numdies, 0, diaini-1, diafi-1);
            Auxiliar aux =  new Auxiliar(nomaulaa,"","","","","","","");
            horari.getItems().add(aux);  
            aux = new Auxiliar (horauxiliar,pintar.get(0), pintar.get(1), pintar.get(2), pintar.get(3), pintar.get(4), pintar.get(5), pintar.get(6));
            horari.getItems().add(aux);
            for(int j=1; j<numhores; ++j) {
                ++horauxiliar;
                pintar = CD.horariperaula(nomaulaa, numhores, numdies, j, diaini-1, diafi-1);
                aux =  new Auxiliar(horauxiliar,pintar.get(0), pintar.get(1), pintar.get(2), pintar.get(3), pintar.get(4), pintar.get(5), pintar.get(6));
                horari.getItems().add(aux);         
            }
            horari.getItems().add(aux); 
            
            
        }
    }
    
    
    @FXML private void Guardar(ActionEvent event) throws IOException{
         CD.Guardar();
     }
    @FXML private void Cargar(ActionEvent event) throws IOException{
        CD.Cargar();  
    //cargar aules
        comboAula.setItems(FXCollections.observableArrayList(CD.consultar_aula()));
        comboBorrarAula.setItems(FXCollections.observableArrayList(CD.consultar_aula()));  
    //cargar asignatures
        comboAsig.setItems(FXCollections.observableArrayList(CD.consultar_assignatura()));
        comboAsigSessio.setItems(FXCollections.observableArrayList(CD.consultar_assignatura()));    
    //cargar Grup
        //CD.consultar_assignatura();
        //comboGrup.setItems(FXCollections.observableArrayList(CD.consultar_grups()));
        //comboGrupSessio.setItems(FXCollections.observableArrayList(CD.consultar_grups()));
     }
   
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Hores.setCellValueFactory(new PropertyValueFactory<>("Hora"));
        Dilluns.setCellValueFactory(new PropertyValueFactory<>("Dill"));
        Dimarts.setCellValueFactory(new PropertyValueFactory<>("Dim"));
        Dimecres.setCellValueFactory(new PropertyValueFactory<>("Dime"));
        Dijous.setCellValueFactory(new PropertyValueFactory<>("Dij"));
        Divendres.setCellValueFactory(new PropertyValueFactory<>("Div"));
        Dissabte.setCellValueFactory(new PropertyValueFactory<>("Diss"));
        Diumenje.setCellValueFactory(new PropertyValueFactory<>("Dium"));
        
        ToggleGroup esPC = new ToggleGroup();
        PCSi.setToggleGroup(esPC);
        PCNo.setToggleGroup(esPC);
        Diainici.setItems(FXCollections.observableArrayList( "Dilluns" , "Dimarts" , "Dimecres", "Dijous", "Divendres", "Dissabte", "Diumenje"));
        Diafinal.setItems(FXCollections.observableArrayList( "Dilluns" , "Dimarts" , "Dimecres", "Dijous", "Divendres", "Dissabte", "Diumenje"));
        Horainici.setItems(FXCollections.observableArrayList("0", "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"));
        Horafinal.setItems(FXCollections.observableArrayList("0", "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"));
        ToggleGroup esObligatoria = new ToggleGroup();
        ObligatoriaSi.setToggleGroup(esObligatoria);
        ObligatoriaNo.setToggleGroup(esObligatoria);
        horessessio.setItems(FXCollections.observableArrayList("1", "2","3","4"));
        comboTipusGrup.setItems(FXCollections.observableArrayList( "Teoria" , "Laboratori" , "Problemes"));
        nompare.setDisable(true);
    }    
    
}
















