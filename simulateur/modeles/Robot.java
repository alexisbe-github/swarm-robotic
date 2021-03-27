package simulateur.modeles;

import java.awt.Point;
import java.awt.geom.Line2D;

import simulateur.math_calc.ArcDeCercle;

public class Robot {

	private int posX, posY, posX1, posY1;
	private String nom;
	private double angle;
	private Serveur serveur;
	private int rayonRobot;
	private int indice;
	private boolean seDeplace;
	private boolean deplacementCurv;
	private ArcDeCercle arc;
	private double quantifTranslat;
	private Line2D.Float trajectoire;

	public boolean isDeplacementCurv() {
		return deplacementCurv;
	}

	public void setDeplacementCurv(boolean deplacementCurv) {
		this.deplacementCurv = deplacementCurv;
		this.serveur.changerEtatRobot(this);
	}

	public Robot(Serveur s, String n, int x, int y, double a, int rr) {
		this.serveur = s;
		this.posX = x * 100;
		this.posY = y * 100;
		this.nom = n;
		this.angle = a;
		this.rayonRobot = rr;
		this.indice = 0;
		this.seDeplace = false;
		this.posX1 = this.posX;
		this.posY1 = this.posY;
		this.deplacementCurv = false;
		this.setTrajectoire(new Point(0,0), new Point(1,1));
	}
	
	public Line2D getTrajectoire() {
		return this.trajectoire;
	}
	
	public void setTrajectoire(Point A,Point B) {
		this.trajectoire = new Line2D.Float(A.x,A.y,B.x,B.y);
	}

	public String getNom() {
		return this.nom;
	}

	public int getposX() {
		return this.posX1;
	}

	public int getposY() {
		return this.posY1;
	}

	public double getAngle() {
		return this.angle;
	}

	public int getRayonRobot() {
		return this.rayonRobot;
	}

	public int getIndice() {
		return this.indice;
	}

	public void setIndice(int i) {
		if (i < 12) {
			this.indice = i;
		} else {
			this.indice = 0;
		}
	}

	public boolean seDeplace() {
		return this.seDeplace;
	}

	public void setSeDeplace(boolean b) {
		this.seDeplace = b;
	}

	public boolean seDeplacer(double vitesse) {
		this.seDeplace = true;
		int x = (int) this.getDeplacement(vitesse).getX();
		int y = (int) this.getDeplacement(vitesse).getY();
		if (this.serveur.emplacementLibre(this, x, y)) {
			this.posX1 = this.posX;
			this.posY1 = this.posY;
			this.posX = x;
			this.posY = y;
			this.seDeplace = false;
			this.serveur.changerEtatRobot(this);
			return true;
		} else {
			return false;
		}
	}

	public void tourneraGauche(double a) {
		this.angle += a;
		if (this.angle > 180) {
			this.angle -= 360;
		}
		if (this.angle < -180) {
			this.angle += 360;
		}
		this.serveur.changerEtatRobot(this);
	}

	public void tourneraDroite(double a) {
		this.angle -= a;
		if (this.angle > 180) {
			this.angle -= 360;
		}
		if (this.angle < -180) {
			this.angle += 360;
		}
		this.serveur.changerEtatRobot(this);
	}

	public Point getDeplacement(double vitesse) {
		double v = (vitesse / 3.6);
		int x = (int) (Math.cos(Math.toRadians(this.angle)) * 100 * v);
		int y = (int) (-Math.sin(Math.toRadians(this.angle)) * 100 * v);
		int px = this.posX + x;
		int py = this.posY + y;
		this.quantifTranslat = new Point(this.posX, this.posY).distance(new Point(px, py)) * 2;
		return new Point(px, py);
	}

	public ArcDeCercle getArc() {
		return arc;
	}

	public void setArc(ArcDeCercle arc) {
		this.arc = arc;
	}

	public double getQuanti() {
		return this.quantifTranslat;
	}

}
