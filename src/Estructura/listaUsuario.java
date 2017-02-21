package Estructura;

/**
 *
 * @author joseph
 */
public class listaUsuario {

    public Nodo inicio;
    private Nodo ultimo;
    private int tamanio;

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

    public usuario avanzarUnEspacio() {
        inicio = inicio.siguiente;
        return inicio.usuario1;
    }


    public void insertarUsuario(usuario user) {

        Nodo nuevo = new Nodo(user);

        if (esVacia()) {

            inicio = nuevo;
            ultimo = nuevo;
            ultimo.siguiente = inicio;
        } else {
            nuevo.siguiente = inicio;
            inicio = nuevo;
            ultimo.siguiente = inicio;
        }
        tamanio++;
    }
 public void listar(){
        // Verifica si la lista contiene elementoa.
        if (!esVacia()) {
            // Crea una copia de la lista.
            Nodo aux = inicio;
            // Posicion de los elementos de la lista.
            int i = 0;
            System.out.println("----------------------------------------------------- ");
            // Recorre la lista hasta llegar nuevamente al incio de la lista.
            do{
                // Imprime en pantalla el valor del nodo.
                System.out.println(i + ".[ " + aux.usuario1.nombre + " ]" + " ->  ");
                // Avanza al siguiente nodo.
                aux = aux.siguiente;
                // Incrementa el contador de la posión.
                i++;
            }while(aux != inicio);
        }
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
