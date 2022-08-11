package first2DSimplePlayerPhysics;

import java.awt.Color;
import java.util.Random;

public class Spike {
	Random rand = new Random();

	int x;
	int y;
	int cx;
	int cy;
	
	Color color;

	int width;
	int height;

	int halfWidth;
	int halfHeight;

	boolean touchingPlayer = false;
	
	// How quick THIS spike moves in the world (compared to the player)
	int moveSpeed = rand.nextInt(2) + 2;

	public Spike(int x, int height) {
		this.x = x;
		this.height = height;
		width = rand.nextInt(15) + 30;
		halfWidth = width/2;
		halfHeight = height/2;
		
		// Generate a color that is darkish blue and gets brighter with the height
		color = new Color(rand.nextInt(50), rand.nextInt(30), rand.nextInt(50) + 50);
	}

	public void playerLeavesSpike(Player[] players) {
		if (touchingPlayer) {
			for (int i = 0; i < players.length; i++) {
				if (Math.abs(players[i].cx - cx) >= halfWidth + players[i].s / 2) {
					players[i].touchingSpike = false;
					touchingPlayer = false;
					players[i].health--;
				}
			}
		}
	}

	public void checkForPlayers(Player[] players) {
		// For each player check their position, if they are nearby do something
		for (int i = 0; i < players.length; i++) {

			// if this Spike is on the screen
			if (x > -20 && x < 920) {

				// Distance from the center of the player to the center of the platform
				if (Math.abs(players[i].cx - cx) <= halfWidth + players[i].s / 2 && Math.abs(players[i].cy - cy) <= halfHeight + players[i].s / 2) {
					players[i].touchingSpike = true;
					touchingPlayer = true;
				}
			}
		}
		playerLeavesSpike(players);
	}
	
	public void updatePosition(Player[] players) {
		cx = x + halfWidth;
		cy = 300 - halfHeight;
		checkForPlayers(players);
	}
}
