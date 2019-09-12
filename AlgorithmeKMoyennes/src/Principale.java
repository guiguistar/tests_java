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
//import javax.swing.Timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

class Surface extends JPanel implements ActionListener, KeyListener {
	private final int NOMBRE_POINTS = 200;
    
	//private final int DELAY = 20;
    //private Timer timer;

    private Normale2D normale;
    private KDistribution KDist = new KDistribution();
	
    ArrayList<Point> liste = new ArrayList<>();
	
    private KMoyennes KMoy = new KMoyennes(3, liste);
    
    public Surface() {
    	KDist.ajouterLoi(150, 200, 5, 5, 0.5);
    	KDist.ajouterLoi(250, 200, 5, 5, 0.3);
    	KDist.ajouterLoi(250, 300, 5, 5, 0.2);    	

        //initTimer();
		
		//initList();
		//printList();
    	
    	int w = this.getWidth();
    	int h = this.getHeight();
    	
    	System.out.println("A la construction de la surface, w: "+w+", h: "+h);
    }

	private void initList() {
		int w = this.getWidth();
		int h = this.getHeight();
		
		this.normale = new Normale2D(w/2, h/2, h/8, h/8);
		this.liste.clear();
		for (int i = 0; i < NOMBRE_POINTS; i++) {
			this.liste.add(this.normale.realisation());
		}
	}
	private void initListe() {
		int w = this.getWidth();
		int h = this.getHeight();
		
		//this.KDist.setNormales(w/2, h/2, h/8, h/8);
		
		this.KDist.lois.get(0).normale.setParams(w/4, 5*h/8, h/8, h/12);
		this.KDist.lois.get(1).normale.setParams(w/2, h/3, h/16, h/10);
		this.KDist.lois.get(2).normale.setParams(3*w/4, h/2, h/16, h/16);
		
		this.liste.clear();
		for (int i = 0; i < NOMBRE_POINTS; i++) {
			this.liste.add(this.KDist.realisation());
		}		
	}

	public void printList() {
		for (Point p : this.liste) {
			System.out.println(p);
		}
	}
	/*
    private void initTimer() {
        timer = new Timer(DELAY, this);
        timer.start();
    }
    */
    /*
    public Timer getTimer() {
        //return timer;
    }
     */
    
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
       
		for (Point p : this.liste) {
			p.draw(g2d, Color.black);
		}
		
		/*
		if (this.liste.size() > 0) {
			ArrayList<Point> l = this.KMoy.pointsAleatoires();
			for (Point p : l) {
				p.fill(g2d);
			}
		}
		*/
		if (this.liste.size() > 0) {
			ArrayList<Point> barycentres = this.KDist.KRealisation();
			for (Point p : barycentres) {
				p.fill(g2d);
			}
		}
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		//System.out.println("Repaint, e: " + e);
    	//this.initList(); 	
    	//repaint();
    }

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println(e);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.initListe();
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
			this.printList();
		}
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
