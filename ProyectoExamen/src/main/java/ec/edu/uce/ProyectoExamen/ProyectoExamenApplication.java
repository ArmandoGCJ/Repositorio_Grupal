package ec.edu.uce.ProyectoExamen;

import ec.edu.uce.ProyectoExamen.view.FrameGame;

public class ProyectoExamenApplication {

	public static void main(String[] args) {
		//Inversion de control Bean
		FrameGame frameGame = new FrameGame();
		frameGame.setVisible(true);

	}

}
