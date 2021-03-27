package simulateur.modeles;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import simulateur.controllers.ChoregraphieController;
import simulateur.math_calc.AnglePoint;

public class Choregraphie extends JPanel {

	private Serveur serveur;
	private Camera camera;
	public HashMap<String, HashMap<String, AnglePoint>> listeChore = new HashMap<String, HashMap<String, AnglePoint>>();
	private ChoregraphieController cc;

	public Choregraphie(Serveur s, Camera c) {
		this.serveur = s;
		this.camera = c;
		JButton b = new JButton("Créer chorégraphie");
		this.add(b);
		JButton b2 = new JButton("Modifier une chorégraphie");
		this.add(b2);
		JButton b3 = new JButton("Lancer une chorégraphie");
		this.add(b3);
		this.cc = new ChoregraphieController(s, c, this);
		b.addActionListener(cc);
		b2.addActionListener(cc);
		b3.addActionListener(cc);
	}

	public void modeCreer() {
		this.removeAll();
		this.creerChore();
		this.validate();
		this.repaint();
	}

	public void modeModifier() {
		this.removeAll();
		this.selectionnerChore();
		this.validate();
		this.repaint();
	}

	public void modeModif() {
		this.removeAll();
		this.modifLigneChore();
		this.validate();
		this.repaint();
	}
	
	public void modeChanger() {
		this.modifLigneChore();
	}

	public void modeAjout() {
		this.removeAll();
		this.ajoutLigneChore();
		this.validate();
		this.repaint();
	}

	public void modeLancer() {
		this.removeAll();
		this.lancerChore();
		this.validate();
		this.repaint();
	}

	public void lancerChore() {
		String[] liste = new String[listeChore.size()];
		int i = 0;
		for (String s : this.listeChore.keySet()) {
			liste[i] = s;
			i++;
		}
		JComboBox jcb = new JComboBox(liste);
		if (listeChore.size() > 0) {
			JButton b2 = new JButton("Lancer");
			b2.addActionListener(cc);
			this.add(b2);
			cc.charger = jcb;
		}
		JButton b = new JButton("Retour");
		b.addActionListener(cc);
		this.add(b);
		this.add(jcb);
	}

	public void selectionnerChore() {
		String[] tabChore = new String[this.listeChore.size()];
		int i = 0;
		for (String s : this.listeChore.keySet()) {
			tabChore[i] = s;
			i++;
		}
		JComboBox jcb = new JComboBox(tabChore);
		this.add(jcb);
		if (listeChore.size() > 0) {
			JButton b2 = new JButton("Modifier");
			b2.addActionListener(cc);
			this.add(b2);
			cc.charger = jcb;
		}
		JButton b = new JButton("Retour");
		b.addActionListener(cc);
		this.add(b);
	}

	public void creerChore() {
		JLabel nom = new JLabel("Nom de la chorégraphie:");
		JTextField jtf = new JTextField();
		jtf.setPreferredSize(new Dimension(150, 20));
		JButton enreg = new JButton("Enregistrer");
		cc.nomChore = jtf;
		enreg.addActionListener(cc);
		this.add(nom);
		this.add(jtf);
		this.add(enreg);
	}

	public void ajoutLigneChore() {
		String[] robots = new String[listeChore.get(cc.choreCourante).size()+1];
		for (int j = 0; j < listeChore.get(cc.choreCourante).size()+1; j++) {
			robots[j] = "Robot " + (j+1);
		}
		JComboBox jcb = new JComboBox(robots);
		JLabel l1 = new JLabel("Arrive(x,y): (");
		JLabel virg = new JLabel(",");
		JLabel l2 = new JLabel(") Angle:");
		JTextField x = new JTextField();
		x.setPreferredSize(new Dimension(20, 20));
		JTextField y = new JTextField();
		y.setPreferredSize(new Dimension(20, 20));
		JTextField angle = new JTextField();
		angle.setPreferredSize(new Dimension(20, 20));
		JButton ok = new JButton("OK");
		JButton aj = new JButton("Nv robot");
		aj.setPreferredSize(new Dimension(100, 26));
		JButton retour = new JButton("Retour");
		ok.addActionListener(cc);
		aj.addActionListener(cc);
		retour.addActionListener(cc);
		cc.x = x;
		cc.y = y;
		cc.angle = angle;
		cc.box = jcb;
		jcb.addActionListener(cc);
		this.add(jcb);
		this.add(l1);
		this.add(x);
		this.add(virg);
		this.add(y);
		this.add(l2);
		this.add(angle);
		this.add(ok);
		this.add(aj);
		this.add(retour);
	}

	public void modifLigneChore() {
		String[] robots = new String[listeChore.get(cc.choreCourante).size()];
		for (int j = 0; j < listeChore.get(cc.choreCourante).size(); j++) {
			robots[j] = "Robot " + (j + 1);
		}
		JComboBox jcb = new JComboBox(robots);
		JLabel l1 = new JLabel("Arrive(x,y): (");
		JLabel virg = new JLabel(",");
		JLabel l2 = new JLabel(") Angle:");
		JTextField x = new JTextField();
		x.setPreferredSize(new Dimension(20, 20));
		JTextField y = new JTextField();
		y.setPreferredSize(new Dimension(20, 20));
		JTextField angle = new JTextField();
		angle.setPreferredSize(new Dimension(20, 20));
		JButton ok = new JButton("OK");
		JButton aj = new JButton("Nv robot");
		aj.setPreferredSize(new Dimension(100, 26));
		JButton retour = new JButton("Retour");
		ok.addActionListener(cc);
		aj.addActionListener(cc);
		retour.addActionListener(cc);
		cc.x = x;
		cc.y = y;
		cc.angle = angle;
		cc.box = jcb;
		jcb.addActionListener(cc);
		if(this.listeChore.get(cc.choreCourante).size()>cc.indiceCourant) {
			cc.x.setText(String.valueOf(this.listeChore.get(cc.choreCourante).get("Robot "+cc.indiceCourant).x));
			cc.y.setText(String.valueOf(this.listeChore.get(cc.choreCourante).get("Robot "+cc.indiceCourant).y));
			cc.angle.setText(String.valueOf(this.listeChore.get(cc.choreCourante).get("Robot "+cc.indiceCourant).angle));
		}
		this.add(jcb);
		this.add(l1);
		this.add(x);
		this.add(virg);
		this.add(y);
		this.add(l2);
		this.add(angle);
		this.add(ok);
		this.add(aj);
		this.add(retour);
	}

	public void debut() {
		this.removeAll();
		JButton b = new JButton("Créer chorégraphie");
		this.add(b);
		JButton b2 = new JButton("Modifier une chorégraphie");
		this.add(b2);
		JButton b3 = new JButton("Lancer une chorégraphie");
		this.add(b3);
		b.addActionListener(cc);
		b2.addActionListener(cc);
		b3.addActionListener(cc);
		this.validate();
		this.repaint();
	}
}
