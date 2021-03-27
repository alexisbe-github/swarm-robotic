package simulateur.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import simulateur.modeles.Robot;
import simulateur.modeles.Serveur;

public class Vue extends JPanel implements Observer {

	private BufferedImage image;
	private ArrayList<Robot> listeRobot = new ArrayList<Robot>();

	public Vue() {
		super();
		this.setBackground(Color.WHITE);
		try {
			this.image = ImageIO.read(new File("image/fleche.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		Serveur s=(Serveur)o;
		listeRobot=s.getListeRobot();
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// On parcourt la liste des robots
		for (Robot r : listeRobot) {

			// On prend la rotation du robot pour l'appliquer sur l'image et on pose l'image
			AffineTransform at = AffineTransform.getTranslateInstance(r.getposX() - 25, r.getposY() - 25);
			at.rotate(Math.toRadians(-r.getAngle()), 25, 25);
			g2d.drawImage(this.image, at, null);

			// On ecrit le nom du robot
			g.drawString(r.getNom(), r.getposX() - 20, r.getposY() + 55);
		}
	}

}
