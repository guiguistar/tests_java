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
	}
	
	ArrayList<Loi> lois = new ArrayList<>();
	
	void ajouterLoi(double muX, double muY, double sigmaX, double sigmaY, double poids) {
		this.lois.add(new Loi(muX, muY, sigmaX, sigmaY, poids));
		
		this.poidsTotal = 0.;
		for (Loi loi : this.lois) {
			this.poidsTotal += loi.poids;
		}
	}
	
	public void realisation() {
		double poidsLimite = this.rnd.nextDouble() * this.poidsTotal;
		double poidsCourant = 0;
		int i;
		
		for (i = 0; i < this.lois.size() && poidsCourant < poidsLimite; i++) {
			poidsCourant += this.lois.get(i).poids;
		}
		System.out.println("poidsCourant: " + poidsCourant);
	}
}
