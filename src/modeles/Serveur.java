package simulateur.modeles;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;

import simulateur.math_calc.ArcDeCercle;

public class Serveur extends Observable {

	private ArrayList<Robot> listeRobot;
	private ArrayList<Point> listPoint;
	private int indiceRobot;

	public Serveur() {
		this.listeRobot = new ArrayList<Robot>();
		this.listPoint = new ArrayList<Point>();
		this.listPoint.add(new Point(100, 100));
		this.listPoint.add(new Point(250, 100));
		this.listPoint.add(new Point(500, 100));
		this.listPoint.add(new Point(750, 100));
		this.listPoint.add(new Point(900, 100));
		this.listPoint.add(new Point(900, 500));
		this.listPoint.add(new Point(900, 700));
		this.listPoint.add(new Point(900, 900));
		this.listPoint.add(new Point(700, 900));
		this.listPoint.add(new Point(100, 900));
		this.listPoint.add(new Point(100, 600));
		this.listPoint.add(new Point(100, 300));
		this.indiceRobot = 0;
	}

	public ArrayList<Point> getlistPoint() {
		return this.listPoint;
	}

	public void cleanList() {
		this.listeRobot.clear();
		setChanged();
		notifyObservers();
	}

	public Robot getRobot(String nom) {
		for (Robot r : this.listeRobot) {
			if (nom.equals(r.getNom())) {
				return r;
			}
		}
		return null;
	}

	public void addRobot(Robot r) {
		boolean peutajouter = true;
		for (Robot ro : this.listeRobot) {
			if (new Point(r.getposX(), r.getposY()).distance(new Point(ro.getposX(), ro.getposY())) < 60) {
				peutajouter = false;
			}
		}
		if (peutajouter) {
			this.listeRobot.add(r);
			// this.listeRobot.get(indiceRobot).setIndice(indiceRobot);
			this.indiceRobot++;
			setChanged();
			notifyObservers();
		}
	}

	public void removeRobot(Robot r) {
		this.listeRobot.remove(r);
		setChanged();
		notifyObservers();
	}

	public ArrayList<Robot> getListeRobot() {
		return this.listeRobot;
	}

	public void changerEtatRobot(Robot r) {
		String nom = r.getNom();
		for (int i = 0; i < listeRobot.size(); i++) {
			if (listeRobot.get(i).getNom().equals(nom)) {
				listeRobot.set(i, r);
			}
		}
		setChanged();
		notifyObservers();
	}

	public boolean recevoirPositionChore(Robot r, Point coorRobot, Point pointArrivee, double angleFinal) {
		boolean estArrive;
		r.setTrajectoire(coorRobot, pointArrivee);
		estArrive = this.recevoirPositionDroiteChore(r, coorRobot, pointArrivee, angleFinal);
		return estArrive;
	}

	public boolean recevoirPositionDroiteChore(Robot r, Point coorRobot, Point pointArrivee, double angleFinal) {
		if (coorRobot.distance(pointArrivee) > 10) {
			// Recuperation de l'angle que le robot doit prendre pour aller en direction du
			// point d'arrivee
			double angle = -Math.toDegrees(Math.atan2(pointArrivee.y - coorRobot.y, pointArrivee.x - coorRobot.x));
			if (angle < 0) {
				angle += 360;
			}
			double angleRobot = r.getAngle();
			if (angleRobot < 0) {
				angleRobot += 360;
			}

			// On fait tourner le robot en fonction du point d'arrivee
			double diff = Math.abs(angleRobot - angle);
			if (angle > angleRobot) {
				if (angle - angleRobot < 180) {
					r.tourneraGauche(diff);
				} else {
					r.tourneraDroite(diff);
				}
			}
			if (angleRobot > angle) {
				if (angleRobot - angle < 180) {
					r.tourneraDroite(diff);
				} else {
					r.tourneraGauche(diff);
				}
			}

			// on fait deplacer le robot
			boolean deplacement;
			deplacement = r.seDeplacer(0.5);

			// cas de blocage
			int i = 0;
			while (!deplacement && i < 8) {
				r.tourneraDroite(45);
				deplacement = r.seDeplacer(0.5);
				i++;
			}
			r.setSeDeplace(true);
			return false;
		} else {
			double angleRobot = r.getAngle();
			if (angleRobot < 0) {
				angleRobot += 360;
			}
			if (angleFinal < 0) {
				angleFinal += 360;
			}
			double diff = Math.abs(angleRobot - angleFinal);
			if (angleFinal > angleRobot) {
				if (angleFinal - angleRobot < 180) {
					r.tourneraGauche(diff);
				} else {
					r.tourneraDroite(diff);
				}
			}
			if (angleRobot > angleFinal) {
				if (angleRobot - angleFinal < 180) {
					r.tourneraDroite(diff);
				} else {
					r.tourneraGauche(diff);
				}
			}
			return true;
		}
	}

	public boolean recevoirPosition(Robot r, Point coorRobot, Point pointArrivee) {
		boolean estArrive;
		r.setTrajectoire(coorRobot, pointArrivee);
		boolean test = this.verifTrajectoire(r);
		if (test && this.listeRobot.size() < 4) {
			estArrive = this.recevoirPositionCurviligne(r, coorRobot, pointArrivee);
		} else {
			estArrive = this.recevoirPositionDroite(r, coorRobot, pointArrivee);
		}
		return estArrive;
	}

