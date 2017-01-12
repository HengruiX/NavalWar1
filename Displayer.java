import javax.swing.*;
import java.awt.*;

public class Displayer extends JPanel {
	private GameBoard gameBoard;
	private StatsBoard statsBoard;

	public Displayer(Game game) {
		gameBoard = new GameBoard(game);
		statsBoard = new StatsBoard(game);
		add(gameBoard);
		add(statsBoard);
	}

	@Override
	public void paint(Graphics g) {
		gameBoard.paint(g);
		statsBoard.paint(g);
	}

	public void refresh() {
		gameBoard.repaint();
		statsBoard.repaint();
		repaint();
	}
}
