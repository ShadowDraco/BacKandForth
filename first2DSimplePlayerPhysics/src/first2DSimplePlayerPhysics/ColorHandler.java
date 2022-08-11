package first2DSimplePlayerPhysics;

import java.awt.Color;
import java.util.Random;

public class ColorHandler {
	static Random rand = new Random();
	
	// Top of the ground "grass"
	Color groundColor1 = generateRandomColor("light");
	// Dirt brown
	Color brown1 = new Color(123, 63, 0);
	// Wood brown
	Color brown2 = new Color(164,116,73);
	// World background
	Color bgColor = generateRandomColor("dark");
	
	public ColorHandler() {
		
	}
	
	public Color generateRandomColor() {
		int r, g, b;
		r = rand.nextInt(255);
		g = rand.nextInt(255);
		b = rand.nextInt(255);

		Color color = new Color(r, g, b);

		return color;
	}
	
	public Color generateRandomColor(String modifier) {
		int r, g, b, m = 0;
		r = rand.nextInt(255);
		g = rand.nextInt(255);
		b = rand.nextInt(255);
		
		// If the modifier is dark, take away up to 50 points of color
		if (modifier == "dark") {
			while (m < 50) {
				if (r > 25) {
					r--;
				}
				if (g > 25) {
					g--;
				}
				if (b > 25) {
					b--;
				}
				m++;
			}
		}
		// If the modifier is light, add color
		else if (modifier == "light") {
			while (m < 50) {
				if (r > 255) {
					r++;
				}
				if (g < 255) {
					g++;
				}
				if (b < 255) {
					b++;
				}
				m++;
			}
		}
		
		Color color = new Color(r, g, b);
		return color;
	}
}
