/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pistas;
import Carros.*;
import Obstaculos.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author psant
 */
public abstract class Pista {
    private Image Fondo;
    private int yFondo;
    private int ancho, alto;
    
    private ArrayList<Obstaculo> obstaculos;
    
    
public Pista(String RutaFondo, int ancho, int alto) {
    this.Fondo = new ImageIcon(getClass().getResource(RutaFondo)).getImage();
    this.yFondo = 0;
    this.ancho = ancho;
    this.alto = alto;
    this.obstaculos = new ArrayList<>();
}

public void DibujarPista(Graphics g){
    g.drawImage(Fondo, 0 ,yFondo, ancho, alto, null);
    g.drawImage(Fondo, 0, yFondo - alto, ancho, alto, null);
    
    // obstaculos
    for (Obstaculo o: obstaculos){
        o.Dibujar(g);
    }
}

public void ActualizarPista(int velocidad){
    yFondo += velocidad;
    if (yFondo >= alto){
        yFondo = 0;
    }
    
    // Mover Obstaculos
    for (Obstaculo o : obstaculos){
        o.MoverObstaculo(velocidad);
    }
}

public void reiniciar() {
    this.yFondo = 0;
    this.obstaculos.clear();
}

public Rectangle getLimiteIzquierdo() {
    return new Rectangle(0, 0, 50, alto);
}

public Rectangle getLimiteDerecho(){
    return new Rectangle(ancho -50, 0, 50, alto);
}

public ArrayList<Obstaculo> getObstaculos() {
    return obstaculos;
}

}
