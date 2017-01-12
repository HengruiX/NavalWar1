import java.awt.*;

public class CircularLandscape extends Landscape {

	public CircularLandscape(Dimension size) {
		super(size);
	}

	public boolean isAvailable(double x, double y) {
		return Math.pow(x - width / 2, 2) + Math.pow(y - height / 2, 2) <= Math.pow(width / 4, 2);
	}

	public void draw(Graphics graphics) {
		graphics.drawOval(width / 4, height / 2 - width / 4, width / 2, width / 2);
		graphics.setColor(Color.CYAN);
		graphics.fillOval(width / 4, height / 2 - width / 4, width / 2, width / 2);
	}
}
