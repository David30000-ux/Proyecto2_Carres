/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package juegocarros;
import javax.swing.*;
import java.awt.*;
import Carros.*;

/**
 *
 * @author psant
 */
public class JuegoCarros extends JFrame {
    private CardLayout cardlayout;
    private JPanel PanelPrincipal;
    private JPanel Menu, Seleccion, Juego, Fin;
    
    // Labels
    private JLabel lblTituloMenu, lblManual, lblSeleccion;
    private JLabel lblJugador1, lblJugador2;
    private JLabel imgCarroJdr1, imgCarroJdr2;
    
    // Botones
    private JButton btnInicio, btnManual, btnJugar;
    
    // Combobox
    private JComboBox<String> cmbJugador1, cmbJugador2;
    
    // Jugadores
    private Carro Jugador1, Jugador2;
    
public JuegoCarros(){
    cardlayout = new CardLayout();
    PanelPrincipal = new JPanel(cardlayout);
    setContentPane(PanelPrincipal);
    
    // Seccion de los paneles
    CrearMenu();
    CrearSeleccion();
    CrearJuego();
    CrearFin();
    
    // Nombre Paneles
    PanelPrincipal.add(Menu, "menu");
    PanelPrincipal.add(Seleccion, "seleccion");
    PanelPrincipal.add(Juego, "juego");
    PanelPrincipal.add(Fin, "fin");
    
    // Iniciar Menu
    cardlayout.show(PanelPrincipal, "menu");
    
    // Parametros del Programa
    setTitle("Las flipantes carreras");
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
}

public void CrearMenu(){
    Menu = new JPanel();
    Menu.setLayout(null);
    
    // Labels
    lblTituloMenu = new JLabel("Bienvenido a las flipantes carreras de coches");
    lblTituloMenu.setFont(new Font("Arial", Font.BOLD, 30));
    lblTituloMenu.setBounds(65, 100, 650, 40);
    
    lblManual = new JLabel("Manual del Juego");
    lblManual.setFont(new Font("Arial", Font.BOLD, 16));
    lblManual.setBounds(630, 700, 150, 30);
    
    // Botones
    btnInicio = new JButton("Selecciona tu Carro");
    btnInicio.setFont(new Font("Arial", Font.BOLD, 20));
    btnInicio.setBounds(260, 400, 250, 40);
    
    btnManual = new JButton("?");
    btnManual.setFont(new Font("Arial", Font.BOLD, 20));
    btnManual.setBounds(675, 650, 50, 50);
      
    // Agregar al Panel
    Menu.add(lblTituloMenu);
    Menu.add(btnInicio);
    Menu.add(btnManual);
    Menu.add(lblManual);
    
    // Acciones
    btnInicio.addActionListener(e -> IniciarSeleccion());
}

public void CrearSeleccion(){
    Seleccion = new JPanel();
    Seleccion.setLayout(null);
    
    // Labels
    lblSeleccion = new JLabel("Selecciona tu Carro");
    lblSeleccion.setFont(new Font("Arial", Font.BOLD, 30));
    lblSeleccion.setBounds(225, 30, 400, 40);
    
    lblJugador1 = new JLabel("Jugador 1");
    lblJugador1.setFont(new Font("Arial", Font.BOLD, 18));
    lblJugador1.setBounds(150, 80, 150, 30);
    
    lblJugador2 = new JLabel("Jugador 2");
    lblJugador2.setFont(new Font("Arial", Font.BOLD, 18));
    lblJugador2.setBounds(500, 80, 150, 30);
    
    // ComboBox
    cmbJugador1 = new JComboBox<>(new String[]{ "Carro 1", "Carro 2", "Carro 3", "Carro 4" });
    cmbJugador1.setFont(new Font("Arial", Font.BOLD, 18));
    cmbJugador1.setSelectedIndex(-1);
    cmbJugador1.setBounds(100, 120, 200, 40);
    
    cmbJugador2 = new JComboBox<>(new String [] { "Carro 1", "Carro 2", "Carro 3", "Carro 4" });
    cmbJugador2.setFont(new Font("Arial", Font.BOLD, 18));
    cmbJugador2.setSelectedIndex(-1);
    cmbJugador2.setBounds(450, 120, 200, 40);
    
    // Imagenes
    imgCarroJdr1 = new JLabel();
    imgCarroJdr1.setBounds(100, 190, 200, 200);
    imgCarroJdr1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    imgCarroJdr2 = new JLabel();
    imgCarroJdr2.setBounds(450, 190, 200, 200);
    imgCarroJdr2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
    // Botones
    btnJugar = new JButton("Iniciar Juego");
    btnJugar.setFont(new Font("Arial", Font.BOLD, 20));
    btnJugar.setBounds(260, 450, 250, 40);
    
    
    // Agregar al Panel
    Seleccion.add(lblSeleccion);
    Seleccion.add(lblJugador1);
    Seleccion.add(lblJugador2);
    Seleccion.add(cmbJugador1);
    Seleccion.add(cmbJugador2);
    Seleccion.add(imgCarroJdr1);
    Seleccion.add(imgCarroJdr2);
    Seleccion.add(btnJugar);
    
    // Acciones
    btnJugar.addActionListener(e -> IniciarCarrera());
}

public void CrearJuego(){
    Juego = new JPanel();
    Juego.setLayout(null);
}

public void CrearFin(){
    Fin = new JPanel();
    Fin.setLayout(null);
}

public void IniciarSeleccion(){
    cardlayout.show(PanelPrincipal, "seleccion");
}

public void IniciarCarrera(){
    try{
    switch (cmbJugador1.getSelectedItem().toString()) {
        case "Carro 1" -> Jugador1 = new Carro1(100, 800);
        case "Carro 2" -> Jugador1 = new Carro2(100, 800);
        case "Carro 3" -> Jugador1 = new Carro3(100, 800);
        case "Carro 4" -> Jugador1 = new Carro4(100, 800);
        case "Vaca" -> Jugador1 = new Vaca(100, 800);
        default -> Jugador1 = null;
    }
    
    switch (cmbJugador2.getSelectedItem().toString()){
        case "Carro 1" -> Jugador2 = new Carro1(100, 800);
        case "Carro 2" -> Jugador2 = new Carro2(100, 800);
        case "Carro 3" -> Jugador2 = new Carro3(100, 800);
        case "Carro 4" -> Jugador2 = new Carro4(100, 800);
        case "Vaca" -> Jugador2 = new Vaca(100, 800);
        default -> Jugador2 = null;
    }
    }catch(NullPointerException e){
        JOptionPane.showMessageDialog(this, "Selecciona una opcion para los 2 jugadores", "Error de Seleccion", JOptionPane.ERROR_MESSAGE, null);
    }
}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new JuegoCarros();
    }
    
}
