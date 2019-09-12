
import java.util.ArrayList;
import java.util.Random;

public class KMoyennes {
	Random rnd = new Random();
	
	int K = 0;
	int indicePointAleatoire = 0;
	ArrayList<Point> liste;
	
	KMoyennes(int K, ArrayList<Point> liste) {
		this.K = K;
		this.liste = liste;
	}
	
	Point pointAleatoire() {
		int indice = this.indicePointAleatoire;
		this.indicePointAleatoire = ( this.indicePointAleatoire + 1 ) % this.liste.size();
		return this.liste.get(indice);
	}
	ArrayList<Point> pointsAleatoires(int n) {
		ArrayList<Point> pointsAleatoires = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			pointsAleatoires.add(this.pointAleatoire());
		}
		
		return pointsAleatoires;
	}
	ArrayList<Point> pointsAleatoires() {
		return pointsAleatoires(this.K);
	}
}