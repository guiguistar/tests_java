import java.awt.Color;

class Barycentre extends Point {
	int numero;
	boolean traite = false;
	
	Barycentre(double x, double y, Color couleur, int numero) {
		super(x, y, couleur);
		this.numero = numero;
	}
	Barycentre(Point p, int numero) {
		super(p);
		this.numero = numero;
	}
}