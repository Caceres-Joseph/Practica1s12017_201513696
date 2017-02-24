package Estructura;

import Juego.Tablero;
import Reporte.archivoDot;
import javafx.application.Platform;

/**
 *
 * @author joseph
 */
public class listaDiccionario {

    public Nodo cabeza = null;
    public int longitud = 0;
    archivoDot archiDot = new archivoDot();

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

    public Integer ValorPalabra() {
        letra funcLetra = new letra();
        Integer retorno = 0;
        Nodo puntero = cabeza;

        while (puntero != null) {
            retorno = retorno + funcLetra.retornarValorLetra(puntero.palabra);
            System.out.println(puntero.palabra);
            puntero = puntero.siguiente;
        }
        return retorno;
    }

    public String cadenaDot() {

        String cadena = "";
        Nodo puntero = cabeza;

        while (puntero.siguiente != null) {
            cadena = cadena + puntero.palabra + "->" + puntero.siguiente.palabra + ";\n";
            puntero = puntero.siguiente;
        }
        return cadena;
    }

    public void actualizarWeb() {
        archiDot.escribirDiccionario(cadenaDot(), "diccionario.dot");
        archiDot.dibujarDot("diccionario.png", "diccionario.dot");
        //archiDot.escribirHTMLDiccionario();

        Thread hilo = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Error al correr hilo>>>>" + ex);
                }
                Platform.runLater(() -> Tablero.webDiccionarioS.getEngine().reload());
            }
        };
        hilo.start();

//        System.out.println(contadorDot);
    }

    public String cadenaDot1() {
        Integer cont = 0;
        String cadena = "";
        Nodo puntero = cabeza;

        while (puntero.siguiente != null) {
            cadena = cadena + "node" + String.valueOf(cont) + " [label=" + puntero.palabra + "];\n";
            cadena = cadena + "node" + String.valueOf(cont) + " -> node" + String.valueOf(cont + 1) + ";\n";
//             cadena = cadena + aux.usuario1.nombre + "->" + aux.siguiente.usuario1.nombre + ";\n";
            puntero = puntero.siguiente;
            cont++;
        }
        cadena=cadena+"node"+String.valueOf(cont)+" [label="+puntero.palabra+"];\n";
        return cadena;
    }

    public void actualizarWeb1() {
        archiDot.escribirDiccionario(cadenaDot1(), "ficha.dot");
        archiDot.dibujarDot("ficha.png", "ficha.dot");
        //archiDot.escribirHTMLDiccionario();

        Thread hilo = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Error al correr hilo>>>>" + ex);
                }
                Platform.runLater(() -> Tablero.webFichaS.getEngine().reload());
            }
        };
        hilo.start();

//        System.out.println(contadorDot);
    }

    public boolean buscar(String busqueda) {
        boolean retorno = false;
        Nodo puntero = cabeza;
        System.out.println("-----------------Diccionario------------------");
        while (puntero.siguiente != null) {
            System.out.println(puntero.palabra);
            if (busqueda.equals(puntero.palabra)) {
                System.out.println("si lo encontro");
                retorno = true;
                break;

            }
            puntero = puntero.siguiente;

        }
        return retorno;
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
