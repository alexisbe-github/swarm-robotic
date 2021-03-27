package simulateur;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import simulateur.controllers.ScenarioController;
import simulateur.controllers.SimulateurController;
import simulateur.modeles.Camera;
import simulateur.modeles.Choregraphie;
import simulateur.modeles.ComboBoxModif;
import simulateur.modeles.Serveur;
import simulateur.views.Vue;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Simulateur");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panelBas = new JPanel();
		JPanel panelHaut = new JPanel();
		JPanel haut1 = new JPanel();
		panelBas.setLayout(new FlowLayout());
		panelHaut.setLayout(new GridLayout(1, 2));
		f.add(panelBas, BorderLayout.SOUTH);
		f.add(panelHaut, BorderLayout.NORTH);
		panelHaut.add(haut1);

		// Components panel du haut
		JLabel x = new JLabel("Position x du robot:");
		JLabel y = new JLabel("Position y du robot:");
		ComboBoxModif cbmX = new ComboBoxModif("x");
		ComboBoxModif cbmY = new ComboBoxModif("y");
		for (int i = 1; i < 8; i++) {
			cbmX.insertItemAt(String.valueOf(i), i - 1);
			cbmY.insertItemAt(String.valueOf(i), i - 1);
		}
		cbmX.insertItemAt("8", 7);
		cbmX.insertItemAt("9",8);
		JButton ajoutRobot = new JButton("Ajout robot");
		JButton supprimerRobots = new JButton("Supprimer robots");
		haut1.add(x);
		haut1.add(cbmX);
		haut1.add(y);
		haut1.add(cbmY);
		haut1.add(ajoutRobot);
		haut1.add(supprimerRobots);

		// Boutons
		JLabel text = new JLabel("Scénario:");
		JButton ech = new JButton("Echanger positions");
		JButton bloquer = new JButton("Robot bloqué");
		String[] formes = { "Carre", "Rectangle", "Triangle" };
		JComboBox forme = new JComboBox(formes);
		panelBas.add(text);
		panelBas.add(ech);
		panelBas.add(bloquer);
		panelBas.add(forme);

		// Vue et Modele
		Vue v = new Vue();
		Serveur s = new Serveur();

		s.addObserver(v);

		Camera c = new Camera(s);
		
		
		Choregraphie haut2 = new Choregraphie(s,c);
		panelHaut.add(haut2);
		
		// Controller panel haut
		SimulateurController simc = new SimulateurController(s, c);
		cbmX.addActionListener(simc);
		cbmY.addActionListener(simc);
		ajoutRobot.addActionListener(simc);
		supprimerRobots.addActionListener(simc);
		
		// Controller panel bas
		ScenarioController sc = new ScenarioController(s, c);
		ech.addActionListener(sc);
		bloquer.addActionListener(sc);
		forme.addActionListener(sc);

		v.setPreferredSize(new Dimension(900, 800));
		f.add(v);
		f.pack();
		f.setResizable(false);
		f.setVisible(true);

		c.run();
	}
}
