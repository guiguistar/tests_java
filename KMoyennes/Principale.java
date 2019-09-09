import java.util.Random;
import java.util.ArrayList;

class Point {
	double x;
	double y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	Point(Random rnd) {
		this.x = rnd.nextGaussian();
		this.y = rnd.nextGaussian();
	}

	@Override
	public String toString() {
		return "(" + this.x + ";" + this.y + ")";
	}
}

class Principale {
	static int NOMBRE_POINTS = 20;

	public static void main(String[] args) {
		System.out.println("Algorithme des K-moyennes.");

		Random rnd = new Random();
		ArrayList<Point> liste = new ArrayList<>();

		for (int i = 0; i < NOMBRE_POINTS; i++) {
			liste.add(new Point(rnd));
		}

		for (Point p : liste) {
			System.out.println(p);
		}
	}
}
