package simulateur.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import simulateur.math_calc.AnglePoint;
import simulateur.modeles.Camera;
import simulateur.modeles.Choregraphie;
import simulateur.modeles.Serveur;

public class ChoregraphieController implements ActionListener{

	
	private Serveur serveur;
	private Camera camera;	
	private Choregraphie choregraphie;
	public String choreCourante;
	public int indiceCourant;
	public JTextField nomChore;
	public JTextField x,y,angle;
	public JComboBox box;
	public JComboBox charger;
	
	public ChoregraphieController(Serveur s, Camera c,Choregraphie ch) {
		this.serveur = s;
		this.camera = c;
		this.choregraphie=ch;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			switch(b.getText()) {
			case "Créer chorégraphie":
				this.choregraphie.modeCreer();
				break;
			case "Modifier une chorégraphie":
				this.choregraphie.modeModifier();
				break;
			case "Retour":
				this.choregraphie.debut();
				break;
			case "Nv robot":
				this.indiceCourant= this.choregraphie.listeChore.get(this.choreCourante).size()+1;
				this.choregraphie.modeAjout();
				box.setSelectedIndex(this.indiceCourant-1);
				break;
			case "OK":
				this.indiceCourant=box.getSelectedIndex()+1;
				boolean numeric = true;
				try {
		           Double.parseDouble(angle.getText());
		           Integer.parseInt(x.getText());
		           Integer.parseInt(y.getText());
		        } catch (NumberFormatException exception) {
		            numeric = false;
		        }
				if(numeric) {
				this.choregraphie.listeChore.get(this.choreCourante).put("Robot "+this.indiceCourant,new AnglePoint(Integer.parseInt(x.getText()),Integer.parseInt(y.getText()),Double.parseDouble(angle.getText())));
				}
				break;
			case "Enregistrer":
				this.choregraphie.listeChore.put(this.nomChore.getText(), new HashMap<String,AnglePoint>());
				this.choreCourante=this.nomChore.getText();
				this.indiceCourant=0;
				this.choregraphie.modeAjout();
				break;
			case "Modifier":
				this.indiceCourant=1;
				this.choreCourante=(String) charger.getSelectedItem();
				this.choregraphie.modeModif();
				break;
			case "Lancer une chorégraphie":
				this.choregraphie.modeLancer();
				break;
			case "Lancer":
				HashMap<String,AnglePoint> table = new HashMap<String,AnglePoint>();
				choreCourante=(String) charger.getSelectedItem();
				for (HashMap.Entry<String,HashMap<String,AnglePoint>> entree : this.choregraphie.listeChore.entrySet())
				{
					if(entree.getKey() == choreCourante) {
						table = entree.getValue();
					}
				}
				this.camera.setOrdreChore("Chorégraphie");
				this.camera.setHashMap(table);
				this.camera.resume();
				break;
			}
		}
		if (e.getSource() instanceof JComboBox) {
			this.indiceCourant=box.getSelectedIndex()+1;
			if(choregraphie.listeChore.get(choreCourante).size()>indiceCourant-1) {
				x.setText(String.valueOf(choregraphie.listeChore.get(choreCourante).get("Robot "+indiceCourant).x));
				y.setText(String.valueOf(choregraphie.listeChore.get(choreCourante).get("Robot "+indiceCourant).y));
				angle.setText(String.valueOf(choregraphie.listeChore.get(choreCourante).get("Robot "+indiceCourant).angle));
			}
		}
	}

}
