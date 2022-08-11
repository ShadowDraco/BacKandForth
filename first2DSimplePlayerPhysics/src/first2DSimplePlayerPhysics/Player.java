package first2DSimplePlayerPhysics;

import java.awt.Color;
import java.util.ArrayList;

public class Player {

	// Time until the player's speed increases
	int speedCounter = 0;
	int noJumpTimer = 0;

	int health = 10;
	int score = 0;
	
	int x;
	int y;
	// Velocity
	int velx;
	int vely;
	int maxVel = 3;
	int jumpVel = 6;
	// Center point
	int cx;
	int cy;
	// bottom of the player
	int bottomY;
	// size
	int s;
	// Rotation
	double angle;
	int vela; // angle velocity
	Color c;
	Color c2;
	// If the player jumps
	boolean hasJumped = false;
	boolean reachedApex = false;
	boolean grounded = false;
	boolean touchingPlatform = false;
	boolean touchingSpike = false;

	int floor = 300;
	// if Moving in the x direction
	boolean movingLeft = false;
	boolean movingRight = false;
	boolean isMoving = false;
	int trailType;

	ArrayList<Integer> trailPointsX = new ArrayList<Integer>();
	ArrayList<Integer> trailPointsY = new ArrayList<Integer>();
	ArrayList<Integer> trailPointsVelX = new ArrayList<Integer>();
	ArrayList<Integer> trailPointsVelY = new ArrayList<Integer>();

	// Class constructor
	public Player(int xpos, int ypos, int size) {
		x = xpos;
		y = ypos;
		s = size;
	}

	public void jump() {
		if (!hasJumped) {
			touchingPlatform = false;
			vely -= jumpVel;
			hasJumped = true;
			grounded = false;
		}
	}

	public void removeTrailPoints(int framesPassed) {
		// delay the deletion
		if (framesPassed > 1) {
			noJumpTimer++;
			if (trailPointsX.size() > 10) {
				trailPointsX.remove(0);
				trailPointsY.remove(0);
				trailPointsVelX.remove(0);
				trailPointsVelY.remove(0);
			}
			// If the player is grounded for a moment it will delete the rest of the points
			if (grounded && noJumpTimer > 10) {
				if (trailPointsX.size() > 0) {
					trailPointsX.remove(0);
					trailPointsY.remove(0);
					trailPointsVelX.remove(0);
					trailPointsVelY.remove(0);
				} else {
					// Avoid index out of bounds, if there is only one point left clear the whole
					// thing
					trailPointsX.clear();
					trailPointsY.clear();
					trailPointsVelX.clear();
					trailPointsVelY.clear();
				}
			}
		}
	}

	public void fall() {
		if (hasJumped || !grounded) {
			// Create trail points
			trailPointsX.add(cx);
			trailPointsY.add(cy);
			trailPointsVelX.add(cx + velx * 2);
			trailPointsVelY.add(cy + vely * 2);
			if (vely < jumpVel) {
				vely++;
			}
			if (vely == 0) {
				reachedApex = true;
			}
		}

		// Don't check if it can be grounded until it reaches the top of its jump
		if (reachedApex || !grounded) {
			becomeGrounded();
		}

	}

	public void fallThrough() {

		touchingPlatform = false;
		grounded = false;
	}

	// become grounded to the floor
	public void becomeGrounded() {
		if (!touchingPlatform) {
			if (hasJumped || !grounded) {
				// If the y value (accounting for player size) is greater than floor value
				if (bottomY >= floor) {
					hasJumped = false;
					reachedApex = false;
					grounded = true;
					// Set velocity equal to 0 and then position equal to the floor (accounting for
					// size)
					vely = 0;
					y = floor - s;
				}
			}
		}
	}

	// become grounded to a platform
	public void becomeGrounded(Platform platform) {
		touchingPlatform = true;
		// If the y value (accounting for player size) is greater than platform y value
		if (bottomY <= platform.y) {
			hasJumped = false;
			reachedApex = false;
			grounded = true;
			// Set velocity equal to 0 and then position equal to the platform (accounting
			// for size)
			vely = 0;
			y = platform.y - s;
		}
	}

	public void addVelocity() {
		if (movingLeft && velx > -maxVel) {
			velx -= 1;
			vela -= 1;
		}
		if (movingRight && velx < maxVel) {
			velx += 1;
			vela += 1;
		}
	}

	public void move(int framesPassed) {
		// Delay changes in velocity only
		if (framesPassed > 1) {

			fall();
			addVelocity();
			speedCounter++;
			if (speedCounter > 2000) {
				maxVel++;
				speedCounter = 0;
			}
		}
		x += velx;
		y += vely;
		angle += vela * 2;
	}

	public void update(int framesPassed, int offsetX) {
		if (health > 0) {
			move(framesPassed);
			score = offsetX;
		}
		removeTrailPoints(framesPassed);
		// Update center point
		cx = x + s / 2;
		cy = y + s / 2;
		bottomY = y + s;
		if (movingRight || movingLeft) {
			isMoving = true;
		} else {
			isMoving = false;
		}
	}
}
