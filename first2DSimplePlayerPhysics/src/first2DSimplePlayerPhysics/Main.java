package first2DSimplePlayerPhysics;


public class Main {

	public static void main(String[] args) {
		
		Player player1 = new Player(100, 200, 25);
		//Player player2 = new Player(300, 200, 25);
		
		Screen screen = new Screen(player1);
		screen.setVisible(true);
		
	}

}
