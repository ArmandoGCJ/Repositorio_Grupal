package ec.edu.uce.ProyectoJuego.view;

import ec.edu.uce.ProyectoJuego.model.User;

import javax.swing.*;

public class FrameGame extends JFrame{

	User u;
	public FrameGame(User u) {
		this.u = u;
		setTitle("Game Galaxy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(270, 100);
		setSize(800, 600);

		GamePanel game = new GamePanel();
		add(game);
	}
}
