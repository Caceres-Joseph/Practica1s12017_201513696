/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

/**
 *
 * @author joseph
 */
public class colaLetra {

    private colaNodo cabeza, ultimo;
    Integer contador=0;
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

    public class colaNodo {

        public letra Letra;
        public colaNodo siguiente;

        public colaNodo(letra Letra) {
            this.Letra = Letra;
        }

    }

}
