package simulateur.modeles;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import simulateur.math_calc.AnglePoint;

public class Camera extends Thread {

	private Serveur s;
	private ArrayList<Robot> listeRobot;
	private String ordre;
	private ArrayList<Point> listePoint;
	public HashMap<String, AnglePoint> Chore= new HashMap<String, AnglePoint>();

	public Camera(Serveur serveur) {
		this.s = serveur;
		listeRobot = s.getListeRobot();
		this.ordre = "";
	}

	public String getOrdre() {
		return this.ordre;
	}

	public void setOrdre(String str) {
		this.suspend();
		this.s.cleanList();
		this.ordre = str;
	}

	public void setOrdreChore(String str) {
		this.suspend();
		this.ordre=str;
	}
	
	public void resumeThread() {
		this.resume();
	}

	public void setListePoint(ArrayList<Point> listP) {
		this.listePoint = listP;
	}

	public void setHashMap(HashMap<String, AnglePoint> lc) {
		this.Chore=lc;
	}
	
	@Override
	public void run() {
		boolean estArrive = false;
		while (true) {
			listeRobot = s.getListeRobot();
			switch (this.ordre) {
			
			case "Chorégraphie":
					for (HashMap.Entry<String,AnglePoint> entree : this.Chore.entrySet()) {
						Robot tmp = this.s.getRobot(entree.getKey());
						estArrive= this.s.recevoirPositionChore(tmp, new Point(tmp.getposX(),tmp.getposY()), new Point(entree.getValue().x*100,entree.getValue().y*100), entree.getValue().angle);
					}
				break;
				
			case "Echanger":
				for (Robot r : listeRobot) {
					switch (r.getNom()) {
					case "Robot 1":
						estArrive = this.s.recevoirPositionCurviligne(r, new Point(r.getposX(), r.getposY()),
								new Point(500, 600));
						break;
					case "Robot 2":
						estArrive = this.s.recevoirPositionCurviligne(r, new Point(r.getposX(), r.getposY()),
								new Point(100, 100));
						break;
					case "Robot 3":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(1000, 600));
						break;
					case "Robot 4":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(600, 100));
						break;
					}
				}
				break;

			case "Bloque":
				for (Robot r : listeRobot) {
					switch (r.getNom()) {
					case "Robot 1":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()), new Point(50, 50));
						break;
					}
				}
				break;

			case "Rectangle":
				for (Robot r : listeRobot) {
					switch (r.getNom()) {
					case "Robot 1":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(100, 200));
						break;
					case "Robot 2":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(300, 200));
						break;
					case "Robot 3":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(500, 200));
						break;
					case "Robot 4":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(700, 200));
						break;
					case "Robot 5":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(700, 400));
						break;
					case "Robot 6":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(700, 600));
						break;
					case "Robot 7":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(500, 600));
						break;
					case "Robot 8":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(300, 600));
						break;
					case "Robot 9":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(100, 600));
						break;
					case "Robot 10":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(100, 400));
						break;
					}
				}
				break;

			case "Triangle":
				for (Robot r : listeRobot) {
					switch (r.getNom()) {
					case "Robot 1":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(500, 200));
						break;
					case "Robot 2":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(600, 400));
						break;
					case "Robot 3":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(700, 600));
						break;
					case "Robot 4":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(500, 600));
						break;
					case "Robot 5":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(300, 600));
						break;
					case "Robot 6":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(400, 400));
						break;
					}
				}
				break;

			case "Carre":
				for (Robot r : listeRobot) {
					switch (r.getNom()) {
					case "Robot 1":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(200, 200));
						break;
					case "Robot 2":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(400, 200));
						break;
					case "Robot 3":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(600, 200));
						break;
					case "Robot 4":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(600, 400));
						break;
					case "Robot 5":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(600, 600));
						break;
					case "Robot 6":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(400, 600));
						break;
					case "Robot 7":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(200, 600));
						break;
					case "Robot 8":
						estArrive = this.s.recevoirPosition(r, new Point(r.getposX(), r.getposY()),
								new Point(200, 400));
						break;
					}
				}
				break;

			}
			try {
				Thread.sleep(80);
			} catch (Exception e) {
			}
		}
	}
}