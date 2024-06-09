package ec.edu.uce.ProyectoJuego.view;

import ec.edu.uce.ProyectoJuego.controller.ServerConnection;
import ec.edu.uce.ProyectoJuego.model.Hero;
import ec.edu.uce.ProyectoJuego.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameGame extends JFrame {

    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JButton jButtonReg;
    private JButton jButtonLin;
    private JTextField jTextFieldName;
    private JTextField jTextFieldP;

    public FrameGame() {
        setTitle("Game Galaxy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(270, 100);
        setSize(800, 600);
        setResizable(false); // Desactivar redimensionamiento manual

        GamePanel game = new GamePanel();
        setContentPane(game);

        //initComponents();
    }

    private void initComponents() {
        jLabel1 = new JLabel("            GALAXY GAME");
        jLabel2 = new JLabel("   REGISTER YOUR NAME");
        jLabel3 = new JLabel("REGISTER YOUR PASSWORD");
        jButtonReg = new JButton("REGISTER");
        jButtonLin = new JButton("LOG IN");
        jTextFieldName = new JTextField();
        jTextFieldP = new JTextField();

        jButtonReg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonLinActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jButtonReg)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonLin)
                                .addGap(36, 36, 36))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jTextFieldP, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(112, 112, 112)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jTextFieldName, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(8, 8, 8)))))
                                .addContainerGap(130, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldName, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldP, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonReg)
                                        .addComponent(jButtonLin))
                                .addGap(21, 21, 21))
        );

        pack();
    }

    private void jButtonLinActionPerformed(ActionEvent evt) {
        GamePanel game = new GamePanel();
        setContentPane(game);
        revalidate();
        repaint();

        // Ajustar el tamaño del contenido y desactivar el ajuste automático
        setSize(new Dimension(800, 600));
        setResizable(false);

        game.requestFocusInWindow();

    }

	/*public void serverConnection(){
		// Crear una instancia de ServerConnection
		ServerConnection serverConnection = new ServerConnection();

		hero.setName(jTextFieldName.getText());
		hero.setPassword(jTextFieldP.getText());

		// Enviar los datos del héroe al servidor
		serverConnection.sendHeroData(hero);
		// Consumir una URL del servidor
		// serverConnection.consumeUrl();
	}*/

}
