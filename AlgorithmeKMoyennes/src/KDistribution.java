import java.util.Random;
import java.util.ArrayList;

public class KDistribution {
	double poidsTotal;
	Random rnd = new Random();

	class Loi {
		Normale2D normale;
		double poids;
		
		Loi(double muX, double muY, double sigmaX, double sigmaY, double poids) {
			this.normale = new Normale2D(muX, muY, sigmaX, sigmaY);
			this.poids = poids;
		}
		
		Point realisation() {
			return this.normale.realisation();
		}
	}
	
	ArrayList<Loi> lois = new ArrayList<>();
	
	void ajouterLoi(double muX, double muY, double sigmaX, double sigmaY, double poids) {
		this.lois.add(new Loi(muX, muY, sigmaX, sigmaY, poids));
		
		this.poidsTotal = 0.;
		for (Loi loi : this.lois) {
			this.poidsTotal += loi.poids;
		}
	}
	
	public Point realisation() {
		double poidsLimite = this.rnd.nextDouble() * this.poidsTotal;
		double poidsCourant = 0;
		int i;
		
		for (i = 0; i < this.lois.size() && poidsCourant < poidsLimite; i++) {
			poidsCourant += this.lois.get(i).poids;
		}
		i--;
		//System.out.println("indice loi: "+i+", poidsCourant: " + poidsCourant);
		
		return this.lois.get(i).realisation();
	}
	void setMusX(double muX) {
		for (Loi loi : this.lois) {
			loi.normale.setMuX(muX);	
		}
	}
	void setMusY(double muY) {
		for (Loi loi : this.lois) {
			loi.normale.setMuY(muY);
		}
	}
	void setSigmasX(double sigmaX) {
		for (Loi loi : this.lois) {
			loi.normale.setSigmaX(sigmaX);
		}
	}
	void setSigmasY(double sigmaY) {
		for (Loi loi : this.lois) {
			loi.normale.setSigmaY(sigmaY);
		}
	}
	void setNormales(double muX, double muY, double sigmaX, double sigmaY) {
		this.setMusX(muX);
		this.setMusY(muY);
		this.setSigmasX(sigmaX);
		this.setSigmasY(sigmaY);
	}
}
