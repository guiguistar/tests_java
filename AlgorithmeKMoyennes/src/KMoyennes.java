 
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
	void clearGroupes() {
		for (ArrayList<Point> groupe : this.groupes) {
			groupe.clear();
		}
	}
	
	void initialiserBarycentres() {
		//this.barycentres = this.KDist.KRealisation();
		//KDistribution KD = new KDistribution();
		//KD.ajouterLoi(400,200,5,5,1);
		
		this.barycentres = new ArrayList<>();
		//for (int i = 0; i < this.KDist.lois.size(); i++) {
		for (int i = 0; i < this.K; i++) {
			//Point p = this.KDist.lois.get(i).realisation();
			Point p = this.KDist.realisation();
			
			//Point p = KD.realisation();
			p.setCouleur(new Color(rnd.nextInt(65536)));
			
			Barycentre barycentre = new Barycentre(p, i);
			barycentre.setRayon(30);
			
			this.barycentres.add(barycentre);
		}
	}
	
	void updateBarycentres() {
		for (int i = 0; i < this.K; i++) {
			ArrayList<Point> groupe = this.groupes.get(i);
			
			Barycentre barycentre = Barycentre.barycentre(groupe);
			barycentre.setCouleur(groupe.get(0).couleur);
			barycentre.setNumero(i);
			barycentre.setRayon(30);
			
			this.barycentres.set(i, barycentre);
		}
	}
	
	ArrayList<Point> getBarycentres() {
		return this.barycentres;
	}
	
	void faireGroupes() {
		this.clearGroupes();
		for(Point p : this.liste) {
			Barycentre plusProche = (Barycentre) p.plusProche(this.barycentres);
			p.setCouleur(plusProche.couleur);
			p.setDone(true);
			
			this.groupes.get(plusProche.numero).add(p);
			//System.out.println("Plus proche de " + p + ": " + plusProche + ", num: " + plusProche.numero);
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