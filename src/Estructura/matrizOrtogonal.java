package Estructura;

/**
 *
 * @author joseph
 */
public class matrizOrtogonal {

    public NodoMatriz cabeza =new NodoMatriz(0,0);//Se crea el nodo cabeza
    public NodoMatriz nuevo;
    public Integer dimension;

    public matrizOrtogonal(Integer dimension) {
        this.dimension = dimension - 1;
        
        crearCabezeras();
        crearElementos();
    }

    public void crearCabezeras() {
        NodoMatriz punteroCol = cabeza;//puntero para las columnas
        NodoMatriz punteroFil = cabeza;//crea el puntero para las filas
        for (int i = 1; i <= dimension; i++) {
            // nuevo = new Nodo("[0," + String.valueOf(i) + "]")
            nuevo = new NodoMatriz(0, i);//El nodo que se va a insertar con la posicion
            punteroCol.siguiente = nuevo;   //cabezera de las filas
            nuevo.anterior = punteroCol;
            punteroCol = nuevo;

            //nuevo = new Nodo("[" + String.valueOf(i) + ",0]")
            nuevo = new NodoMatriz(i, 0);//cabezera de las columnas
            punteroFil.abajo = nuevo;
            nuevo.arriba = punteroFil;
            punteroFil = nuevo;
        }
    }

    public void crearElementos() {
        NodoMatriz auxFila1 = cabeza;
        NodoMatriz auxFila0 = cabeza;

        for (int i = 0; i < dimension; i++) {
            auxFila1 = cabeza;
            auxFila0 = cabeza;
            for (int j = 0; j <= i; j++) {
                auxFila1 = auxFila1.abajo;
                if (j < i) {
                    auxFila0 = auxFila0.abajo;
                }
            }
            auxFila0 = auxFila0.siguiente;
            for (int k = 0; k < dimension; k++) {
                nuevo = new NodoMatriz(i+1,k+1); //se crea el nodo
                auxFila1.siguiente = nuevo;
                nuevo.anterior = auxFila1;

                auxFila0.abajo = nuevo;
                nuevo.arriba = auxFila0;

                auxFila1 = nuevo;//aquí avanzo
                auxFila0 = auxFila0.siguiente;
            }

        }
    }

    public class NodoMatriz {

        public ficha ficha;
        public NodoMatriz arriba = null;
        public NodoMatriz abajo = null;
        public NodoMatriz siguiente = null;
        public Integer columna;
        public Integer fila;

        public NodoMatriz(ficha ficha) {
            this.ficha = ficha;
        }

        public NodoMatriz(Integer fila, Integer columna) {
            this.columna = columna;
            this.fila = fila;
        }

        public ficha getFicha() {
            return ficha;
        }

        public void setFicha(ficha ficha) {
            this.ficha = ficha;
        }
        public NodoMatriz anterior = null;

    }

}
