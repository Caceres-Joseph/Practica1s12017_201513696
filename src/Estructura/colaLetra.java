/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

import Juego.Tablero;
import Reporte.archivoDot;
import javafx.application.Platform;

/**
 *
 * @author joseph
 */
public class colaLetra {

    private colaNodo cabeza, ultimo;
    Integer contador=0;
    archivoDot archiDot = new archivoDot();
    
    public void push(letra letr) {
        colaNodo nuevoNodo = new colaNodo(letr);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            ultimo.siguiente = nuevoNodo;
        }
        ultimo = nuevoNodo;
        contador++;
       // System.out.println(contador);
    }

    public letra pop() {
        letra retorno = null;
        if (cabeza != null) {
            retorno = cabeza.Letra;
            colaNodo eliminar = cabeza;
            cabeza = cabeza.siguiente;
            eliminar.siguiente = null;
            if (cabeza == null) {
                ultimo = null;
            }
        }
        return retorno;
    }
    
    public String cadenaDot() {
        Integer cont=0;
        String cadena = "";
        colaNodo puntero = cabeza;

        while (puntero.siguiente != null) {
//            node1 [label=a];
            cadena=cadena+"node"+String.valueOf(cont)+" [label="+puntero.Letra.letra+"];\n";
            cadena=cadena+"node"+String.valueOf(cont)+" -> node"+String.valueOf(cont+1)+";\n";
//             cadena = cadena + aux.usuario1.nombre + "->" + aux.siguiente.usuario1.nombre + ";\n";
            puntero = puntero.siguiente;
            cont++;
        }
         cadena=cadena+"node"+String.valueOf(cont)+" [label="+puntero.Letra.letra+"];\n";
        return cadena;
    }

    public void actualizarWeb() {
        archiDot.escribirDiccionario(cadenaDot(),"cola.dot");
        archiDot.dibujarDot("cola.png","cola.dot");
        //archiDot.escribirHTMLDiccionario();
        
        Thread hilo = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    System.out.println("Error al correr hilo Cola>>>>" + ex);
                }
                Platform.runLater(() -> Tablero.webColaS.getEngine().reload());
            }
        };
        hilo.start();
        
//        System.out.println(contadorDot);
    }

    public class colaNodo {

        public letra Letra;
        public colaNodo siguiente;

        public colaNodo(letra Letra) {
            this.Letra = Letra;
        }

    }

}
