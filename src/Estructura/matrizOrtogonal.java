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
public class matrizOrtogonal {

    public NodoMatriz cabeza = new NodoMatriz(0, 0);//Se crea el nodo cabeza
    public NodoMatriz nuevo;
    public Integer dimension;
    archivoDot archiDot = new archivoDot();

    public matrizOrtogonal(Integer dimension) {
        this.dimension = dimension - 1;
        crearCabezeras();
        crearElementos();
    }

    public void imprimir() {

        NodoMatriz recorrer = cabeza;
        NodoMatriz recorrido2 = cabeza;
        for (int i = 0; i <= dimension; i++) {
            recorrido2 = recorrer;
            System.out.println("");
            for (int j = 0; j <= dimension; j++) {
                System.out.print("[" + recorrido2.ficha.letra.getText() + "]");
                recorrido2 = recorrido2.siguiente;
            }
            recorrer = recorrer.abajo;
        }
        System.out.println("");
    }

    public Integer buscarPalabraHorizontal() {
        Integer retorno = 0;
        listaDiccionario listaLetras = new listaDiccionario();

        NodoMatriz recorrer = cabeza;
        NodoMatriz recorrido2 = cabeza;
        String palabraTemporal = "";
        String letra;
        //System.out.println("------------PalabraHorizontal---------");
        for (int i = 0; i <= dimension; i++) {
            recorrido2 = recorrer;
            for (int j = 0; j <= dimension; j++) {
                if (recorrido2.ficha.hayLetraTemporal) {
                    //System.out.println(recorrido2.ficha.letra.getText());
                    letra = recorrido2.ficha.letra.getText();
                    palabraTemporal = palabraTemporal + letra;
                    listaLetras.insertarDeUltimo(letra);
                    recorrido2.ficha.hayLetraTemporal = false;
                }
                recorrido2 = recorrido2.siguiente;
            }
            recorrer = recorrer.abajo;
        }
        if (Lectura.LeerXML.ListaDiccionario.buscar(palabraTemporal)) {
            retorno = listaLetras.ValorPalabra();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("...");
            alert.setHeaderText("Palabra no esta en diccionario.");
            String s = "Palabra no encontrada en el diccioario";
            alert.setContentText(s);
            alert.show();
        }
        return retorno;
    }

    public void cancelarTiro() {

        NodoMatriz recorrer = cabeza;
        NodoMatriz recorrido2 = cabeza;
        String letra;
        //System.out.println("------------PalabraHorizontal---------");
        for (int i = 0; i <= dimension; i++) {
            recorrido2 = recorrer;
            for (int j = 0; j <= dimension; j++) {
                if (recorrido2.ficha.hayLetraTemporal) {
                    letra = recorrido2.ficha.letra.getText();
                    colocarLetraEnBoton(letra);
                    recorrido2.ficha.letra.setText("");
                    recorrido2.ficha.hayLetraTemporal = false;
                }
                recorrido2 = recorrido2.siguiente;
            }
            recorrer = recorrer.abajo;
        }
    }

    public void colocarLetraEnBoton(String letra) {
        if (Tablero.PrimerFichaS.getText().equals("")) {
            Tablero.PrimerFichaS.setText(letra);
        } else if (Tablero.PrimerFicha1S.getText().equals("")) {
            Tablero.PrimerFicha1S.setText(letra);
        } else if (Tablero.PrimerFicha2S.getText().equals("")) {
            Tablero.PrimerFicha2S.setText(letra);
        } else if (Tablero.PrimerFicha3s.getText().equals("")) {
            Tablero.PrimerFicha3s.setText(letra);
        } else if (Tablero.PrimerFicha4s.getText().equals("")) {
            Tablero.PrimerFicha4s.setText(letra);
        } else if (Tablero.PrimerFicha5s.getText().equals("")) {
            Tablero.PrimerFicha5s.setText(letra);
        } else if (Tablero.PrimerFicha6s.getText().equals("")) {
            Tablero.PrimerFicha6s.setText(letra);
        }
    }

