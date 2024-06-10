package ec.edu.uce.ProyectoJuego.view;

import javax.swing.*;

public class FrameGame extends JFrame {

    public FrameGame() {
        setTitle("Game Galaxy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(270, 100);
        setSize(800, 600);
        setResizable(false); // Desactivar redimensionamiento manual

        GamePanel game = new GamePanel();
        setContentPane(game);

    }

}
