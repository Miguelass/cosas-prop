/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Miguel
 */
public class in {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      try(FileWriter fw = new FileWriter("C:\\Users\\Miguel\\Documents\\NetBeansProjects\\Pruebas\\data.prop.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
            {
    Scanner tap = new Scanner(System.in);
    out.println(tap.next());
} catch (IOException e) {
    //exception handling left as an exercise for the reader
}
    }
    
}
