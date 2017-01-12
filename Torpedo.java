import java.awt.*;

public class Torpedo {

	private double x;
	private double y;
	private double speed;
	private double direction;
	private int damage;
	private int length;
	private int width;

	public Torpedo(double x, double y, double speed, double direction, int damage, int length, int width) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.direction = direction;
		this.damage = damage;
		this.length = length;
		this.width = width;
	}

	public void draw(Graphics graphics) {

		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.rotate(-direction, x, y);
		graphics2D.setColor(Color.BLACK);
		graphics2D.fillOval((int) x - length / 2, (int) y - width / 2, length, width);
		graphics2D.rotate(direction, x, y);
	}

	public boolean advance(int dt, Landscape landscape) {

		x += (speed * dt) * Math.cos(direction);
		y -= (speed * dt) * Math.sin(direction);
		if (landscape.isAvailable(x + ((speed * dt + length / 2) * Math.cos(direction)),
				y - ((speed * dt + length / 2) * Math.sin(direction)))) {
			return true;
		}
		return false;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getDamage() {
		return damage;
	}

	public int getLength() {
		return length;
	}

	public double getDirection() {
		return direction;
	}
}
