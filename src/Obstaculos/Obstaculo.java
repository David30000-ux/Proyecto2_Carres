/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Obstaculos;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author psant
 */
public abstract class Obstaculo {
    // Posicion
    protected int x, y;
    // Tamanio
    protected int Ancho, Alto;
    // Imagen de Obstaculo
    protected Image Imagen;
    
public Obstaculo(int x, int y, String RutaImagen){
    this.x = x;
    this.y = y;
    this.Ancho = 40;
    this.Alto = 60;
    this.Imagen = new ImageIcon(getClass().getResource(RutaImagen)).getImage();
}

public void MoverObstaculo(int Velocidad){
    y += Velocidad;
}

public int getX() { return x; }
public int getY() { return y; }
public int getAncho() { return Ancho; }
public int getAlto() { return Alto; }

public void Dibujar(Graphics g){
    g.drawImage(Imagen, x, y, Ancho, Alto, null);
}
}
