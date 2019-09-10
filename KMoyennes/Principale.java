import java.util.Random;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

	public Point realisation() {
		double x = this.muX + this.sigmaX * this.rnd.nextGaussian();
		double y = this.muY + this.sigmaY * this.rnd.nextGaussian();
		
		return new Point(x, y);
	}
}

class Point {
	double x;
	double y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "(" + this.x + ";" + this.y + ")";
	}
}

class KDistribution {
	
}

class Surface extends JPanel implements ActionListener, KeyListener {

    private final int DELAY = 150;
    private Timer timer;

    private Normale2D normale;
    
	ArrayList<Point> liste = new ArrayList<>();
	Random rnd = new Random();;
	
    public Surface() {
        //initTimer();
		
		//initList();
		//printList();
    }

	private void initList() {
		int w = this.getWidth();
		int h = this.getHeight();
		
		this.normale = new Normale2D(w/2, h/2, h/8, h/8);
		this.liste.clear();
		for (int i = 0; i < 200; i++) {
			this.liste.add(this.normale.realisation());
		}
	}

	public void printList() {
		for (Point p : this.liste) {
			System.out.println(p);
		}
	}
    private void initTimer() {
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public Timer getTimer() {
        return timer;
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.blue);

        int rayon = 10;
        
		for (Point p : this.liste) {
			g2d.fillOval((int)p.x, (int)p.y, rayon, rayon);
		}
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		System.out.println("Repaint, e: " + e);
        repaint();
    }

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println(e);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		this.initList();
        repaint();
		//System.out.println(e);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println(e);
	}
}

class FramePrincipale extends JFrame {
    public FramePrincipale() {
        initUI();
    }

    private void initUI() {
        final Surface surface = new Surface();
        add(surface);

        addWindowListener(new WindowAdapter() {
			@Override
            public void windowClosing(WindowEvent e) {
                //Timer timer = surface.getTimer();
                //timer.stop();
				System.out.println(e);
            }
		});
        addWindowStateListener(new WindowAdapter() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				System.out.println(e);
			}
		});
 
        setTitle("Points");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// A méditer
		surface.addKeyListener(surface);
		surface.setFocusable(true);
    }
}

class Principale {
	static int NOMBRE_POINTS = 20;

	public static void main(String[] args) {
		System.out.println("Algorithme des K-moyennes.");

		EventQueue.invokeLater( new Runnable() {
				@Override
				public void run() {
					FramePrincipale frame = new FramePrincipale();
					frame.setVisible(true);
				}
			} );
	}
}
