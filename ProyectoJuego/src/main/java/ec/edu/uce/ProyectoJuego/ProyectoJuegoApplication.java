package ec.edu.uce.ProyectoJuego;

import ec.edu.uce.ProyectoJuego.view.FrameGame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoJuegoApplication {

	public static void main(String[] args) {
		FrameGame frameGame = new FrameGame();
		frameGame.setVisible(true);

	}

}
