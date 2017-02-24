package Estructura;

import Juego.Tablero;
import Reporte.archivoDot;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author joseph
 */
public class listaUsuario {

    public Nodo puntero;
    public Nodo inicio;
    private Nodo ultimo;
    private int tamanio;
    archivoDot archiDot = new archivoDot();

    public listaUsuario() {
        inicio = null;
        ultimo = null;
        tamanio = 0;
    }

    public boolean esVacia() {
        return inicio == null;
    }

    public int getTamanio() {
        return tamanio;
    }
    public void avanzarUno(){
        puntero = puntero.siguiente;
    }
    public Integer avanzarEspacio(Integer valor) {//Para avanzar hay que inicialiar el auxiliar
        Integer retorno = 0;
        puntero.usuario1.puntuacion = puntero.usuario1.puntuacion + valor;
        retorno = puntero.usuario1.puntuacion;
         System.out.println(puntero.usuario1.nombre);
        puntero = puntero.siguiente;

        return retorno;
    }

    public void inicicializarAux() {
        puntero = inicio;
    }

    public void insertarUsuario(usuario user) {

        Nodo nuevo = new Nodo(user);

        if (esVacia()) {

            inicio = nuevo;
            ultimo = nuevo;
            ultimo.siguiente = inicio;
            tamanio++;
        } else if (yaEstabaElUsuario(user.nombre)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("...");
            alert.setHeaderText("Usuario repetido");
            String s = "El usuario ya estaba registrado, elija otro nombre. ";
            alert.setContentText(s);
            alert.show();
        } else {
            nuevo.siguiente = inicio;
            inicio = nuevo;
            ultimo.siguiente = inicio;
            tamanio++;
        }

    }

    public boolean yaEstabaElUsuario(String nombre) {
        // Verifica si la lista contiene elementoa.
        boolean retorno = false;
        if (!esVacia()) {
            // Crea una copia de la lista.
            Nodo aux = inicio;
            // Posicion de los elementos de la lista.
            int i = 0;
            System.out.println("----------------------------------------------------- ");
            // Recorre la lista hasta llegar nuevamente al incio de la lista.
            do {
                if (nombre.equals(aux.usuario1.nombre)) {
                    System.out.println("el usuario ya estaba");
                    retorno = true;
                    break;
                }
                // Avanza al siguiente nodo.
                aux = aux.siguiente;
                // Incrementa el contador de la posión.
                i++;
            } while (aux != inicio);
        }
        return retorno;
    }

    public void listar() {
        // Verifica si la lista contiene elementoa.
        if (!esVacia()) {
            // Crea una copia de la lista.
            Nodo aux = inicio;
            // Posicion de los elementos de la lista.
            int i = 0;
            System.out.println("----------------------------------------------------- ");
            // Recorre la lista hasta llegar nuevamente al incio de la lista.
            do {
                // Imprime en pantalla el valor del nodo.
                System.out.println(i + ".[ " + aux.usuario1.nombre + " ]" + " ->  ");
                // Avanza al siguiente nodo.
                aux = aux.siguiente;
                // Incrementa el contador de la posión.
                i++;
            } while (aux != inicio);
        }
    }
    public String cadenaUsuario(){
        String retorno="";
         if (!esVacia()) {
            Nodo aux = inicio;
            int i = 0;
            do {
                retorno=retorno+"----------------\n";
                retorno=retorno+aux.usuario1.nombre+"\n";
                retorno=retorno+"Puntos: "+String.valueOf(aux.usuario1.puntuacion)+"\n";
                aux = aux.siguiente;
                i++;
            } while (aux != inicio);
        }
        return retorno;
    }

    public String cadenaDot() {

        String cadena = "";
        if (!esVacia()) {
            // Crea una copia de la lista.
            Nodo aux = inicio;
            // Posicion de los elementos de la lista.
            // Recorre la lista hasta llegar nuevamente al incio de la lista.
            do {
                // Imprime en pantalla el valor del nodo.
                cadena = cadena + aux.usuario1.nombre + "->" + aux.siguiente.usuario1.nombre + ";\n";
                // Avanza al siguiente nodo.
                aux = aux.siguiente;
                // Incrementa el contador de la posión.

            } while (aux != inicio);
        }

        return cadena;
    }

    public void actualizarWeb() {
        archiDot.escribirDiccionario(cadenaDot(), "jugadores.dot");
        archiDot.dibujarDot("jugadores.png", "jugadores.dot");
        //archiDot.escribirHTMLDiccionario();

        Thread hilo = new Thread() {
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println("Error al correr hilo jugadores>>>>" + ex);
                }
                Platform.runLater(() -> Tablero.webJugadoresS.getEngine().reload());
            }
        };
        hilo.start();

//        System.out.println(contadorDot);
    }

    public class Nodo {

        public usuario usuario1;
        public Nodo siguiente;

        public Nodo(usuario usuario1) {
            this.usuario1 = usuario1;
        }

        public Nodo() {

        }

        public usuario getUsuario1() {
            return usuario1;
        }

        public void setUsuario1(usuario usuario1) {
            this.usuario1 = usuario1;
        }

    }
}
