/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegocarros;

import Carros.*;
import Pistas.*;
import Obstaculos.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Iterator;

public class Carrera extends JPanel {
    private Carro Jugador1, Jugador2;
    private Pista pista1, pista2;
    private JLabel lblPuntosJ1, lblPuntosJ2;
    private Timer timer, timerPuntos;

    private int velocidad = 5;
    private int puntosJ1 = 0, puntosJ2 = 0;
    private final int PUNTOS_MAX = 100; 

    public Carrera(Carro J1, Carro J2, Pista P1, Pista P2) {
        this.Jugador1 = J1;
        this.Jugador2 = J2;
        this.pista1 = P1;
        this.pista2 = P2;

        setLayout(null);
        // Labels para visualizar puntos
        lblPuntosJ1 = new JLabel("Jugador 1: 0");
        lblPuntosJ1.setFont(new Font("Arial", Font.BOLD, 20));
        lblPuntosJ1.setForeground(Color.BLACK);
        lblPuntosJ1.setBounds(145, 20, 200, 30);
        
        lblPuntosJ2 = new JLabel("Jugador 2: 0");
        lblPuntosJ2.setFont(new Font("Arial", Font.BOLD, 20));
        lblPuntosJ2.setForeground(Color.BLACK);
        lblPuntosJ2.setBounds(545, 20, 200, 30);
        
        add(lblPuntosJ1);
        add(lblPuntosJ2);
        
        setBackground(Color.DARK_GRAY);
        setFocusable(true);
        requestFocusInWindow();

        // Manejo de teclas
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    // Jugador 1
                    case KeyEvent.VK_A -> Jugador1.MoverIzquierdaJ1();
                    case KeyEvent.VK_D -> Jugador1.MoverDerechaJ1();
                    // Jugador 2
                    case KeyEvent.VK_LEFT -> Jugador2.MoverIzquierdaJ2();
                    case KeyEvent.VK_RIGHT -> Jugador2.MoverDerechaJ2();
                }
            }
        });

        // Timer del juego (FPS 30)
        timer = new Timer(30, e -> {
            Actualizar();
            repaint();
        });
        timer.start();
        
        timerPuntos = new Timer(500, e -> {
        // Sumar puntos por avanzar
        puntosJ1 += 1;
        puntosJ2 += 1;
        
        lblPuntosJ1.setText("Jugador 1: " + puntosJ1);
        lblPuntosJ2.setText("Jugador 2: " + puntosJ2);

        // Terminar carrera si alguien llega a PUNTOS_MAX
        if (puntosJ1 >= PUNTOS_MAX || puntosJ2 >= PUNTOS_MAX) {
            timer.stop();
            timerPuntos.stop();
            String ganador;
            if (puntosJ1 > puntosJ2) ganador = "Jugador 1";
            else if (puntosJ2 > puntosJ1) ganador = "Jugador 2";
            else ganador = "Un Empate";
            mostrarGanador(ganador);
        }
        });
        timerPuntos.start();
    }

    private void Actualizar() {
        // Actualizar pistas y obstáculos
        pista1.ActualizarPista(velocidad);
        pista2.ActualizarPista(velocidad);

        // Colisiones Jugador1
        Iterator<Obstaculo> it1 = pista1.getObstaculos().iterator();
        while (it1.hasNext()) {
            Obstaculo o = it1.next();
            Rectangle rCarro = new Rectangle(Jugador1.getX(), Jugador1.getY(), Jugador1.getAncho(), Jugador1.getAlto());
            Rectangle rObs = new Rectangle(o.getX(), o.getY(), o.getAncho(), o.getAlto());

            if (rCarro.intersects(rObs)) {
                // Le resta puntos por chocar con obstaculos
                puntosJ1 -= 5;
                // Remueve el obstaculo cuando choca 
                it1.remove(); 
            } else if (o.getY() > getHeight()) {
                // Remueve el obstaculo cuando sale de la pantalla
                it1.remove();
            }
        }

        // Colisiones Jugador2
        Iterator<Obstaculo> it2 = pista2.getObstaculos().iterator();
        while (it2.hasNext()) {
            Obstaculo o = it2.next();
            Rectangle rCarro = new Rectangle(Jugador2.getX(), Jugador2.getY(), Jugador2.getAncho(), Jugador2.getAlto());
            Rectangle rObs = new Rectangle(o.getX(), o.getY(), o.getAncho(), o.getAlto());

            if (rCarro.intersects(rObs)) {
                // Le resta puntos por chocar con obstaculos
                puntosJ2 -= 5;
                // Remueve el obstaculo cuando choca
                it2.remove();
            } else if (o.getY() > getHeight()) {
                // Remueve el obstaculo cuando sale de la pantalla
                it2.remove();
            }
        }
}
    private void mostrarGanador(String ganador) {
    String mensaje = "¡El ganador es: " + ganador + "!";
    Object[] opciones = { "Reiniciar", "Volver al menu" };
    
    int opcion = JOptionPane.showOptionDialog(
            SwingUtilities.getWindowAncestor(this),                       
            mensaje,
            "Resultado Final",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            opciones,
            opciones[0]
    );

    if (opcion == 0) { 
        reiniciarCarrera();
    } else if (opcion == 1) {
        detenerTimers();
        volverAlMenu();
    }
}

private void volverAlMenu() {
    // Obtenemos el JFrame principal (JuegoCarros)
    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

    if (topFrame instanceof JuegoCarros) {
        JuegoCarros main = (JuegoCarros) topFrame;
        main.mostrarMenu(); // llamamos a un método en JuegoCarros que cambie al panel "Menu"
    }
}

private void detenerTimers() {
    timer.stop();
    timerPuntos.stop();
}

private void reiniciarCarrera() {
    // Parar timers
    detenerTimers();

    // Reiniciar puntos y labels
    puntosJ1 = 0;
    puntosJ2 = 0;
    lblPuntosJ1.setText("Jugador 1: 0");
    lblPuntosJ2.setText("Jugador 2: 0");

    // Limpiar obstáculos y reiniciar scroll de las pistas
    pista1.getObstaculos().clear();
    pista2.getObstaculos().clear();

    // Volver pistas a coordenadas inciales
    pista1.reiniciar();
    pista2.reiniciar();

    // Volver a posiciones iniciales de los carros (si tienes setters)   
    Jugador1.setX(75); Jugador1.setY(600);
    Jugador2.setX(75); Jugador2.setY(600);

    // Reiniciar timers
    timer.start();
    timerPuntos.start();

    // Asegurar foco para recibir teclas
    SwingUtilities.invokeLater(() -> requestFocusInWindow());
}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Genera la pista 1
        Graphics2D g2d = (Graphics2D) g.create();
        pista1.DibujarPista(g2d);
        if (Jugador1 != null) Jugador1.Dibujar(g2d);
        g2d.dispose();

        // Genera la pista 2
        g2d = (Graphics2D) g.create();
        // se desplaza la segunda pista a la derecha
        g2d.translate(400, 0);
        pista2.DibujarPista(g2d);
        if (Jugador2 != null) Jugador2.Dibujar(g2d);
        g2d.dispose();

    }
}