package first2DSimplePlayerPhysics;

import java.awt.Color;
import java.util.Random;

public class Tree {
	Random rand = new Random();
	
	int x;
	int y;
	// trunk and leaves compared to x
	int tx;
	int ty;
	int lx;
	int ly;
	
	int height;
	int width;
	// leaves size
	int lw;
	int lh;
	Color color;
	
	int halfWidth;
	int halfHeight;
	
	// How quick THIS tree moves in the world (compared to the player)
	int moveSpeed = rand.nextInt(2) + 2;
	
	public Tree(int x, int y) {
		this.x = x;
		this.y = y;
		// Generate variation in the trees
		width = rand.nextInt(8) + 10;
		height = rand.nextInt(80) + 40;
		
		halfWidth = width/2;
		halfHeight = height/2;
		
		// Generate a green color with some variation and always gets darker based on how "far back" the tree is
		color = new Color(rand.nextInt(30), (rand.nextInt(50) + 155 - (3 * moveSpeed)), rand.nextInt(30));
		lw = width * rand.nextInt(5) + 25;
		lh = height - rand.nextInt(5) * 5;
		
		// Set the x and y for the trunk and the leaves
		tx = x-halfWidth;
		ty = y-height;
		
		lx = x-lw/2;
		ly = y-height-lh/2;
		
		
	}
	
	public void updatePosition() {
		tx = x-halfWidth;
		ty = y-height;
		
		lx = x-lw/2;
		ly = y-height-lh/2;
	}
}
