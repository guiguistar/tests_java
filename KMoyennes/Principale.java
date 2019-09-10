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

class Point {
	double x;
	double y;

	static double muX = 100;
	static double muY = 120;
	
	static double sigmaX = 100;
	static double sigmaY = 100;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	Point(Random rnd) {
		this.x = Point.muX + Point.sigmaX * rnd.nextGaussian();
		this.y = Point.muY + Point.sigmaY * rnd.nextGaussian();
	}

	@Override
	public String toString() {
		return "(" + this.x + ";" + this.y + ")";
	}

	static void setMuX(double muX) {
		Point.muX = muX;
		System.out.println("Point.muX: " + Point.muX);
	};
	static void setMuY(double muY) {
		Point.muY = muY;
		System.out.println("Point.muY: " + Point.muY);
	};
	static void setSigmaX(double sigmaX) {
		Point.sigmaX = sigmaX;
	};
	static void setSigmaY(double sigmaY) {
		Point.sigmaY = sigmaY;
	};
}
class Surface extends JPanel implements ActionListener, KeyListener {

    private final int DELAY = 150;
    private Timer timer;

	ArrayList<Point> liste = new ArrayList<>();
	Random rnd = new Random();;
	
    public Surface() {
        //initTimer();
		//initPointClass();
		
		initList();
		printList();
    }

	private void initList() {
		this.liste.clear();
		for (int i = 0; i < 200; i++) {
			this.liste.add(new Point(this.rnd));
		}
	}

	public void printList() {
		for (Point p : this.liste) {
			System.out.println(p);
		}
	}
	
	public void initPointClass() {
		int w = this.getWidth();
		int h = this.getHeight();

		System.out.println("w: " + w + ", h:" + h);
		
		Point.setMuX(w / 2.);
		Point.setMuY(h / 2.);
		Point.setSigmaX(200.);
		Point.setSigmaY(200.);
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

		for (Point p : this.liste) {
			g2d.fillOval((int)p.x, (int)p.y, 16, 16);
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
		this.initPointClass();
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
		surface.initPointClass();
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
