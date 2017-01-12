import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public abstract class Ship {

	protected double x;
	protected double y;
	protected double direction;
	protected double speed;
	protected int torpedoRecharge;
	protected int specialMoveRecharge;
	protected int health;
	protected List<Torpedo> torpedos;

	public Ship(double x, double y) {
		this.x = x;
		this.y = y;
		direction = Math.PI / 4;
		speed = 0;
		torpedoRecharge = 0;
		specialMoveRecharge = 0;
		torpedos = new LinkedList<>();
		health = getMaxHealth();
	}

	public abstract int getLength();

	public abstract int getWidth();

	public abstract double getMaxSpeed();

	public abstract double getMinSpeed();

	public abstract int getMaxHealth();

	public abstract void fireTorpedo();

	public abstract void specialMove();

	public boolean beHit(int damage) {
		health -= damage;
		if (health <= 0)
			return true;
		return false;
	}

	public void draw(Graphics graphics) {

		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.rotate(-direction, x, y);
		graphics2D.setColor(Color.orange);
		graphics2D.fillOval((int) x - getLength() / 2, (int) y - getWidth() / 2, getLength(), getWidth());
		graphics2D.rotate(direction, x, y);
		for (Torpedo torpedo : torpedos) {
			torpedo.draw(graphics);
		}
	}

	public void turn(double dtheta, Landscape landscape) {
		if (landscape.isAvailable(x + ((getLength() / 2) * Math.cos(direction + dtheta)),
				y - (getLength() / 2) * Math.sin(direction + dtheta)))
			direction += dtheta;
	}

	public void advance(int dt, Landscape landscape) {
		if (landscape.isAvailable(x + (speed * dt + getLength() / 2) * Math.cos(direction),
				y - (speed * dt + getLength() / 2) * Math.sin(direction))) {
			x += (speed * dt) * Math.cos(direction);
			y -= (speed * dt) * Math.sin(direction);
		}
		torpedoRecharge = torpedoRecharge - dt < 0 ? 0 : torpedoRecharge - dt;
		specialMoveRecharge = specialMoveRecharge - dt < 0 ? 0 : specialMoveRecharge - dt;
		ListIterator<Torpedo> iterator = torpedos.listIterator();
		while (iterator.hasNext()) {
			if (!iterator.next().advance(dt, landscape))
				iterator.remove();
		}
	}

	public void checkHit(List<Ship> ships) {
		ListIterator<Torpedo> iterator = torpedos.listIterator();
		while (iterator.hasNext()) {
			Torpedo torpedo = iterator.next();
			boolean hit = false;
			for (Ship ship : ships) {
				if (ship != this && ElipseUtil.isWithin(ship.x, ship.y, ship.getLength(), ship.direction,
						ship.getWidth(), torpedo.getX() + torpedo.getLength() / 2 * Math.cos(torpedo.getDirection()),
						torpedo.getY() - torpedo.getLength() / 2 * Math.sin(torpedo.getDirection()))) {
					hit = true;
					System.out.println("hit");
					ship.beHit(torpedo.getDamage());
				}
			}
			if (hit)
				iterator.remove();
		}
	}

	public void speedUp(double dspeed) {
		speed += dspeed;
	}

	public double getSpeed() {
		return speed;
	}

	public int getHealth() {
		return health;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getDirection() {
		return direction;
	}

	public boolean torpedoReady() {
		return torpedoRecharge == 0;
	}

	public boolean specialMoveReady() {
		return specialMoveRecharge == 0;
	}

	public void attachAutoPilot(Landscape landscape, Ship target) {
		AutoPilot autoPilot = new AutoPilot(this, target, landscape);
		autoPilot.start();
	}
}
