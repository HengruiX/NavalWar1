import java.awt.*;

public abstract class Landscape {
	protected int width;
	protected int height;

	public Landscape(Dimension size) {
		width = (int) size.getWidth();
		height = (int) size.getHeight();
	}

	public abstract boolean isAvailable(double x, double y);

	public abstract void draw(Graphics graphics);

}
