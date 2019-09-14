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
    boolean done = false;
   
	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	Point(double x, double y, Color couleur) {
		this.x = x;
		this.y = y;
		this.couleur = couleur;
	}
	Point(Point p) {
		this.x = p.x;
		this.y = p.y;
		this.couleur = p.couleur;
	}
	void ajouter(Point p) {
		this.x += p.x;
		this.y += p.y;
	}
	void multiplier(double reel) {
		this.x *= reel;
		this.y *= reel;
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
		
		if (this.done) {
			this.fill(g2d);
		}
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
	void setDone(boolean done) {
		this.done = done;
	}
	void setRayon(int rayon) {
		this.rayon = rayon;
	}
}

