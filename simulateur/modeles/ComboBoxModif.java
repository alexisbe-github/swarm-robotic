package simulateur.modeles;

import javax.swing.JComboBox;

public class ComboBoxModif extends JComboBox {

	private String type;

	public ComboBoxModif(String t) {
		this.type = t;
	}

	public String getType() {
		return this.type;
	}
}
