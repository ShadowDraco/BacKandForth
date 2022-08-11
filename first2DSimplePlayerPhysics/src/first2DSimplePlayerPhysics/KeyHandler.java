package first2DSimplePlayerPhysics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	Player[] players;
	String inputKeys;

	public KeyHandler(Player player) {
		this.players = new Player[1];
		this.players[0] = player;
	}

	public KeyHandler(Player player1, Player player2) {
		this.players = new Player[2];
		this.players[0] = player1;
		this.players[1] = player2;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Player 1
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			players[0].jump();
		}
		// Player 2
		if (e.getKeyCode() == KeyEvent.VK_W) {
			players[1].jump();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// Player 1
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			players[0].jump();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			players[0].fallThrough();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			players[0].movingLeft = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			players[0].movingRight = true;
		}

		// Player 2
		if (e.getKeyCode() == KeyEvent.VK_W) {
			players[1].jump();
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			players[1].fallThrough();
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			players[1].movingLeft = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			players[1].movingRight = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Player 1 controls
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			players[0].movingLeft = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			players[0].movingRight = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
				players[0].jump();
		}
		// Player 2 controls
		if (players.length > 1) {

			if (e.getKeyCode() == KeyEvent.VK_A) {
				players[1].movingLeft = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				players[1].movingRight = false;
			}
			// Player 2
			if (e.getKeyCode() == KeyEvent.VK_W) {
				players[1].jump();
			}
		}
				
	}
}
