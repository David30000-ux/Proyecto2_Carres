/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegocarros; // Asegúrate que coincida con tu paquete

import javax.swing.*;
import java.awt.*;

public class PanelConFondo extends JPanel {
    private Image imagenFondo;

    public PanelConFondo(String rutaImagen) {
        setImagenFondo(rutaImagen);
    }

    public void setImagenFondo(String rutaImagen) {
        java.net.URL url = getClass().getResource(rutaImagen);
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            imagenFondo = icon.getImage();
            repaint(); // Redibuja el panel
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}



