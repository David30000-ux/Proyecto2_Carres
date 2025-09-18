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
    this.Alto = 20;
    this.Ancho = 15;
    this.Cambio = Cambio;
    this.Imagen = new ImageIcon(getClass().getResource(RutaImagen)).getImage();
    }

public void MoverIzquierda(){ x -= Cambio; }
public void MoverDerecha(){ x += Cambio; }

public void Dibujar(Graphics g){
    g.drawImage(Imagen, x, y, Ancho, Alto, null);
}
}
