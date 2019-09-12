import java.util.Random;

class Normale2D {
	Random rnd = new Random();
	double muX, muY, sigmaX, sigmaY;
	
	Normale2D(double muX, double muY, double sigmaX, double sigmaY) {
		this.muX = muX;
		this.muY = muY;
		this.sigmaX = sigmaX;
		this.sigmaY = sigmaY;
	}
	
	public void setMuX(double muX) {
		this.muX = muX;
	};
	public void setMuY(double muY) {
		this.muY = muY;
	};
	public void setSigmaX(double sigmaX) {
		this.sigmaX = sigmaX;
	};
	public void setSigmaY(double sigmaY) {
		this.sigmaY = sigmaY;
	};
	
	public void setParams(double muX, double muY, double sigmaX, double sigmaY) {
		this.setMuX(muX);
		this.setMuY(muY);
		this.setSigmaX(sigmaX);
		this.setSigmaY(sigmaY);
	}

	public Point realisation() {
		double x = this.muX + this.sigmaX * this.rnd.nextGaussian();
		double y = this.muY + this.sigmaY * this.rnd.nextGaussian();
		
		return new Point(x, y);
	}
}