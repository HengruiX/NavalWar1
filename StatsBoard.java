import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class StatsBoard extends JPanel {

	private Game game;

	public StatsBoard(Game game) {
		setBackground(Color.WHITE);
		this.game = game;
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setColor(Color.BLACK);
		FontRenderContext context = graphics2D.getFontRenderContext();
		Font font = graphics2D.getFont();
		String text1 = "ship 1";
		String text2 = "ship 2";
		Rectangle2D bound1 = font.getStringBounds(text1, context);
		Rectangle2D bound2 = font.getStringBounds(text2, context);
		graphics2D.drawString(text1, 10, 30 - (int) bound1.getY());
		graphics2D.drawString(text2, 10, (int) bound1.getHeight() + 60 - (int) bound2.getY());
		graphics2D.drawRect(20 + (int) bound1.getWidth(), 30, 100, (int) bound1.getHeight());
		graphics2D.drawRect(20 + (int) bound2.getWidth(), (int) bound1.getHeight() + 60, 100, (int) bound2.getHeight());
		graphics2D.setColor(Color.RED);
		graphics2D.fillRect(20 + (int) bound1.getWidth(), 30, (int) (100 * game.getPercetage(0)),
				(int) bound1.getHeight());
		graphics2D.fillRect(20 + (int) bound2.getWidth(), (int) bound1.getHeight() + 60,
				(int) (100 * game.getPercetage(1)), (int) bound2.getHeight());
	}
}
