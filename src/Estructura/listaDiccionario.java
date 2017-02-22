package Estructura;

import Reporte.archivoDot;

/**
 *
 * @author joseph
 */
public class listaDiccionario {

    public Nodo cabeza = null;
    public int longitud = 0;
    archivoDot archiDot=new archivoDot();
    Integer contadorDot=0;
    /**
     * ******************
     *                  *
     * Insertar *********
     */
    public void insertarAlPrincipio(String palabra) {

        Nodo nodo = new Nodo(palabra);
        if (cabeza != null) {
            nodo.siguiente = cabeza;
        }
        longitud++;
        cabeza = nodo;
    }

    public void insertarDeUltimo(String libro) {
        Nodo nodo = new Nodo(libro);
        if (cabeza == null) {
            cabeza = nodo;
        } else {
            Nodo puntero = cabeza;
            while (puntero.siguiente != null) {
                puntero = puntero.siguiente;

            }
            puntero.siguiente = nodo;
            longitud++;
        }
    }

    /**
     * ******************
     *                  *
     * Eliminar
     *
     *********
     * @param posicion
     * @return
     */
    public String pop(int posicion) {
        String retorno = null;
        if (posicion == 0) {
            retorno = obtener(posicion);
            eliminarPrincipio();
        } else if (posicion < longitud) {
            retorno = obtener(posicion);
            eliminar(posicion);
        } else if (posicion == longitud) {
            retorno = obtener(posicion);
            eliminarUltimo();
        } else {
            System.out.println("No se encontró elemento");
        }

        return retorno;
    }

    public void eliminarPrincipio() {
        if (cabeza != null) {
            Nodo primer = cabeza;
            cabeza = cabeza.siguiente;
            primer.siguiente = null;
            longitud--;
        }

    }

    public void eliminarUltimo() {
        Nodo puntero = cabeza;
        if (cabeza.siguiente == null) {
            cabeza = null;
        } else {
            while (puntero.siguiente.siguiente != null) {
                puntero = puntero.siguiente;
            }
            puntero.siguiente = null;
            longitud--;
        }
    }

    public void eliminar(int n) {
        if (cabeza != null) {
            if (n == 0) {
                eliminarPrincipio();
            } else if (n < longitud) {
                Nodo puntero = cabeza;
                int contador = 0;
                while (contador < (n - 1)) {
                    puntero = puntero.siguiente;
                    contador++;
                }
                Nodo temp = puntero.siguiente;
                puntero.siguiente = temp.siguiente;
                temp.siguiente = null;
                longitud--;
            }
        }

    }

    /**
     * ******************
     *                  *
     * Busqueda *********
     */
    public void imprimir() {
        Nodo puntero = cabeza;
        System.out.println("------------Long:" + String.valueOf(longitud) + "---------");
        while (puntero.siguiente != null) {

            System.out.println(puntero.palabra);
            puntero = puntero.siguiente;
        }
        System.out.println(puntero.palabra);
        System.out.println("----------------------fin--------------------");
    }

    public String cadenaDot() {
        String cadena = "";
        Nodo puntero = cabeza;

        while (puntero.siguiente.siguiente != null) {

//        String lineasDot = " V1 -> V2;\n";
//        lineasDot=lineasDot+" V2 <- V1;";
//        System.out.println(lineasDot);
//        return lineasDot;
            cadena = cadena + puntero.palabra + "->" + puntero.siguiente.palabra + ";\n";
            
            System.out.println(puntero.palabra);
            puntero = puntero.siguiente;
        }
        return cadena;
    }
    public Integer actuaalizarDot(){
       archiDot.escribirHTMLDiccionario(contadorDot);
        archiDot.escribirDiccionario(cadenaDot());
        archiDot.dibujarDiccionario(contadorDot);
        
       return  contadorDot++;
    }

    public boolean buscar(String busqueda) {

        Nodo puntero = cabeza;
        while (puntero.siguiente != null) {

            puntero = puntero.siguiente;
            puntero = puntero.siguiente;
            if (puntero.palabra.equals(busqueda)) {
                System.out.println(puntero.palabra);
                break;
            }

        }
        return true;
    }

    public String obtener(int n) {
        String retorno = null;
        if (cabeza != null) {
            Nodo puntero = cabeza;
            int contador = 0;
            while (contador < n && puntero.siguiente != null) {
                puntero = puntero.siguiente;
                contador++;
            }
            if (contador != n) {
            } else {
                retorno = puntero.palabra;
            }
        }
        return retorno;
    }

    public class Nodo {

        public String palabra;
        public Nodo siguiente;

        public Nodo(String palabra) {
            this.palabra = palabra;
        }

    }
}
