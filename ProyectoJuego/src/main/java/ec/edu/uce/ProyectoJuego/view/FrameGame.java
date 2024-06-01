package ec.edu.uce.ProyectoJuego.view;

import javax.swing.*;

public class FrameGame extends JFrame{

	public FrameGame() {
		setTitle("Game Galaxy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(270, 100);
		setSize(800, 600);

		GamePanel game = new GamePanel();
		add(game);
	}
}
