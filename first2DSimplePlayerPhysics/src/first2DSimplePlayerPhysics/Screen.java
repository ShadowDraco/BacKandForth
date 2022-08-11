package first2DSimplePlayerPhysics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import javax.swing.Timer;

public class Screen extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1976128654269116425L;
	Timer time = new Timer(5, this);
	int framesPassed = 0;
	DrawGraphics graphics;
	Player[] players;
	
	public Screen(Player player) {
		// Create player and graphics objects
		this.players = new Player[1];
		this.players[0] = player;
		graphics = new DrawGraphics(player);
		// Start timer
		time.start();
		
		// add keyListeners
		addKeyListener(new KeyHandler(player));
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 400);
		setLocationRelativeTo(null);
		setFocusable(true);
		setResizable(false);
		
		add(graphics);

	}
	public Screen(Player player1, Player player2) {
		this.players = new Player[2];
		this.players[0] = player1;
		this.players[1] = player2;
		graphics = new DrawGraphics(players[0], players[1]);
		
		time.start();
		
		addKeyListener(new KeyHandler(player1, player2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 400);
		setLocationRelativeTo(null);
		setFocusable(true);
		setResizable(false);
		add(graphics);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Frames passed allows objects to delay certain actions without pausing the whole program
		framesPassed++;
		if (framesPassed == 3) {
			framesPassed = 0;
		}
		graphics.updateSelf(framesPassed);
	}
	
}
