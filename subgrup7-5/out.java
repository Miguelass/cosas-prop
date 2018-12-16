/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;
 import java.io.*;
/**
 *
 * @author Miguel
 */
public class out {
   
  public static void main(String[] args)throws Exception 
  { 
  // We need to provide file path as the parameter: 
  // double backquote is to avoid compiler interpret words 
  // like \test as \t (ie. as a escape sequence) 
  File file = new File("C:\\Users\\Miguel\\Documents\\NetBeansProjects\\Pruebas\\data.prop.txt");
  BufferedReader br = new BufferedReader(new FileReader(file)); 
  String st; 
  while ((st = br.readLine()) != null) 
    System.out.println(st); 
  } 
}
