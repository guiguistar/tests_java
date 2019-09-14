import java.awt.Color;
import java.util.ArrayList;

class Barycentre extends Point {
	int numero;
	
	Barycentre(double x, double y, Color couleur, int numero) {
		super(x, y, couleur);
		this.numero = numero;
	}
	Barycentre(Point p, int numero) {
		super(p);
		this.numero = numero;
	}
	Barycentre() {
		super(0,0,Color.black);
		this.numero = 0;
	}
	
	public static Barycentre barycentre(ArrayList<Point> liste) {
		Barycentre barycentre = new Barycentre();
		for (Point p : liste) {
			barycentre.ajouter(p);
		}
		
		barycentre.multiplier(1. / liste.size());
		
		return barycentre;
	}
	
	void setNumero(int numero) {
		this.numero = numero;
	}
}