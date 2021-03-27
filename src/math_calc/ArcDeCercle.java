package simulateur.math_calc;
import java.awt.Point;

public class ArcDeCercle {
	
	private double rayon;
	private double angle;
	private double distance;
	private double vitesseAngulaire;
	private Point a,b,centre;
	
	public ArcDeCercle(Point pa,Point pb) {
		this.a=pa;
		this.b=pb;
		if(pb.y != pa.y) {
			double aa,bb,cc;
			aa=1;
			bb=-2*(pa.x);
			cc= Math.pow(pa.x, 2)-Math.pow(pa.distance(pb), 2);
			double delta= Math.pow(bb, 2)-4*aa*cc;
			if(delta>0) {
				double x1,x2;
				x1=(-bb-Math.sqrt(delta))/(2*aa);
				x2=(-bb+Math.sqrt(delta))/(2*aa);
				if(a.x>b.x) {
					this.b=new Point((int) x1,pa.y);
				}else {
					this.b=new Point((int) x2,pa.y);
				}
			}
		}
		this.calculRayon();
		this.calculCentre();
		this.calculAngle();
		this.calculDistance();
	}
	public double getRayon() {
		return rayon;
	}
	public void calculRayon() {
		this.rayon = ((Math.pow((a.distance(b)/200), 2)/0.5)+0.5)/2;
	}
	public Point getCentre() {	
		return this.centre;
	}
	public void calculCentre() {
		if(a.distance(b)>50) {
		Circle ca=new Circle(new Vector2(this.a.x,this.a.y),this.rayon*100);
		Circle cb=new Circle(new Vector2(this.b.x,this.b.y),this.rayon*100);
		CircleCircleIntersection cci=new CircleCircleIntersection(ca,cb);
		Vector2[] tmp = cci.getIntersectionPoints();
		Point resultat = new Point((int)tmp[0].x,(int)tmp[0].y);
		this.centre=resultat;
		}
	}
	public double getAngle() {
		return angle;
	}
	public void calculAngle() {
		Vector OA=new Vector(this.centre.x,this.centre.y,this.a.x,this.a.y);
		Vector OB=new Vector(this.centre.x,this.centre.y,this.b.x,this.b.y);
		this.angle = OA.angle(OB);
	}
	public double getDistance() {
		return distance;
	}
	public void calculDistance() {
		this.distance = (2*Math.PI*this.rayon*(this.angle/360));
	}
	public double getVitesseAngulaire() {
		return vitesseAngulaire;
	}
	public void calculVitesseAngulaire(double vitesseAng) {
		//90° pour 24 à 1 de vitesse (théorique) = 3.75° par appel
		double tmp=distance*100/vitesseAng;
		tmp=30/tmp;
		this.vitesseAngulaire= tmp;
	}

}
