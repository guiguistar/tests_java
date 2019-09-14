 
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class KMoyennes {
	Random rnd = new Random();
	
	int K = 0;
	//int indicePointAleatoire = 0;
	ArrayList<Point> liste;
	ArrayList<Point> barycentres;
	ArrayList<ArrayList<Point>> groupes = new ArrayList<>();
	KDistribution KDist;
	
	KMoyennes(int K, ArrayList<Point> liste, KDistribution distribution) {
		this.K = K;
		this.liste = liste;
		this.KDist = distribution;
		
		this.initialiserGroupes();
	}
	
	void initialiserGroupes() {
		for (int i = 0; i < this.K; i++) {
			groupes.add(new ArrayList<Point>());
		}
	}
	
	void initialiserBarycentres() {
		//this.barycentres = this.KDist.KRealisation();
		
		this.barycentres = new ArrayList<>();
		for (int i = 0; i < this.KDist.lois.size(); i++) {
			//Point p = this.KDist.lois.get(i).realisation();
			Point p = this.KDist.realisation();
			p.setCouleur(new Color(rnd.nextInt(65000)));
			
			Barycentre barycentre = new Barycentre(p, i);
			
			this.barycentres.add(p);
		}
	}
	
	ArrayList<Point> getBarycentres() {
		return this.barycentres;
	}
	
	void faireGroupes() {
		for(Point p : this.liste) {
			Point plusProche = p.plusProche(this.barycentres);
			p.setCouleur(plusProche.couleur);
			p.setDone(true);
			System.out.println("Plus proche de " + p + ": " + plusProche);
		}
	}
	
	/*
	Point pointAleatoire() {
		int indice = this.indicePointAleatoire;
		this.indicePointAleatoire = ( this.indicePointAleatoire + 1 ) % this.liste.size();
		return this.liste.get(indice);
	}
	ArrayList<Point> pointsAleatoires(int n) {
		ArrayList<Point> pointsAleatoires = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			Point p = this.pointAleatoire();
			p.setCouleur(new Color(rnd.nextInt(65000)));
			pointsAleatoires.add(p);
		}
		
		return pointsAleatoires;
	}
	ArrayList<Point> pointsAleatoires() {
		return pointsAleatoires(this.K);
	}
	*/
}