package simulateur.controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;

import simulateur.modeles.Camera;
import simulateur.modeles.Robot;
import simulateur.modeles.Serveur;

public class ScenarioController implements ActionListener {

	private Serveur serveur;
	private Camera camera;

	public ScenarioController(Serveur s, Camera c) {
		this.serveur = s;
		this.camera = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			switch (b.getText()) {
			case "Echanger positions":
				this.camera.setOrdre("Echanger");
				Robot r1 = new Robot(serveur, "Robot 1", 1, 1, -150, 40);
				Robot r2 = new Robot(serveur, "Robot 2", 5, 6, 30, 40);
				Robot r3 = new Robot(serveur, "Robot 3",6,1,-150,40);
				Robot r4 = new Robot(serveur, "Robot 4",10,6,30,40);
				serveur.addRobot(r1);
				serveur.addRobot(r2);
				serveur.addRobot(r3);
				serveur.addRobot(r4);
				this.camera.resume();
				break;
			case "Robot bloqué":
				this.camera.setOrdre("Bloque");
				Robot a1 = new Robot(serveur, "Robot 1", 4, 4, 0, 40);
				Robot a2 = new Robot(serveur, "Robot 2", 3, 2, 0, 40);
				Robot a3 = new Robot(serveur, "Robot 3", 4, 2, 0, 40);
				Robot a4 = new Robot(serveur, "Robot 4", 5, 2, 0, 40);
				Robot a5 = new Robot(serveur, "Robot 5", 5, 3, 0, 40);
				Robot a6 = new Robot(serveur, "Robot 6", 5, 4, 0, 40);
				Robot a7 = new Robot(serveur, "Robot 7", 5, 5, 0, 40);
				Robot a8 = new Robot(serveur, "Robot 8", 4, 5, 0, 40);
				Robot a9 = new Robot(serveur, "Robot 9", 3, 5, 0, 40);
				Robot a10 = new Robot(serveur, "Robot 10", 2, 5, 0, 40);
				Robot a11 = new Robot(serveur, "Robot 11", 2, 4, 0, 40);
				Robot a12 = new Robot(serveur, "Robot 12", 2, 3, 0, 40);
				Robot a13 = new Robot(serveur, "Robot 13", 2, 2, 0, 40);
				serveur.addRobot(a1);
				serveur.addRobot(a2);
				serveur.addRobot(a3);
				serveur.addRobot(a4);
				serveur.addRobot(a5);
				serveur.addRobot(a6);
				serveur.addRobot(a7);
				serveur.addRobot(a8);
				serveur.addRobot(a9);
				serveur.addRobot(a10);
				serveur.addRobot(a11);
				serveur.addRobot(a12);
				serveur.addRobot(a13);
				ArrayList<Point> listP = new ArrayList<Point>();
				listP.add(new Point(a2.getposX(), a2.getposY()));
				listP.add(new Point(a3.getposX(), a3.getposY()));
				listP.add(new Point(a4.getposX(), a4.getposY()));
				listP.add(new Point(a5.getposX(), a5.getposY()));
				listP.add(new Point(a6.getposX(), a6.getposY()));
				listP.add(new Point(a7.getposX(), a7.getposY()));
				listP.add(new Point(a8.getposX(), a8.getposY()));
				listP.add(new Point(a9.getposX(), a9.getposY()));
				listP.add(new Point(a10.getposX(), a10.getposY()));
				listP.add(new Point(a11.getposX(), a11.getposY()));
				listP.add(new Point(a12.getposX(), a12.getposY()));
				listP.add(new Point(a13.getposX(), a13.getposY()));
				this.camera.setListePoint(listP);
				this.camera.resume();
				break;
			}
		} else {
			JComboBox j = (JComboBox) e.getSource();
			switch (j.getSelectedItem().toString()) {
			case "Rectangle":
				this.camera.setOrdre("Rectangle");
				Robot a1 = new Robot(serveur, "Robot 1", 1, 1, 0, 40);
				Robot a2 = new Robot(serveur, "Robot 2", 2, 1, 0, 40);
				Robot a3 = new Robot(serveur, "Robot 3", 3, 1, 0, 40);
				Robot a4 = new Robot(serveur, "Robot 4", 4, 1, 0, 40);
				Robot a5 = new Robot(serveur, "Robot 5", 5, 1, 0, 40);
				Robot a6 = new Robot(serveur, "Robot 6", 6, 1, 0, 40);
				Robot a7 = new Robot(serveur, "Robot 7", 7, 1, 0, 40);
				Robot a8 = new Robot(serveur, "Robot 8", 8, 1, 0, 40);
				Robot a9 = new Robot(serveur, "Robot 9", 9, 1, 0, 40);
				Robot a10 = new Robot(serveur, "Robot 10", 1, 1, 0, 40);
				Robot a11 = new Robot(serveur, "Robot 11", 1, 3, 0, 40);
				Robot a12 = new Robot(serveur, "Robot 12", 5, 5, 0, 40);
				Robot a13 = new Robot(serveur, "Robot 13", 0, 5, 0, 40);
				Robot a14 = new Robot(serveur, "Robot 14", 4, 1, 0, 40);
				Robot a15 = new Robot(serveur, "Robot 15", 6, 4, 0, 40);
				Robot a16 = new Robot(serveur, "Robot 16", 3, 1, 0, 40);
				Robot a17 = new Robot(serveur, "Robot 17", 1, 5, 0, 40);
				Robot a18 = new Robot(serveur, "Robot 18", 2, 1, 0, 40);
				Robot a19 = new Robot(serveur, "Robot 19", 2, 5, 0, 40);
				Robot a20 = new Robot(serveur, "Robot 20", 1, 1, 0, 40);
				Robot a21 = new Robot(serveur, "Robot 21", 1, 3, 0, 40);
				Robot a22 = new Robot(serveur, "Robot 22", 5, 5, 0, 40);
				Robot a23 = new Robot(serveur, "Robot 23", 0, 5, 0, 40);
				Robot a24 = new Robot(serveur, "Robot 24", 4, 1, 0, 40);
				Robot a25 = new Robot(serveur, "Robot 25", 6, 4, 0, 40);
				Robot a26 = new Robot(serveur, "Robot 26", 3, 1, 0, 40);
				Robot a27 = new Robot(serveur, "Robot 27", 1, 5, 0, 40);
				Robot a28 = new Robot(serveur, "Robot 28", 2, 1, 0, 40);
				Robot a29 = new Robot(serveur, "Robot 29", 2, 5, 0, 40);
				Robot a30 = new Robot(serveur, "Robot 30", 1, 1, 0, 40);
				serveur.addRobot(a1);
				serveur.addRobot(a2);
				serveur.addRobot(a3);
				serveur.addRobot(a4);
				serveur.addRobot(a5);
				serveur.addRobot(a6);
				serveur.addRobot(a7);
				serveur.addRobot(a8);
				serveur.addRobot(a9);
				serveur.addRobot(a10);
				this.camera.resume();
				break;
			case "Triangle":
				this.camera.setOrdre("Triangle");
				Robot r1 = new Robot(serveur, "Robot 1", 1, 1, 0, 40);
				Robot r2 = new Robot(serveur, "Robot 2", 5, 5, 0, 40);
				Robot r3 = new Robot(serveur, "Robot 3", 2, 3, 0, 40);
				Robot r4 = new Robot(serveur, "Robot 4", 4, 5, 0, 40);
				Robot r5 = new Robot(serveur, "Robot 5", 2, 5, 0, 40);
				Robot r6 = new Robot(serveur, "Robot 6", 3, 5, 0, 40);
				serveur.addRobot(r1);
				serveur.addRobot(r2);
				serveur.addRobot(r3);
				serveur.addRobot(r4);
				serveur.addRobot(r5);
				serveur.addRobot(r6);
				this.camera.resume();
				break;

			case "Carre":
				this.camera.setOrdre("Carre");
				Robot b1 = new Robot(serveur, "Robot 1", 1, 3, 0, 40);
				Robot b2 = new Robot(serveur, "Robot 2", 5, 5, 0, 40);
				Robot b3 = new Robot(serveur, "Robot 3", 6, 5, 0, 40);
				Robot b4 = new Robot(serveur, "Robot 4", 4, 1, 0, 40);
				Robot b5 = new Robot(serveur, "Robot 5", 6, 4, 0, 40);
				Robot b6 = new Robot(serveur, "Robot 6", 3, 1, 0, 40);
				Robot b7 = new Robot(serveur, "Robot 7", 3, 5, 0, 40);
				Robot b8 = new Robot(serveur, "Robot 8", 2, 1, 0, 40);
				serveur.addRobot(b1);
				serveur.addRobot(b2);
				serveur.addRobot(b3);
				serveur.addRobot(b4);
				serveur.addRobot(b5);
				serveur.addRobot(b6);
				serveur.addRobot(b7);
				serveur.addRobot(b8);
				this.camera.resume();
				break;
			}
		}
	}

}
