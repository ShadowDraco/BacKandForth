package first2DSimplePlayerPhysics;

import java.awt.Color;
import java.util.Random;

public class Platform {

	Random rand = new Random();

	int x;
	int y;

	// The left and right bounds of the platform
	int lx;
	int rx;

	// Center
	int cx;

	int height;
	int width;

	Color color;

	int halfWidth;
	int halfHeight;

	// if the platform is touching the player
	boolean touchingPlayer = false;

	// How quick THIS platform moves in the world (compared to the player)
	int moveSpeed = rand.nextInt(2) + 2;

	public Platform(int x, int y) {
		this.x = x;
		this.y = y;

		lx = x;
		rx = x + width;
		cx = x + halfWidth;

		// Generate variation in the size
		width = rand.nextInt(30) + 30;
		height = rand.nextInt(12) + 7;

		halfWidth = width / 2;
		halfHeight = height / 2;

		// Generate a color that is dark red and gets brighter with the length
		color = new Color(rand.nextInt(50) + 25 + width / 3, rand.nextInt(30), rand.nextInt(30));

	}

	public void playerLeavesPlatform(Player[] players) {
		if (touchingPlayer) {
			for (int i = 0; i < players.length; i++) {
				if (Math.abs(players[i].cx - cx) >= halfWidth + players[i].s / 2) {
					players[i].touchingPlatform = false;
					players[i].grounded = false;
					touchingPlayer = false;
				}
			}
		}
	}

	public void checkForPlayers(Player[] players) {
		// For each player check their position, if they are nearby do something
		for (int i = 0; i < players.length; i++) {

			// if this platform is on the screen
			if (x > -20 && x < 920) {

				// Distance from the center of the player to the center of the platform
				if (Math.abs(players[i].cx - cx) <= halfWidth + players[i].s) {

					// if the player is above this platform and not too far above it
					if (players[i].bottomY < y && players[i].bottomY > y - 10) {

						// The player has reached the top of their jump already, or is falling
						if (players[i].vely > 0) {
							players[i].becomeGrounded(this);
							touchingPlayer = true;
						}
					}
				}
			}
		}
		playerLeavesPlatform(players);
	}

	public void updatePosition(Player[] players) {
		lx = x;
		rx = x + width;
		cx = x + halfWidth;
		checkForPlayers(players);
	}
}
