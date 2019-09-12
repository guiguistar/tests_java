import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

class Point {
	double x;
	double y;

    int rayon = 10;
    
	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	Point plusProche(ArrayList<Point> liste) {
		return new Point();
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
}