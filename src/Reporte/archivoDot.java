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
    public String rutaDotDiccionario = "/home/joseph/NetBeansProjects/[1Semestre2017]/Estructuras/Scrabble/src/Images/Grafo/diccionario.dot";

     public void dibujarColaletras(int numero) {
        try {
            ProcessBuilder pbuilder;

            /*
			 * Realiza la construccion del comando    
			 * en la linea de comandos esto es: 
			 * dot -Tpng -o archivo.png archivo.dot
             */
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", rutaRoot+"letras" + String.valueOf(numero) + ".png", rutaRoot+"letras.odt");
            pbuilder.redirectErrorStream(true);
            //Ejecuta el proceso
            pbuilder.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dibujarDiccionario(int numero) {
        try {
            ProcessBuilder pbuilder;

            /*
			 * Realiza la construccion del comando    
			 * en la linea de comandos esto es: 
			 * dot -Tpng -o archivo.png archivo.dot
             */
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", rutaRoot+"/diccionario" + String.valueOf(numero) + ".png", rutaDotDiccionario);
            pbuilder.redirectErrorStream(true);
            //Ejecuta el proceso
            pbuilder.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    public String devolverCadenaDot() {
//        String lineasDot = " V1 -> V2;\n";
//        lineasDot=lineasDot+" V2 <- V1;";
//        System.out.println(lineasDot);
//        return lineasDot;
//    }

    public void escribirDiccionario(String cadenaDot) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(rutaDotDiccionario);
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
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
     public void escribirHTMLDiccionario(int numero) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(rutaRoot+"/diccionario.html");
            pw = new PrintWriter(fichero);
            pw.println("<!DOCTYPE html>");
            pw.println("<html>\n"
                    + "<head>");
            pw.println("	<title></title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<Center><img src=" + (char) 34 + rutaRoot + "diccionario" + String.valueOf(numero) + ".png" + (char) 34 + ">\n"
                    + "</Center></body>\n"
                    + "</html>");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al escribir el HTML");
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
