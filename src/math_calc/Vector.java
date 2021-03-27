package simulateur.math_calc;


public class Vector {

	public final double x;
	public final double y;

	/**
	 * Création d'un vecteur à partir de ses coordonnées.
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Création d'un vecteur à partir des coordonnées de deux points.
	 */
	public Vector(double xA, double yA, double xB, double yB) {
		this(xB - xA, yB - yA);
	}

	/**
	 * La norme du vecteur this.
	 */
	public double norm() {
		return Math.sqrt(x * x + y * y);
	}

	/**
	 * Le produit scalaire entre this et v.
	 */
	public double scalarProduct(Vector v) {
		return x * v.x + y * v.y;
	}

	/**
	 * L'angle (en radians) entre les vecteurs this et v.
	 */
	public double angle(Vector v) {
		return Math.toDegrees(Math.acos(scalarProduct(v) / (norm() * v.norm())));
	}

	/**
	 * Exemple.
	 *
	 * double aX = 0, aY = 0; // A(0,0) 
	 * double bX = 1, bY = 0; // B(1,0) 
	 * double cX =1, cY = 1; // C(1,1)
	 *
	 * Vector u = new Vector(aX, aY, bX, bY); // AB 
	 * Vector v = new Vector(aX, aY, cX, cY); // AC
	 *
	 * double a = u.angle(v); // BÂC 
	 * System.out.println(Math.toDegrees(a)); // 45°
	 */

}