    public String textoParaDot() {
        String cadena = "";
        NodoMatriz aux1 = this.cabeza;
        NodoMatriz aux2 = this.cabeza;
        Integer fil=0;
        for (int i = 0; i <= dimension; i++) {
            aux2 = aux1;
            Integer col=0;
            for (int j = 0; j <= dimension; j++) {
                if (aux2.abajo != null) {
                    
                    
                    cadena = cadena + "node" + String.valueOf(fil) + "_" + String.valueOf(col) + " [label= \"" + aux2.ficha.letra.getText() + "\"];\n";
                    cadena = cadena + "node" + String.valueOf(fil) + "_" + String.valueOf(col) + " -> node" + String.valueOf(fil+1) + "_" + String.valueOf(col) + ";\n";

                   // cadena = cadena + "node" + String.valueOf(fil) + "_" + String.valueOf(col + 1) + " -> node" + String.valueOf(fil) + "_" + String.valueOf(col) + ";\n";

                }
                if (aux2.arriba != null) {
                    cadena = cadena + "node" + String.valueOf(fil) + "_" + String.valueOf(col) + " [label= \"" + aux2.ficha.letra.getText() + "\"];\n";
                    cadena = cadena + "node" + String.valueOf(fil) + "_" + String.valueOf(col) + " -> node" + String.valueOf(fil-1) + "_" + String.valueOf(col) + ";\n";

//                    dot = dot + aux2.posi + "->" + aux2.getArriba().posi + ";\n";
                }
                if (aux2.siguiente != null) {
                    cadena = cadena + "node" + String.valueOf(fil) + "_" + String.valueOf(col) + " [label= \"" + aux2.ficha.letra.getText() + "\"];\n";
                    cadena = cadena + "node" + String.valueOf(fil) + "_" + String.valueOf(col) + " -> node" + String.valueOf(fil) + "_" + String.valueOf(col+1) + ";\n";

//                    dot = dot + aux2.posi + "->" + aux2.getSiguiente().posi + ";\n";
                }
                if (aux2.anterior != null) {
                    cadena = cadena + "node" + String.valueOf(fil) + "_" + String.valueOf(col) + " [label= \"" + aux2.ficha.letra.getText() + "\"];\n";
                    cadena = cadena + "node" + String.valueOf(fil) + "_" + String.valueOf(col) + " -> node" + String.valueOf(fil) + "_" + String.valueOf(col-1) + ";\n";

//                    dot = dot + aux2.posi + "->" + aux2.getAnterior().posi + ";\n";
                }
                col++;
                aux2 = aux2.siguiente;
            }
            fil++;
            aux1 = aux1.abajo;
        }

        return cadena;
    }

    public String cadenaDot() {
        String cadena = "";
        NodoMatriz recorrer = cabeza;
        NodoMatriz recorrido2 = cabeza;

        NodoMatriz recorrer11 = cabeza;
        NodoMatriz recorrido22 = cabeza;

        Integer fil = 0;
        for (int i = 0; i <= dimension - 1; i++) {
            recorrido2 = recorrer;

            System.out.println("");
            Integer col = 0;
            for (int j = 0; j <= dimension - 1; j++) {
                cadena = cadena + "node" + String.valueOf(fil) + "_" + String.valueOf(col) + " [label= \"" + recorrido2.ficha.letra.getText() + "\"];\n";
                cadena = cadena + "node" + String.valueOf(fil) + "_" + String.valueOf(col) + " -> node" + String.valueOf(fil) + "_" + String.valueOf(col + 1) + ";\n";

                cadena = cadena + "node" + String.valueOf(fil) + "_" + String.valueOf(col + 1) + " -> node" + String.valueOf(fil) + "_" + String.valueOf(col) + ";\n";

                recorrido2 = recorrido2.siguiente;
                col++;
            }
            cadena = cadena + "node" + String.valueOf(fil) + "_" + String.valueOf(col) + " [label=\"" + recorrido2.ficha.letra.getText() + "\"];\n";
            recorrer = recorrer.abajo;
            fil++;
        }

        /*
        Integer col2 = 0;
        for (int i = 0; i < dimension - 1; i++) {
            recorrido22 = recorrer11;
            Integer fil2 = 0;
            for (int j = 0; j < dimension - 1; j++) {
                cadena = cadena + "node" + String.valueOf(fil2) + "_" + String.valueOf(col2) + " [label= \"" + recorrido22.ficha.letra.getText() + "\"];\n";
                cadena = cadena + "node" + String.valueOf(fil2) + "_" + String.valueOf(col2) + " -> node" + String.valueOf(fil2 + 1) + "_" + String.valueOf(col2) + ";\n";

                cadena = cadena + "node" + String.valueOf(fil2 + 1) + "_" + String.valueOf(col2) + " -> node" + String.valueOf(fil2) + "_" + String.valueOf(col2) + ";\n";

                recorrido22 = recorrido22.abajo;
                fil2++;
            }
//            cadena = cadena + "node" + String.valueOf(fil2) + "_" + String.valueOf(col2) + " [label=\"" + recorrido22.ficha.letra.getText() + "\"];\n";
            recorrer11 = recorrer11.siguiente;
            col2++;
        }*/
        return cadena;
    }

