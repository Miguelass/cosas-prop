/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
 import java.io.*;
/**
 *
 * @author aleix
 */
public class Out {
     public static void main(String[] args)throws Exception 
  { 
  
  //File file = new File("C:\\Users\\Miguel\\Documents\\NetBeansProjects\\Pruebas\\data.prop.txt");
  //File file = new File("C:\\Users\\gerard\\Desktop\\Escritorio\\pro2\\JavaMasterRace\\NetBeans\\Prop\\subgrup7-5\\Codi\\src\\DB");
  File file = new File("C:\\Users\\aleix\\Desktop\\Escritorio\\pro2\\JavaMasterRace\\NetBeans\\Prop\\subgrup7-5\\Codi\\src\\DB");
  BufferedReader br = new BufferedReader(new FileReader(file)); 
  String st; 
  while ((st = br.readLine()) != null) 
    System.out.println(st); 
  } 

    
}
