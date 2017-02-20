package Estructura;

/**
 *
 * @author joseph
 */
public class letra {

    public String letra;
    public Integer valor;

    public Integer retornarValorLetra(String letr) {
        Integer retorno = 0;
        if (letr.equals("A") || letr.equals("E") || letr.equals("O") || letr.equals("I") || letr.equals("N") || letr.equals("L") || letr.equals("R") || letr.equals("U") || letr.equals("U")) {
            retorno = 1;
        } else if (letr.equals("D") || letr.equals("G")) {
            retorno = 2;
        } else if (letr.equals("C") || letr.equals("B") || letr.equals("M") || letr.equals("P")) {
            retorno = 3;
        } else if (letr.equals("H") || letr.equals("F") || letr.equals("V") || letr.equals("Y")) {
            retorno = 4;
        } else if (letr.equals("Q")) {
            retorno = 5;
        } else if (letr.equals("J") || letr.equals("Ñ") || letr.equals("X")) {
            retorno = 8;
        } else if (letr.equals("Z")) {
            retorno = 10;
        } else {
            System.out.println("No se encontro la letra");
            retorno = 1;
        }
        return retorno;
    }

}
