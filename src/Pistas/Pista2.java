/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pistas;
/**
 *
 * @author psant
 */
import Obstaculos.*;
import java.util.Random;

public class Pista2 extends Pista {
    private int[] posicionesX = {75, 140, 200, 263};
    private Random random = new Random();

    public Pista2(int ancho, int alto){
        super("/Pistas/Imagenes/pista.png", ancho, alto);
    }

    @Override
    public void ActualizarPista(int velocidad){
        // Primero mover fondo y obstáculos existentes
        super.ActualizarPista(velocidad);

        // Generar obstáculos aleatorios en posiciones específicas de Pista1
        if (random.nextInt(100) < 3) {  // 3% de probabilidad por frame
            int x = posicionesX[random.nextInt(posicionesX.length)];
            int tipo = random.nextInt(3); // 0 = Cono, 1 = Bache, 2 = Tierra

            switch(tipo) {
                case 0 -> getObstaculos().add(new Cono(x, -20));
                case 1 -> getObstaculos().add(new Bache(x, -20));
                case 2 -> getObstaculos().add(new Tierra(x, -20));
            }
        }
    }
}
