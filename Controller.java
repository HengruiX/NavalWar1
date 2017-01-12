import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {

	private Game game;
	private GameBoard displayer;
	private Timer timer;

	public void init(JFrame jFrame) {
		game = new Game(new CircularLandscape(Game.size), new boolean[] { false, true },
				new BattleShip((int) GameBoard.center.getX(), (int) GameBoard.center.getY()),
				new Destroyer((int) GameBoard.center.getX(), (int) GameBoard.center.getY()));
		displayer = new GameBoard(game);
		displayer.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					game.left(1);
					break;
				case KeyEvent.VK_RIGHT:
					game.right(1);
					break;
				case KeyEvent.VK_UP:
					game.up(1);
					break;
				case KeyEvent.VK_DOWN:
					game.down(1);
					break;
				case KeyEvent.VK_M:
					game.fire(1);
					break;
				case KeyEvent.VK_N:
					game.specialMove(1);
					break;
				case KeyEvent.VK_A:
					game.left(0);
					break;
				case KeyEvent.VK_D:
					game.right(0);
					break;
				case KeyEvent.VK_W:
					game.up(0);
					break;
				case KeyEvent.VK_S:
					game.down(0);
					break;
				case KeyEvent.VK_F:
					game.fire(0);
					break;
				case KeyEvent.VK_G:
					game.specialMove(0);
					break;
				}
				displayer.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		timer = new Timer(Game.DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.advance(Game.DELAY);
				displayer.repaint();
			}
		});
		timer.start();
		jFrame.getContentPane().add(displayer);
	}
}
