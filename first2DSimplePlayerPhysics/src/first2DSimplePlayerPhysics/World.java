package first2DSimplePlayerPhysics;

import java.util.Random;

public class World {

	Random rand = new Random();

	int numTrees;
	int numPlatforms;
	Tree[] trees;
	Spike[] spikes;
	Platform[] platforms;
	
	int offsetX = 0;
	Player[] players;

	public World(int numTrees, int numPlatforms, int numSpikes) {
		this.trees = new Tree[numTrees];
		this.platforms = new Platform[numPlatforms];
		this.spikes = new Spike[numSpikes];
		createTrees(trees);
		createPlatforms(platforms);
		createSpikes(spikes);
	}

	public void createTrees(Tree[] trees) {
		for (int i = 0; i < trees.length; i++) {
			// trees are spaced based on their number at creation, and a little randomness
			trees[i] = new Tree(-20 + i * 15 * rand.nextInt(20), 300);
		}
	}
	
	public void createPlatforms(Platform[] platforms) {
		for (int i = 0; i < platforms.length; i++) {
			platforms[i] = new Platform(-20 + i * 40 * rand.nextInt(30), rand.nextInt(30) + 240);
		}
	}
	
	public void createSpikes(Spike[] spikes) {
		for (int i = 0; i < platforms.length; i++) {
			spikes[i] = new Spike(250 + i * 30 * rand.nextInt(30), rand.nextInt(10) + 12);
		}
	}

	public void updateWorld(Player[] players, int framesPassed) {
		// Update spikes
		for (int i = 0; i < spikes.length; i++) {
			// If player1 is moving then move everything to the left based on its own move speed
			if (players[0].velx > 0) {
				spikes[i].x += -players[0].velx / spikes[i].moveSpeed;
			}
			// Check positions and check for players on top of platforms regardless of movement
			spikes[i].updatePosition(players);
		}
		// Update platforms
		for (int i = 0; i < platforms.length; i++) {
			// If player1 is moving then move everything to the left based on its own move speed
			if (players[0].velx > 0) {
				platforms[i].x += -players[0].velx / platforms[i].moveSpeed;
			}
			// Check positions and check for players on top of platforms regardless of movement
			platforms[i].updatePosition(players);
		}
		// Update trees
		for (int i = 0; i < trees.length; i++) {
			// If player1 is moving then move everything to the left based on its own move speed
			if (players[0].velx > 0) {
				trees[i].x += -players[0].velx / trees[i].moveSpeed;
				trees[i].updatePosition();
			}
		}
		
		// If the player is moving right add to the world offset
		if (players[0].velx > 0) {
			if (framesPassed > 1) {
				offsetX += 1;
			}
		}
	}
}
