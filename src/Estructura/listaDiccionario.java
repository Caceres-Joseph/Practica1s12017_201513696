package Estructura;

/**
 *
 * @author joseph
 */
public class listaDiccionario {

    public static Nodo cabeza = null;

    public void saludar() {
        System.out.println("hola");
    }

    public void insertarAlPrincipio(String palabra) {

        Nodo nodo = new Nodo(palabra);
        if (cabeza != null) {
            nodo.siguiente = cabeza;
        }
        
        cabeza = nodo;
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

    public class Nodo {

        public String palabra;
        public Nodo siguiente;

        public Nodo(String palabra) {
            this.palabra = palabra;
        }

    }
}
