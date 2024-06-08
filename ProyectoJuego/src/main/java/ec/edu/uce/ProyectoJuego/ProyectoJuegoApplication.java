package ec.edu.uce.ProyectoJuego;

import ec.edu.uce.ProyectoJuego.model.User;
import ec.edu.uce.ProyectoJuego.view.FrameGame;


public class ProyectoJuegoApplication {

	public static void main(String[] args) {
		User u = new User();
		FrameGame frameGame = new FrameGame(u);
		frameGame.setVisible(true);

	}
}
