package first2DSimplePlayerPhysics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;

import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawGraphics extends JPanel {

	private static final long serialVersionUID = -3591163524199624752L;
	static Random rand = new Random();
	Player[] players;
	ColorHandler colors = new ColorHandler();

	World world;

	// Class Constructor
	public DrawGraphics(Player player) {
		this.players = new Player[1];
		setBorder(BorderFactory.createLineBorder(Color.black));
		this.players[0] = player;
		players[0].c = colors.generateRandomColor();
		players[0].c2 = colors.generateRandomColor("dark");
		players[0].trailType = rand.nextInt(5);
		this.world = new World(60, 60, 60);
	}

	public DrawGraphics(Player player1, Player player2) {
		this.players = new Player[2];
		setBorder(BorderFactory.createLineBorder(Color.black));
		this.players[0] = player1;
		this.players[1] = player2;
		for (int i = 0; i < players.length; i++) {
			players[i].c = colors.generateRandomColor();
			players[i].c2 = colors.generateRandomColor("dark");
			players[i].trailType = rand.nextInt(5);
		}
		this.world = new World(60, 60, 60);

	}

	public void updateSelf(int framesPassed) {
		for (int i = 0; i < players.length; i++) {
			players[i].update(framesPassed, world.offsetX);
			world.updateWorld(players, framesPassed);
			repaint();
		}
	}

	public void drawPlayerTrails(Graphics2D g2, AffineTransform old) {
		// Standard fuzzy blots trail
		for (int i = 0; i < players.length; i++) {
			if (players[i].trailType < 4) {
				g2.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
				g2.setColor(players[i].c2);
				for (int j = 0; j < players[i].trailPointsX.size(); j++) {
					g2.drawLine(players[i].trailPointsX.get(j), players[i].trailPointsY.get(j),
							players[i].trailPointsX.get(j) + rand.nextInt(-5, 5),
							players[i].trailPointsY.get(j) + rand.nextInt(-5, 5));
				}
			}
			// Connected line
			if (players[i].trailType == 4) {
				g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
				for (int j = 0; j < players[i].trailPointsX.size(); j++) {
					g2.setColor(colors.generateRandomColor("dark"));
					g2.drawLine(players[i].trailPointsX.get(j), players[i].trailPointsY.get(j),
							players[i].trailPointsVelX.get(j), players[i].trailPointsVelY.get(j));
				}
			}
			// fuzzy ovals
			if (players[i].trailType == 5) {
				g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
				g2.setColor(players[i].c2);
				for (int j = 0; j < players[i].trailPointsX.size(); j++) {
					g2.drawOval(players[i].trailPointsX.get(j), players[i].trailPointsY.get(j) + rand.nextInt(-3, 3),
							rand.nextInt(3) + 2, rand.nextInt(3) + 2);
				}
			}
		}
	}

	public void drawPlayers(Graphics2D g2, AffineTransform old) {
		// draw trails first so the player is on top of them
		drawPlayerTrails(g2, old);

		for (int i = 0; i < players.length; i++) {
			// Reset the transform
			g2.setTransform(old);

			g2.setColor(players[i].c);
			g2.fillOval(players[i].x, players[i].y, players[i].s, players[i].s);

			g2.translate(players[i].cx, players[i].cy);
			g2.rotate(Math.toRadians(players[i].angle));
			g2.translate(-players[i].s / 4, -players[i].s / 4);
			g2.setColor(players[i].c2);
			g2.fillRect(0, 0, players[i].s / 2, players[i].s / 2);

			// reset the transform
			g2.setTransform(old);
		}
	}

	public void drawGround(Graphics2D g2, AffineTransform old) {

		g2.setTransform(old);

		g2.setColor(colors.groundColor1);
		g2.fillRect(-1, 300, 900, 20);
		g2.setColor(colors.brown1);
		g2.fillRect(-1, 320, 900, 100);

		g2.setTransform(old);
	}

	public void drawTrees(Graphics2D g2, AffineTransform old) {

		g2.setTransform(old);

		for (int i = 0; i < world.trees.length; i++) {
			g2.setColor(colors.brown2);
			g2.fillRect(world.trees[i].tx, world.trees[i].ty, world.trees[i].width, world.trees[i].height);
			g2.setColor(world.trees[i].color);
			g2.fillRect(world.trees[i].lx, world.trees[i].ly, world.trees[i].lw, world.trees[i].lh);
		}

		g2.setTransform(old);
	}

	public void drawPlatforms(Graphics2D g2, AffineTransform old) {

		g2.setTransform(old);

		for (int i = 0; i < world.platforms.length; i++) {
			g2.setColor(world.platforms[i].color);
			g2.fillRect(world.platforms[i].x, world.platforms[i].y, world.platforms[i].width,
					world.platforms[i].height);

		}

		g2.setTransform(old);
	}

	public void drawSpikes(Graphics2D g2, AffineTransform old) {

		g2.setTransform(old);

		for (int i = 0; i < world.spikes.length; i++) {
			g2.setColor(world.spikes[i].color);
			g2.fillRect(world.spikes[i].x, 300-world.spikes[i].height, world.spikes[i].width, world.spikes[i].height);
		}

		g2.setTransform(old);
	}

	public void showWorldPosition(Graphics2D g2) {
		g2.setFont(new Font("Mona Lisa Solid ITC TT", Font.CENTER_BASELINE, 20));
		g2.setColor(players[0].c2);
		g2.drawString("Distance: " + world.offsetX, 30, 30);
		g2.setColor(colors.groundColor1);
		g2.setFont(new Font("Mona Lisa Solid ITC TT", Font.CENTER_BASELINE, 20));
		g2.drawString("Health: " + players[0].health, 30, 60);
	}
	
	public void showScore(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Microsoft YaHei", Font.CENTER_BASELINE, 48));
		g2.drawString("SCORE: " + players[0].score, 320, 190);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		// Create a graphics 2d object
		Graphics2D g2 = (Graphics2D) g;
		// Sets the transform
		AffineTransform old = g2.getTransform();

		g2.setColor(colors.bgColor);
		g2.fillRect(-1, -1, 900, 900);

		drawTrees(g2, old);
		drawSpikes(g2, old);
		drawPlatforms(g2, old);

		drawPlayers(g2, old);
		// undoes rotations and transforms by setting it to previous
		drawGround(g2, old);

		showWorldPosition(g2);
		if (players[0].health < 1) {
			showScore(g2);
		}

	}

}
