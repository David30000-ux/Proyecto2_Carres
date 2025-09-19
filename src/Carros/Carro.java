/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Carros;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author psant
 */
public abstract class Carro {
    // Posicion
    protected int x, y;
    // Tamanio
    protected int Alto, Ancho;
    // Cambio de carril
    protected int Cambio;
    // Modelo del carro
    protected Image Imagen;

public Carro(int x, int y, int Cambio, String RutaImagen){
    this.x = x;
    this.y = y;
    this.Alto = 90;
    this.Ancho = 60;
    this.Cambio = Cambio;
    this.Imagen = new ImageIcon(getClass().getResource(RutaImagen)).getImage();
    }

// Jugador 1 - Pista izquierda
private int[] posicionesJ1 = {75, 140, 200, 263};
private int indicePosJ1 = 0;

// Jugador 2 - Pista derecha
private int[] posicionesJ2 = {75, 140, 200, 263};
private int indicePosJ2 = 0;

// Movimiento Jugador 1
public void MoverIzquierdaJ1() {
    if (indicePosJ1 > 0) indicePosJ1--;
    x = posicionesJ1[indicePosJ1];
}
public void MoverDerechaJ1() {
    if (indicePosJ1 < posicionesJ1.length - 1) indicePosJ1++;
    x = posicionesJ1[indicePosJ1];
}

// Movimiento Jugador 2
public void MoverIzquierdaJ2() {
    if (indicePosJ2 > 0) indicePosJ2--;
    x = posicionesJ2[indicePosJ2];
}
public void MoverDerechaJ2() {
    if (indicePosJ2 < posicionesJ2.length - 1) indicePosJ2++;
    x = posicionesJ2[indicePosJ2];
}


public int getX() { return x; }
public int getY() { return y; }
public int getAncho() { return Ancho; }
public int getAlto() { return Alto; }
public void setX(int x){ this.x = x; }
public void setY(int y){ this.y = y; }

public void Dibujar(Graphics g){
    g.drawImage(Imagen, x, y, Ancho, Alto, null);
}
}
