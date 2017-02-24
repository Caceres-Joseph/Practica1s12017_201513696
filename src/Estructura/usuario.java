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
public class usuario {
    public listaDiccionario listaDeLetras;
            
    public String nombre;
    public int puntuacion;

    public usuario() {
        puntuacion = 0;
        listaDeLetras=new listaDiccionario();
    }
}
