import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

class Surface extends JPanel implements ActionListener, KeyListener {
	private final int NOMBRE_POINTS = 1000;
    
	private final int DELAY = 100;
    private Timer timer;
    private boolean animated = false;

    private Normale2D normale;
    private KDistribution KDist = new KDistribution();
	
    ArrayList<Point> liste = new ArrayList<>();
	
    private KMoyennes KMoy = new KMoyennes(3, liste, KDist);
    
    public Surface() {
    	KDist.ajouterLoi(150, 200, 1, 1, 0.5);
    	KDist.ajouterLoi(250, 200, 2, 5, 0.3);
    	KDist.ajouterLoi(250, 300, 5, 2, 0.2);    	

        initTimer();
		
		//initList();
		//printList();
    	//this.setSize(640, 480);
    	this.setPreferredSize(new Dimension(640, 480));
        
    	int w = this.getWidth();
    	int h = this.getHeight();
    	
    	System.out.println("A la construction de la surface, w: "+w+", h: "+h);
    	
    	this.KMoy.initialiserBarycentres();
    }

	private void initList() {
		int w = this.getWidth();
		int h = this.getHeight();
		
		this.normale = new Normale2D(w/2, h/2, h/32, h/32);
		this.liste.clear();
		for (int i = 0; i < NOMBRE_POINTS; i++) {
			this.liste.add(this.normale.realisation());
		}
	}
	private void initListe() {
		int w = this.getWidth();
		int h = this.getHeight();
		
		//this.KDist.setNormales(w/2, h/2, h/8, h/8);
		
		this.KDist.lois.get(0).normale.setParams(w/4, 5*h/8, h/20, h/12);
		this.KDist.lois.get(1).normale.setParams(w/2, h/4, h/12, h/20);
		this.KDist.lois.get(2).normale.setParams(3*w/4, 2*h/3, h/22, h/8);
		
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
	
    private void initTimer() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public Timer getTimer() {
        return timer;
    }
    
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
		
		if ( !this.liste.isEmpty() && !(this.KMoy.barycentres == null) ) {
			for (Point p : this.KMoy.barycentres) {
				p.fill(g2d);
				p.draw(g2d,Color.black);
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
    	if ( this.animated && this.liste.size() > 0) {
    		this.KMoy.faireGroupes();
    		this.KMoy.updateBarycentres();
    		repaint();
    	}
    }

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println(e);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.initListe();
			if (this.liste.size() > 0) {
				this.KMoy.initialiserBarycentres();
			}	
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
			this.printList();
		}
		if (e.getKeyCode() == KeyEvent.VK_V) {
			this.KMoy.faireGroupes();
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_B ) {
			this.KMoy.updateBarycentres();
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_A ) {
			this.animated = !this.animated;
		}
		//System.out.println(e);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println(e);
	}

	public boolean isAnimated() {
		return animated;
	}

	public void setAnimated(boolean animated) {
		this.animated = animated;
	}
}

class Controle extends JPanel implements ActionListener {
	Boolean animationState = false;
	GridLayout layout = new GridLayout(1, 2);
	JButton animationButton = new JButton("Enable animation");
	Surface surface;
	Controle(Surface surface) {
		this.surface = surface;
		setLayout(layout);
		
		add(animationButton);
		animationButton.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == animationButton) {
			animationState = !animationState;
			animationButton.setText(animationState ? "Disable animation"  : "Enable animation");
			this.surface.setAnimated(!this.surface.isAnimated());
		}
	}
}

class FramePrincipale extends JFrame {
    public FramePrincipale() {
        initUI();
    }

    private void initUI() {
    	JPanel contentPane = new JPanel();
    	this.setContentPane(contentPane);
    	
    	FlowLayout layout = new FlowLayout();
    	layout.setAlignment(FlowLayout.LEFT);
    	
    	contentPane.setLayout(layout);
    	contentPane.setBackground(Color.ORANGE);
    	
    	final Surface surface = new Surface();
    	//add(surface);
        contentPane.add(surface);

        final Controle controle = new Controle(surface);
        add(controle);
        
        System.out.println("Dans initUI, w: " + surface.getWidth() + ", h: " + surface.getHeight());
        
        addWindowListener(new WindowAdapter() {
			@Override
            public void windowClosing(WindowEvent e) {
                Timer timer = surface.getTimer();
                timer.stop();
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
        //setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        System.out.println("Dans initUI, w: " + this.getWidth() + ", h: " + this.getHeight());
		//A méditer
		surface.addKeyListener(surface);
		surface.setFocusable(true);
		
		pack();
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
