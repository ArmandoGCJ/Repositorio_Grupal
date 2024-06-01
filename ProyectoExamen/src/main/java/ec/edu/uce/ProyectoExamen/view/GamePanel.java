package ec.edu.uce.ProyectoExamen.view;

import ec.edu.uce.ProyectoExamen.controller.Container;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements KeyListener {

	Container container;

	public GamePanel() {

		setBackground(Color.BLACK);
		setFocusable(true);
		container = new Container();
		addKeyListener(this);

		Timer timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				container.moveDown(1);
				repaint();
			}
		});
		timer.start();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		container.draw(g);

		//Linea roja
		int yLinea = (int) (getHeight() * 0.76);
		g.setColor(Color.RED);
		g.drawLine(0, yLinea, getWidth(), yLinea);

		// Dibujar puntaje
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		g.drawString("Score: " + container.score(), 670, 20);
		g.drawString("Hola amigo", 0, 20);

		// Dibujar la barra de vida
		g.setColor(Color.WHITE);
		g.drawRect(10,10,100,20);
		g.setColor(Color.RED);
		g.fillRect(10, 10, 100, 20); // Dibuja la barra de vida en la esquina superior izquierda
	

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A: {
			container.moveLeft(10);
			break;
		}
		case KeyEvent.VK_D: {
			container.moveRight(10);
			break;
		}
		
		case KeyEvent.VK_SPACE: {
			container.moveUp(5);
			//container.drawShoot(getGraphics());
			break;
		}
		default:
			//throw new IllegalArgumentException("Unexpected value: " + e.getKeyCode());
		}
		repaint();


	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
