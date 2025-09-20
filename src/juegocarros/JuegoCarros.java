/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package juegocarros;
import javax.swing.*;
import java.awt.*;
import Carros.*;
import Pistas.*;
import javax.sound.sampled.*; 

/**
 *
 * @author psant
 */
public class JuegoCarros extends JFrame {
    private CardLayout cardlayout;
    private JPanel PanelPrincipal;
    private JPanel Menu, Seleccion, Juego, Fin;
    private Clip Musiquita;
    
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
    // Pistas
    private Pista Pista1, Pista2;
    
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
    
    //Directorio de la musica :)
    reproducirMusica("/Sonidos/musica.wav");
    
    // Parametros del Programa
    setTitle("Need for Gasolina");
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
}

public void CrearMenu(){
    Menu = new PanelConFondo("/Imagenes/fondoMenu.png");
    Menu.setLayout(null);
    
    ImageIcon Logo = new ImageIcon(getClass().getResource("/Imagenes/logo.png"));
    
    // Labels
    lblTituloMenu = new JLabel(Logo);
    lblTituloMenu.setFont(new Font("Arial", Font.BOLD, 30));
    lblTituloMenu.setBounds(175, 65, 450, 200);
    
    lblManual = new JLabel("Manual del Juego");
    lblManual.setFont(new Font("Arial", Font.BOLD, 16));
    lblManual.setBounds(550, 700, 150, 30);
    
    // Botones
    btnInicio = new JButton("Selecciona tu Carro");
    btnInicio.setFont(new Font("Arial", Font.BOLD, 20));
    btnInicio.setBounds(260, 350, 250, 40);
    
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
    Seleccion = new PanelConFondo("/Imagenes/fondoSeleccion.png");
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
    cmbJugador1 = new JComboBox<>(new String[]{ "Carro 1", "Carro 2", "Carro 3", "Carro 4", "Vaca" });
    cmbJugador1.setFont(new Font("Arial", Font.BOLD, 18));
    cmbJugador1.setSelectedIndex(-1);
    cmbJugador1.setBounds(100, 120, 200, 40);
    
    cmbJugador2 = new JComboBox<>(new String [] { "Carro 1", "Carro 2", "Carro 3", "Carro 4", "Vaca" });
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
    cmbJugador1.addActionListener(e -> {
    if (cmbJugador1.getSelectedIndex() != -1) {
        mostrarImagen(cmbJugador1.getSelectedItem().toString(), 1);
    }
    });
    
    cmbJugador2.addActionListener(e -> {
    if (cmbJugador2.getSelectedIndex() != -1) {
        mostrarImagen(cmbJugador2.getSelectedItem().toString(), 2);
    }
    });
        
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
        case "Carro 1" -> Jugador1 = new Carro1(75, 600);
        case "Carro 2" -> Jugador1 = new Carro2(75, 600);
        case "Carro 3" -> Jugador1 = new Carro3(75, 600);
        case "Carro 4" -> Jugador1 = new Carro4(75, 600);
        case "Vaca" -> Jugador1 = new Vaca(75, 600);
        default -> Jugador1 = null;
    }
    
    switch (cmbJugador2.getSelectedItem().toString()){
        case "Carro 1" -> Jugador2 = new Carro1(75, 600);
        case "Carro 2" -> Jugador2 = new Carro2(75, 600);
        case "Carro 3" -> Jugador2 = new Carro3(75, 600);
        case "Carro 4" -> Jugador2 = new Carro4(75, 600);
        case "Vaca" -> Jugador2 = new Vaca(75, 600);
        default -> Jugador2 = null;
    }
    }catch(NullPointerException e){
        JOptionPane.showMessageDialog(this, "Selecciona una opcion para los 2 jugadores", "Error de Seleccion", JOptionPane.ERROR_MESSAGE, null);
        return;
    }
    
    Pista1 = new Pista1(400, 800);
    Pista2 = new Pista2(400, 800);
    
    Carrera carrera = new Carrera(Jugador1, Jugador2, Pista1, Pista2);
    Juego.removeAll();
    carrera.setBounds(0, 0, Juego.getWidth(), Juego.getHeight());
    Juego.add(carrera);
    carrera.setFocusable(true);
    carrera.requestFocusInWindow();
    
    Juego.revalidate(); 
    Juego.repaint();
    
    SwingUtilities.invokeLater(() -> {
    carrera.requestFocusInWindow();
});
    
    cardlayout.show(PanelPrincipal, "juego");

}

private void mostrarImagen(String tipo, int jugador) {
    String archivo = "/Imagenes/" + tipo.toLowerCase() + ".png";
    java.net.URL url = getClass().getResource(archivo);

    if (url != null) {
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        if (jugador == 1) {
            imgCarroJdr1.setIcon(new ImageIcon(img));
        } else {
            imgCarroJdr2.setIcon(new ImageIcon(img));
        }
    } else {
        if (jugador == 1) imgCarroJdr1.setIcon(null);
        else imgCarroJdr2.setIcon(null);
    }
}

private void reproducirMusica(String archivo){
    try {
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(getClass().getResource(archivo));
        Musiquita = AudioSystem.getClip();
        Musiquita.open(audioInput);
        Musiquita.loop(Clip.LOOP_CONTINUOUSLY); // inicia un bucle infinito para que no se detenga la musica
    } catch (Exception e){
        e.printStackTrace();
    }
}

public void mostrarMenu(){
    cardlayout.show(PanelPrincipal, "menu");
}
    
    public static void main(String[] args) {
        new JuegoCarros();
    }
    
}
