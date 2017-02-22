package Lectura;

/**
 *
 * @author joseph
 */
import Estructura.colaLetra;
import Estructura.listaDiccionario;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class LeerXML {

    public static listaDiccionario ListaDiccionario = new listaDiccionario();
    public static colaLetra colaLetra1 = new colaLetra();
    public static Integer dimensionMatriz;

    public LeerXML(String ruta) {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(ruta);
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();

            List dimension = rootNode.getChildren("dimension");//Capturando las palabras

            System.out.println("\n----Tablero----");
            Element node4 = (Element) dimension.get(0);

            dimensionMatriz = Integer.valueOf(node4.getText());
            System.out.println("Dimensión: " + node4.getText());

            List list = rootNode.getChildren("dobles");//Capturando los dobles
            for (int i = 0; i < list.size(); i++) {
                Element node = (Element) list.get(i);
                List listaCasilla = node.getChildren("casilla");
                for (int j = 0; j < listaCasilla.size(); j++) {
                    Element node2 = (Element) listaCasilla.get(j);
                    System.out.println("\n-----CasillaDoble----");
                    System.out.println("PosX: " + node2.getChildText("x"));
                    System.out.println("PosY: " + node2.getChildText("y"));
                }
            }
            List triples = rootNode.getChildren("triples");//Capturando los triples
            for (int i = 0; i < triples.size(); i++) {
                Element node = (Element) triples.get(i);
                List listaCasilla = node.getChildren("casilla");
                for (int j = 0; j < listaCasilla.size(); j++) {
                    Element node2 = (Element) listaCasilla.get(j);
                    System.out.println("\n-----CasillaTriple----");
                    System.out.println("PosX: " + node2.getChildText("x"));
                    System.out.println("PosY: " + node2.getChildText("y"));
                }
            }
            List diccionario = rootNode.getChildren("diccionario");//Capturando las palabras
            System.out.println("\n----Diccionario----");
            for (int i = 0; i < diccionario.size(); i++) {
                Element node = (Element) diccionario.get(i);
                List listaPalabras = node.getChildren("palabra");
                for (int j = 0; j < listaPalabras.size(); j++) {
                    Element node2 = (Element) listaPalabras.get(j);
                    String palabraDeDiccionario=node2.getText();
                    String palDicc=palabraDeDiccionario.toUpperCase();
                            // System.out.println(miCadena.toUpperCase());
                    System.out.println("Palabra: " + palDicc);
                    ListaDiccionario.insertarAlPrincipio(palDicc);
                }
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
    }

}
