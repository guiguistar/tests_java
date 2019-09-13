import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.lang.Math;

class Point {
	double x;
	double y;

    int rayon = 10;
    Color couleur = new Color(0x0);
    
	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	Point(double x, double y, Color couleur) {
		this.x = x;
		this.y = y;
		this.couleur = couleur;
	}
	double distance(Point autre) {
		double dx = this.x - autre.x;
		double dy = this.y - autre.y;
		
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	Point plusProche(ArrayList<Point> liste) {
		double distance = this.distance(liste.get(0));
		int iMin = 0;
		double dMin = distance;
		
		for (int i = 1; i < liste.size(); i++) {
			distance = this.distance(liste.get(i));
			
			if (distance < dMin) {
				dMin = distance;
				iMin = i;
			}
		}
		
		return liste.get(iMin);
	}
	
	@Override
	public String toString() {
		return "(" + this.x + ";" + this.y + ")";
	}
	
	void draw(Graphics g, Color color) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(color);

		g2d.drawOval((int)this.x, (int)this.y, this.rayon, this.rayon);
	}
	void fill(Graphics g, Color color) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(color);

		g2d.fillOval((int)this.x, (int)this.y, this.rayon, this.rayon);
	}
	void draw(Graphics g) {
		this.draw(g, this.couleur);
	}
	void fill(Graphics g) {
		this.fill(g, this.couleur);
	}
	void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
}