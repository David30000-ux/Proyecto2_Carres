/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pistas;

import Obstaculos.*;
import java.util.Random;

public class Pista1 extends Pista {
    private int[] posicionesX = {75, 140, 200, 263};
    private Random random = new Random();

    public Pista1(int ancho, int alto){
        super("/Pistas/Imagenes/pista.png", ancho, alto);
    }

    @Override
    public void ActualizarPista(int velocidad){
        // Primero mover fondo y obstáculos existentes
        super.ActualizarPista(velocidad);

        if (random.nextInt(100) < 3) { 
            int x = posicionesX[random.nextInt(posicionesX.length)];
            int tipo = random.nextInt(3);

            switch(tipo) {
                case 0 -> getObstaculos().add(new Cono(x, -20));
                case 1 -> getObstaculos().add(new Bache(x, -20));
                case 2 -> getObstaculos().add(new Tierra(x, -20));
            }
        }
    }
}