    public void actualizarWeb() {
        try {
//            archiDot.escribirDiccionario(cadenaDot(), "tablero.dot");
                archiDot.escribirDiccionario(textoParaDot(),"tablero.dot");
        } catch (Exception e) {
            System.out.println(e);
        }

        archiDot.dibujarDot("tablero.png", "tablero.dot");
        //archiDot.escribirHTMLDiccionario();

        Thread hilo = new Thread() {
            public void run() {
                try {
                    Thread.sleep(12000);
                } catch (InterruptedException ex) {
                    System.out.println("Error al correr hilo Tablero>>>>" + ex);
                }
                Platform.runLater(() -> Tablero.webTableroS.getEngine().reload());
                System.out.println("----->Creando la matriz ortogonal");
            }
        };
        hilo.start();

    }

    public void crearCabezeras() {
        NodoMatriz punteroCol = cabeza;//puntero para las columnas
        NodoMatriz punteroFil = cabeza;//crea el puntero para las filas
        for (int i = 1; i <= dimension; i++) {
            // nuevo = new Nodo("[0," + String.valueOf(i) + "]")
            nuevo = new NodoMatriz(0, i);//El nodo que se va a insertar con la posicion

//            ficha fichaTablero = new ficha(j, i);
//                matriz.insertarElementoEnPosicion(j, i, fichaTablero);
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
                nuevo = new NodoMatriz(i + 1, k + 1); //se crea el nodo
                auxFila1.siguiente = nuevo;
                nuevo.anterior = auxFila1;

                auxFila0.abajo = nuevo;
                nuevo.arriba = auxFila0;

                auxFila1 = nuevo;//aquí avanzo
                auxFila0 = auxFila0.siguiente;
            }

        }
    }

    ///Función que devuelve el nodo ingresando la fila y la columna
    public ficha devolverFichaMatriz(int fila, int columna) {

        if (fila > dimension) {
            fila = dimension;
        }
        if (columna > dimension) {
            columna = dimension;
        }
        NodoMatriz recorrer = cabeza;

        for (int i = 0; i < fila; i++) { ///recore hacia abajo la cantidad de filas
            recorrer = recorrer.abajo;
        }
        for (int j = 0; j < columna; j++) {
            recorrer = recorrer.siguiente;
        }
        return recorrer.ficha;

    }

    public void insertarElementoEnPosicion(int fila, int columna, ficha fich) {

        if (fila > dimension) {
            fila = dimension;
        }
        if (columna > dimension) {
            columna = dimension;
        }
        NodoMatriz recorrer = cabeza;

        for (int i = 0; i < fila; i++) { ///recore hacia abajo la cantidad de filas
            recorrer = recorrer.abajo;
        }
        for (int j = 0; j < columna; j++) {
            recorrer = recorrer.siguiente;
        }
        recorrer.setFicha(fich);

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
