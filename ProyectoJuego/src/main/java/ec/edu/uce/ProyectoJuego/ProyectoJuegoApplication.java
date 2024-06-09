package ec.edu.uce.ProyectoJuego;

import ec.edu.uce.ProyectoJuego.view.FrameGame;

import java.awt.*;


public class ProyectoJuegoApplication {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameGame().setVisible(true);
            }
        });

    }
}
