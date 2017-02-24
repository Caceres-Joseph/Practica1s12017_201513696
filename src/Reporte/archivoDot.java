/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author joseph
 */
public class archivoDot {
    public String rutaRoot="/home/joseph/NetBeansProjects/[1Semestre2017]/Estructuras/Scrabble/src/Images/Grafo/";
    
    public void dibujarDot(String imagen, String dot){
         try {
            ProcessBuilder pbuilder;
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", rutaRoot+imagen, rutaRoot+dot);
            pbuilder.redirectErrorStream(true);
            pbuilder.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void escribirDiccionario(String cadenaDot, String ruta) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        String rutaPrinc=rutaRoot+ruta;
        try {
            fichero = new FileWriter(rutaPrinc);
            
            pw = new PrintWriter(fichero);
            pw.println("digraph grafo {");
            pw.println("node [shape=circle];");
            pw.println("node [style=filled];");
            pw.println("node [fillcolor=" + (char) 34 + "#EEEEEE" + (char) 34 + "];");
            pw.println("node [color=" + (char) 34 + "#EEEEEE" + (char) 34 + "];");
            pw.println("edge [color=" + (char) 34 + "#005500   #214a89" + (char) 34 + "];");
            pw.println(cadenaDot);
            pw.println("rankdir=LR;}");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al escribir el .dot");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
