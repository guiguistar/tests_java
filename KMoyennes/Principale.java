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
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Point {
	double x;
	double y;

	static double muX = 100;
	static double muY = 120;
	
	static double sigmaX = 50;
	static double sigmaY = 50;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	Point(Random rnd) {
		this.x = muX + sigmaX * rnd.nextGaussian();
		this.y = muY + sigmaY * rnd.nextGaussian();
	}

	@Override
	public String toString() {
		return "(" + this.x + ";" + this.y + ")";
	}

	static void setMuX(double muX) { Point.muX = muX; };
	static void setMuY(double muY) { Point.muX = muY; };
	static void setSigmaX(double sigmaX) { Point.sigmaX = sigmaX; };
	static void setSigmaY(double sigmaY) { Point.sigmaY = sigmaY; };
}
class Surface extends JPanel implements ActionListener {

    private final int DELAY = 150;
    private Timer timer;

	ArrayList<Point> liste = new ArrayList<>();
	Random rnd = new Random();;
	
    public Surface() {
        initTimer();
		initPointClass();
		
		initList();
		printList();
    }

	private void initList() {
		for (int i = 0; i < 20; i++) {
			this.liste.add(new Point(this.rnd));
		}
	}

	public void printList() {
		for (Point p : this.liste) {
			System.out.println(p);
		}
	}
	
	private void initPointClass() {
		int w = getWidth();
		int h = getHeight();

		System.out.println("w: " + w + ", h:" + h);
		
		Point.setMuX(w / 2.);
		Point.setMuY(h / 2.);
		Point.setSigmaX(10.);
		Point.setSigmaY(10.);
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

        int w = getWidth();
        int h = getHeight();

        Random r = new Random();

		/*
        for (int i = 0; i < 2000; i++) {
            int x = Math.abs(r.nextInt()) % w;
            int y = Math.abs(r.nextInt()) % h;
            //g2d.drawLine(x, y, x, y);
            g2d.drawOval(x, y, 20, 20);
        }
		*/
		for (Point p : this.liste) {
			g2d.drawOval((int)p.x, (int)p.y, 20, 20);
		}
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
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
                Timer timer = surface.getTimer();
                timer.stop();
            }
        });

        setTitle("Points");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			});
	}
}