	public boolean recevoirPositionDroite(Robot r, Point coorRobot, Point pointArrivee) {
		if (coorRobot.distance(pointArrivee) > 10) {
			// Recuperation de l'angle que le robot doit prendre pour aller en direction du
			// point d'arrivee
			double angle = -Math.toDegrees(Math.atan2(pointArrivee.y - coorRobot.y, pointArrivee.x - coorRobot.x));
			if (angle < 0) {
				angle += 360;
			}
			double angleRobot = r.getAngle();
			if (angleRobot < 0) {
				angleRobot += 360;
			}

			// On fait tourner le robot en fonction du point d'arrivee
			double diff = Math.abs(angleRobot - angle);
			if (angle > angleRobot) {
				if (angle - angleRobot < 180) {
					r.tourneraGauche(diff);
				} else {
					r.tourneraDroite(diff);
				}
			}
			if (angleRobot > angle) {
				if (angleRobot - angle < 180) {
					r.tourneraDroite(diff);
				} else {
					r.tourneraGauche(diff);
				}
			}

			// on fait deplacer le robot
			boolean deplacement;
			deplacement = r.seDeplacer(0.5);

			// cas de blocage
			int i = 0;
			while (!deplacement && i < 8) {
				r.tourneraDroite(45);
				deplacement = r.seDeplacer(0.5);
				i++;
			}
			r.setSeDeplace(true);
			return false;
		} else {
			return true;
		}
	}

	public boolean recevoirPositionCurviligne(Robot r, Point coorRobot, Point pointArrivee) {
		if (!r.isDeplacementCurv()) {
			ArcDeCercle adc = new ArcDeCercle(coorRobot, pointArrivee);
			r.setArc(adc);
			double angle = -Math.toDegrees(Math.atan2(pointArrivee.y - coorRobot.y, pointArrivee.x - coorRobot.x));
			if (angle < 0) {
				angle += 360;
			}
			double angleRobot = r.getAngle();
			if (angleRobot < 0) {
				angleRobot += 360;
			}

			// On fait tourner le robot en fonction du point d'arrivee
			double diff = Math.abs(angleRobot - angle);
			if (angle > angleRobot) {
				if (angle - angleRobot < 180) {
					r.tourneraGauche(diff);
				} else {
					r.tourneraDroite(diff);
				}
			}
			if (angleRobot > angle) {
				if (angleRobot - angle < 180) {
					r.tourneraDroite(diff);
				} else {
					r.tourneraGauche(diff);
				}
			}
			r.tourneraGauche(30);
			r.getDeplacement(0.5);
			r.getArc().calculVitesseAngulaire(r.getQuanti());
			r.setDeplacementCurv(true);
		}
		if (coorRobot.distance(pointArrivee) > 10) {
			r.setSeDeplace(true);
			r.seDeplacer(0.5);
			r.getArc().calculVitesseAngulaire(r.getQuanti());
			r.tourneraDroite(r.getArc().getVitesseAngulaire());
			return false;
		} else {
			r.setSeDeplace(false);
			return true;
		}
	}

	public boolean verifTrajectoire(Robot robotCourant) {
		boolean res = false;
		for (Robot r : this.listeRobot) {
			Line2D tmp = r.getTrajectoire();
			if (r.getTrajectoire().intersectsLine(tmp)) {
				Point p = calculateInterceptionPoint(r.getTrajectoire().getP1(), r.getTrajectoire().getP2(),
						robotCourant.getTrajectoire().getP1(), robotCourant.getTrajectoire().getP2());
				if (p == null) {
					res = true;
				} else {
					if (Math.abs(p.distance(new Point(r.getposX(), r.getposY()))
							- p.distance(new Point(robotCourant.getposX(), robotCourant.getposY()))) < 50) {
						res = true;
					}
				}
			}
		}
		return res;
	}

	// inutile car l'algorithme d'echange de position en courbe ne fonctionne pas
	// bien avec beaucoup de robots
	public static Point calculateInterceptionPoint(Point2D point2d, Point2D point2d2, Point2D point2d3,
			Point2D point2d4) {

		int sNumerator = (int) (point2d.getY() * point2d2.getX() + point2d3.getX() * point2d2.getY()
				- point2d.getX() * point2d2.getY() - point2d3.getY() * point2d2.getX());
		int sDenominator = (int) (point2d4.getY() * point2d2.getX() - point2d4.getX() * point2d2.getY());

		// parallel ... 0 or infinite points, or one of the vectors is 0|0
		if (sDenominator == 0) {
			return null;
		}

		int s = sNumerator / sDenominator;

		int t;
		if (point2d2.getX() != 0) {
			t = (int) ((point2d3.getX() + s * point2d4.getX() - point2d.getX()) / point2d2.getX());
		} else {
			t = (int) ((point2d3.getY() + s * point2d4.getY() - point2d.getY()) / point2d2.getY());
		}

		Point i1 = new Point((int) (point2d.getX() + t * point2d2.getX()),
				(int) (point2d.getY() + t * point2d2.getY()));

		return i1;

	}

	public boolean emplacementLibre(Robot r, int x, int y) {
		for (Robot robot : listeRobot) {
			if (r.getNom() != robot.getNom()) {
				if ((Math.sqrt(Math.pow(x - robot.getposX(), 2) + Math.pow(y - robot.getposY(), 2))) <= robot
						.getRayonRobot() + r.getRayonRobot()) {
					if (robot.seDeplace()) {
						return false;
					} else {
						boolean deplacement = false;
						int i = 0;
						while (!deplacement && i < 36) {
							robot.tourneraDroite(10);
							deplacement = robot.seDeplacer(0.8);
							i++;
						}
					}
				}
			}
		}
		return true;
	}

}
