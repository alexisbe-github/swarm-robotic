package simulateur.controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import simulateur.modeles.Camera;
import simulateur.modeles.ComboBoxModif;
import simulateur.modeles.Robot;
import simulateur.modeles.Serveur;

public class SimulateurController implements ActionListener {
	private Serveur serveur;
	private Camera camera;
	private Point pointCourant = new Point(1, 1);

	public SimulateurController(Serveur s, Camera c) {
		this.serveur = s;
		this.camera = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			switch (b.getText()) {
			case "Ajout robot":
				this.serveur.addRobot(new Robot(this.serveur, "Robot " + (this.serveur.getListeRobot().size() + 1),
						this.pointCourant.x, this.pointCourant.y, 0, 40));
				break;
			case "Supprimer robots":
				this.camera.suspend();
				this.camera.setOrdre("");
				this.serveur.cleanList();
				break;
			}
		}

		if (e.getSource() instanceof JComboBox) {
			ComboBoxModif cbm = (ComboBoxModif) e.getSource();
			switch (cbm.getType()) {
			case "x":
				this.pointCourant.x = Integer.parseInt((String) cbm.getSelectedItem());
				break;
			case "y":
				this.pointCourant.y = Integer.parseInt((String) cbm.getSelectedItem());
				break;
			}
		}

	}

}
